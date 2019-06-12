package com.dnd.dto

abstract class Actor
{
	int currHp
	int maxHp
	int initiative

	abstract int getInitiative();
}
