package com.pokemon.Card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Button;
import com.pokemon.Main.ImageLoader;
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
		Energy[] array1 = new Energy[1];
		array1[0] = ObjectHandler.colorless;
		Energy[] array2 = new Energy[3];
		array2[0] = ObjectHandler.colorless;
		array2[1] = ObjectHandler.colorless;
		array2[2] = ObjectHandler.colorless;

		this.ability1 = new Ability(ability1, attackHit1, array1);
		this.ability2 = new Ability(ability2, attackHit2, array2); // if not
																	// exists,
																	// put
		// 0, 0
		this.stage = level;
		if (level == CardCategory.StageOne) {
			this.basicName = evolution;
		}
		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();
		this.ability = new ArrayList<Button>();
		if (!ability1.equals("0")) {
			this.ability.add(new Button(220, 620, 50, ability1, Color.WHITE, new Color(49, 156, 12)));
		}
		if (!ability2.equals("0")) {
			this.ability.add(new Button(220, 700, 50, ability2, Color.WHITE, new Color(49, 156, 12)));
		}
	}

	public void addEnergy(Energy e) {
		this.energys.add(e);
		Graphics g2d = this.cardImage.getGraphics();
		g2d.drawImage(e.getIcon(), 50 * (energys.size() - 1), 342, 50, 50, null);

	}

	public void costEnergy(int cost) {
		for (int i = 0; i < cost; i++) {
			this.energys.remove(this.energys.size() - 1);
		}
		setEnergys(this.energys);
	}

	public void attack(int attackAbility, Pokemon pokemonTarget) {

		int damegAfterHit;
		switch (attackAbility) {
		case 1:
			// if (validateAttackExist(ability1.getName()) == false) {
			// break;
			// } //

			damegAfterHit = pokemonTarget.getCurrentHP() - ability1.getAttackHit();
			pokemonTarget.setCurrentHP(damegAfterHit);
			this.costEnergy(ability1.getCost());
			System.out.println(pokemonTarget.getCurrentHP());
			break;

		case 2:
			// if (validateAttackExist(ability2.getName()) == false) {
			// break;
			// } //
			damegAfterHit = pokemonTarget.getCurrentHP() - ability2.getAttackHit();
			pokemonTarget.setCurrentHP(damegAfterHit);
			this.costEnergy(ability2.getCost());
			System.out.println(pokemonTarget.getCurrentHP());
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

	public ArrayList<Energy> getEnergys() {
		return energys;
	}

	public void setEnergys(ArrayList<Energy> energys) {
		if (energys.size() != 0) {
			ArrayList<Energy> list = energys;
			this.energys = new ArrayList<Energy>();
			ImageLoader loader = new ImageLoader();
			BufferedImage bi = loader.load(this.url);
			cardImage = new BufferedImage(245, 342 + 50, BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = cardImage.getGraphics();
			g2.drawImage(bi, 0, 0, 245, 342, null);
			for (Energy e : list) {
				this.addEnergy(e);
			}
			System.out.println("=====" + energys.size());
		} else {
			this.energys = new ArrayList<Energy>();
			ImageLoader loader = new ImageLoader();
			BufferedImage bi = loader.load(this.url);
			cardImage = new BufferedImage(245, 342 + 50, BufferedImage.TYPE_INT_ARGB);
			Graphics g2 = cardImage.getGraphics();
			g2.drawImage(bi, 0, 0, 245, 342, null);
		}

	}

	public void attackButton(Button b) {
		if (b.getText().equals(ability1.getName())) {
			if (ability1.checkCost(this)) {
				attack(1, ObjectHandler.getEnemy().getPoke());
				System.out.println("ability1 success attack!");
			}
		} else {
			if (ability2.checkCost(this)) {
				attack(2, ObjectHandler.getEnemy().getPoke());
				System.out.println("ability2 success attack!");
			}
		}

	}

}
