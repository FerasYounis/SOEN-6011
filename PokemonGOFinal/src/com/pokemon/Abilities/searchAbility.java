package com.pokemon.Abilities;

import com.pokemon.Enums.CardType;
import com.pokemon.Main.ObjectHandler;

public class searchAbility extends Abilities{
	private String filter;
	private String source;
	
	public searchAbility(String line){
		String[] datas = line.split(":");
		
		if(!line.contains("filter")){
			this.target = datas[1];
			this.source = datas[3];
			this.amount = Integer.parseInt(datas[4]);
		}else{
			this.target = datas[1];
			this.source = datas[3];
			this.amount = Integer.parseInt(datas[7]);
			this.filter = datas[6];
			
			
		}
		
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
