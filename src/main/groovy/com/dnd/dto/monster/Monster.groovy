package com.dnd.dto.monster

import com.dnd.dto.Actor
import com.dnd.dto.combat.Attack
import com.dnd.dto.combat.CombatActions
import com.dnd.dto.combat.Move
import com.dnd.enums.Action

abstract class Monster extends Actor
{
	CombatActions combatActions

	abstract int rollForInitiative();
	abstract Action determineAction(char[][] map, List<Actor> combatants);
	abstract Attack attack(char[][] map, List<Actor> combatants);
	abstract int damage();
	abstract Move move(char[][] map, List<Actor> combatants);
}
