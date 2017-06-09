package com.pokemon.Abilities;

public abstract class Abilities {
	protected String target;
	protected int amount;
	
	public void turn(String target){}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	};
	
	public void turn(int benchNum){}
}
