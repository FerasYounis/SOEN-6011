package com.pokemon.Abilities;

import com.pokemon.Main.ObjectHandler;

public class healAbility extends Abilities{

	public healAbility(String line){
		String[] datas = line.split(":");
		
			this.target = datas[1];
			this.amount = Integer.parseInt(datas[2]);
		
		
	}

	public void turn(String enemy) {
		System.out.println(enemy + ": " + amount);

	
		switch (this.target) {
	
			
		case "your-active":
			if (enemy.equals("player")) {
				int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP();
				ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP + amount);
				ObjectHandler.getEnemy().getPoke().setHealed(true);
			}
			if (enemy.equals("enemy")) {
				int currentHP = ObjectHandler.getPlayer().getPoke().getCurrentHP();
				ObjectHandler.getPlayer().getPoke().setCurrentHP(currentHP + amount);
				ObjectHandler.getPlayer().getPoke().setHealed(true);

			}
			break;
			
		case "your":
			if (enemy.equals("player")) {
				int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP();
				ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP + amount);
				ObjectHandler.getEnemy().getPoke().setHealed(true);
			}
			if (enemy.equals("enemy")) {
				int currentHP = ObjectHandler.getPlayer().getPoke().getCurrentHP();
				ObjectHandler.getPlayer().getPoke().setCurrentHP(currentHP + amount);
				ObjectHandler.getPlayer().getPoke().setHealed(true);

			}
			break;
			
		
			
	
		

		default:
			break;
		}
		return;
	
	
	
	
	}
	
	
	
	
	
}
