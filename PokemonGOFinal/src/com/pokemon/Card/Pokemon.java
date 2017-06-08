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

public class Pokemon extends Card {
	private final String name;
	private final int HP;
	private final Ability ability1, ability2;
	private int currentHP;

	public static enum Status {
		normal, asleep, paralyzed, stuck, poisoned
	}

	private Status status;
	private ArrayList<Button> ability;
	private String basicName; // to indicate the name of this. basic pokemon
	private CardCategory stage;
	private String retreatCost;
	private CardCategory attr;
	private boolean attackable;
	private boolean retreatable;

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

		// this.ability1 = new Ability(ability1, attackHit1, array1);
		// this.ability2 = new Ability(ability2, attackHit2, array2); // if not
		// // exists,
		// // put 0, 0
		if (level == CardCategory.Basic) {
			this.ability1 = new Ability("ability1", 10, array1);
			this.ability2 = new Ability("ability2", 30, array2);
		} else {
			this.ability1 = new Ability("ability1", 30, array1);
			this.ability2 = new Ability("ability2", 50, array2);
		}

		this.stage = level;
		this.basicName = null;
		if (level == CardCategory.StageOne) {
			this.basicName = evolution;
		}
		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();
		this.ability = new ArrayList<Button>();
		// if (!ability1.equals("0")) {
		this.ability.add(new Button(220, 620, 50, "ability1", Color.WHITE, new Color(49, 156, 12)));
		// }
		// if (!ability2.equals("0")) {
		this.ability.add(new Button(220, 700, 50, "ability2", Color.WHITE, new Color(49, 156, 12)));
		// }

		this.status = Status.normal;
		this.retreatable = true;
		this.attackable = true;
	}

	public Pokemon(String url, String name, CardCategory level, int HP, String evolution, Ability[] ability,
			String retreatCost, CardCategory attr) {
		super(url, CardType.Pokemon, level);
		this.name = name;
		this.HP = HP;
		this.attr = attr;

		Energy[] array1 = new Energy[1];
		array1[0] = ObjectHandler.colorless;
		Energy[] array2 = new Energy[3];
		array2[0] = ObjectHandler.colorless;
		array2[1] = ObjectHandler.colorless;
		array2[2] = ObjectHandler.colorless;

		if (level == CardCategory.Basic) {
			this.ability1 = new Ability("ability1", 10, array1);
			this.ability2 = new Ability("ability2", 30, array2);
		} else {
			this.ability1 = new Ability("ability1", 30, array1);
			this.ability2 = new Ability("ability2", 50, array2);
		}

		this.stage = level;
		this.basicName = null;
		if (level == CardCategory.StageOne) {
			this.basicName = evolution;
		}
		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();

		this.ability = new ArrayList<Button>();
		this.ability.add(new Button(220, 620, 50, "ability1", Color.WHITE, new Color(49, 156, 12)));
		this.ability.add(new Button(220, 700, 50, "ability2", Color.WHITE, new Color(49, 156, 12)));
		this.retreatCost = retreatCost; // if the pokemon cannot be retreated,
										// put null
		this.status = Status.asleep;
		this.retreatable = true;
		this.attackable = true;
	}

	public void addEnergy(Energy e) {
		this.energys.add(e);
		Graphics g2d = this.cardImage.getGraphics();
		g2d.drawImage(e.getIcon(), 50 * (energys.size() - 1), 342, 50, 50, null);

	}

	public ArrayList<Card> costEnergy(int cost) {
		ArrayList<Card> list = new ArrayList<Card>();
		for (int i = 0; i < cost; i++) {
			list.add(this.energys.get(this.energys.size() - 1));
			this.energys.remove(this.energys.size() - 1);
		}
		setEnergys(this.energys);
		return list;
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
			System.out.println(pokemonTarget.getCurrentHP());
			break;

		case 2:
			// if (validateAttackExist(ability2.getName()) == false) {
			// break;
			// } //
			damegAfterHit = pokemonTarget.getCurrentHP() - ability2.getAttackHit();
			pokemonTarget.setCurrentHP(damegAfterHit);
			System.out.println(pokemonTarget.getCurrentHP());
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public String getBasicName() {
		return basicName;
	}

	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}

	public CardCategory getStage() {
		return stage;
	}

	public void setStage(CardCategory stage) {
		this.stage = stage;
	}

	public String getRetreatCost() {
		return retreatCost;
	}

	public void setRetreatCost(String retreatCost) {
		this.retreatCost = retreatCost;
	}

	public CardCategory getAttr() {
		return attr;
	}

	public void setAttr(CardCategory attr) {
		this.attr = attr;
	}

	public boolean evolve(Pokemon p) {
		if (this.basicName.equals(p.getName()))
			return true;
		return false;

	}

	public boolean isAttackable() {
		return attackable;
	}

	public void setAttackable(boolean attackable) {
		this.attackable = attackable;
	}

	public boolean isRetreatable() {
		return retreatable;
	}

	public void setRetreatable(boolean retreatable) {
		this.retreatable = retreatable;
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

	public boolean attackButton(Button b) {
		if (b.getText().equals(ability1.getName())) {
			if (ability1.checkCost(this) && ObjectHandler.getEnemy().getPoke() != null) {
				attack(1, ObjectHandler.getEnemy().getPoke());
				System.out.println("ability1 success attack!");
				System.out.println("Player's discards Num: " + ObjectHandler.getPlayer().getGraveyard().size());
				return true;

			}
		} else {
			if (ability2.checkCost(this) && ObjectHandler.getEnemy().getPoke() != null) {
				attack(2, ObjectHandler.getEnemy().getPoke());
				System.out.println("ability2 success attack!");
				return true;
			}
		}
		return false;
	}

	public void attackPlayer(int attackAbility) {
		Pokemon p = ObjectHandler.player.getPoke();

		if (p != null) {
			int damegAfterHit;
			switch (attackAbility) {
			case 1:
				if (ability1.checkCost(this)) {
					damegAfterHit = p.getCurrentHP() - ability1.getAttackHit();
					p.setCurrentHP(damegAfterHit);
					System.out.println(p.getCurrentHP());
					break;
				}
			case 2:
				if (ability2.checkCost(this)) {
					damegAfterHit = p.getCurrentHP() - ability2.getAttackHit();
					p.setCurrentHP(damegAfterHit);
					System.out.println(p.getCurrentHP());
					break;
				}
			}
		}
	}

	public void checkStatus() {

		if (status == Status.normal) {
			this.retreatable = true;
			this.attackable = true;
		}
		if (status == Status.paralyzed) {
			this.retreatable = false;
			this.attackable = false;
		}
		if (status == Status.asleep) {
			this.retreatable = false;
			this.attackable = false;
		}
		if (status == Status.stuck) {
			this.retreatable = false;
			this.attackable = true;
		}
		if (status == Status.poisoned) {
			this.retreatable = true;
			this.attackable = true;
		}

	}

	public void turn() {
		if (status == Status.paralyzed) {
			status = Status.normal;
		}
		if (status == Status.stuck) {
			status = Status.normal;
		}
		if (status == Status.asleep) {
			if(Math.random() * 2 > 1)
				status = Status.normal;
		}
		if (status == Status.poisoned) {
			this.currentHP--;
		}
		
		
	}

}
