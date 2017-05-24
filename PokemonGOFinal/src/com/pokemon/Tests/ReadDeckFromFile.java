package com.pokemon.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Main.DataReader;
import com.pokemon.Main.ObjectHandler;

public class ReadDeckFromFile {

	ArrayList<Card> deck = new ArrayList<Card>();
	DataReader dr = new DataReader();
	static ObjectHandler objectHandler = new ObjectHandler();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		deck = dr.loadData("/deck1.txt",1);
		assertTrue(deck.size() > 0);
		
		
	}

}
