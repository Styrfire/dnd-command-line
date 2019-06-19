package com.dnd.dto.monster

import com.dnd.AStar
import com.dnd.dto.Actor
import com.dnd.dto.playerCharacter.PlayerCharacter
import com.dnd.dto.weapon.Scimitar
import com.dnd.dto.weapon.Weapon
import com.dnd.enums.Action
import com.dnd.enums.WeaponType
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

	void act(int[][] grid, List<Actor> actors)
	{
		Actor enemy = findClosestEnemy(grid, actors)

		if (enemyInRange(grid, enemy))
		{
			if (attack(enemy))
				damage(enemy)
		}
		else
		{
			moveNextToEnemy(grid, enemy)
			if (enemyInRange(grid, enemy))
			{
				if (attack(enemy))
					damage(enemy)
			}
			else
			{
				System.out.println(name + " not in range of " + enemy.name + "!")
			}
		}
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

	Actor findClosestEnemy(int[][] grid, List<Actor> actors)
	{
		Actor closestEnemy = null
		for (Actor actor : actors)
		{
			if (actor instanceof PlayerCharacter) {
				if (closestEnemy != null)
				{
					AStar aStar = new AStar(grid, x, y)
					List<AStar.Node> path = aStar.findPathTo(closestEnemy.getX(), closestEnemy.getY())
					List<AStar.Node> path1 = aStar.findPathTo(actor.getX(), actor.getY())

					if ((path1.get(path.size() - 1).g) > (path.get(path.size() - 1).g))
						closestEnemy = actor
				}
				else
					closestEnemy = actor
			}
		}

		System.out.println(getName() + " has aquired target " + closestEnemy.getName() + " at location [" + closestEnemy.getX() + ", " + closestEnemy.getY() + "]!")
		return closestEnemy
	}

	boolean enemyInRange(int[][] grid, Actor enemy)
	{
		if (currentWeapon.type == WeaponType.MELEE)
		{
			AStar aStar = new AStar(grid, x, y)
			List<AStar.Node> path = aStar.findPathTo(enemy.getX(), enemy.getY())
			if (path.get(path.size() - 1).g <= 5)
			{
				System.out.println(name + " is in melee range of " + enemy.name + "!")
				return true
			}
			else
			{
				System.out.println(name + " not in melee range of " + enemy.name + "!")
				return false
			}
		}
		return true
	}

	void moveNextToEnemy(int[][] grid, Actor enemy)
	{
		AStar aStar = new AStar(grid, x, y)
		List<AStar.Node> path = aStar.findPathTo(enemy.getX(), enemy.getY())

		x = path.get(path.size() - 2).x
		y = path.get(path.size() - 2).y

		System.out.println(name + " moved " + path.get(path.size() - 2).g + " feet from location [" + path.get(0).x + ", " + path.get(0).y + "] to location [" + x + ", " + y + "]!")
	}
}
