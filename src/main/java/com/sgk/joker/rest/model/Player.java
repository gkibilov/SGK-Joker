package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.sgk.joker.rest.model.GameState;

public final class Player {
	
	private GameState state;

	private String name;
	
	private int position;
	
	private Long id;
	
	private List<Card> cards;
	
	boolean bWantsAll = false;	
	private Integer cantCallNumer;
	
	private Integer call;
	private int taken = 0;
	
	private List<Integer> calls = new ArrayList<Integer>();	
	private List<Integer> scores = new ArrayList<Integer>();	
	int totalScore;
	
	public boolean isbWantsAll() {
		return bWantsAll;
	}

	public void setbWantsAll(boolean bWantsAll) {
		this.bWantsAll = bWantsAll;
	}

	public List<Integer> getCalls() {
		return calls;
	}

	public void setCalls(List<Integer> calls) {
		this.calls = calls;
	}

	
	public Player getOpponentCopy(Player p) {
		
		Player o = new Player(p.state, p.name, p.position, null);

		o.call = this.call;
		o.taken = this.taken;
		o.calls = this.calls;
		o.scores = this.scores;
		o.totalScore = this.totalScore;
		o.cantCallNumer = this.cantCallNumer;
		o.bWantsAll = this.bWantsAll;
		
		return o;
	}
	
	public Player(GameState state, String name, int position, Long id) {
		this.state = state;
		this.name = name;
		this.position = position;
		this.id = id;
	}
	
	public Integer getCantCallNumer() {
		return cantCallNumer;
	}

	public void setCantCallNumer(Integer cantCallNumer) {
		this.cantCallNumer = cantCallNumer;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Card> getCards() {
		if (state.getNumCards() == 9 && state.getCurrentPlay().getKozyr() == null)
			return cards.subList(0, 3);
		else	
			return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public void addCard(int i, boolean isFirst) {
		if (isFirst) {
			if (cards == null)
				cards = new ArrayList <Card>();
			else
				cards.clear();
		}
		
		cards.add(new Card(i));
	}

	public Integer getCall() {
		return call;
	}

	public void setCall(Integer call) {
		this.call = call;
	}

	public int getTaken() {
		return taken;
	}

	public void setTaken(int taken) {
		this.taken = taken;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public void removeCard(int cardId) {
		int ri = 0;
		boolean bFound = false;
		for (Card c : cards) {
			if (c.id == cardId) {
				bFound = true;
				break;
			}
			ri++;
		}
		
		if(!bFound) {
			throw new IllegalStateException("Player doesnt have such card!");
		}
		cards.remove(ri);
	}

	public boolean hasSuite(CardSuite suite) {
		for (Card c : cards) {
			if (c.suite == suite) {
				return true;
			}
		}
		return false;
	}

	public boolean hasHigherSuiteCard(Card rCard) {
		for (Card c : cards) {
			if (c.suite == rCard.suite  && c.id > rCard.id) {
				return true;
			}
		}
		return false;
	}

	public void calculateHandResult() {
		int score = 0;
		
		//calculate
		if (call.equals(taken)) {
			if (isbWantsAll()) {
				score = taken *100;
			}
			else {
				score = call == 0 ? 50 : 100 + (taken -1) * 50;
			}
		}
		else {
			if (taken == 0)
				score = -200;
			else
				score = taken *10;
		}
		
		//update results
		totalScore += score;
		calls.add(call);
		scores.add(score);
		
		//reset play 
		taken = 0;
		bWantsAll = false;
		call = null;
		cantCallNumer = null;
	}
		
}