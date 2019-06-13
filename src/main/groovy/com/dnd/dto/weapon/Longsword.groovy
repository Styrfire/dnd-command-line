package com.dnd.dto.weapon

import com.dnd.enums.WeaponCatagory
import com.dnd.enums.WeaponType

class Longsword extends Weapon {
	Longsword()
	{
		name = "longsword"
		type = WeaponType.MELEE
		catagory = WeaponCatagory.Martial
		cost = 1500
		damage = [8]
	}
}
