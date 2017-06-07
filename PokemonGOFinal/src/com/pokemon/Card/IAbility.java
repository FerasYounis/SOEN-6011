/**
 * 
 */
package com.pokemon.Card;

/**
 * @author Muhammad Umer
 *
 */
public interface IAbility {

	public String abilityName = null;
	public String target = null;
	public boolean isFlip = false;;
	
	
	public Boolean runAbility() throws Exception;
	
	
}
