package com.pokemon.Card;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class CardFactory {

	//create a pokemon 
	public Card createCard(String name, CardType type, CardCategory level, int deck, String evolution, int HP,
			GenericAbility[] ability, String retreatCost, CardCategory attr) {
			return new Pokemon("/deck" + deck + "/" + name + ".png", name, level, HP, evolution, ability, retreatCost, attr);
	}

	// create a energy
	public Card createCard(String name, CardType type, CardCategory level, int deck) {
		if (level == CardCategory.Colorless) {
			return new Energy("/Colorless.png", level);
		}
			return new Energy("/deck" + deck + "/" + name + ".png", level);
	}

	// create a trainer
	public Card createCard(String name, CardType cardType, CardCategory cardCategory, int deck, GenericAbility ability) {
			return new Trainer("/deck" + deck + "/" + name + ".png", cardType, cardCategory, ability, name);
	}

}
