package com.dnd.dto.playerCharacter

import com.dnd.enums.Ability

class Fighter extends PlayerClass {
	int profBonus
	String[] features

	public Fighter()
	{
		name = "fighter"
		hitDie = 10
		primaryAbility = [Ability.STRENGTH]
		savingThrowProf = [Ability.STRENGTH, Ability.CONSTITUTION]
		profBonus = 2
		features = ["fighting style", "second wind"]
	}
}
