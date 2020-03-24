package com.sgk.joker.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgk.joker.rest.model.CardSuite;
import com.sgk.joker.rest.model.GameState;
import com.sgk.joker.rest.model.JokerAction;
import com.sgk.joker.rest.model.JokerReaction;
import com.sgk.joker.rest.model.PlayerState;
import com.sgk.joker.rest.model.Status;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PlayerController {
	
	static private GameState state = new GameState();

	@GetMapping("/newTable")
	public Long newTable(@RequestParam(value = "name") String name) {
		state.reset(name);
		return state.getTableId();
	}
	
	@GetMapping("/addPlayer")
	public PlayerState addPlayer(@RequestParam(value = "name") String name) {
		return state.getPlayerState(state.addPlayer(name));
	}
	
	@GetMapping("/startGame")
	public PlayerState startGame(@RequestParam(value = "playerId") long playerId) {
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
	public PlayerState setKozyr(@RequestParam(value = "playerId") long playerId, 
								@RequestParam(value = "kozyrSuite") CardSuite kozyrSuite) {
		state.setKozyr(playerId, kozyrSuite);
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/getPlayersState")
	public PlayerState getState(@RequestParam(value = "playerId") long playerId) {
		
		return state.getPlayerState(playerId);
	}	
	
	
	@GetMapping("/call")
	public PlayerState call(@RequestParam(value = "playerId") long playerId, 
						   @RequestParam(value = "wantQty") int wantQty) {

		state.call(playerId, wantQty);
		
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/action")
	public PlayerState action(@RequestParam(value = "playerId") long playerId, 
							  @RequestParam(value = "cardId") int cardId, 
							  @RequestParam(value = "jokerAction", required = false) JokerAction jokerAction /*0-7*/) {

		state.action(playerId, cardId, jokerAction);
		
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/reaction")
	public PlayerState reaction(@RequestParam(value = "playerId") long playerId, 
						   		@RequestParam(value = "cardId") int cardId, 
						   		@RequestParam(value = "jokerReaction", required = false) JokerReaction jokerReaction /*0-1*/) {
		
		 state.react(playerId, cardId, jokerReaction);

		return state.getPlayerState(playerId);
	}

}
