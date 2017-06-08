package com.pokemon.Abilities;

import com.pokemon.Card.Pokemon;
import com.pokemon.Main.Game;
import com.pokemon.Main.ObjectHandler;

//lack the count condition to calculate the amount field
//choice:opponent is not implmented yet

public class damAbility extends Abilities {


	public damAbility(String line) {
		super();
		if (!line.contains("count")) {
			String[] datas = line.split(":");
			
			if(!line.contains("choice")){
				this.target = datas[1];
				this.amount = Integer.parseInt(datas[2]);
			}
			else{
				this.target = datas[1];
				this.amount = Integer.parseInt(datas[3]);
			}
		}

	}

	public void turn(String enemy) {

		System.out.println(target + ": " + amount);
		switch (this.target) {
		case "opponent-active":
			if (enemy.equals("enemy")) {
				int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP();
				ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP - amount);
			}
			if (enemy.equals("player")) {
				int currentHP = ObjectHandler.getPlayer().getPoke().getCurrentHP();
				ObjectHandler.getPlayer().getPoke().setCurrentHP(currentHP - amount);
			}
			break;
			
		case "your-active":
			if (enemy.equals("player")) {
				int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP();
				ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP - amount);
			}
			if (enemy.equals("enemy")) {
				int currentHP = ObjectHandler.getPlayer().getPoke().getCurrentHP();
				ObjectHandler.getPlayer().getPoke().setCurrentHP(currentHP - amount);
			}
			break;
			
		case "choice":
		//	int selected = Game.gameInterface.getSelected();
			if (enemy.equals("enmey")) {
				int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP();
			//	if(31 == selected)
					ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP - amount);
//				if(40 <= selected && 44 >= selected)
//					ObjectHandler.getEnemy().getBench().get(selected - 40).setCurrentHP(currentHP - amount);
			}
			if (enemy.equals("player")) {
				int currentHP = ObjectHandler.getPlayer().getPoke().getCurrentHP();
				ObjectHandler.getPlayer().getPoke().setCurrentHP(currentHP - amount);
			}
			
			Game.gameInterface.setSelected(-1);
			break;
			
		case "your-bench":
			if (enemy.equals("player") && ObjectHandler.getEnemy().getBench().size() != 0) {
				for(Pokemon p: ObjectHandler.getEnemy().getBench()){
					int currentHP =p.getCurrentHP();
					p.setCurrentHP(currentHP - amount);
				}
			}
			if (enemy.equals("enemy") && ObjectHandler.getPlayer().getBench().size() != 0) {
				for(Pokemon p: ObjectHandler.getPlayer().getBench()){
					int currentHP =p.getCurrentHP();
					p.setCurrentHP(currentHP - amount);
				}
			}
			break;	
			
			
		case "opponent-bench":
			if (enemy.equals("enemy") && ObjectHandler.getEnemy().getBench().size() != 0) {
				for(Pokemon p: ObjectHandler.getEnemy().getBench()){
					int currentHP =p.getCurrentHP();
					p.setCurrentHP(currentHP - amount);
				}
			}
			if (enemy.equals("player") && ObjectHandler.getPlayer().getBench().size() != 0) {
				for(Pokemon p: ObjectHandler.getPlayer().getBench()){
					int currentHP =p.getCurrentHP();
					p.setCurrentHP(currentHP - amount);
				}
			}
			break;	
		

		default:
			break;
		}
		return;
	}

}
