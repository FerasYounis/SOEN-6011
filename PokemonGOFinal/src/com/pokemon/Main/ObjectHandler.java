package com.pokemon.Main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.pokemon.Card.Card;
import com.pokemon.Card.CardFactory;
import com.pokemon.Card.Energy;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;



public class ObjectHandler {

	public static ArrayList<GameObject> gameObjects;
	public static ArrayList<Card> allCards;
	public static Player player;
	public static Enemy enemy;
	public static Energy water;
	public static Energy lightning;
	public static Energy psychic;
	public static Energy fighting;
	public static Energy colorless;

	public ObjectHandler() {
		gameObjects = new ArrayList<GameObject>();
		allCards = new ArrayList<Card>();
		player = new Player();
		enemy = new Enemy();
		
		CardFactory cf = new CardFactory();
		cf.createCard("Water", CardType.Engergy, CardCategory.Water, 2);
		cf.createCard("Lightning", CardType.Engergy, CardCategory.Lightning, 2);
		cf.createCard("Psychic", CardType.Engergy, CardCategory.Psychic, 1);
		cf.createCard("Fighting", CardType.Engergy, CardCategory.Fighting, 1);
		cf.createCard("", CardType.Engergy, CardCategory.Colorless, 0);
	}

	public void update() {
	}

	public void draw(Graphics g) {
	}

	public static void addObject(GameObject go) {
		gameObjects.add(go);
	}
	public static void addCard(Card cd){
		allCards.add(cd);
		
	}
	
	public static void removeObject(GameObject go){
		gameObjects.remove(go);
	}
	
	public static void removeCard(Card cd){
		allCards.remove(cd);
		removeObject((GameObject) cd);
	}

	public static Player getPlayer() {
		return player;
	}

	public static Enemy getEnemy(){
		return enemy;
	}
	
	
}
