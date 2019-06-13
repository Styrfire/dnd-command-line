package com.dnd.dto.monster

import com.dnd.dto.playerCharacter.PlayerCharacter
import com.dnd.dto.weapon.Scimitar
import com.dnd.dto.weapon.Weapon
import com.dnd.util.Dice

class Goblin extends Monster {
	Weapon currentWeapon

	public Goblin()
	{
		name = "goblin"
		currHp = 7
		maxHp = 7
		ac = 15
		strength = 8
		dexterity = 14
		constitution = 10
		intelligence = 10
		wisdom = 8
		charisma = 8
		currentWeapon = new Scimitar()
	}

	static boolean attack(PlayerCharacter defender)
	{
		//attack role plus bonus to attack
		int attackRole = Dice.rollDice(20) + 4

		return attackRole >= defender.getAc()
	}

	int damage()
	{
		int damage = 0
		for (int i = 0; i < currentWeapon.getDamage().length; i++)
			damage += Dice.rollDice(currentWeapon.getDamage()[i])

		return damage + 2
	}
}
