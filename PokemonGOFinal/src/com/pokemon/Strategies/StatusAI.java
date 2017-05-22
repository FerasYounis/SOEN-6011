package com.pokemon.Strategies;

import com.pokemon.Card.Card;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.GameInterface;
import com.pokemon.Main.ObjectHandler;

public class StatusAI implements Strategy {
	private Enemy enemy;
	private boolean hasHandBasic;
	private boolean hasHandStageone;

	public StatusAI() {
		enemy = ObjectHandler.getEnemy();
		hasHandBasic = false;
		hasHandStageone = false;
	}

	@Override
	public void turn() {
		if (GameInterface.turn == 1) {
			checkHandBasic();
			if(hasHandBasic){
				enemy.setPoke(getHandBasic()); 
				enemy.getHand().remove(getHandBasic());
			}else{
				enemy.shuffleDeck();
				turn();
			}
				
			
			
			
			
			
			
			
		}
		
		GameInterface.turn++;
		GameInterface.playerTurn = true;

	}

	public void checkHandBasic() {
		for (Card c : enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.Basic)
				hasHandBasic = true;
		}
	}
	
	public Pokemon getHandBasic(){
		for (Card c: enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.Basic){
				return (Pokemon)c;
			}
		}
		return null;
	}
	
	
	
	

}
