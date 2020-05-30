package com.dnd.dto.monster

import com.dnd.AStar
import com.dnd.dto.Actor
import com.dnd.dto.combat.Attack
import com.dnd.dto.combat.CombatActions
import com.dnd.dto.combat.Move
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
		this("Goblin")
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
		letterId = 'g'
		strength = 8
		dexterity = 14
		constitution = 10
		intelligence = 10
		wisdom = 8
		charisma = 8
		this.currentWeapon = weapon
		combatActions = new CombatActions(1, 1, moveSpeed)
	}

	int rollForInitiative()
	{
		int initiative = Dice.rollDice(20) + AbilityHelper.abilityMod(dexterity)
		System.out.println(name + " rolled a " + initiative + " for initiative!")

		return initiative
	}

	Action determineAction(char[][] map, List<Actor> combatants)
	{
		Actor enemy = findClosestEnemy(map, combatants)

		if (enemyInRange(map, enemy) && combatActions.getCurrActions() > 0)
		{
			System.out.println(name + " is going to attack!")
			combatActions.setCurrMovement(0)
			return Action.ATTACK
		}
		else if (combatActions.getCurrMovement() > 0)
		{
			System.out.println(name + " is going to move!")
			return Action.MOVE
		}
		else
		{
			System.out.println(name + " is ending their turn!")
			return Action.END
		}
	}

	Attack attack(char[][] map, List<Actor> combatants)
	{
		//attack role plus bonus to attack
		int attackRole = Dice.rollDice(20) + 4
		Actor enemy = findClosestEnemy(map, combatants)
		System.out.println(name + " rolled a " + attackRole + " to attack " + enemy.getName() + "!")

		return new Attack(enemy.getName(), attackRole)
	}

	int damage()
	{
		int damage = 0
		//weapon damage
		for (int i = 0; i < currentWeapon.getDamage().length; i++)
			damage += Dice.rollDice(currentWeapon.getDamage()[i])
		//goblin bonus damage
		damage += 2
		System.out.println(name + " rolled a " + damage + "for damage!")
		return damage
	}

	Move move(char[][] map, List<Actor> combatants)
	{
		AStar aStar = new AStar(map, x, y)

		Actor enemy = findClosestEnemy(map, combatants)
		List<AStar.Node> path = aStar.findPathTo(enemy.getX(), enemy.getY())

		// find closest spot to enemy that doesn't exceed moveSpeed
		for (int i = 0; i < path.size() - 2; i++)
		{
			if (path.get(path.size() - 2 - i).g <= moveSpeed)
			{
				// set x and y to the patch right next to the target (not on top of)
				x = path.get(path.size() - 2 - i).x
				y = path.get(path.size() - 2 - i).y
				combatActions.setCurrMovement(combatActions.getCurrMovement() - path.get(path.size() - 2 - i).g)
				System.out.println(name + " moved " + path.get(path.size() - 2 - i).g + " feet from location [" + path.get(0).x + ", " + path.get(0).y + "] to location [" + x + ", " + y + "]!")
				break
			}
		}

		return new Move(x, y)
	}

	Actor findClosestEnemy(char[][] map, List<Actor> combatants)
	{
		Actor closestEnemy = null
		for (Actor actor : combatants)
		{
			if (actor instanceof PlayerCharacter) {
				if (closestEnemy != null)
				{
					AStar aStar = new AStar(map, x, y)
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

	boolean enemyInRange(char[][] grid, Actor enemy)
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

	void moveNextToEnemy(char[][] grid, Actor enemy)
	{
		AStar aStar = new AStar(grid, x, y)
		List<AStar.Node> path = aStar.findPathTo(enemy.getX(), enemy.getY())

		// find closest spot to enemy that doesn't exceed moveSpeed
		for (int i = 0; i < path.size() - 2; i++)
		{
			if (path.get(path.size() - 2 - i).g <= moveSpeed)
			{
				// set x and y to the patch right next to the target (not on top of)
				x = path.get(path.size() - 2 - i).x
				y = path.get(path.size() - 2 - i).y
				System.out.println(name + " moved " + path.get(path.size() - 2 - i).g + " feet from location [" + path.get(0).x + ", " + path.get(0).y + "] to location [" + x + ", " + y + "]!")
				break
			}
		}
	}
}
