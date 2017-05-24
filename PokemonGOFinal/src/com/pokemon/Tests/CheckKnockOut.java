package com.pokemon.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Card.Card;
import com.pokemon.Main.DataReader;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class CheckKnockOut {

	ArrayList<Card> deck = new ArrayList<Card>();
	
	DataReader dr = new DataReader();
	static ObjectHandler objectHandler = new ObjectHandler();
	Player player = new Player(true);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		assertFalse(player.checkKnockout());
		
	}

}
