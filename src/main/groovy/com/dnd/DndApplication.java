package com.dnd;

import com.dnd.dto.Actor;
import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.dto.monster.Goblin;
import com.dnd.util.SetupCharacter;

import java.util.ArrayList;

public class DndApplication
{
	private static PlayerCharacter getStartingCharacter()
	{
		PlayerCharacter playerCharacter;
		while(true)
		{
			System.out.println(
					"Greetings Adventurer! What Character would you like to be?\n\n" +
							"Your choices are:\n" +
							"\tfighter\n\n" +
							"Type in one of the above choices and press enter."
			);

			playerCharacter = SetupCharacter.setupDefaultCharacterByClass("fighter");//Input.readInput());
			if (playerCharacter == null)
				System.out.println("Option unavailable... Please try again.");
			else
				return playerCharacter;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("hello world!");

		ArrayList<Actor> playerCharacters = new ArrayList<>();

		if (System.getProperty("premade", "false").equals("true"))
			playerCharacters.add(SetupCharacter.setupcharacter());
		else
			playerCharacters.add(getStartingCharacter());

		ArrayList<Actor> monsters = new ArrayList<>();
		monsters.add(new Goblin("goblin1"));
		monsters.add(new Goblin("goblin2"));

		Game game = new Game(playerCharacters, monsters);

		//combat
		game.marchingOrder();
		game.rollForInitiative();
		game.combat();

	}
}
