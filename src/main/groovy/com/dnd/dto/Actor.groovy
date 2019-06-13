package com.dnd.dto

import com.dnd.enums.Action

abstract class Actor
{
	String name
	int ac
	int currHp
	int maxHp
	int initiative

	abstract int rollForInitiative();
	abstract Action getAction();
	abstract boolean attack(Actor defender);
	abstract boolean damage(Actor defender);
}
