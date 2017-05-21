package com.pokemon.Main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;

public class Enemy extends GameObject {

	protected ArrayList<Card> deck, hand, bench, poke, graveyard, prize;
	private CardFactory cf;

	public Enemy() {
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		bench = new ArrayList<Card>();
		poke = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		prize = new ArrayList<Card>();
		cf = new CardFactory();
		for (int i = 0; i < 7; i++) {
//			hand.add(cf.createCard("Diglett", CardType.Pokemon, 1));
			hand.get(i).setX(500 + 90 * i);
			hand.get(i).setY(30);
		}
	}

	public void update() {

	}

	public void draw(Graphics g) {

	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public ArrayList<Card> getBench() {
		return bench;
	}

	public void setBench(ArrayList<Card> bench) {
		this.bench = bench;
	}

	public ArrayList<Card> getPoke() {
		return poke;
	}

	public void setPoke(ArrayList<Card> poke) {
		this.poke = poke;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(ArrayList<Card> graveyard) {
		this.graveyard = graveyard;
	}

}
