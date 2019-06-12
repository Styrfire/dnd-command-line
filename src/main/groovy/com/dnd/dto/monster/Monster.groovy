package com.dnd.dto.monster

import com.dnd.dto.Actor
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

class Monster extends Actor {
	String name
	int ac
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
}
