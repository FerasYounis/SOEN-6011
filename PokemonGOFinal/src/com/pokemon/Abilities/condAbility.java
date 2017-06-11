package com.pokemon.Abilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import com.pokemon.Main.ObjectHandler;

public class condAbility extends Abilities {
	private Abilities condAbility, elseAbility;
	private String cond;

	public condAbility(String line) {
		super();
		this.condAbility = null;
		this.elseAbility = null;

		boolean flag = false;

		for (char c : line.toCharArray()) {
			if (c == '(')
				flag = true;
		}

		if (line.contains("flip") && !flag) {
			String condA = line.substring(line.indexOf(":") + 1);
			this.cond = line.substring(0, line.indexOf(":"));
			if (!line.contains("else")) {
				String abilityName = condA.substring(0, condA.indexOf(":"));
				String abilityLine = condA.substring(condA.indexOf(":") + 1);
				this.condAbility = createCondAbility(abilityName, abilityLine);
			} else {
				String[] abilities = condA.split(":else:");
				String condAbilityName = abilities[0].substring(0, abilities[0].indexOf(":"));
				String condAbilityLine = abilities[0].substring(abilities[0].indexOf(":") + 1);
				this.condAbility = createCondAbility(condAbilityName, condAbilityLine);
				String elseAbilityName = abilities[1].substring(0, abilities[1].indexOf(":"));
				String elseAbilityLine = abilities[1].substring(abilities[1].indexOf(":") + 1);
				this.elseAbility = createCondAbility(elseAbilityName, elseAbilityLine);
			}
		}

		if (line.contains("healed")) {
			String[] ss = line.split(":");
			this.cond = ss[0].concat(":").concat(ss[1]).concat(":").concat(ss[2]);
			String condA = line.substring(cond.length() + 1);

			if (!line.contains("else")) {
				String abilityName = condA.substring(0, condA.indexOf(":"));
				String abilityLine = condA.substring(condA.indexOf(":") + 1);
				this.condAbility = createCondAbility(abilityName, abilityLine);
			}
		}

	}

	public void turn(String target) {
		if (checkCond(target)) {
			System.out.println("condAbility:");
			this.condAbility.turn(target);
		} else if (this.elseAbility != null) {
			System.out.println("elseAbility:");
			this.elseAbility.turn(target);
		}
		return;
	}

	public boolean checkCond(String target) {
		if (this.cond.equals("flip")) {
			System.out.println("is fliping......");
			return (new Random().nextInt(100) > 50);
		}

		if (this.cond.contains("healed")) {
			String[] conds = cond.split(":");
			switch (conds[2]) {
			case "your-active":
				if ("enemy".equals(target) && ObjectHandler.getPlayer().getPoke().isHealed()) {
					return true;
				}
				if ("player".equals(target) && ObjectHandler.getEnemy().getPoke().isHealed()) {
					return true;
				}
				break;

			default:
				break;
			}

		}

		return false;
	}

	public Abilities createCondAbility(String abilityName, String abilityLine) {
		Object o = null;
		Constructor<?> con = null;
		Class<?> classType = null;
		try {
			classType = Class.forName("com.pokemon.Abilities." + abilityName + "Ability");
			con = classType.getConstructor(String.class);
			o = con.newInstance(abilityLine);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		return (Abilities) o;

	}

}
