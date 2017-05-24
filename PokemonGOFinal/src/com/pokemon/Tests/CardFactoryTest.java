package com.pokemon.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.DataReader;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class CardFactoryTest {
	ArrayList<Card> deck = new ArrayList<Card>();
	
	DataReader dr = new DataReader();
	static ObjectHandler objectHandler = new ObjectHandler();
	CardFactory factory = new CardFactory();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Card c1 = factory.createCard("SF", CardType.Engergy, CardCategory.Colorless, 1);
		assertNotNull(c1);

	}

}
