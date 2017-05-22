package com.pokemon.Strategies;

import com.pokemon.Card.Card;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.GameInterface;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class StatusAI implements Strategy {
	private Enemy enemy;
	private Player player;
	private boolean hasHandBasic;
	private boolean hasHandStageone;

	public StatusAI() {
		enemy = ObjectHandler.getEnemy();
		player = ObjectHandler.getPlayer();
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
		if (GameInterface.turn > 1) {
			enemy.drawOneCard();
			checkHandBasic();
			if(hasHandBasic && enemy.getBench().size() < 5){
				enemy.getBench().add(getHandBasic());
				enemy.getHand().remove(getHandBasic());
			}else{
				if(hasHandStageone){

					
				}
				
			}
			
			if(enemy.getPoke() != null){
				Pokemon p = enemy.getPoke();
				if(p.validateAttackExist(enemy.getPoke().getAbility1().getName())){
					enemy.getPoke().attackPlayer(1);
				}
				if(p.validateAttackExist(enemy.getPoke().getAbility2().getName())){
					enemy.getPoke().attackPlayer(2);
				}
				
				
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
	
	public void checkHandStageone(){
		for (Card c : enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.StageOne)
				hasHandStageone = true;
		}
	}
	
	public Pokemon getHandStageone(){
		for (Card c: enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.StageOne){
				return (Pokemon)c;
			}
		}
		return null;
	}
	

}
