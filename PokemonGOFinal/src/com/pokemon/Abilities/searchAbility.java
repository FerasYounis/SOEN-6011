package com.pokemon.Abilities;

import java.util.Random;

import com.pokemon.Card.Card;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.ObjectHandler;

public class searchAbility extends Abilities {
	private String filter;
	private String source;

	public searchAbility(String line) {
		this.filter = "";
		String[] datas = line.split(":");

		if (!line.contains("filter")) {
			this.target = datas[1];
			this.source = datas[3];
			this.amount = Integer.parseInt(datas[4]);
		} else {

			if (line.contains("pokemon")) {
				if (line.contains("cat:basic")) {
					if (!line.contains("evoloves-from")) {
						this.target = datas[1];
						this.source = datas[3];
						this.amount = Integer.parseInt(datas[8]);
						this.filter = "pokemon:cat:basic";
					}
				} else {
					this.target = datas[1];
					this.source = datas[3];
					this.amount = Integer.parseInt(datas[6]);
					this.filter = "pokemon";
				}
			}
			if (line.contains("type:energy")) {
				this.target = datas[1];
				this.source = datas[3];
				this.amount = Integer.parseInt(datas[7]);
				this.filter = "type:energy";
			}
			if (line.contains("top")) {
				this.target = datas[1];
				this.source = datas[3];
				this.amount = Integer.parseInt(datas[6]);
				this.filter = "top";
			}

		}

	}

	public void turn(String enemy) {
	//	System.out.println(enemy + ": " + amount);
		System.out.println(amount + " " + filter + " is searching...");

		switch (this.filter) {
		case "type:energy":
			if (enemy.equals("player")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getEnemy().getDeck()) {
						if (c.getCardType() == CardType.Engergy) {
							ObjectHandler.getEnemy().getHand().add(c);
							ObjectHandler.getEnemy().getDeck().remove(c);
							break;
						}
					}
				}

			}
			if (enemy.equals("enemy")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getPlayer().getDeck()) {
						if (c.getCardType() == CardType.Engergy) {
							ObjectHandler.getPlayer().getHand().add(c);
							ObjectHandler.getPlayer().getDeck().remove(c);
							break;
						}
					}
				}

			}
			break;

		case "pokemon":
			if (enemy.equals("enemy")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getPlayer().getDeck()) {
						if (c.getCardType() == CardType.Pokemon) {
							ObjectHandler.getPlayer().getHand().add(c);
							ObjectHandler.getPlayer().getDeck().remove(c);
							break;
						}
					}
				}

			}
			if (enemy.equals("player")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getEnemy().getDeck()) {
						if (c.getCardType() == CardType.Pokemon) {
							ObjectHandler.getEnemy().getHand().add(c);
							ObjectHandler.getEnemy().getDeck().remove(c);
							break;
						}
					}
				}

			}
			break;

		case "pokemon:cat:basic":
			System.out.println("searching...");
			if (enemy.equals("enemy")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getPlayer().getDeck()) {
						if (c.getCardCategory() == CardCategory.Basic) {
							ObjectHandler.getPlayer().getHand().add(c);
							ObjectHandler.getPlayer().getDeck().remove(c);
							break;
						}
					}
				}

			}
			if (enemy.equals("player")) {

				for (int i = 0; i < this.amount; i++) {
					for (Card c : ObjectHandler.getEnemy().getDeck()) {
						if (c.getCardCategory() == CardCategory.Basic) {
							ObjectHandler.getEnemy().getHand().add(c);
							ObjectHandler.getEnemy().getDeck().remove(c);
							break;
						}
					}
				}

			}
			break;

		case "top":
			if (enemy.equals("player")) {

				for (int i = 0; i < this.amount; i++) {
					Card c = ObjectHandler.getPlayer().getDeck().get(0);
					ObjectHandler.getEnemy().getHand().add(c);
					ObjectHandler.getPlayer().getDeck().remove(c);

				}

			}
			if (enemy.equals("enemy")) {

				for (int i = 0; i < this.amount; i++) {
					Card c = ObjectHandler.getEnemy().getDeck().get(0);
					ObjectHandler.getPlayer().getHand().add(c);
					ObjectHandler.getEnemy().getDeck().remove(c);

				}

			}
			break;

		case "":
			if (enemy.equals("player")) {
				for (int i = 0; i < this.amount; i++) {
					Card c = ObjectHandler.getEnemy().getDeck()
							.get(new Random().nextInt(ObjectHandler.getEnemy().getDeck().size()));
					ObjectHandler.getEnemy().getHand().add(c);
					ObjectHandler.getEnemy().getDeck().remove(c);

				}

			}

			if (enemy.equals("enemy")) {

				for (int i = 0; i < this.amount; i++) {
					Card c = ObjectHandler.getPlayer().getDeck()
							.get(new Random().nextInt(ObjectHandler.getPlayer().getDeck().size()));
					ObjectHandler.getPlayer().getHand().add(c);
					ObjectHandler.getPlayer().getDeck().remove(c);

				}

			}
			break;

		default:
			break;
		}
		return;

	}

}
