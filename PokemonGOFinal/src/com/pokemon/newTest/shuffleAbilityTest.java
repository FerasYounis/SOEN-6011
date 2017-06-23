package com.pokemon.newTest;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.drawAbility;
import com.pokemon.Abilities.shuffleAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

import junit.framework.Assert;

public class shuffleAbilityTest {
	shuffleAbility shuf = new shuffleAbility("target:opponent");
	

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
	public void shuffleTest(){
		String cardBeforShuffle = ObjectHandler.getEnemy().getDeck().get(5).toString();
		shuf.turn("enemy");
		Assert.assertFalse(ObjectHandler.getEnemy().getDeck().get(5).toString().equalsIgnoreCase(cardBeforShuffle));

		
	}
	
}
