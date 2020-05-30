package com.dnd.dto.combat

class Attack {
	String target
	int attackRoll

	Attack(String target, int attackRoll)
	{
		this.target = target
		this.attackRoll = attackRoll
	}
}
