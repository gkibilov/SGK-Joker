package com.sgk.joker.rest.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sgk.joker.rest.model.GameInfo;
import com.sgk.joker.rest.model.GameState;

@Service
public class GameManager {
	
	static int MAX_GAMES = 10;
	//static private Map<String, GameState> games = new ConcurrentHashMap<String, GameState>();
	static private Cache<String, Object> games = CacheBuilder.newBuilder()
 		    										.maximumSize(10)
 		    										.expireAfterAccess(5, TimeUnit.MINUTES).build();
	
	static private Cache<String, Object> expiredGames = CacheBuilder.newBuilder()
 		    												.maximumSize(10)
 		    												.expireAfterAccess(1, TimeUnit.MINUTES).build();
	
	private static Random random =  new Random();

	public GameState newGame(String name) {
		if (games.size() >= 10)
			throw new IllegalStateException("Max number of games is running, please try again later!");
		
		//generate unique game id
		String gameId = ((Integer)random.nextInt()).toString();
		while (games.getIfPresent(gameId) != null) {
			gameId = ((Integer)random.nextInt()).toString();
		}		
		
		GameState game = new GameState();
		game.setGameId(gameId);
		game.setGameName(name);
		games.put(gameId, game);
		
		return game;
	}

	public GameState getGame(String gameId) {
		
		GameState game = (GameState) games.getIfPresent(gameId);
		
		if (game == null) {
			game = (GameState) expiredGames.getIfPresent(gameId);
		}
		
		if (game == null)
			throw new IllegalStateException("No such game found!");
		
		return game;
	}

	public void expireGame(String gameId) {
		GameState game = (GameState) games.getIfPresent(gameId);
		games.invalidate(gameId);		
		expiredGames.put(gameId, game);		
	}

	public List<GameInfo> getAllGames() {
		
		List<GameInfo> gi = new ArrayList<GameInfo>();
		
		for ( Object gs: games.asMap().values()) {
			gi.add(new GameInfo((GameState) gs));
		}

		return gi;
	}

}
