package com.pokemon.Card;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class Trainer extends Card {
	private Ability ability;

	protected Trainer(String name, CardType cardType, CardCategory cardCategory, String abilityName,
			int abilityAttackHP) {
		super(name, CardType.Trainer, cardCategory);
		this.ability = new Ability(abilityName, abilityAttackHP, null);
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}

}
