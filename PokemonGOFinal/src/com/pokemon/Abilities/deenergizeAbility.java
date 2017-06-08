package com.pokemon.Abilities;

import java.util.ArrayList;

import com.pokemon.Card.Energy;
import com.pokemon.Main.ObjectHandler;

public class deenergizeAbility extends Abilities {
	private String count;

	public deenergizeAbility(String line) {
		String[] datas = line.split(":");
		this.count = null;

		if (!line.contains("count")) {
			this.target = datas[1];
			this.amount = Integer.parseInt(datas[2]);
		} else {
			this.target = datas[1];
			this.amount = 1;
			String s = line.substring(datas[0].length() + datas[1].length() + 2);
			for (char c : s.toCharArray()) {
				if (c == '[' || c == '(' || c == ']' || c == ')') {
					s = s.replace(c, ' ');
				}
			}
			String[] p = s.split(" ");
			this.count = p[1];
			System.out.println(count);

		}

	}

	public void turn(String enemy) {
		System.out.println("losing " + amount + " cards");
		if (this.count != null && this.count.contains("your-active:energy")) {
			if (enemy.equals("enemy")) {
				ObjectHandler.getPlayer().getPoke().setEnergys(new ArrayList<Energy>());
				return;
			}
			if (enemy.equals("player")) {
				ObjectHandler.getEnemy().getPoke().setEnergys(new ArrayList<Energy>());
				return;
			}
		} else {

			switch (this.target) {

			case "opponent-active":
				if (enemy.equals("player")) {
					if(ObjectHandler.getPlayer().getPoke().getEnergys().size() >= this.amount)
						ObjectHandler.getPlayer().getPoke().costEnergy(amount);
					else
						ObjectHandler.getPlayer().getPoke().setEnergys(new ArrayList<Energy>());
					return;
				}
				if (enemy.equals("enemy")) {
					if(ObjectHandler.getEnemy().getPoke().getEnergys().size() >= this.amount)
						ObjectHandler.getEnemy().getPoke().costEnergy(amount);
					else
						ObjectHandler.getEnemy().getPoke().setEnergys(new ArrayList<Energy>());					return;
				}
				break;

		

			default:
				break;
			}
			return;

		}

	}

}
