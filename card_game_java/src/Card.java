package card_game.card_game_java.src;
import java.util.ArrayList;

public class Card {
	private String suit;
	private int rank;
	
	public Card(String suit, int rank) {
		this.suit = suit;
		this.rank = rank;
	}
	//game score calculate
	
	
	public String getsuit() {
		return suit;
	}
	public int getrank() {
		return rank;
	}
	public void score(ArrayList<Card> cards) {
		ArrayList<String> Suit = new ArrayList<>();
		ArrayList<Integer> Ranknum = new ArrayList<>();
		for(int x=0;x<cards.size();x++) {
			Ranknum.add(cards.get(x).getrank());//arraylist,get card,getnumber
			Suit.add(cards.get(x).getsuit());
		}
		
		
	//samenumber
		int[]samenumber = new int[13];
		for(int y:Ranknum) {
			samenumber[y-1]+=1;
		}
		
		
				
		ArrayList<Integer> Samenumber = new ArrayList<>();
		for(int q=0;q<13;q++) {
			Samenumber.add(samenumber[q]);
		}
		
		
		//sameicon
		int c=0;
		int samesuit=0;
		if(Suit.get(c).equals(Suit.get(c+1))&&Suit.get(c+1).equals(Suit.get(c+2))&&Suit.get(c+2).equals(Suit.get(c+3))) {
			samesuit = 1;
		}
		else {
			samesuit = 0;
		}
		
		//rule
		int Score = 0;
		//start
		int maxN = samenumber[0];
	    for (int ktr = 0; ktr < samenumber.length; ktr++) {
	        if (samenumber[ktr] >= maxN) {
	            maxN = samenumber[ktr];
	        }
	    }
		int twocount = 0;
	    for (int k = 0; k < samenumber.length; k++) {
	    	if(samenumber[k]==2) {
	    		twocount+=1;
	    	}
	    }
	    
	    boolean isOrder = true;
	    int[] order = new int[18];
	    for(int number:Ranknum) {
	    	for(int x=1;x<=13;x++) {
	    		if(number==x) {
	    			order[x]=1;
	    		}
		    }
	    	
	    	if(number==1) {
	    		order[14]=1;
	    	}
	    	if(number==2) {
	    		order[15]=1;
	    	}
	    	if(number==3) {
	    		order[16]=1;
	    	}
	    	if(number==4) {
	    		order[17]=1;
	    	}
	    }
	   
	    for(int u=1;u<13;u++) {
	    	
	    	int count = 0;
	    	count = order[u]+order[u+1]+order[u+2]+order[u+3]+order[u+4]+order[u+5];
	    	if(count==5) {
	    		isOrder = true;
	    		break;
	    	}
	    	else {
	    		isOrder = false;
	    	}
	    }
	   
	   
		if(maxN==1) {
			if(isOrder == true) {
				if(samesuit==1) {
					Score+=100;
				}
				else  {
					Score+=5;
					//continue
				}		
			}
			else if(isOrder == false) {
				if(Ranknum.contains(1)) {
					Score+=1;
					//single but A
				}
				
				else {
					Score+=0;
				}	
			}	
			else  {
				Score+=0;
			}
			
		}
		else if(maxN==2){
			
			if(twocount==2&&samenumber[0]==2) {
				Score+=5;
				//2+2+A
			}
			else if(twocount==2) {
				Score+=4;
				//2+2+1
			}
			else if(samenumber[0]==1) {
				Score+=3;
				//2+1+1+A
			}
			else {
				Score+=2;
				//2+1+1+1
			}
			
		}
		else if(maxN==3) {
			if(Samenumber.contains(2)) {
				Score+=10;
				//3+2
			}
			else if(samenumber[0]>0) {
				Score+=3;
				//3+1+A
				//AAA+1+1
			}
			else {
				Score+=2;
				//3+1+1
			}
			
		}
		else if(maxN==4) {
			if(samenumber[0]==1) {
				Score+=21;
				//4+A
			}
			else {
				Score+=20;
				//4+1
			}
			
		}

		else if(samesuit==1) {
			Score+=3;
			
		}
		else {
			
		}
		System.out.println(Score);
		
	}	
	}
	
	
		
		
		
		
	
	
    
	


