package com.dnd.dto.combat

class CombatActions {
	int baseActions
	int currActions
	int currMovement

	CombatActions(int baseActions, int currActions, int currMovement)
	{
		this.baseActions = baseActions
		this.currActions = currActions
		this.currMovement = currMovement
	}
}
