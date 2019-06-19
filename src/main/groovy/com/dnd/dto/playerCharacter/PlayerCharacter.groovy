package com.dnd.dto.playerCharacter

import com.dnd.dto.Actor
import com.dnd.dto.weapon.Weapon
import com.dnd.enums.Action
import com.dnd.enums.Race
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

class PlayerCharacter extends Actor
{
	PlayerClass playerClass
	Race race
	int level
	Weapon currentWeapon

	int rollForInitiative()
	{
		int initiative = Dice.rollDice(20) + AbilityHelper.abilityMod(dexterity)
		System.out.println(name + "rolled a " + initiative + " for initiative!")

		return initiative
	}

	Action getAction()
	{
		return Action.ATTACK
	}

	Action getAction(int[][] grid, List<Actor> actors)
	{
		return Action.ATTACK
	}

	boolean attack(Actor defender)
	{
		int attackRole = Dice.rollDice(20) + AbilityHelper.abilityMod(strength)
		System.out.println(name + " rolled a " + attackRole + " to attack " + defender.getName() + "!")

		if (attackRole >= defender.getAc())
		{
			return true
		}
		else
		{
			System.out.println(name + " missed " + defender.getName())
			return false
		}
	}

	boolean damage(Actor defender)
	{
		int damage = 0
		//weapon damage
		for (int i = 0; i < currentWeapon.getDamage().length; i++)
			damage += Dice.rollDice(currentWeapon.getDamage()[i])
		//bonus damage
		damage += AbilityHelper.abilityMod(strength)
		defender.setCurrHp(defender.getCurrHp() - damage)
		System.out.println(name + " hit " + defender.getName() + " for " + damage + " damage!")
		return true
	}
}
