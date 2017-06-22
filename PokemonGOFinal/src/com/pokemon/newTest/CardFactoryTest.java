package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;
import com.pokemon.Card.Trainer;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.ObjectHandler;

public class CardFactoryTest {
	ObjectHandler oh;
	CardFactory cf;
	Pokemon p;
	Trainer t;
	Energy e;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		cf = new CardFactory();
		
		
	}

	@Test
	public void test1() {
		p = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 60, new GenericAbility[2],
				"1", CardCategory.Water);
		assertEquals("Doduo", p.getName());
		assertEquals(null, p.getBasicName());
		assertEquals(CardCategory.Basic, p.getCardCategory());
		assertEquals(60, p.getHP());
		assertEquals(CardCategory.Water, p.getAttr());
	}
	
	@Test
	public void test2() {
		e = (Energy) cf.createCard("Lightning", CardType.Engergy, CardCategory.Lightning, 1);
		assertEquals(CardCategory.Lightning, e.getCardCategory());
		assertEquals(CardType.Engergy, e.getCardType());
	}
	
	@Test
	public void test3() {
		t = (Trainer) cf.createCard("Clemont", CardType.Trainer, CardCategory.Supporter, 1, null);
		assertEquals(CardCategory.Supporter, t.getCardCategory());
		assertEquals(CardType.Trainer, t.getCardType());
	}

	
	
	
}
