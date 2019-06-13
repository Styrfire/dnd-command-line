package com.dnd.util;

public class Dice {
	public static int rollDice(int sides)
	{
		long dieVal;
		long x = System.nanoTime();
		x ^= (x << 13);
		x ^= (x >>> 7);
		x ^= (x << 17);

		if (x < 0)
			x = x*(-1);

//		System.out.println("x before the mod = " + x);
		dieVal = (x%sides) + 1;
//		System.out.println(sides + "sided die rolled a " + dieVal);
		return (int) dieVal;
	}
}
