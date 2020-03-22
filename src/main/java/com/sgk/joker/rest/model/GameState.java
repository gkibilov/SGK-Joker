package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sgk.joker.rest.Card;
import com.sgk.joker.rest.Player;

public class GameState {
	
	private boolean isGameOn = false;
	
	private CardSuite kozyr = null;
	
	private int roundNumber = 0;
	
	private int numCards = 0;
	
	private int actingPlayerId = 0;
	
	public int getActingPlayerId() {
		return actingPlayerId;
	}

	//private List<Player> players = new ArrayList<Player>();
	private Map<Long, Player> players = new HashMap<Long, Player>();
	
	public boolean isValidPlayer(long id) {
		return players.containsKey(id);
	}
	
	public int getNumCards() {
		return numCards;
	}
	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	public int getRoundNumber() {
		return roundNumber;
	}
	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	public CardSuite getKozyr() {
		return kozyr;
	}
	public void setKozyr(long playerId, CardSuite kozyr) {
		if (players.get(playerId).getId() != actingPlayerId)
			throw new IllegalStateException("Not your turn to call the suite! Acting player is player #" + actingPlayerId);
		this.kozyr = kozyr;
	}

	public boolean isGameOn() {
		return isGameOn;
	}
	public void setGameOn(boolean gameOn) {
		if (gameOn && !canStartGame())
			throw new IllegalStateException("Need all 4 players to start the game!"); 
		this.isGameOn = gameOn;
	}

	public Long addPlayer(String name) {
		if (players.size() >= 4)
			throw new IllegalStateException("Can not have more than 4 players, sorry!");
		for (Player p: players.values()) {
			if (p.getName().equalsIgnoreCase(name))
				throw new IllegalStateException("Player with the name '"+ p.getName() +"' already exists, please pick a different name!"); 
		}
		
		
		long id = random.nextLong();
		
		players.put(id, new Player(this, name, players.size() + 1, id));
		
		return id;
	}

	public void assignCards() {
		
		roundNumber++;
		
		actingPlayerId = roundNumber%4 == 0 ? 4 : roundNumber%4;
		
		kozyr = null;
		
		int[] c = new int[36];
		for (int i = 0; i<36; i++)
			c[i]=i+1;
		shuffle(c);
		
		if (roundNumber <= 8)
			numCards = roundNumber;
		else if (roundNumber <= 12 || roundNumber >= 21)
			numCards = 9;
		else
			numCards = 21 - roundNumber;
		
		int counter = 0;
		for (int j = 1; j<=numCards; j++) {
			for(Player p : players.values())
				p.addCard(c[counter++], j==1);
		}
		
		if(counter <= 33) {
			Card kk = new Card(counter);
			kozyr = kk.getSuite();
		}	
	}

	private static Random random =  new Random();

	public static void shuffle(int[] array) {
		int count = array.length;
		for (int i = count; i > 1; i--) {
			swap(array, i - 1, random.nextInt(i));
		}
	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public PlayerState getPlayerState(long id) {
		return new PlayerState (players.get(id), this);
	}
	public List<Player> getOpponents(long id) {
		List<Player> opponents = new ArrayList <Player> ();
		
		for (Player p : players.values()) {
			if(p.getId() != id) {
				opponents.add(p.getOpponentCopy(p));
			}
		}
				
		return opponents;
	}
	
	private boolean canStartGame() {
		return players.size() == 4;
	}

}
