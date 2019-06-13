package com.dnd.dto.playerCharacter

import com.dnd.dto.Actor
import com.dnd.enums.Action
import com.dnd.enums.Race
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

class PlayerCharacter extends Actor {
	PlayerClass playerClass
	Race race
	int level
	int strength
	int dexterity
	int constitution
	int intelligence
	int wisdom
	int charisma

	int getInitiative()
	{
		int initiative = Dice.rollDice(20) + AbilityHelper.abilityMod(dexterity)
		System.out.println(name + "rolled a " + initiative + "for initiative!")

		return initiative
	}

	Action getAction()
	{
		return Action.ATTACK
	}

	boolean attack(Actor defender)
	{
		int attackRole = Dice.rollDice(20) + AbilityHelper.abilityMod(strength)
		System.out.println(name + "rolled a " + attackRole + " to attack " + defender.getName() + "!")

		if (attackRole >= defender.getAc())
		{
			System.out.println(name + " hit " + defender.getName())
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
		int damage = 2
		System.out.println(name + "dealt " + damage + " to " + defender.getName() + "!")

		return true
	}
}
