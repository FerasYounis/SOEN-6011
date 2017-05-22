package com.pokemon.Card;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Button;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Strategies.Strategy;

public class Pokemon extends Card {
	private final String name;
	private final int HP;
	private final Ability ability1, ability2;
	private int currentHP;
	private Strategy status;
	private ArrayList<Button> ability;
	private String basicName;
	private CardCategory stage;

	private ArrayList<Energy> energys;

	public Pokemon(String url, CardCategory level, int HP, String evolution, String ability1, int attackHit1,
			String ability2, int attackHit2, String name) {
		super(url, CardType.Pokemon, level);
		this.name = name;
		this.HP = HP;
		this.ability1 = new Ability(ability1, attackHit1);
		this.ability2 = new Ability(ability2, attackHit2); // if not exists, put
															// 0, 0
		this.stage = level;
		if (level == CardCategory.StageOne) {
			this.basicName = evolution;
		}
		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();
		this.ability = new ArrayList<Button>();
		if (!ability1.equals("0")) {
			this.ability.add(new Button(220, 550, 50, ability1, Color.WHITE, new Color(49, 156, 12)));
		}
		if (!ability2.equals("0")) {
			this.ability.add(new Button(220, 630, 50, ability2, Color.WHITE, new Color(49, 156, 12)));
		}
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
			if (validateAttackExist(ability1.getName()) == false) {
				break;
			} //
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

	public String getName() {
		return name;
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

	public ArrayList<Button> getAbility() {
		return ability;
	}

	public void setAbility(ArrayList<Button> ability) {
		this.ability = ability;
	}

	public boolean evolve(Pokemon p) {
		if (this.basicName.equals(p.getName()))
			return true;
		return false;

	}

	public void attackButton(Button b) {
		if (b.equals(ability.get(0))) {
			// attack(1, ObjectHandler.getEnemy().getPoke());
			System.out.println("ability1 success attack!");
		} else {
			// attack(2, ObjectHandler.getEnemy().getPoke());
			System.out.println("ability2 success attack!");
		}

	}

}
