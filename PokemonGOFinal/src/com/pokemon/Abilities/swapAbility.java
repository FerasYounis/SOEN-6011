package com.pokemon.Abilities;

import com.pokemon.Card.Pokemon;
import com.pokemon.Main.Game;
import com.pokemon.Main.ObjectHandler;

public class swapAbility extends Abilities{
	private String source;
	
	public swapAbility(String line){
		String[] datas = line.split(":");
		if(line.contains("choice")){
			this.target = datas[0];
			this.source = datas[2] + ":" + datas[3];
		}
		
	}
	
	public void turn(String enemy){
		if("player".equals(enemy)){
			if( ObjectHandler.getEnemy().getPoke() != null &&  ObjectHandler.getEnemy().getBench() != null){
				Pokemon p = ObjectHandler.getEnemy().getPoke();
				ObjectHandler.getEnemy().setPoke(ObjectHandler.getEnemy().getBench().get(0));
				ObjectHandler.getEnemy().getBench().set(0, p);
				 
			}
		}
		
		if("enemy".equals(enemy)){
			Game.gameInterface.setChoiceAbilities(this);
			Game.gameInterface.setChoiceRange(source);
			
			
		}
		
		
	}
	
	
	public void turn(int benchNum){
		
		Pokemon p = ObjectHandler.getPlayer().getPoke();
		p.setStatus(Pokemon.Status.normal);
		ObjectHandler.getPlayer().setPoke(ObjectHandler.getPlayer().getBench().get(benchNum));
		ObjectHandler.getPlayer().getBench().set(benchNum, p);
		
	}
	
	
}
