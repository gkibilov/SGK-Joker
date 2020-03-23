package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sgk.joker.rest.model.Card;
import com.sgk.joker.rest.model.Player;

public class GameState {
	
	private CardSuite kozyr = null;
	
	private int roundNumber = 0;
	
	private int numCards = 0;
	
	private int actingPlayerPosition = 0;
	
	private int currentTurnPosition = 0;

	private Status status = Status.NOT_STARTED;
	
	//private List<Player> players = new ArrayList<Player>();
	private Map<Long, Player> players = new HashMap<Long, Player>();
	
	public int getCurrentTurnPosition() {
		return currentTurnPosition;
	}

	public void setCurrentTurnPosition(int currentTurnPosition) {
		this.currentTurnPosition = currentTurnPosition;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getActingPlayerPosition() {
		return actingPlayerPosition;
	}

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
		if (players.get(playerId).getPosition() != actingPlayerPosition)
			throw new IllegalStateException("Not your turn to call the suite!");
		this.kozyr = kozyr;
	}

	public boolean isGameOn() {
		return !status.equals(Status.NOT_STARTED) || status.equals(Status.GAME_OVER);
	}
	public void setGameOn(boolean gameOn) {
		if (gameOn && !canStartGame())
			throw new IllegalStateException("Need all 4 players to start the game!"); 
		this.status = Status.STARTED;
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
	
	private void advanceCurrentTurnPosition() {
		currentTurnPosition++;
		if(currentTurnPosition == 4)
			currentTurnPosition = 1;
	}
	
	public boolean isPlayersTurn(long id) {
		return players.get(id).getPosition() == currentTurnPosition;
	}

	public void assignCards() {
		
		roundNumber++;
		
		actingPlayerPosition = roundNumber%4 == 0 ? 4 : roundNumber%4;
		currentTurnPosition = actingPlayerPosition;
		
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

	public void call(long playerId, int wantQty) {
		
		if(!isValidPlayer(playerId)) {
			throw new IllegalStateException("Not a valid player id!");
		}
		
		if (status != Status.DEALT) {
			// || Status.CALLS_MADE;
			//TODO: for friendly play allow option to:
			//if this is previous caller and next caller has not made a call yet allow to set again

			throw new IllegalStateException("To make a call app needs ot be in DEALT status, current state is: " + status);
		}
		
		if (this.getKozyr() == null) {
			throw new IllegalStateException("Can not make a call when kozyr is not set!");
		}
		
		if(!this.isPlayersTurn(playerId)) {		
			//TODO: for friendly play allow option to:
			//if this is previous caller and next caller has not made a call yet allow to set again
			
			throw new IllegalStateException("Not this players turn to act!");
		}
		
		players.get(playerId).setCall(wantQty);		
		
		this.advanceCurrentTurnPosition();
		
		if(this.currentTurnPosition == this.actingPlayerPosition) {
			status = Status.CALLS_MADE;
		}
	}

}
