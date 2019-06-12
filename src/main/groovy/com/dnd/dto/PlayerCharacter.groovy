package com.dnd.dto

import com.dnd.dto.playerClass.PlayerClass
import com.dnd.enums.Race
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

class PlayerCharacter extends Actor {
	String name
	PlayerClass playerClass
	Race race
	int level
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
