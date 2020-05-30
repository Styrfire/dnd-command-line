package com.dnd.dto.playerCharacter

import com.dnd.AStar
import com.dnd.dto.Actor
import com.dnd.dto.combat.Attack
import com.dnd.dto.combat.Move
import com.dnd.dto.monster.Monster
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
		System.out.println(name + " rolled a " + initiative + " for initiative!")

		return initiative
	}

	Action determineAction(char[][] map, List<Actor> combatants)
	{
		return Action.ATTACK
	}

	Attack attack(char[][] map, List<Actor> combatants)
	{
		//attack role plus bonus to attack
		int attackRole = Dice.rollDice(20) + 4
		Actor enemy = null
		for (Actor defender : combatants)
		{
			if (defender instanceof Monster)
			{
				enemy = defender
				break
			}
		}
		System.out.println(name + " rolled a " + attackRole + " to attack " + enemy.getName() + "!")

		return new Attack(enemy.getName(), attackRole)
	}

	int damage()
	{
		int damage = 0
		//weapon damage
		for (int i = 0; i < currentWeapon.getDamage().length; i++)
			damage += Dice.rollDice(currentWeapon.getDamage()[i])
		//bonus damage
		damage += AbilityHelper.abilityMod(strength)
		System.out.println(name + " rolled a " + damage + " for damage!")
		return damage
	}

	Move move(char[][] map, List<Actor> combatants)
	{
		return new Move(x, y)
	}

}
