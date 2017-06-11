package com.pokemon.newTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.healAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class healAbilityTest {

	healAbility da1 = new healAbility("target:your-active:20");

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
		ObjectHandler.getEnemy().setPoke(p);
		ObjectHandler.getPlayer().setPoke(p);
	}

	@Test
	public void test() {
		int currentHp = oh.getEnemy().getPoke().getCurrentHP();
		da1.turn("enemy");
		int currentHp2 = oh.getEnemy().getPoke().getCurrentHP();
		assertTrue(currentHp2 > currentHp);
		assertTrue(oh.getEnemy().getPoke().isHealed());
	}

}
