package com.pokemon.Abilities;

import com.pokemon.Card.Pokemon;
import com.pokemon.Main.Game;
import com.pokemon.Main.ObjectHandler;

//lack the count condition to calculate the amount field
//choice:opponent is not implmented yet

public class damAbility extends Abilities {
	private String count;

	public damAbility(String line) {
		super();
		count = null;
		String[] datas = line.split(":");
		if (!line.contains("count")) {
			if(!line.contains("choice")){
				this.target = datas[1];
				this.amount = Integer.parseInt(datas[2]);
			}
			else{
				this.target = datas[1] + ":" + datas[2];
				this.amount = Integer.parseInt(datas[3]);
			}
		}else{
			this.target = datas[1];
			String s = line.substring(datas[0].length() + datas[1].length() + 2);
			String sss = null;
			boolean flag = false;
			for(char c: s.toCharArray()){
				if(c == '*'){
					flag = true;
					sss = s.replace(c, ' ');
				}
			}
			
			if(!flag){
				this.amount = 1;
			}else{
				String[] ss = sss.split(" ");
				if(ss[0].contains("count")){
					String temp = ss[0];
					ss[0] = ss[1];
					ss[1] = temp;
				}
				this.amount = Integer.parseInt(ss[0]);
				
				for(char c: s.toCharArray()){
					if(c == '[' || c == '(' || c == ']' || c == ')'){
						ss[1] = ss[1].replace(c, ' ');
					}
				}
				String[] p = ss[1].split(" ");
				this.count = p[1];
				
				
			}
		}

	}

	public void turn(String enemy) {
		if(count != null){
			if(count.contains("your-bench")){
				if(enemy.equals("enemy"))
					this.amount = this.amount * ObjectHandler.getPlayer().getBench().size(); 
				if(enemy.equals("player"))
					this.amount = this.amount * ObjectHandler.getEnemy().getBench().size(); 
			}
			if(count.contains("your-active:damage")){
				if(enemy.equals("enemy"))
					this.amount = this.amount * ((ObjectHandler.getPlayer().getPoke().getHP() - ObjectHandler.getPlayer().getPoke().getCurrentHP()) / 10);
				if(enemy.equals("player"))
					this.amount = this.amount * ((ObjectHandler.getEnemy().getPoke().getHP() - ObjectHandler.getEnemy().getPoke().getCurrentHP()) / 10);
			}
			
			if(count.contains("opponent-active:energy")){
				if(enemy.equals("player"))
					this.amount = this.amount * ObjectHandler.getPlayer().getPoke().getEnergys().size(); 
				if(enemy.equals("enemy"))
					this.amount = this.amount * ObjectHandler.getEnemy().getPoke().getEnergys().size(); 
			}
			
			
			
		}
			
		System.out.println(enemy + ": " + target + ": "  + amount);
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
			
		case "choice:opponent-bench":
			if (enemy.equals("enemy")) {
				if(ObjectHandler.getEnemy().getBench().size() != 0){
					int currentHP = ObjectHandler.getEnemy().getBench().get(0).getCurrentHP();
					ObjectHandler.getEnemy().getBench().get(0).setCurrentHP(currentHP - amount);
					System.out.println("attack successfully!");
				}
			}
			if (enemy.equals("player")) {
				if(ObjectHandler.getPlayer().getBench().size() != 0){
					int currentHP = ObjectHandler.getPlayer().getBench().get(0).getCurrentHP();
					ObjectHandler.getPlayer().getBench().get(0).setCurrentHP(currentHP - amount);
				}
			}
			
			break;
			
			
		case "choice:opponent":
			if (enemy.equals("enemy")) {
				if(ObjectHandler.getEnemy().getBench().size() != 0){
					int currentHP = ObjectHandler.getEnemy().getBench().get(0).getCurrentHP();
					ObjectHandler.getEnemy().getBench().get(0).setCurrentHP(currentHP - amount);
				}
			}
			if (enemy.equals("player")) {
				if(ObjectHandler.getPlayer().getBench().size() != 0){
					int currentHP = ObjectHandler.getPlayer().getBench().get(0).getCurrentHP();
					ObjectHandler.getPlayer().getBench().get(0).setCurrentHP(currentHP - amount);
				}
			}
			
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
	
	
	

