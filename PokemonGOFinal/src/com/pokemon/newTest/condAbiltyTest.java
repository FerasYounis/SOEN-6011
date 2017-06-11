package com.pokemon.newTest;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.condAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class condAbiltyTest {

	ObjectHandler oh;
	CardFactory cf;
	Pokemon p;
	condAbility ability;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		ability = new condAbility("cond:healed:target:your-active:dam:target:opponent-active:80");
		oh.player = new Player(false);
		oh.enemy = new Enemy(false);
		cf = new CardFactory();
		// Pokemon p has 60 HP;
		p = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 60, new GenericAbility[2],
				"1", CardCategory.Water);

		ObjectHandler.getPlayer().setPoke(p);
		ObjectHandler.getEnemy().setPoke(p);
	}

	@Test
	public void testConstruction() {

	}

}
