package com.pokemon.Main;

import java.util.ArrayList;
import java.util.Random;

import com.pokemon.Card.Card;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;

public class Player extends GameObject {

	protected ArrayList<Card> deck, hand, graveyard, prize;
	protected ArrayList<Pokemon> bench;
	protected Pokemon poke;
	private DataReader dr;
	private DataReader2 dr2;


	public Player() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		bench = new ArrayList<Pokemon>();
		poke = null;
		graveyard = new ArrayList<Card>();
		prize = new ArrayList<Card>();
		dr = new DataReader();


		setDeck();
		shuffleDeck();
		setHand();
		System.out.println(hand.size());
		
		while(checkHand()){
			deck.addAll(hand);
			hand.clear();
			shuffleDeck();
			setHand();
		}
		setPrize();

		// initial hand location
		for (int i = 0; i < hand.size(); i++) {
			hand.get(i).setX(500 + 90 * i);
			hand.get(i).setY(685);
		}
	}
	
	public Player(boolean flag) {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		bench = new ArrayList<Pokemon>();
		poke = null;
		graveyard = new ArrayList<Card>();
		prize = new ArrayList<Card>();
		dr = new DataReader();

		setDeck();
		shuffleDeck();
		setHand();

	}

	private boolean checkHand() {
		for(Card c: hand){
			if(c.getCardCategory() == CardCategory.Basic)
				return false;
		}
		return true;
	}

	public void update() {
		if (!Game.getMouseManager().LDragging) {

			for (int i = 0; i < hand.size(); i++) {
				hand.get(i).setX(500 + 90 * i);
				hand.get(i).setY(685);
			}

			if (poke != null) {
				poke.setX(GameInterface.playerPoke.x);
				poke.setY(GameInterface.playerPoke.y);
			}

			for (int i = 0; i < bench.size(); i++) {
				bench.get(i).setX(GameInterface.playerBench[i].x);
				bench.get(i).setY(GameInterface.playerBench[i].y);
			}

		}

	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck() {
		System.out.println("player is loading");
		dr2 = new DataReader2("/deck1.txt");
	//	deck = dr.loadData("/deck1.txt", 1);
		deck = dr2.deck;
		System.out.println(deck.size());
		for(Card c: deck){
			if(c != null)
			System.out.println(c.url);
		}
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand() {
		for (int i = 0; i < 7; i++) {
			hand.add(drawOneCard());
		}
	}
	

	public ArrayList<Card> getPrize() {
		return prize;
	}

	private void setPrize() {
		for (int i = 0; i < 6; i++) {
			prize.add(drawOneCard());
		}
	}

	public void shuffleDeck() {
		int size = deck.size();
		ArrayList<Card> shuffledDeck = new ArrayList<Card>();
		Random r = new Random();
		while(!deck.isEmpty()){
			Card c = deck.get(r.nextInt(deck.size()));
			shuffledDeck.add(c);
			deck.remove(c);
		}
		deck.addAll(shuffledDeck);
	}

	public ArrayList<Pokemon> getBench() {
		return bench;
	}

	public void setBench(ArrayList<Pokemon> bench) {
		this.bench = bench;
	}

	public Pokemon getPoke() {
		return poke;
	}

	public void setPoke(Pokemon poke) {
		this.poke = poke;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(ArrayList<Card> graveyard) {
		this.graveyard = graveyard;
	}

	public Card drawOneCard() {
		Card card = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return card;
	}
	
	public boolean checkKnockout(){
		if(poke != null && poke.getCurrentHP() <= 0){
			graveyard.add(poke);
			poke = null;
			return true;
		}
		return false;
	}
	
	

}
