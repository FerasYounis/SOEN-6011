package com.pokemon.newTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Abilities.deenergizeAbility;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class DeenergizeAbilityTest {
	deenergizeAbility deengiz = new deenergizeAbility("target:opponent-active:1");

	ObjectHandler oh;
	CardFactory cf;
	Pokemon p;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		oh.player = new Player();
		oh.enemy = new Enemy();
		cf = new CardFactory();
		// Pokemon p has 60 HP;
		p = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 60, new GenericAbility[2],
				"1", CardCategory.Water);
		ObjectHandler.getEnemy().setPoke(p);

	}
	
	@Test
	public void deenergizeAbilityTest1(){
		ArrayList<Energy> list = new ArrayList<Energy>();
		list.add(new Energy());
		list.add(new Energy());
		ObjectHandler.getEnemy().getPoke().setEnergys(list);
		int sizeBefore = ObjectHandler.getEnemy().getPoke().getEnergys().size();
		System.out.println(sizeBefore);
		deengiz.turn("enemy");
		Assert.assertEquals(sizeBefore - 1, ObjectHandler.getEnemy().getPoke().getEnergys().size());

	}
}
