package com.sgk.joker.rest.model;

import java.util.ArrayList;
import java.util.List;

public class PlayState {
	
	private CardSuite kozyr = null;
	
	private List<Action> actions = new ArrayList<Action>();
	
	Action winningAction = null;
	
	public CardSuite getKozyr() {
		return kozyr;
	}

	public void setKozyr(CardSuite kozyr) {
		this.kozyr = kozyr;
	}

	public Action getWinningAction() {
		return winningAction;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public void reset() {
		actions.clear();
	}

	public void addAction(int position, int cardId, JokerAction jokerAction) {
		actions.add(new Action(position, cardId, jokerAction));
		winningAction = actions.get(0);
	}
	
	public void addReaction(int position, int cardId, JokerReaction jokerReaction) {
		actions.add(new Action(position, cardId, jokerReaction));
		calculateWinningAction();
	}

	public CardSuite getActingSuite() {
		return actions.get(0).getActingSuite();
	}
	
	public boolean isJokerActionWant() {
		 return actions.get(0).isJokerActionWant();
	}
	
	private void calculateWinningAction() {
		
		Action lastAction = actions.get(actions.size()-1);
		
		//joker wins
		if (lastAction.getJokerReaction() != null) {
			if (lastAction.getJokerReaction() == JokerReaction.WANT)
				winningAction = lastAction;
			else return;//nije
		}
		//everything else looses to wanting joker
		else if (winningAction.getJokerReaction() != null && winningAction.getJokerReaction() == JokerReaction.WANT) {
			return;
		}
		//not joker 
		else {
			
			//same suite
			if (lastAction.getSuite() == winningAction.getSuite()) {
				if (lastAction.getCardId() > winningAction.getCardId()) 
					winningAction = lastAction;
				return; 	
			}
			//not same suite
			else {
				//winning action is Joker wants
				if (winningAction.isJokerActionWant()) {
					if (lastAction.getSuite() == kozyr && getActingSuite() != kozyr) 
						winningAction = lastAction;
					else return; 				
				}			
				//winning action is Joker take
				else if (winningAction.isJokerActionTake()) {
					if (lastAction.getSuite() == kozyr || lastAction.getSuite() == getActingSuite()) 
						winningAction = lastAction;
					else return; 
				}
				//winning action is a kozyr
				else if (lastAction.getSuite() == kozyr) {
						winningAction = lastAction;
				}
			}//end not same suite
			
		}//end not joker 
	}
}
