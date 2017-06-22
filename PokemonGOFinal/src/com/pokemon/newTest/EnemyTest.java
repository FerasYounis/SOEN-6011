package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class EnemyTest {

	Enemy enemy;
	ObjectHandler oh = new ObjectHandler();

	@Before
	public void setUp() throws Exception {
		enemy = new Enemy();
	}

	/**
	 * To check the amount of Prize, Hand and the rest cards in Deck
	 */
	@Test
	public void test1() {
		assertEquals(6, enemy.getPrize().size());
		assertEquals(7, enemy.getHand().size());
		assertEquals(60 - 6 - 7, enemy.getDeck().size());
	}

	
	
	/**
	 * To check the drawOneCard() function
	 */
	@Test
	public void test2() {
		int originalDeckSize = enemy.getDeck().size();
		enemy.drawOneCard();

		assertEquals(originalDeckSize - 1, enemy.getDeck().size());
	}
	
	
	
	/**
	 * To check the shuffleDeck() function
	 */
	@Test
	public void test3() {
		ArrayList<Card> originalDeck = (ArrayList<Card>)enemy.getDeck().clone();
		enemy.shuffleDeck();
		ArrayList<Card> shuffledDeck = enemy.getDeck();
		boolean flag = false;
		for(int i = 0; i < originalDeck.size(); i++){
			if(!originalDeck.get(i).getUrl().equals(shuffledDeck.get(i).getUrl()))
				flag = true;
		}
		
		assertEquals(true, flag);
	}
	
}
