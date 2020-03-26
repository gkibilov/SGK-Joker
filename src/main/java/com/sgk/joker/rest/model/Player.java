package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.List;

import com.sgk.joker.rest.model.GameState;

public final class Player implements Comparable<Player>{
	
	private GameState state;

	private String name;
	
	private int position;
	
	private String id;
	
	private List<Card> cards;
	
	boolean bWantsAll = false;	
	private Integer cantCallNumer;
	
	private Integer call;
	private int taken = 0;
	
	private List<Integer> takes = new ArrayList<Integer>();	
	private List<Integer> calls = new ArrayList<Integer>();	
	private List<Integer> scores = new ArrayList<Integer>();	
	private List<Integer> bonusMultipliers = new ArrayList<Integer>();	

	int totalScore = 0;
	int totalScoreWithBonuses = 0;
	
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
	
	public List<Integer> getBonusMultipliers() {
		return bonusMultipliers;
	}

	public void setBonusMultipliers(List<Integer> bonusMultipliers) {
		this.bonusMultipliers = bonusMultipliers;
	}
	
	public void reset() {
		this.call = null;
		this.taken = 0;
		this.calls.clear();
		this.takes.clear();
		this.scores.clear();
		this.totalScore = 0;
		this.totalScoreWithBonuses = 0;
		this.cantCallNumer = null;
		this.bWantsAll = false;
		this.bonusMultipliers.clear();
	}

	
	public Player getOpponentCopy(Player p) {
		
		Player o = new Player(p.state, p.name, p.position, null);

		o.id = this.id;
		o.call = this.call;
		o.taken = this.taken;
		o.calls = this.calls;
		o.takes = this.takes;
		o.scores = this.scores;
		o.totalScore = this.totalScore;
		o.totalScoreWithBonuses = this.totalScoreWithBonuses;
		o.cantCallNumer = this.cantCallNumer;
		o.bWantsAll = this.bWantsAll;
		o.bonusMultipliers = this.bonusMultipliers;
		if(p.getCards() != null)
			o.cards = generateDummyCards(p.getCards().size());
		
		return o;
	}
	
	private List<Card> generateDummyCards(int size) {
		List<Card> curds = new ArrayList<Card>();
		int dg = 100;
		do {
			curds.add(new Card(dg--));
		} while(size-- > 0);
		return curds;
	}

	public Player(GameState state, String name, int position, String id) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		calls.add(call);
		takes.add(taken);
		scores.add(score);
		
		//calculate bonuses
		if (calls.size() == 4 || calls.size() == 20) {
			totalScore = totalScoreWithBonuses + calculateBonuses(8);
			totalScoreWithBonuses = totalScore;
		}
		else if (calls.size() == 12 || calls.size() == 24) {
			totalScore = totalScoreWithBonuses + calculateBonuses(4);
			totalScoreWithBonuses = totalScore;
		}
		else
			totalScore += score;
		
		//reset play 
		taken = 0;
		bWantsAll = false;
		call = null;
		cantCallNumer = null;
	}
	
	protected static boolean isBonusRoundForPlayer(Player p, int n) {		
		for (int i = p.calls.size()-1; i >= p.calls.size()-n; i--) {
			if(p.calls.get(i) != p.takes.get(i))
				return false;
		}
		return true;
	}

	private int calculateBonuses(int n) {
		int score = 0;
		//populate
		bonusMultipliers.add(1);
		
		int bonusMultiplier = 1;
		//bonus
		if (Player.isBonusRoundForPlayer(this, n)) 
			bonusMultiplier = 2;
		//remove highest score 
		else if (state.getNumOfBonusesThisRound(n) == 1)
			bonusMultiplier = 0;
		
		//fill up bonusMultipliers with 1s, count -200s, find highest score index for bonus, 
		int khishtcounter = 0;
		int highScoreIndex = -1;
		int highestMadeScore = 100;//only consider made hands
		for (int i = scores.size()-1; i >= scores.size()-n; i--) {
			bonusMultipliers.add(1);
			if (scores.get(i) == 0) 
				khishtcounter++;
			if (i == scores.size()-1)
				continue;
			if(scores.get(i) >= highestMadeScore) {
				highScoreIndex = i;
				highestMadeScore = scores.get(i);
			}
		}
		
		//set bonusMultiplier
		if(highScoreIndex >=0)
			bonusMultipliers.set(highScoreIndex, bonusMultiplier);
		
		//apply bonusMultiplier
		for (int i = scores.size()-1; i > scores.size()-n; i--) {
			score += scores.get(i)*bonusMultipliers.get(i);
		}
		
		return score - khishtcounter*200;
	}



	@Override
	public int compareTo(Player o) {
		return this.id.compareTo(o.id);
	}
		
}
