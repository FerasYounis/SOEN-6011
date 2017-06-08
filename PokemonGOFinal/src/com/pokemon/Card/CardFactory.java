package com.pokemon.Card;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class CardFactory {
	// create a pokemon
	public Card createCard(String name, CardType type, CardCategory level, int deck, String evolution, int HP,
			String ability1, int attackHit1, String ability2, int attackHit2) {
			return new Pokemon("/deck" + deck + "/" + name + ".png", level, HP, evolution, ability1, attackHit1, ability2,
					attackHit2, name);
	}
	//create a pokemon overload
	public Card createCard(String name, CardType type, CardCategory level, int deck, String evolution, int HP,
			Ability[] ability, String retreatCost, CardCategory attr) {
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
	public Card createCard(String name, CardType cardType, CardCategory cardCategory, int deck, Ability ability) {
			return new Trainer("/deck" + deck + "/" + name + ".png", cardType, cardCategory, ability, name);
	}

}
