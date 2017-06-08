package com.pokemon.Abilities;

import com.pokemon.Main.ObjectHandler;

//lack the count condition to calculate the amount field

public class damAbility implements Abilities {
	private String target;
	private int amount;

	public damAbility(String line) {
		super();
		if (!line.contains("count")) {
			String[] datas = line.split(":");
			this.target = datas[1];
			this.amount = Integer.parseInt(datas[2]);
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

		default:
			break;
		}
	}

}
