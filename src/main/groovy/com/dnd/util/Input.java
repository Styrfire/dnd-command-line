package com.dnd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input
{
	public static String readInput()
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		try
		{
			input = reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Oops, something went wrong! Could not get user input!");
		}

		return input;
	}
}
