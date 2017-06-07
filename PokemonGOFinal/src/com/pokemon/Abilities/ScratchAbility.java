package com.pokemon.Abilities;

import com.pokemon.Card.IAbility;
import com.pokemon.Main.ObjectHandler;

public class ScratchAbility implements IAbility {

	public String abilityName;
	public String target;
	private String damage;
	public boolean isFlip;


	public ScratchAbility(String parseLine) throws Exception
	{
		if(parseLine.length() > 0)
		{
			String[] partsOfLine = parseLine.split(":");
			abilityName = partsOfLine[0]; //Assign ability name.

			int k=1;
			while(k<partsOfLine.length)
			{
				switch(partsOfLine[k])
				{
				case "target":
					target = partsOfLine[k+1];
					damage = partsOfLine[k+2];
					break;
				}
			}

		} else {
			throw new Exception("Ability Parsing line is missing");
		}
	}

	@Override
	public Boolean runAbility() throws Exception {
		if(target.equals("opponent-active"))
		{
			int currentHP = ObjectHandler.getEnemy().getPoke().getCurrentHP() - Integer.parseInt(damage);
			ObjectHandler.getEnemy().getPoke().setCurrentHP(currentHP);
			return true;
		}
		return false;


	}

}
