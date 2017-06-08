package com.pokemon.Abilities;

import com.pokemon.Main.ObjectHandler;

public class shuffleAbility extends Abilities{
	
	
	
	public shuffleAbility(String line){
		String[] datas = line.split(":");
		this.target = datas[1];
	}

	public void turn(String enemy) {
		System.out.println(enemy + ": " + amount);
		switch (this.target) {
		case "you":
			if (enemy.equals("player")) {
				ObjectHandler.getEnemy().shuffleDeck();
			}
			if (enemy.equals("enemy")) {
				ObjectHandler.getPlayer().shuffleDeck();
			}
			break;
			
		case "opponent":
			if (enemy.equals("enemy")) {
				ObjectHandler.getEnemy().shuffleDeck();
			}
			if (enemy.equals("player")) {
				ObjectHandler.getPlayer().shuffleDeck();
			}
			break;
			
		
			
	
		

		default:
			break;
		}
		return;
	
	
	
	
	}
	
	
	
	
	
	
}
