package com.pokemon.Card;

import java.awt.Color;
import java.util.ArrayList;

import com.pokemon.Abilities.GenericAbility;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Button;

public class Trainer extends Card {
	private GenericAbility ability;
	private String name;
	private Button button;

	

	protected Trainer(String url, CardType cardType, CardCategory cardCategory, GenericAbility ability, String name) {
		super(url, CardType.Trainer, cardCategory);
		this.ability = ability;
		this.name = name;
		this.button = new Button(220, 620, 50, ability.getName(), Color.WHITE, new Color(49, 156, 12));
	}

	public GenericAbility getAbility() {
		return ability;
	}

	public void setAbility(GenericAbility ability) {
		this.ability = ability;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
	
	
	
	
}
