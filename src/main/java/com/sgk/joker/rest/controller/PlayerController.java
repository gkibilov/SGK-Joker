package com.sgk.joker.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgk.joker.rest.model.CardSuite;
import com.sgk.joker.rest.model.GameInfo;
import com.sgk.joker.rest.model.GameState;
import com.sgk.joker.rest.model.JokerAction;
import com.sgk.joker.rest.model.JokerReaction;
import com.sgk.joker.rest.model.PlayerState;
import com.sgk.joker.rest.model.Status;
import com.sgk.joker.rest.service.GameManager;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PlayerController {
	
   @Autowired
   GameManager gameManager;
   
   
	@GetMapping("/setTestNumCards")
	public String setTestNumCards(@RequestParam(value = "gameId") String gameId,
								 @RequestParam(value = "numCards") Integer numCards) {	
		GameState state = gameManager.getGame(gameId);
		return state.setTestNumCards(numCards);	
	}
	
	@GetMapping("/testFastForward")
	public String testFastForward(@RequestParam(value = "gameId") String gameId,
									@RequestParam(value = "roundNumber") Integer roundNumber) {
		GameState state = gameManager.getGame(gameId);
		try {
			state.fastForward(this, roundNumber);
			return "OK";
		}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}	

	@GetMapping("/newGame")
	public String newTable(@RequestParam(value = "name") String name) {		
		return gameManager.newGame(name).getGameId();
	}
	
	@GetMapping("/getAllGames")
	public List<GameInfo> getAllGames() {		
		return gameManager.getAllGames();
	}
	
	@GetMapping("/addPlayer")
	public PlayerState addPlayer(@RequestParam(value = "gameId") String gameId, 
								 @RequestParam(value = "name") String name, 
								 @RequestParam(value = "playerId", required = false) String playerId, //for changing name or position
								 @RequestParam(value = "position", required = false) Integer pos) {
		GameState state = gameManager.getGame(gameId);
		return state.getPlayerState(state.addPlayer(name, playerId, pos));
	}
	
	@GetMapping("/startGame")
	public PlayerState startGame(@RequestParam(value = "gameId") String gameId,
								 @RequestParam(value = "playerId") String playerId) {
		
		GameState state = gameManager.getGame(gameId);
		if(!state.isValidPlayer(playerId)) {
			throw new IllegalStateException("Not a valid player id!");
		}
		
		synchronized(state) {
			if (!state.isGameOn()) {
				//TODO randomize seats numbers
				state.setGameOn(true);
				state.assignCards();
				state.setStatus(Status.DEALT);
			}
		}
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/setKozyr")
	public PlayerState setKozyr(@RequestParam(value = "gameId") String gameId,
			 					@RequestParam(value = "playerId") String playerId, 
								@RequestParam(value = "kozyrSuite") CardSuite kozyrSuite) {
		
		GameState state = gameManager.getGame(gameId);
		
		state.setKozyr(playerId, kozyrSuite);
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/getPlayersState")
	public PlayerState getState(@RequestParam(value = "gameId") String gameId,
			 					@RequestParam(value = "playerId") String playerId) {
		GameState state = gameManager.getGame(gameId);
		return state.getPlayerState(playerId);
	}	
	
	@GetMapping("/sendMessage")
	public PlayerState sendMessage(@RequestParam(value = "gameId") String gameId,
			    				   @RequestParam(value = "playerId") String playerId, 
			    				   @RequestParam(value = "message") String message) {
		GameState state = gameManager.getGame(gameId);
		PlayerState ps = state.getPlayerState(playerId);
		state.addMessage(message);
		return ps;
	}	
	
	@GetMapping("/call")
	public PlayerState call(@RequestParam(value = "gameId") String gameId,
			 			    @RequestParam(value = "playerId") String playerId, 
						    @RequestParam(value = "wantQty") int wantQty) {

		GameState state = gameManager.getGame(gameId);
		
		state.call(playerId, wantQty);
		
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/action")
	public PlayerState action(@RequestParam(value = "gameId") String gameId,
			 				  @RequestParam(value = "playerId") String playerId, 
							  @RequestParam(value = "cardId") int cardId, 
							  @RequestParam(value = "jokerAction", required = false) JokerAction jokerAction /*0-7*/) {

		GameState state = gameManager.getGame(gameId);
		
		if ((cardId == 0 || cardId == 1) && jokerAction == null){
			jokerAction = JokerAction.getWantActionForSuite(state.getCurrentPlay().getKozyr().getSuite());
		}
		
		state.action(playerId, cardId, jokerAction);
		
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/reaction")
	public PlayerState reaction(@RequestParam(value = "gameId") String gameId,
			 					@RequestParam(value = "playerId") String playerId, 
						   		@RequestParam(value = "cardId") int cardId, 
						   		@RequestParam(value = "jokerReaction", required = false) JokerReaction jokerReaction /*0-1*/) {
		
		GameState state = gameManager.getGame(gameId);
		
		if ((cardId == 0 || cardId == 1) && jokerReaction == null){
			jokerReaction = JokerReaction.TAKE;
		}
		
		state.react(playerId, cardId, jokerReaction);
		
		if (state.getStatus() == Status.GAME_OVER) {
			gameManager.expireGame(gameId);
		}

		return state.getPlayerState(playerId);
	}

}
