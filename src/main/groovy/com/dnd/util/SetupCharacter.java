package com.dnd.util;

import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.dto.playerCharacter.Fighter;
import com.dnd.dto.weapon.Longsword;
import com.dnd.enums.Race;

public class SetupCharacter
{
	public static PlayerCharacter setupDefaultCharacterByClass(String playerClass)
	{
		PlayerCharacter playerCharacter = new PlayerCharacter();

		switch (playerClass.toLowerCase())
		{
			case "fighter":

				playerCharacter.setName("Tuuk-Tuuk");
				playerCharacter.setPlayerClass(new Fighter());
				playerCharacter.setRace(Race.HUMAN);
				playerCharacter.setLevel(1);
				playerCharacter.setCurrHp((playerCharacter.getPlayerClass().getHitDie()/2) + 1);
				playerCharacter.setMaxHp((playerCharacter.getPlayerClass().getHitDie()/2) + 1);
				playerCharacter.setAc(18);
				playerCharacter.setMoveSpeed(30);
				playerCharacter.setLetterId('P');
				playerCharacter.setStrength(16);
				playerCharacter.setDexterity(12);
				playerCharacter.setConstitution(14);
				playerCharacter.setIntelligence(8);
				playerCharacter.setWisdom(10);
				playerCharacter.setCharisma(12);

				playerCharacter.setCurrentWeapon(new Longsword());
				System.out.println("You have chosen to be a fighter! You're name is: " + playerCharacter.getName());
				break;
			case "priest":
				break;
			default:
				return null;
		}

		return playerCharacter;
	}

	// use http://chicken-dinner.com/5e/5e-point-buy.html
	// custom rules:
	// Total Pointes = 31
	// Max Purchasable Attribute Before Bonuses: 17
	public static PlayerCharacter setupcharacter()
	{
		PlayerCharacter playerCharacter;
		System.out.println(
				"Greetings Adventurer! First thing's first, what is your name?\n\n" +
						"Type in your character's name and press enter."
		);

		String name = Input.readInput();
		while (name.equals(""))
		{
			System.out.println(
					"I'm sorry, that is not a valid name...\n\n" +
							"Type in your character's name and press enter."
			);

			name = Input.readInput();
		}

		playerCharacter = new PlayerCharacter();
		playerCharacter.setName(name);

		System.out.println(
				name + "? Ah, yes! That is a fine name. Now what race is your adventurer?\n\n" +
						"Your choices are:\n" +
						"\thuman\n\n" +
						"Type in one of the above choices and press enter."
		);

		playerCharacter.setRace(selectRace(Input.readInput()));
		while (playerCharacter.getRace() == null)
		{
			System.out.println(
					"I'm sorry, that is not a valid race...\n\n" +
							"Type in your character's race and press enter."
			);

			playerCharacter.setRace(selectRace(Input.readInput()));
		}

		System.out.println("Character isn't fully created yet!!! Prepare for a null pointer exception!");
		return null;
	}


	private static Race selectRace(String race)
	{
		switch(race.toLowerCase())
		{
			case "human":
				return Race.HUMAN;
			case "elf":
				return Race.ELF;
			default:
				return null;
		}
	}
}
