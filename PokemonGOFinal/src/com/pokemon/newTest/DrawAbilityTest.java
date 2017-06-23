package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.drawAbility;
import com.pokemon.Abilities.healAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class DrawAbilityTest {

	drawAbility draw = new drawAbility("opponent:4");
	drawAbility draw2 = new drawAbility("your:4");

	ObjectHandler oh;
	CardFactory cf;
	Pokemon p;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		oh.player = new Player(false);
		oh.enemy = new Enemy();
		cf = new CardFactory();
		// Pokemon p has 60 HP;
		p = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 60, new GenericAbility[2],
				"1", CardCategory.Water);
		ObjectHandler.getEnemy().setPoke(p);
	}

	@Test
	public void drawCardtest(){
		// the hand size before drawing is 7, after drawing 4 cards it should be 7+4=11
		System.out.println(ObjectHandler.getEnemy().getHand().size());
		draw.turn("enemy");
		assertEquals(ObjectHandler.getEnemy().getHand().size(),11);
		
	}
	
	
}
