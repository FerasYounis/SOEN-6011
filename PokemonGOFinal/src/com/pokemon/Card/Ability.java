package com.pokemon.Card;

public class Ability {
	private final String name;
	private final int attackHit;

	public Ability(String name, int attackHit) {
		super();
		this.name = name;
		this.attackHit = attackHit;
	}

	public String getName() {
		return name;
	}

	public int getAttackHit() {
		return attackHit;
	}

}
