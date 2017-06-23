package com.pokemon.newTest;



import static org.junit.Assert.assertTrue;

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

public class PokemonTest {
	condAbility ca1 = new condAbility("healed:target:your-active:dam:target:opponent-active:80");

	ObjectHandler oh;
	CardFactory cf;
	Pokemon p1, p2;

	@Before
	public void setUp() throws Exception {
		oh = new ObjectHandler();
		oh.player = new Player(false);
		oh.enemy = new Enemy(false);
		cf = new CardFactory();
		// Pokemon p1 has 100 HP;
		p1 = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 100, new GenericAbility[2],
				"1", CardCategory.Water);
		ObjectHandler.getEnemy().setPoke(p1);
		
		// Pokemon p2 has 100 HP
		p2 = (Pokemon) cf.createCard("Doduo", CardType.Pokemon, CardCategory.Basic, 1, null, 100, new GenericAbility[2],
				"1", CardCategory.Water);
		ObjectHandler.getPlayer().setPoke(p2);
	}
	
	@Test
	public void testPokenonCreate(){
		assertTrue(p1.getHP() == 100);
		assertTrue(p1.getCardCategory().equals(CardCategory.Basic));
		
		
	}
	
	@Test
	public void testPokemonCreate2(){
		p2.setCurrentHP(50);
		assertTrue(p2.getCurrentHP() == 50);
	}

}
