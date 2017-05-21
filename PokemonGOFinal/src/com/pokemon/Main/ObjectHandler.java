package com.pokemon.Main;

import java.awt.Graphics;
import java.util.ArrayList;

import com.pokemon.Card.Card;



public class ObjectHandler {

	public static ArrayList<GameObject> gameObjects;
	public static ArrayList<Card> allCards;
	public static Player player;
	public static Enemy enemy;

	public ObjectHandler() {
		gameObjects = new ArrayList<GameObject>();
		allCards = new ArrayList<Card>();
		player = new Player();
		enemy = new Enemy();
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
