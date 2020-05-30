package com.dnd.dto

import com.dnd.dto.combat.Attack
import com.dnd.dto.combat.Move
import com.dnd.enums.Action

abstract class Actor
{
	String name
	int currHp
	int maxHp
	int ac
	int moveSpeed
	char letterId
	int strength
	int dexterity
	int constitution
	int intelligence
	int wisdom
	int charisma

	int initiative
	int x
	int y

	abstract int rollForInitiative();
	abstract Action determineAction(char[][] map, List<Actor> combatants);
	abstract Attack attack(char[][] map, List<Actor> combatants);
	abstract int damage();
	abstract Move move(char[][] map, List<Actor> combatants);
}
