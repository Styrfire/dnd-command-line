package com.dnd.util;

import com.dnd.dto.playerCharacter.PlayerCharacter;
import com.dnd.dto.playerCharacter.Fighter;
import com.dnd.dto.weapon.Greatsword;
import com.dnd.dto.weapon.Longsword;
import com.dnd.enums.Race;

public class SetupCharacter
{
	public static PlayerCharacter setupCharacterByClass(String playerClass)
	{
		PlayerCharacter playerCharacter = new PlayerCharacter();

		switch (playerClass.toLowerCase()) {
			case "fighter":

				playerCharacter.setName("Tuuk-Tuuk");
				playerCharacter.setPlayerClass(new Fighter());
				playerCharacter.setRace(Race.HUMAN);
				playerCharacter.setLevel(1);
				playerCharacter.setCurrHp((playerCharacter.getPlayerClass().getHitDie()/2) + 1);
				playerCharacter.setMaxHp((playerCharacter.getPlayerClass().getHitDie()/2) + 1);
				playerCharacter.setAc(10);
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

	public static PlayerCharacter setUpCharacter()
	{
		return null;
	}
}
