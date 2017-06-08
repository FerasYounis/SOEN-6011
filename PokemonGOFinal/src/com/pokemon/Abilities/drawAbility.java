package com.pokemon.Abilities;

import com.pokemon.Main.ObjectHandler;

public class drawAbility extends Abilities {

	public drawAbility(String line) {
		String[] s = line.split(":");
		if(s.length == 1){
			this.amount = Integer.parseInt(line);
			this.target = "your";
		}
		if(s.length == 2){
			this.amount = Integer.parseInt(s[1]);
			this.target = s[0];
		}
	}

	public void turn(String enemy) {
		
		switch (this.target) {
		case "your":
			if (enemy.equals("player")) {
				for(int i = 0; i < this.amount; i++){
					ObjectHandler.getEnemy().getHand().add(ObjectHandler.getEnemy().drawOneCard());
				}
			}
			if (enemy.equals("enemy")) {
				for(int i = 0; i < this.amount; i++){
					ObjectHandler.getPlayer().getHand().add(ObjectHandler.getPlayer().drawOneCard());
				}
			}
			
			break;

		case "opponent":
			if (enemy.equals("enemy")) {
				for(int i = 0; i < this.amount; i++){
					ObjectHandler.getEnemy().getHand().add(ObjectHandler.getEnemy().drawOneCard());
				}
			}
			if (enemy.equals("player")) {
				for(int i = 0; i < this.amount; i++){
					ObjectHandler.getPlayer().getHand().add(ObjectHandler.getPlayer().drawOneCard());
				}
			}
			
			break;
			
			
		default:
			break;
		}
		
		
	
	}
}
