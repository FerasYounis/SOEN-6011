package com.pokemon.Strategies;

import com.pokemon.Card.Card;
import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;
import com.pokemon.Enums.CardCategory;
import com.pokemon.Enums.CardType;
import com.pokemon.Main.Enemy;
import com.pokemon.Main.GameInterface;
import com.pokemon.Main.ObjectHandler;
import com.pokemon.Main.Player;

public class StatusAI implements Strategy {
	private Enemy enemy;
	private Player player;
	private boolean hasHandBasic;
	private boolean hasHandStageone;
	private boolean hasEnergy;

	public StatusAI() {
		enemy = ObjectHandler.getEnemy();
		player = ObjectHandler.getPlayer();
		hasHandBasic = false;
		hasHandStageone = false;
		hasEnergy = false;
	}

	@Override
	public void turn() {
		if (GameInterface.turn == 1) {
			checkHandBasic();
			if (hasHandBasic) {
				enemy.setPoke(getHandBasic());
				enemy.getHand().remove(getHandBasic());
			} else {
				enemy.shuffleDeck();
				turn();
			}
		}
		if (GameInterface.turn > 1) {
			enemy.getHand().add(enemy.drawOneCard());
			checkHandBasic();
			
			if(enemy.getPoke() == null && enemy.getBench().size() > 0){
				for(Pokemon p: enemy.getBench()){
					if(p.getEnergys().size() > 0){
						enemy.setPoke(p);
						enemy.getBench().remove(p);
						enemy.getPoke().costEnergy(1);
					}
				}
			}
			
			
			if (hasHandBasic && enemy.getBench().size() < 5) {
				enemy.getBench().add(getHandBasic());
				enemy.getHand().remove(getHandBasic());
			}

			while (checkHandEnergy()) {
				if (hasEnergy && enemy.getPoke() != null && enemy.getPoke().getEnergys().size() < 4) {
					enemy.getPoke().addEnergy(getHandEnergy());
					enemy.getHand().remove(getHandEnergy());
				}
			}

			if (enemy.getPoke() != null) {
				Pokemon p = enemy.getPoke();
				if (p.validateAttackExist(enemy.getPoke().getAbility2().getName())) {
					enemy.getPoke().attackPlayer(2);
					if (player.checkKnockout()) {
						player.getGraveyard().add(player.getPoke());
						player.setPoke(null);
						enemy.getHand().add(enemy.getPrize().get(enemy.getPrize().size() - 1));
						enemy.getPrize().remove(enemy.getPrize().size() - 1);
					}
				} else if (p.validateAttackExist(enemy.getPoke().getAbility1().getName())) {
					enemy.getPoke().attackPlayer(1);
					if (player.checkKnockout()) {
						player.getGraveyard().add(player.getPoke());
						player.setPoke(null);
						enemy.getHand().add(enemy.getPrize().get(enemy.getPrize().size() - 1));
						enemy.getPrize().remove(enemy.getPrize().size() - 1);
					}
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

	public Pokemon getHandBasic() {
		for (Card c : enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.Basic) {
				hasHandBasic = false;
				return (Pokemon) c;
			}
		}
		return null;
	}

	public void checkHandStageone() {
		for (Card c : enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.StageOne)
				hasHandStageone = true;
		}
	}

	public Pokemon getHandStageone() {
		for (Card c : enemy.getHand()) {
			if (c.getCardCategory() == CardCategory.StageOne) {
				hasHandStageone = false;
				return (Pokemon) c;
			}
		}
		return null;
	}

	public boolean checkHandEnergy() {
		for (Card c : enemy.getHand()) {
			if (c.getCardType() == CardType.Engergy)
				hasEnergy = true;
		}
		return hasEnergy;
	}

	public Energy getHandEnergy() {
		for (Card c : enemy.getHand()) {
			if (c.getCardType() == CardType.Engergy) {
				hasEnergy = false;
				return (Energy) c;
			}
		}
		return null;
	}

}
