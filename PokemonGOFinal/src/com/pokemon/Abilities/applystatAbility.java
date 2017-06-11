package com.pokemon.Abilities;

import com.pokemon.Card.Pokemon;
import com.pokemon.Main.ObjectHandler;

public class applystatAbility extends Abilities {
	private Pokemon.Status status;

	public applystatAbility(String line) {
		String[] datas = line.split(":");
		String st = datas[1];
		if (st.equals("stuck")) {
			this.status = Pokemon.Status.stuck;
		}
		if (st.equals("poisoned")) {
			this.status = Pokemon.Status.poisoned;
		}
		if (st.equals("asleep")) {
			this.status = Pokemon.Status.asleep;
		}
		if (st.equals("paralyzed")) {
			this.status = Pokemon.Status.paralyzed;
		}
		this.target = datas[2];

	}

	public void turn(String enemy) {
		System.out.println(enemy + ": " + amount);

		switch (this.target) {

		case "opponent-active":
			if (enemy.equals("enemy")) {
				ObjectHandler.getEnemy().getPoke().setStatus(this.status);
			}
			if (enemy.equals("player")) {
				ObjectHandler.getPlayer().getPoke().setStatus(this.status);

			}
			break;

		default:
			break;
		}
		return;
	}

}
