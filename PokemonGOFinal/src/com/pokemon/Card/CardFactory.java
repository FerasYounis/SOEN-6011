package com.pokemon.Card;

import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;

public class CardFactory {
	// create a pokemon
	public Card createCard(String name, CardType type, CardCategory level, int deck, String evolution, int HP,
			String ability1, int attackHit1, String ability2, int attackHit2) {
		if (1 == deck) {
			return new Pokemon("/deck1/" + name + ".png", level, HP, evolution, ability1, attackHit1, ability2,
					attackHit2, name);
		} else {
			return new Pokemon("/deck2/" + name + ".png", level, HP, evolution, ability1, attackHit1, ability2,
					attackHit2, name);
		}
	}

	public Card createCard(String name, CardType type, CardCategory level, int deck, String evolution, int HP,
			Ability[] ability, String retreatCost, CardCategory attr) {
		if (1 == deck) {
			return new Pokemon("/deck1/" + name + ".png", name, level, HP, evolution, ability, retreatCost, attr);
		} else {
			return new Pokemon("/deck2/" + name + ".png", name, level, HP, evolution, ability, retreatCost, attr);
		}
	}

	// create a energy
	public Card createCard(String name, CardType type, CardCategory level, int deck) {
		if (level == CardCategory.Colorless) {
			return new Energy("/Colorless.png", level);
		}

		if (1 == deck) {
			return new Energy("/deck1/" + name + ".png", level);
		}

		else {
			return new Energy("/deck2/" + name + ".png", level);
		}

	}

	// create a trainer
	public Card createCard(String name, CardType cardType, CardCategory cardCategory, int deck, String abilityName,
			int abilityAttackHP) {
		if (1 == deck) {
			return new Trainer("/deck1/" + name + ".png", cardType, cardCategory, abilityName, abilityAttackHP);
		} else {
			return new Trainer("/deck2/" + name + ".png", cardType, cardCategory, abilityName, abilityAttackHP);
		}
	}

}
