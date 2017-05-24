package com.pokemon.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Main.DataReader;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class ShuffleDeck {

	ArrayList<Card> deck = new ArrayList<Card>();
	ArrayList<Card> deck2 = new ArrayList<Card>();
	DataReader dr = new DataReader();
	static ObjectHandler objectHandler = new ObjectHandler();
	Player player = new Player(true);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		deck = player.getDeck();
		player.shuffleDeck();
		deck2 = player.getDeck();
		
		assertNotSame(deck.toArray(), deck2.toArray());
		
		
	}

}
