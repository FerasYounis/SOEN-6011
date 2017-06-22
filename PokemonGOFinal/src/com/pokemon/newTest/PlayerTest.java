package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class PlayerTest {

	Player player;
	ObjectHandler oh = new ObjectHandler();

	@Before
	public void setUp() throws Exception {
		player = new Player();
	}

	/**
	 * To check the amount of Prize, Hand and the rest cards in Deck
	 */
	@Test
	public void test1() {
		assertEquals(6, player.getPrize().size());
		assertEquals(7, player.getHand().size());
		assertEquals(60 - 6 - 7, player.getDeck().size());
	}

	/**
	 * To check the shuffleDeck() function
	 */
	@Test
	public void test2() {
		ArrayList<Card> originalDeck = (ArrayList<Card>)player.getDeck().clone();
		player.shuffleDeck();
		ArrayList<Card> shuffledDeck = player.getDeck();
		boolean flag = false;
		for(int i = 0; i < originalDeck.size(); i++){
			if(!originalDeck.get(i).getUrl().equals(shuffledDeck.get(i).getUrl()))
				flag = true;
		}
		
		assertEquals(true, flag);
	}
	
	/**
	 * To check the drawOneCard() function
	 */
	@Test
	public void test3() {
		int originalDeckSize = player.getDeck().size();
		player.drawOneCard();

		assertEquals(originalDeckSize - 1, player.getDeck().size());
	}
	
	
}
