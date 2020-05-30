package com.dnd;

import com.dnd.dto.Actor;
import com.dnd.dto.combat.Attack;
import com.dnd.dto.monster.Monster;
import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.enums.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
	This class acts as the "Dungeon Master" and controls the fights
 */
class CombatManager
{
	// Key is the unique name of the Actor (ex. Goblin1, Goblin2, Zann...)
	private Map<String, Actor> combatants;
	private List<Actor> initiativeOrder;
	private char[][] map;

	CombatManager(Map<String, Actor> combatants)
	{
		this.combatants = combatants;
		initiativeOrder = new ArrayList<>();
		map = new char[][] {
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		};
	}

	CombatManager(Map<String, Actor> combatants, char[][] map)
	{
		this.combatants = combatants;
		initiativeOrder = new ArrayList<>();
		this.map = map;
	}

	private void updateCombatantsOnMap()
	{
		map = new char[][]{
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		};

		for (Actor actor : initiativeOrder)
			map[actor.getY()][actor.getX()] = actor.getLetterId();

		System.out.println("Current grid (# are actors):");
		for (char[] map_row : map) {
			for (char map_entry : map_row) {
				switch (map_entry) {
					case 0:
						System.out.print("_");
						break;
					case '#':
						System.out.print("#");
						break;
					default:
						System.out.print(map_entry);
				}
			}
			System.out.println();
		}
	}

	private void marchingOrder()
	{
		System.out.println("\nEstablishing marching order!\n");
		//set monsters position
		int i = 0;
		for (Map.Entry<String, Actor> combatant : combatants.entrySet())
		{
			if (combatant.getValue() instanceof Monster)
			{
				if (((i * 2) + 2) < 10)
					combatant.getValue().setX((i * 2) + 2);

				combatant.getValue().setY(1);

				System.out.println(combatant.getKey() + " is set at x = " + combatant.getValue().getX() + " and y = " + combatant.getValue().getY());
				i++;
			}
			else if (combatant.getValue() instanceof PlayerCharacter)
			{
				if (((i*2) + 3) < 10)
					combatant.getValue().setX((i*2) + 2);

				combatant.getValue().setY(9);

				System.out.println(combatant.getKey() + " is set at x = " + combatant.getValue().getX() + " and y = " + combatant.getValue().getY());
				i++;
			}
			else
			{
				System.out.println("Combatant " + combatant.getKey() + "is not a Monster or PlayerCharacter!");
			}
		}

		System.out.println("============================================");
	}

	private void rollForInitiative()
	{
		System.out.println("\nTime to roll for initiative!\n");
		//add combatants to initiative
		for (Map.Entry<String, Actor> combatant : combatants.entrySet())
		{
			combatant.getValue().setInitiative(combatant.getValue().rollForInitiative());
			//insert actor at bottom of list
			initiativeOrder.add(combatant.getValue());

			for (int i = initiativeOrder.size() -2; i >= 0; i--)
			{
				if (combatant.getValue().getInitiative() > initiativeOrder.get(i).getInitiative())
				{
					Actor temp = initiativeOrder.get(i);
					initiativeOrder.set(i, combatant.getValue());
					initiativeOrder.set(i+1, temp);
				}
				else
					break;
			}
		}

		System.out.println("============================================");
	}

	private boolean aPlayerCharacterIsAlive()
	{
		for (Actor actor: initiativeOrder)
		{
			if (actor instanceof PlayerCharacter)
				if (actor.getCurrHp() > 0)
					return true;
		}

		return false;
	}

	private boolean aMonsterIsAlive()
	{
		for (Actor actor: initiativeOrder)
		{
			if (actor instanceof Monster)
				if (actor.getCurrHp() > 0)
					return true;
		}

		return false;
	}

	void combat()
	{
		// establish marching order
		marchingOrder();

		// role for initiative
		rollForInitiative();

		// place combatants on map
		updateCombatantsOnMap();

		System.out.println("\nLet the battle begin!");
		int i = 0;
		Action action;
		while (aPlayerCharacterIsAlive() && aMonsterIsAlive())
		{
//			System.out.println("i = " + i);
			System.out.println("\nIt's " + initiativeOrder.get(i).getName() + "'s turn!");
			do
			{
				action = initiativeOrder.get(i).determineAction(map, initiativeOrder);
				switch (action)
				{
					case ATTACK:
						Attack attack = initiativeOrder.get(i).attack(map, initiativeOrder);
						if (attack.getAttackRoll() >= combatants.get(attack.getTarget()).getAc())
						{
							int damage = initiativeOrder.get(i).damage();
							combatants.get(attack.getTarget()).setCurrHp(combatants.get(attack.getTarget()).getCurrHp() - damage);
							System.out.println(initiativeOrder.get(i).getName() + " hit " + attack.getTarget() + " for " + damage + " damage!");
						}
						break;
					case MOVE:
						initiativeOrder.get(i).move(map, initiativeOrder);
						break;
					case END:
						break;
					default:
						break;
				}
			} while (action != Action.END);

			//check if anyone has died
			for (int j = 0; j < initiativeOrder.size(); j++)
			{
				if (initiativeOrder.get(j).getCurrHp() <= 0)
				{
					initiativeOrder.remove(j);
					if (i > j)
						i--;
					j--;
				}
			}

			updateCombatantsOnMap();

			//end of round statistics!
			System.out.println("\nEND OF TURN STATISTICS\n");

			for (Actor actor : initiativeOrder)
			{
				System.out.println(actor.getName() + " has " + actor.getCurrHp() + " hit points!");
			}
			System.out.println("============================================");

			i += 1;
			if (i >= initiativeOrder.size())
				i = 0;
		}
	}
}
