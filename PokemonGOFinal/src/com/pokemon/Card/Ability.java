package com.pokemon.Card;

public class Ability {
	private final String name;
	private final int attackHit;
	private final Energy[] cost;

	public Ability(String name, int attackHit, Energy[] cost) {
		this.cost = cost;
		this.name = name;
		this.attackHit = attackHit;
	}

	public String getName() {
		return name;
	}

	public int getAttackHit() {
		return attackHit;
	}
	
	public boolean checkCost(){
		
		
		return false;
	}
	
	
	

}
