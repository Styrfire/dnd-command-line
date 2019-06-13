package com.dnd;

import com.dnd.dto.Actor;
import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.dto.monster.Goblin;
import com.dnd.dto.monster.Monster;
import com.dnd.util.Input;
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

			playerCharacter = SetupCharacter.setupClass(Input.readInput());
			if (playerCharacter == null)
				System.out.println("Option unavailable...");
			else
				return playerCharacter;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("hello world!");

		PlayerCharacter playerCharacter = getStartingCharacter();
		Monster goblin = new Goblin();

		ArrayList<Actor> playerCharacters = new ArrayList<>();
		playerCharacters.add(playerCharacter);

		ArrayList<Actor> monsters = new ArrayList<>();
		playerCharacters.add(goblin);

		Game game = new Game(playerCharacters, monsters);

		//combat
		game.rollForInitiative();
		game.combat();

	}
}
