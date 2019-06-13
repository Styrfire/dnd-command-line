package com.dnd.dto.weapon

import com.dnd.enums.WeaponCatagory
import com.dnd.enums.WeaponType

class Weapon {
	String name
	WeaponType type
	WeaponCatagory catagory
	int cost
	int[] damage
}
