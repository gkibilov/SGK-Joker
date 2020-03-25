package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sgk.joker.rest.model.Card;
import com.sgk.joker.rest.model.Player;

public class GameState {
	
	int version = 0;
	
	private String tableId;
	private String tableName;
	
	private int roundNumber = 0;
	
	private int prevRoundNumber = 0;
	private int [] prevAssignment = null;		
	
	private int numCards = 0;
	
	private int actingPlayerPosition = 0;
	
	private int currentTurnPosition = 0;

	private Status status = Status.NOT_STARTED;
	
	//private List<Player> players = new ArrayList<Player>();
	private Map<String, Player> players = new HashMap<String, Player>();
	
	private PlayState currentPlay = new PlayState();
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public PlayState getCurrentPlay() {
		return currentPlay;
	}

	public void setCurrentPlay(PlayState currentPlay) {
		this.currentPlay = currentPlay;
	}

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

	public boolean isValidPlayer(String playerId) {
		return players.containsKey(playerId);
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
	
	private CardSuite getKozyr() {
		return currentPlay == null ? null : currentPlay.getKozyr();
	}
	
	public void setKozyr(String playerId, CardSuite kozyr) {
		if (players.get(playerId).getPosition() != actingPlayerPosition)
			throw new IllegalStateException("Not your turn to call the suite!");
		this.currentPlay.setKozyr(kozyr);
		
		version++;
	}
	
	public void reset(String tableName) {
		roundNumber = 0;
		
		this.status = Status.NOT_STARTED;
		
		//full reset
		if(tableName != null) {
			this.tableId = ((Long)random.nextLong()).toString();
			players.clear();
		}
		else {
			for (Player p : players.values()) {
				p.reset();
			}
		}
	}

	public boolean isGameOn() {
		return !status.equals(Status.NOT_STARTED) && !status.equals(Status.GAME_OVER);
	}
	public void setGameOn(boolean gameOn) {
		if (gameOn && !canStartGame())
			throw new IllegalStateException("Need all 4 players to start the game!"); 
		
		//do not remove players
		reset(null);

		this.status = Status.STARTED;
	}

	public String addPlayer(String name) {
		if (players.size() >= 4)
			throw new IllegalStateException("Can not have more than 4 players, sorry!");
		for (Player p: players.values()) {
			if (p.getName().equalsIgnoreCase(name))
				throw new IllegalStateException("Player with the name '"+ p.getName() +"' already exists, please pick a different name!"); 
		}
				
		String id = ((Long)random.nextLong()).toString();
		
		players.put(id, new Player(this, name, players.size() + 1, id));
		
		version++;
		
		return id;
	}
	
	private void advanceCurrentTurnPosition() {
		currentTurnPosition++;
		if(currentTurnPosition > 4)
			currentTurnPosition = 1;
	}
	
	public boolean isPlayersTurn(String id) {
		return players.get(id).getPosition() == currentTurnPosition;
	}
	
	public void assignCards() {
		assignCards(false, null, null);
	}

	public void assignCards(boolean revert, int c[], Integer rn) {
		
		if(revert) {
			rn = this.prevRoundNumber;
			c = this.prevAssignment;
		}
		
		if(rn == null) {
			prevRoundNumber = roundNumber;
			roundNumber++;
		}
		else
			roundNumber = rn;
		
		currentPlay.reset();
		
		actingPlayerPosition = roundNumber%4 == 0 ? 4 : roundNumber%4;
		currentTurnPosition = actingPlayerPosition;
		
		this.currentPlay.setKozyr(null);
		
		
		if(c == null) {
			c = new int[36];
			for (int i = 0; i<36; i++)
				c[i]=i+1;
			shuffle(c);
		}
		this.prevAssignment = c;
		
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
			this.currentPlay.setKozyr(kk.getSuite());
		}	
		
		version++;
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
	
	public PlayerState getPlayerState(String id) {
		if(!isValidPlayer(id)) {
			throw new IllegalStateException("Not a valid player id!");
		}
		return new PlayerState (players.get(id), this);
	}
	public List<Player> getOpponents(String id) {
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

	public void call(String playerId, int wantQty) {
		
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
		
		validateCall(playerId, wantQty);
		
		players.get(playerId).setCall(wantQty);	
		players.get(playerId).setbWantsAll(wantQty == this.numCards);
			
		setCantCallNumberOnTheLastPlayer();
		
		this.advanceCurrentTurnPosition();
		
		//everyone made calls
		if(this.currentTurnPosition == this.actingPlayerPosition) {
			status = Status.CALLS_MADE;
		}
		
		version++;
	}

	private void setCantCallNumberOnTheLastPlayer() {
		int lastPos = actingPlayerPosition == 1 ? 4 : (actingPlayerPosition + 3) %4;
		int wants = 0;
		Player lp = null;
		for ( Player p : players.values()) {
			if (p.getPosition() == lastPos) 
				lp = p;
			else if (p.getCall() != null)
				wants += p.getCall();
		}
		
		lp.setCantCallNumer(this.numCards - wants);
	}

	private void validateCall(String playerId, int wantQty) {
		if(wantQty < 0 || wantQty > numCards) {
			throw new IllegalStateException("Please call between 0 and " + numCards);
		}
		
		if (players.get(playerId).getCantCallNumer() != null && players.get(playerId).getCantCallNumer().equals(wantQty)) {
			throw new IllegalStateException("Cant call "+wantQty);
		}
	}

	public void action(String playerId, int cardId, JokerAction jokerAction) {
		if(!isValidPlayer(playerId)) {
			throw new IllegalStateException("Not a valid player id!");
		}
		
		if (status != Status.CALLS_MADE && status != Status.PLAY_DONE) {
			throw new IllegalStateException("To make a play app needs ot be in CALLS_MADE or PLAY_DONE status, current state is: " + status);
		}
		
		if(!this.isPlayersTurn(playerId)) {		
			throw new IllegalStateException("Not this players turn to act!");
		}
		
		//ignore bogus joker actions
		if(jokerAction != null && cardId > 1) {		
			jokerAction = null;
		}
		
		players.get(playerId).removeCard(cardId);
		
		currentPlay.addAction(players.get(playerId).getPosition(), cardId, jokerAction);
		
		status = Status.PLAY_STARTED;
		
		this.advanceCurrentTurnPosition();
		
		version++;		
	}

	public void react(String playerId, int cardId, JokerReaction jokerReaction) {
		if(!isValidPlayer(playerId)) {
			throw new IllegalStateException("Not a valid player id!");
		}
		
		if (status != Status.PLAY_STARTED) {
			throw new IllegalStateException("To make a reaction play app needs ot be in PLAY_STARTED status, current state is: " + status);
		}
		
		if(!this.isPlayersTurn(playerId)) {		
			throw new IllegalStateException("Not this players turn to act!");
		}
		
		//ignore bogus joker reaction
		if(jokerReaction != null && cardId > 1) {		
			jokerReaction = null;
		}
		
		if (jokerReaction == null) {
			validateReaction(playerId, cardId);
		}		
		
		players.get(playerId).removeCard(cardId);
		
		currentPlay.addReaction(players.get(playerId).getPosition(), cardId, jokerReaction);
		
		this.advanceCurrentTurnPosition();
		
		//everyone made their play?
		if(this.currentTurnPosition == this.actingPlayerPosition) {
			calculatePlayResult();
			status = Status.PLAY_DONE;
		
			//hand is over?
			if(players.get(playerId).getCards().isEmpty()) {
				calculateHandResult();				
				//game over?
				if(roundNumber == 24) {
					status = Status.GAME_OVER;
				}
				else {
					//deal next hand
					assignCards();
					status =Status.DEALT;
				}
			}
		}
		
		version++;		
	}

	private void validateReaction(String playerId, int cardId) {
	
		Card rCard = Card.cardMap.get(cardId);
		
		CardSuite actingSuite = currentPlay.getActingSuite();
		
		if(actingSuite != rCard.suite) {
			if(players.get(playerId).hasSuite(actingSuite)) {
				throw new IllegalStateException("Please react with the acting suite, acting suite is: " + actingSuite);
			} else if (currentPlay.getKozyr() != CardSuite.BEZ && rCard.suite != currentPlay.getKozyr() && players.get(playerId).hasSuite(currentPlay.getKozyr())) {
				throw new IllegalStateException("Please react with kozyr!");				
			}
		}
		else if (currentPlay.isJokerActionWant() && players.get(playerId).hasHigherSuiteCard(rCard)) {
			throw new IllegalStateException("Please react with the highest card in sute: " + actingSuite);
		}
	}

	private void calculatePlayResult() {
		for (Player p : players.values()) {
			if(p.getPosition() == currentPlay.getWinningAction().getPlayerPosition()) {
				p.setTaken(p.getTaken()+1);
				break;
			}
		}		
	}
	
	private void calculateHandResult() {
		for (Player p : players.values()) {
			p.calculateHandResult();
		}	
	}
	
	protected int getNumOfBonusesThisRound(int n) {
		int numBonuses = 0;
		for (Player p : players.values()) {
			if (Player.isBonusRoundForPlayer(p, n))
				numBonuses++;
		}	
		return numBonuses;
	}

}
