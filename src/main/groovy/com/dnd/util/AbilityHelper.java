package com.dnd.util;

public class AbilityHelper {
	public static int abilityMod(int abilityScore)
	{
		int results = (abilityScore - 10)/2;

		if (results >= 0)
			return results;
		else
			return 0;
	}
}
