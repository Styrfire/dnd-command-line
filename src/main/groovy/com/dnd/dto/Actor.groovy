package com.dnd.dto

import com.dnd.enums.Action

abstract class Actor
{
	String name
	int currHp
	int maxHp
	int ac
	int moveSpeed
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
	abstract Action getAction();
	abstract void act(int[][] grid, List<Actor> actors);
	abstract boolean attack(Actor defender);
	abstract boolean damage(Actor defender);
	//abstract int move();
}
