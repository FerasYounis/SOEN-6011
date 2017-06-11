package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.damAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class damAbilityTest {
	damAbility da1 = new damAbility("target:opponent-active:20");
	damAbility da2 = new damAbility("target:opponent-active:20*count[target:your-bench]");
	damAbility da3 = new damAbility("target:choice:opponent:30");
	ObjectHandler oh;
	CardFactory cf;
	Pokemon p;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		oh.player = new Player(false);
		oh.enemy = new Enemy(false);
		cf = new CardFactory();
		// Pokemon p has 60 HP;
		p = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 60, new GenericAbility[2],
				"1", CardCategory.Water);
		ObjectHandler.getPlayer().setPoke(p);
	}

	/**
	 * To test whether the construction can parse the input line
	 */
	@Test
	public void testConstruction() {
		assertEquals("opponent-active", da1.getTarget());
		assertEquals(20, da1.getAmount());
		assertEquals("target:your-bench", da2.getCount());
	}

	/**
	 * da1: "target:opponent-active:20" To test the Turn(String enemy) function
	 * in da1
	 */
	@Test
	public void testDamAbility1Turn() {
		da1.turn("player");
		assertEquals(40, ObjectHandler.getPlayer().getPoke().getCurrentHP());
	}

	/**
	 * da2: "target:opponent-active:20*count[target:your-bench]" To test the
	 * Turn(String enemy) function in da2
	 */
	@Test
	public void testDamAbility2Turn() {
		// Size of enemy bench is 2 now
		for (int i = 0; i < 2; i++) {
			ObjectHandler.getEnemy().getBench().add(p);
		}
		da2.turn("player");
		assertEquals(20, ObjectHandler.getPlayer().getPoke().getCurrentHP());
	}

	
	
	/**
	 * da3: "target:choice:opponent:30"
	 * To test the choice function of da3
	 */
	@Test
	public void testTurnInt() {
		// Size of enemy bench is 4 now
		for (int i = 0; i < 4; i++) {
			ObjectHandler.getEnemy().getBench().add(p);
		}
		da3.turn(2);
		assertEquals(30, ObjectHandler.getEnemy().getBench().get(2).getCurrentHP());
		
		
	}

}
