package com.dnd.dto.weapon

import com.dnd.enums.WeaponCatagory
import com.dnd.enums.WeaponType

class Greatsword extends Weapon {
	Greatsword()
	{
		name = "greatsword"
		type = WeaponType.MELEE
		catagory = WeaponCatagory.Martial
		cost = 5000
		damage = [6,6]
	}
}
