package com.dnd;

import com.dnd.dto.Actor;

import java.util.ArrayList;

class Game {
	private ArrayList<Actor> playerCharacters;
	private ArrayList<Actor> monsters;
	private ArrayList<Actor> initiativeOrder;

	Game(ArrayList<Actor> playerCharacters, ArrayList<Actor> monsters)
	{
		this.playerCharacters = playerCharacters;
		this.monsters = monsters;
	}

	void rollForInitiative()
	{
		//add monsters to initiative
		for (Actor actor : monsters)
		{
			actor.setInitiative(actor.getInitiative());
			//insert actor at bottom of list
			initiativeOrder.add(actor);

			for (int i = initiativeOrder.size() -2; i >= 0; i--)
			{
				if (actor.getInitiative() > initiativeOrder.get(i).getInitiative())
				{
					Actor temp = initiativeOrder.get(i);
					initiativeOrder.set(i, actor);
					initiativeOrder.set(i+1, temp);
				}
				else
					break;
			}
		}

		//add playerCharacters to initiative
		for (Actor actor : playerCharacters)
		{
			actor.setInitiative(actor.getInitiative());
			//insert actor at bottom of list
			initiativeOrder.add(actor);

			for (int i = initiativeOrder.size() -2; i >= 0; i--)
			{
				if (actor.getInitiative() > initiativeOrder.get(i).getInitiative())
				{
					Actor temp = initiativeOrder.get(i);
					initiativeOrder.set(i, actor);
					initiativeOrder.set(i+1, temp);
				}
				else
					break;
			}
		}
	}

	private boolean playerCharacterAlive()
	{
		for (Actor playerCharacter: playerCharacters)
		{
			if (playerCharacter.getCurrHp() > 0)
				return true;
		}

		return false;
	}

	void combat()
	{
		while (playerCharacterAlive() && initiativeOrder.size() > 1)
		{
			int i = 0;
//			initiativeOrder.get(i).getAction();
		}
	}
}
