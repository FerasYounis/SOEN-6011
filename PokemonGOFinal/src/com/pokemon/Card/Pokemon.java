package com.pokemon.Card;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.pokemon.Abilities.Abilities;
import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Button;
import com.pokemon.Main.ImageLoader;
import com.pokemon.Main.ObjectHandler;

public class Pokemon extends Card {
	private final String name;
	private final int HP;
	private final GenericAbility ability1, ability2;
	private int currentHP;
	private boolean isHealed;

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

	
	
	public Pokemon(String url, String name, CardCategory level, int HP, String evolution, GenericAbility[] ability,
			String retreatCost, CardCategory attr) {
		super(url, CardType.Pokemon, level);
		this.name = name;
		this.HP = HP;
		this.attr = attr;
		this.ability1 = ability[0];
		this.ability2 = ability[1];
		this.isHealed = false;

		this.stage = level;
		this.basicName = null;
		if (level == CardCategory.StageOne) {
			this.basicName = evolution;
		}
		this.currentHP = HP;
		this.energys = new ArrayList<Energy>();

		this.ability = new ArrayList<Button>();
		if(ability[0] != null)
			this.ability.add(new Button(220, 620, 50, ability1.getName(), Color.WHITE, new Color(49, 156, 12)));
		if(ability[1] != null)
			this.ability.add(new Button(220, 700, 50, ability2.getName(), Color.WHITE, new Color(49, 156, 12)));
		this.retreatCost = retreatCost; // if the pokemon cannot be retreated,
										// put null
		this.status = Status.normal;
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


	public boolean validateAttackExist(int i) {
		if (1 == i && ability1 == null) {
			return false;
		}
		if (2 == i && ability2 == null) {
			return false;
		}
		return true;
	}

	
	
	public boolean isHealed() {
		return isHealed;
	}


	public void setHealed(boolean isHealed) {
		this.isHealed = isHealed;
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


	public ArrayList<Button> getAbility() {
		return ability;
	}

	public GenericAbility getAbility1() {
		return ability1;
	}


	public GenericAbility getAbility2() {
		return ability2;
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
		if (this.basicName != null && this.basicName.equals(p.getName()))
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
				ability1.turn("enemy");
				return true;
			}
		} else {
			if (ability2.checkCost(this) && ObjectHandler.getEnemy().getPoke() != null) {
				ability2.turn("enemy");
				return true;
				
			}
		}
		return false;
	}

	public boolean attackPlayer(int attackAbility) {
		Pokemon p = ObjectHandler.player.getPoke();

		if (p != null) {
			switch (attackAbility) {
			case 1:
				if (ability != null && ability1.checkCost(this)) {
					System.out.println("enemy: ability1");
					ability1.turn("player");
					return true;
				}
				break;
			case 2:
				if (ability2 != null && ability2.checkCost(this)) {
					System.out.println("enemy: ability2");
					ability2.turn("player");
					return true;
				}
				break;
			}
		}
		return false;
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
