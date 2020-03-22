package com.sgk.joker.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgk.joker.rest.model.CardSuite;
import com.sgk.joker.rest.model.GameState;
import com.sgk.joker.rest.model.PlayerState;

@RestController
public class PlayerController {
	
	static private GameState state = new GameState();

	@GetMapping("/addPlayer")
	public PlayerState addPlayer(@RequestParam(value = "name") String name) {
		return state.getPlayerState(state.addPlayer(name));
	}
	
	@GetMapping("/startGame")
	public PlayerState startGame(@RequestParam(value = "playerId") long playerId) {
		if(!state.isValidPlayer(playerId)) {
			return null;
		}
		
		synchronized(state) {
			if (!state.isGameOn()) {
				//TODO randomize seats numbers
				state.assignCards();
				state.setGameOn(true);
			}
		}
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/setKozyr")
	public PlayerState setKozyr(@RequestParam(value = "playerId") long playerId, 
						   @RequestParam(value = "cardId") CardSuite suite) {
		state.setKozyr(playerId, suite);
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/getPlayersState")
	public PlayerState getState(@RequestParam(value = "playerId") long playerId) {
		return state.getPlayerState(playerId);
	}	
	
	
	@GetMapping("/call")
	public PlayerState call(@RequestParam(value = "playerId") long playerId, 
						   @RequestParam(value = "wantQty") int wantQty) {

		//TODO: state.call(wantQty);
		
		return state.getPlayerState(playerId);
	}
	

	
	@GetMapping("/action")
	public PlayerState action(@RequestParam(value = "playerId") long playerId, 
						   @RequestParam(value = "cardId") int cardId, 
						   @RequestParam(value = "jokerWantsSute", defaultValue ="0") int jws /*0-8*/) {

		//TODO: state.action();
		
		return state.getPlayerState(playerId);
	}
	
	@GetMapping("/reaction")
	public PlayerState reaction(@RequestParam(value = "playerId") long playerId, 
						   @RequestParam(value = "cardId") int cardId, 
						   @RequestParam(value = "jokerWants", defaultValue ="1") int jw /*0-1*/) {
		
		//TODO: state.react();
		
//		if (state.isLastMove()) {
//			state.assignCards();
//		}

		return state.getPlayerState(playerId);
	}

}
