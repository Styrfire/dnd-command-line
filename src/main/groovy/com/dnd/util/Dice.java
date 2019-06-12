package com.dnd.util;

public class Dice {
	public static int rollDice(int sides)
	{
		long x = System.nanoTime();
		x ^= (x << 21);
		x ^= (x >>> 35);
		x ^= (x << 4);

		return (int)(x%sides) + 1;
	}
}
