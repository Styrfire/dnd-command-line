package com.dnd.dto.monster

import com.dnd.dto.Actor
import com.dnd.enums.Action
import com.dnd.util.AbilityHelper
import com.dnd.util.Dice

abstract class Monster extends Actor
{
	abstract int rollForInitiative();
	abstract Action getAction();
	abstract boolean attack(Actor defender);
	abstract boolean damage(Actor defender);
}
