package com.pokemon.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Abilities.*;
import com.pokemon.Card.CardFactory;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.DataReader;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class ScratchAbilityTest {

	
	ScratchAbility ability;
	ArrayList<Card> deck = new ArrayList<Card>();
	
	DataReader dr = new DataReader();
	static ObjectHandler objectHandler = new ObjectHandler();
	Player player = new Player(true);
	
	@Test
	public void test() {
		try {
			 ability = new ScratchAbility("Scratch:dam:target:opponent-active:20");
			 assertTrue(ability.runAbility());
			 
		} catch (Exception e)
		{
			
		}
		

	}

}
