package com.dnd.dto.weapon

import com.dnd.enums.WeaponCatagory
import com.dnd.enums.WeaponType

class Scimitar extends Weapon {
	Scimitar()
	{
		name = "scimitar"
		type = WeaponType.MELEE
		catagory = WeaponCatagory.Martial
		cost = 2500
		damage = [6]
	}
}
