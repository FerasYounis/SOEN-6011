package com.pokemon.Card;

import java.awt.Graphics;
import java.util.ArrayList;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Strategies.Strategy;

public class Pokemon extends Card {
	private final int HP;
	private final Ability ability1, ability2;
	private int currentHP;
	private Strategy status;
	// private ArrayList<Button> ability;

	private ArrayList<Energy> energys;

	public Pokemon(String url, CardCategory level, int HP, String ability1, int attackHit1, String ability2,
			int attackHit2) {
		super(url, CardType.Pokemon, level);
		this.HP = HP;
		this.ability1 = new Ability(ability1, attackHit1);
		this.ability2 = new Ability(ability2, attackHit2); // if not exists, put
															// 0, 0

		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();
	}

	public void addEnergy(Energy e) {
		this.energys.add(e);

		Graphics g2d = this.cardImage.getGraphics();
		g2d.drawImage(e.getIcon(), 50 * (energys.size() - 1), 342, 50, 50, null);

	}

	public void attack(int attackAbility, Pokemon pokemonTarget) {

		int damegAfterHit;
		switch (attackAbility) {
		case 1:
			damegAfterHit = pokemonTarget.getCurrentHP() - ability1.getAttackHit();
			pokemonTarget.setCurrentHP(damegAfterHit);
			break;

		case 2:
			if (validateAttackExist(ability2.getName()) == false) {
				break;
			} //
			damegAfterHit = pokemonTarget.getCurrentHP() - ability2.getAttackHit();
			pokemonTarget.setCurrentHP(damegAfterHit);
			break;
		}

	}

	public boolean validateAttackExist(String attackAbility) {
		if (attackAbility.equals("0")) {
			return false;
		}
		return true;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}

	public Strategy getStatus() {
		return status;
	}

	public void setStatus(Strategy status) {
		this.status = status;
	}

	public int getHP() {
		return HP;
	}

	public Ability getAbility1() {
		return ability1;
	}

	public Ability getAbility2() {
		return ability2;
	}

}
