package com.sgk.joker.rest.model;

import java.util.HashMap;

import com.sgk.joker.rest.model.CardSuite;
import com.sgk.joker.rest.model.CardType;

public class Card {
	
	
	public static HashMap <Integer, Card> cardMap = new HashMap<Integer, Card>();
	static{
		//1
		cardMap.put(1, new Card(1, CardSuite.BEZ, CardType.JOKER));
		cardMap.put(2, new Card(2, CardSuite.BEZ, CardType.JOKER));
		cardMap.put(3, new Card(3, CardSuite.DIAMOND, CardType.SIX));
		cardMap.put(4, new Card(4, CardSuite.HART, CardType.SIX));
		//2
		cardMap.put(5, new Card(5, CardSuite.SPADE, CardType.SEVEN));
		cardMap.put(6, new Card(6, CardSuite.CLUB, CardType.SEVEN));
		cardMap.put(7, new Card(7, CardSuite.DIAMOND, CardType.SEVEN));
		cardMap.put(8, new Card(8, CardSuite.HART, CardType.SEVEN));
		//3
		cardMap.put(9, new Card(9, CardSuite.SPADE, CardType.EIGHT));
		cardMap.put(10, new Card(10, CardSuite.CLUB, CardType.EIGHT));
		cardMap.put(11, new Card(11, CardSuite.DIAMOND, CardType.EIGHT));
		cardMap.put(12, new Card(12, CardSuite.HART, CardType.EIGHT));		
		//4
		cardMap.put(13, new Card(13, CardSuite.SPADE, CardType.NINE));
		cardMap.put(14, new Card(14, CardSuite.CLUB, CardType.NINE));
		cardMap.put(15, new Card(15, CardSuite.DIAMOND, CardType.NINE));
		cardMap.put(16, new Card(16, CardSuite.HART, CardType.NINE));
		//5
		cardMap.put(17, new Card(17, CardSuite.SPADE, CardType.TEN));
		cardMap.put(18, new Card(18, CardSuite.CLUB, CardType.TEN));
		cardMap.put(19, new Card(19, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(20, new Card(20, CardSuite.CLUB, CardType.TEN));
		//6
		cardMap.put(21, new Card(21, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(22, new Card(22, CardSuite.HART, CardType.TEN));			
		cardMap.put(23, new Card(23, CardSuite.SPADE, CardType.TEN));
		cardMap.put(24, new Card(24, CardSuite.CLUB, CardType.TEN));
		//7
		cardMap.put(25, new Card(25, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(26, new Card(26, CardSuite.HART, CardType.TEN));			
		cardMap.put(27, new Card(27, CardSuite.SPADE, CardType.TEN));
		cardMap.put(28, new Card(28, CardSuite.CLUB, CardType.TEN));
		//8
		cardMap.put(29, new Card(29, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(30, new Card(30, CardSuite.HART, CardType.TEN));		
		cardMap.put(31, new Card(31, CardSuite.SPADE, CardType.TEN));
		cardMap.put(32, new Card(32, CardSuite.CLUB, CardType.TEN));
		//9
		cardMap.put(33, new Card(33, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(34, new Card(34, CardSuite.HART, CardType.TEN));					
		cardMap.put(35, new Card(35, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(36, new Card(36, CardSuite.HART, CardType.TEN));			
	};
	
	
	int id;
	CardSuite suite;
	CardType type;
	
	public Card (int id, CardSuite suite, CardType type) {
		this.id = id;
		this.suite = suite;
		this.type = type;
	}
	
	public Card (int id) {
		this.id = id;
		this.suite = cardMap.get(id).getSuite();
		this.type = cardMap.get(id).getType();
	}
	
	
	public CardSuite getSuite() {
		return suite;
	}
	public void setSuite(CardSuite suite) {
		this.suite = suite;
	}
	
	public CardType getType() {
		return type;
	}
	public void setType(CardType type) {
		this.type = type;
	}
}
