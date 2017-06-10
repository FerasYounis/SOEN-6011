package com.pokemon.Abilities;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.pokemon.Card.Energy;
import com.pokemon.Card.Pokemon;

public class GenericAbility {
	private final String name;
	private final Energy[] cost;
	private ArrayList<Abilities> ability;

	public GenericAbility(String line, Energy[] cost) {
		this.cost = cost;
		this.name = line.substring(0, line.indexOf(":"));

		String restLine = line.substring(line.indexOf(":") + 1);
		ability = new ArrayList<Abilities>();

		String[] abilities = restLine.split(",");
		for (String s : abilities) {
			String name = s.substring(0, s.indexOf(":"));
			Object o = null;
			Constructor<?> con = null;
			Class<?> classType = null;

			try {
				classType = Class.forName("com.pokemon.Abilities." + name + "Ability");
				con = classType.getConstructor(String.class);
				o = con.newInstance(s.substring(s.indexOf(":") + 1));
				//System.out.println(o instanceof Abilities);
				ability.add((Abilities) o);

			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e1) {
				ability.add(new drawAbility("1"));
				//e1.printStackTrace();
			}

			catch (Exception e) {
				e.printStackTrace();
			}


		}

	}

	public String getName() {
		return name;
	}

	public boolean checkCost(Pokemon p) {
		if (p.getEnergys().size() >= cost.length)
			return true;

		return false;
	}

	public int getCost() {
		return cost.length;
	}

	public void turn(String target) {
		for (Abilities a : ability) {
			a.turn(target);
		}

	}



}
