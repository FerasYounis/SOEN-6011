package com.pokemon.Main;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;

public class Enemy extends GameObject {

	protected ArrayList<Card> deck, hand, graveyard, prize;
	protected ArrayList<Pokemon> bench;
	protected Pokemon poke;
	private DataReader dr;

	public Enemy() {
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
		setPrize();

		// initial hand location
		for (int i = 0; i < hand.size(); i++) {
			hand.get(i).setX(500 + 90 * i);
			hand.get(i).setY(30);
		}
	}

	public void update() {
		if (!Game.getMouseManager().LDragging) {

			for (int i = 0; i < hand.size(); i++) {
				hand.get(i).setX(500 + 90 * i);
				hand.get(i).setY(30);
			}

			if (poke != null) {
				poke.setX(GameInterface.enemyPoke.x);
				poke.setY(GameInterface.enemyPoke.y);
			}

			for (int i = 0; i < bench.size(); i++) {
				bench.get(i).setX(GameInterface.enemyBench[i].x);
				bench.get(i).setY(GameInterface.enemyBench[i].y);
			}

		}

	}


	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck() {
		deck = dr.loadData("resourse/deck1.txt", 1);
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
		Collections.shuffle(deck);
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
