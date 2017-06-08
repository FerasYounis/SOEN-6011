package com.pokemon.Abilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class condAbility extends Abilities{
	private Abilities condAbility;
	private String cond;
	
	public condAbility(String line){
		super();
		this.condAbility = null;
		String[] datas = line.split(":");
		this.cond = datas[0];
		String condA = line.substring(line.indexOf(":") + 1);
		String abilityName = condA.substring(0, condA.indexOf(":"));
		String abilityLine = condA.substring(condA.indexOf(":") + 1);
		
		Object o = null;
		Constructor<?> con = null;
		Class<?> classType = null;
		try {
			classType = Class.forName("com.pokemon.Abilities." + abilityName + "Ability");
			con = classType.getConstructor(String.class);
			o = con.newInstance(abilityLine);
			this.condAbility = (Abilities)o;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	
	public void turn(String target) {
		if(checkCond() && this.condAbility != null)
			this.condAbility.turn(target);
		return;
	}

	public boolean checkCond(){
		if(this.cond.equals("flip")){
			return Math.random() * 2 > 1;
		}
		
		
		
		return false;
	}
	
	
	
}
