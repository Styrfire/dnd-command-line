package com.dnd;

import com.dnd.dto.Actor;
import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.dto.monster.Goblin;
import com.dnd.util.SetupCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

			playerCharacter = SetupCharacter.setupCharacterByClass("fighter");//Input.readInput());
			if (playerCharacter == null)
				System.out.println("Option unavailable... Please try again.");
			else
				return playerCharacter;
		}
	}

	public static void main(String[] args)
	{
		System.out.println("hello world!");

//		ArrayList<Actor> playerCharacters = new ArrayList<>();
//		playerCharacters.add(getStartingCharacter());

//		ArrayList<Actor> monsters = new ArrayList<>();
//		monsters.add(new Goblin("goblin1"));
//		monsters.add(new Goblin("goblin2"));

//		Game game = new Game(playerCharacters, monsters);
//
//		//combat
//		game.marchingOrder();
//		game.rollForInitiative();
//		game.combat();

		Map<String, Actor> combatants = new HashMap<>();

		Actor goblin1 = new Goblin("Goblin1");
		combatants.put(goblin1.getName(), goblin1);

		Actor goblin2 = new Goblin("Goblin2");
		combatants.put(goblin2.getName(), goblin2);

		Actor playerCharacter = getStartingCharacter();
		combatants.put(playerCharacter.getName(), playerCharacter);

		CombatManager combatManager = new CombatManager(combatants);

		combatManager.combat();
	}
}
