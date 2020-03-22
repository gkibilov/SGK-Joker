package com.sgk.joker.rest;

import java.util.ArrayList;
import java.util.List;

import com.sgk.joker.rest.model.GameState;

public final class Player {
	
	private GameState state;

	private String name;
	
	private int number;
	
	private long id;
	
	private List<Card> cards;
	
	private int call;
	private int taken;
	
	private List<Integer> calls;
	private List<Integer> scores;
	
	int totalScore;
	
	public Player getOpponentCopy(Player p) {
		
		Player o = new Player(p.state, p.name, p.number, p.id);

		o.call = this.call;
		o.taken = this.taken;
		o.calls = this.calls;
		o.scores = this.scores;
		o.totalScore = this.totalScore;
		
		return o;
	}
	
	public Player(GameState state, String name, int number, long id) {
		this.state = state;
		this.name = name;
		this.number = number;
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Card> getCards() {
		if (state.getNumCards() == 9 && state.getKozyr() == null)
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

	public int getCall() {
		return call;
	}

	public void setCall(int call) {
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
		
}
