package card_game.card_game_java.src;
import java.util.Scanner;
import java.util.ArrayList;

public class Bonus {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//initialized sc Scanner
		//拆解input並加入ArrayList<String>中
		String SUIT = sc.nextLine();//黑桃、愛心、方塊、梅花
		String RANK = sc.nextLine();
		String[] SUITS = SUIT.split(",");
		String[] RANKS = RANK.split(",");
		ArrayList<String> Suit = new ArrayList<>();
		ArrayList<String> Rank = new ArrayList<>();
		ArrayList<Integer> Ranknum = new ArrayList<>();
		
		
		for (String S:SUITS) {
			Suit.add(S);
		}
		
		for (String R:RANKS) {
			if(R.equals("A")) {
				Rank.add(R);
				Ranknum.add(1);
				
			}
			else if(R.equals("J")) {
				Rank.add(R);
				Ranknum.add(11);
				
			}
			else if(R.equals("Q")) {
				Rank.add(R);
				Ranknum.add(12);
				
			}
			else if(R.equals("K")) {
				Rank.add(R);
				Ranknum.add(13);
				
			}
			else {
				int r = Integer.parseInt(R);
				Ranknum.add(r);
				
			}
		}
	
		ArrayList<Card> cards = new ArrayList<>();
		//input Suit and Ranknum to card1-5
		Card card1 = new Card(Suit.get(0), Ranknum.get(0));
		Card card2 = new Card(Suit.get(1), Ranknum.get(1));
		Card card3 = new Card(Suit.get(2), Ranknum.get(2));
		Card card4 = new Card(Suit.get(3), Ranknum.get(3));
		Card card5 = new Card(Suit.get(4), Ranknum.get(4));
		//create a <Card>arraylist to record card content and add them
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		
		
		cards.get(0).score(cards);
		sc.close();
		
	}		
}
