
Request Examples:

http://localhost:8080/newTable?name=MyTable1 - removes players from the table so you can re-seat them, returns room id, not used yet

http://localhost:8080/addPlayer?name=P1 - need to add 4 players before startGame succeeds

http://localhost:8080/startGame?playerId=-2754113531719643820   -  can be called by any and all players, will start only once, subsequent calls will act as getPlayersState

http://localhost:8080/getPlayersState?playerId=5342122246140593088  -  can be called any time and as many times as needed

http://localhost:8080/setKozyr?playerId=5342122246140593088&kozyrSuite=BEZ

http://localhost:8080/call?playerId=-4212449938605158310&wantQty=1

http://localhost:8080/action?playerId=5342122246140593088&cardId=1&jokerAction=WANT_HART

http://localhost:8080/reaction?playerId=9056376217752554794&cardId=1&jokerReaction=TAKE

Response Examples:

After game start:
{"state":{"version":5,"roundNumber":1,"numCards":1,"actingPlayerPosition":1,"currentTurnPosition":1,"status":"DEALT","currentPlay":{"kozyr":"HART","actions":[],"winningAction":null,"actingSuite":null,"jokerActionWant":false,"jokerActionTake":false},"gameOn":true},"player":{"name":"P4","position":4,"id":8755545805337593630,"cards":[{"id":32,"suite":"HART","type":"KING"}],"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[],"scores":[],"totalScore":0},"opponents":[{"name":"P2","position":2,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[],"scores":[],"totalScore":0},{"name":"P3","position":3,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[],"scores":[],"totalScore":0},{"name":"P1","position":1,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[],"scores":[],"totalScore":0}]}

After 3rd  player called/"daidzakhes":
{"state":{"version":8,"roundNumber":1,"numCards":1,"actingPlayerPosition":1,"currentTurnPosition":4,"status":"DEALT","currentPlay":{"kozyr":"HART","actions":[],"winningAction":null,"actingSuite":null,"jokerActionWant":false,"jokerActionTake":false},"gameOn":true},"player":{"name":"P3","position":3,"id":-6957350532758079508,"cards":[{"id":30,"suite":"CLUB","type":"KING"}],"bWantsAll":false,"cantCallNumer":null,"call":0,"taken":0,"calls":[],"scores":[],"totalScore":0},"opponents":[{"name":"P2","position":2,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":0,"taken":0,"calls":[],"scores":[],"totalScore":0},{"name":"P4","position":4,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":0,"call":null,"taken":0,"calls":[],"scores":[],"totalScore":0},{"name":"P1","position":1,"id":null,"cards":null,"bWantsAll":true,"cantCallNumer":null,"call":1,"taken":0,"calls":[],"scores":[],"totalScore":0}]}

After 3 players acted/"iares", this is state from 4th players view:
{
   "state":{
      "version":12,
      "roundNumber":1,
      "numCards":1,
      "actingPlayerPosition":1,
      "currentTurnPosition":4,
      "status":"PLAY_STARTED",
      "currentPlay":{
         "kozyr":"HART",
         "actions":[
            {
               "cardId":16,
               "jokerAction":null,
               "jokerReaction":null,
               "playerPosition":1,
               "suite":"HART",
               "type" :"NINE",
               "jokerActionTake":false
            },
            {
               "cardId":12,
               "jokerAction":null,
               "jokerReaction":null,
               "playerPosition":2,
               "suite":"HART",
               "type" :"EIGHT",
               "jokerActionTake":false
            },
            {
               "cardId":30,
               "jokerAction":null,
               "jokerReaction":null,
               "playerPosition":3,
               "suite":"CLUB",
               "type" :"KING",
               "jokerActionTake":false
            }
         ],
         "winningAction":{
            "cardId":16,
            "jokerAction":null,
            "jokerReaction":null,
            "playerPosition":1,
            "suite":"HART",
            "type" :"NINE",
            "jokerActionTake":false
         },
         "actingSuite":"HART",
         "jokerActionWant":false,
         "jokerActionTake":false
      },
      "gameOn":true
   },
   "player":{
      "name":"P4",
      "position":4,
      "id":8755545805337593630,
      "cards":[
         {
            "id":32,
            "suite":"HART",
            "type":"KING"
         }
      ],
      "bWantsAll":true,
      "cantCallNumer":0,
      "call":1,
      "taken":0,
      "calls":[

      ],
      "scores":[

      ],
      "totalScore":0
   },
   "opponents":[
      {
         "name":"P2",
         "position":2,
         "id":null,
         "cards":null,
         "bWantsAll":false,
         "cantCallNumer":null,
         "call":0,
         "taken":0,
         "calls":[

         ],
         "scores":[

         ],
         "totalScore":0
      },
      {
         "name":"P3",
         "position":3,
         "id":null,
         "cards":null,
         "bWantsAll":false,
         "cantCallNumer":null,
         "call":0,
         "taken":0,
         "calls":[

         ],
         "scores":[

         ],
         "totalScore":0
      },
      {
         "name":"P1",
         "position":1,
         "id":null,
         "cards":null,
         "bWantsAll":true,
         "cantCallNumer":null,
         "call":1,
         "taken":0,
         "calls":[

         ],
         "scores":[

         ],
         "totalScore":0
      }
   ]
}




After 4th player acted:
{"state":{"version":14,"roundNumber":2,"numCards":2,"actingPlayerPosition":2,"currentTurnPosition":2,"status":"DEALT","currentPlay":{"kozyr":"HART","actions":[],"winningAction":null,"actingSuite":null,"jokerActionWant":false,"jokerActionTake":false},"gameOn":true},"player":{"name":"P4","position":4,"id":8755545805337593630,"cards":[{"id":33,"suite":"SPADE","type":"ACE"},{"id":14,"suite":"CLUB","type":"NINE"}],"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[1],"scores":[100],"totalScore":100},"opponents":[{"name":"P2","position":2,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[0],"scores":[50],"totalScore":50},{"name":"P3","position":3,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[0],"scores":[50],"totalScore":50},{"name":"P1","position":1,"id":null,"cards":null,"bWantsAll":false,"cantCallNumer":null,"call":null,"taken":0,"calls":[1],"scores":[-200],"totalScore":-200}]}


Enums:
JokerAction 
	WANT_HART, WANT_DIAMOND, WANT_CLUB, WANT_SPADE, TAKE_HART, TAKE_DIAMOND, TAKE_CLUB, TAKE_SPADE

JokerReaction
	WANT, TAKE
	
CardSuite 	
	HART,DIAMOND,CLUB,SPADE,BEZ	
	
CardType	
	SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE, JOKER

Status 
	NOT_STARTED, STARTED, DEALT, CALLS_MADE, PLAY_STARTED, PLAY_DONE, GAME_OVER		

Cards:
		cardMap.put(1, new Card(1, CardSuite.BEZ, CardType.JOKER));
		cardMap.put(2, new Card(2, CardSuite.BEZ, CardType.JOKER));
		cardMap.put(3, new Card(3, CardSuite.DIAMOND, CardType.SIX));
		cardMap.put(4, new Card(4, CardSuite.HART, CardType.SIX));
	
		cardMap.put(5, new Card(5, CardSuite.SPADE, CardType.SEVEN));
		cardMap.put(6, new Card(6, CardSuite.CLUB, CardType.SEVEN));
		cardMap.put(7, new Card(7, CardSuite.DIAMOND, CardType.SEVEN));
		cardMap.put(8, new Card(8, CardSuite.HART, CardType.SEVEN));
		
		cardMap.put(9, new Card(9, CardSuite.SPADE, CardType.EIGHT));
		cardMap.put(10, new Card(10, CardSuite.CLUB, CardType.EIGHT));
		cardMap.put(11, new Card(11, CardSuite.DIAMOND, CardType.EIGHT));
		cardMap.put(12, new Card(12, CardSuite.HART, CardType.EIGHT));		
	
		cardMap.put(13, new Card(13, CardSuite.SPADE, CardType.NINE));
		cardMap.put(14, new Card(14, CardSuite.CLUB, CardType.NINE));
		cardMap.put(15, new Card(15, CardSuite.DIAMOND, CardType.NINE));
		cardMap.put(16, new Card(16, CardSuite.HART, CardType.NINE));
		
		cardMap.put(17, new Card(17, CardSuite.SPADE, CardType.TEN));
		cardMap.put(18, new Card(18, CardSuite.CLUB, CardType.TEN));
		cardMap.put(19, new Card(19, CardSuite.DIAMOND, CardType.TEN));
		cardMap.put(20, new Card(20, CardSuite.HART, CardType.TEN));
		
		cardMap.put(21, new Card(21, CardSuite.SPADE, CardType.JACK));
		cardMap.put(22, new Card(22, CardSuite.CLUB, CardType.JACK));			
		cardMap.put(23, new Card(23, CardSuite.DIAMOND, CardType.JACK));
		cardMap.put(24, new Card(24, CardSuite.HART, CardType.JACK));
		
		cardMap.put(25, new Card(25, CardSuite.SPADE, CardType.QUEEN));
		cardMap.put(26, new Card(26, CardSuite.CLUB, CardType.QUEEN));			
		cardMap.put(27, new Card(27, CardSuite.DIAMOND, CardType.QUEEN));
		cardMap.put(28, new Card(28, CardSuite.HART, CardType.QUEEN));
		
		cardMap.put(29, new Card(29, CardSuite.SPADE, CardType.KING));
		cardMap.put(30, new Card(30, CardSuite.CLUB, CardType.KING));		
		cardMap.put(31, new Card(31, CardSuite.DIAMOND, CardType.KING));
		cardMap.put(32, new Card(32, CardSuite.HART, CardType.KING));
		
		cardMap.put(33, new Card(33, CardSuite.SPADE, CardType.ACE));
		cardMap.put(34, new Card(34, CardSuite.CLUB, CardType.ACE));					
		cardMap.put(35, new Card(35, CardSuite.DIAMOND, CardType.ACE));
		cardMap.put(36, new Card(36, CardSuite.HART, CardType.ACE));			
