package com.pokemon.Card;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class Trainer extends Card {
	private Ability ability;
	private String name;
	

	protected Trainer(String url, CardType cardType, CardCategory cardCategory, Ability ability, String name) {
		super(url, CardType.Trainer, cardCategory);
		this.ability = ability;
		this.name = name;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
