package com.pokemon.newTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pokemon.Main.DataReader2;
import com.pokemon.Main.ObjectHandler;

public class DataReader2Test {
	DataReader2 dr;
	ObjectHandler oh = new ObjectHandler();

	@Before
	public void setUp() throws Exception {
		dr = new DataReader2("/deck1.txt");
	}

	@Test
	public void test1() {
		assertEquals(51, dr.getDeckNum()[0]);
		assertEquals(33, dr.getDeckNum()[13]);
		assertEquals(57, dr.getDeckNum()[21]);
		assertEquals(47, dr.getDeckNum()[53]);
	}

	@Test
	public void test2() {
		assertEquals("Glameow:pokemon:cat:basic:cat:colorless:60:"
				+ "retreat:cat:colorless:2:attacks:cat:colorless:1:1,cat:colorless:2:2", dr.getCardData()[0]);
		assertEquals("Misty's Determination:trainer:cat:supporter:33", dr.getCardData()[21]);
		assertEquals("Shauna:trainer:cat:supporter:69", dr.getCardData()[50]);
	}
	
	
	@Test
	public void test3() {
		assertEquals("Act Cute:deck:target:them:destination:deck:bottom:choice:target:1", dr.getAbilityData()[0]);
		assertEquals("Potion:heal:target:your:30", dr.getAbilityData()[31]);
		assertEquals("Beatdown:dam:target:opponent-active:40", dr.getAbilityData()[62]);
	}

}
