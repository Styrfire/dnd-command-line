package com.dnd.dto.monster

import com.dnd.dto.Actor
import com.dnd.dto.weapon.Scimitar
import com.dnd.dto.weapon.Weapon
import com.dnd.enums.Action
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

class Goblin extends Monster {
	Weapon currentWeapon

	Goblin()
	{
		this("goblin")
	}

	Goblin(String name)
	{
		this(name, new Scimitar())
	}

	Goblin(String name, Weapon weapon)
	{
		this.name = name
		currHp = 7
		maxHp = 7
		ac = 15
		moveSpeed = 30
		strength = 8
		dexterity = 14
		constitution = 10
		intelligence = 10
		wisdom = 8
		charisma = 8
		this.currentWeapon = weapon
	}

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
		//attack role plus bonus to attack
		int attackRole = Dice.rollDice(20) + 4
		System.out.println(name + " rolled a " + attackRole + " to attack " + defender.getName() + "!")

		if (attackRole >= defender.getAc())
		{
//			System.out.println(name + " hit " + defender.getName())
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
		//goblin bonus damage
		damage += 2
		defender.setCurrHp(defender.getCurrHp() - damage)
		System.out.println(name + " hit " + defender.getName() + " for " + damage + " damage!")
		return true
	}
}
