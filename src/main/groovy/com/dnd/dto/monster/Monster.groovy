package com.dnd.dto.monster

import com.dnd.dto.Actor
import com.dnd.enums.Action

abstract class Monster extends Actor
{
	abstract int rollForInitiative();
	abstract Action getAction();
	abstract Action act(int[][] grid, List<Actor> actors);
	abstract boolean attack(Actor defender);
	abstract boolean damage(Actor defender);
}
