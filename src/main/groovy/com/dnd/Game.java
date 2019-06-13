package com.dnd;

import com.dnd.dto.Actor;
import com.dnd.dto.monster.Monster;
import com.dnd.dto.playerCharacter.PlayerCharacter;

import java.util.ArrayList;

class Game {
	private ArrayList<Actor> playerCharacters;
	private ArrayList<Actor> monsters;
	private ArrayList<Actor> initiativeOrder;

	Game(ArrayList<Actor> playerCharacters, ArrayList<Actor> monsters)
	{
		this.playerCharacters = playerCharacters;
		this.monsters = monsters;
		initiativeOrder = new ArrayList<>();
	}

	void rollForInitiative()
	{
		//add monsters to initiative
		for (Actor actor : monsters)
		{
			actor.setInitiative(actor.rollForInitiative());
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
			actor.setInitiative(actor.rollForInitiative());
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
		for (Actor playerCharacter: initiativeOrder)
		{
			if (playerCharacter.getCurrHp() > 0)
				return true;
		}

		return false;
	}

	void combat()
	{
		int i = 0;
		while (playerCharacterAlive() && initiativeOrder.size() > 1)
		{
			System.out.println("i = " + i);
			System.out.println("It's " + initiativeOrder.get(i).getName() + "'s turn!");
			switch (initiativeOrder.get(i).getAction())
			{
				case ATTACK:
					if (initiativeOrder.get(i) instanceof Monster)
					{
						for (int i1 = 0; i1 < initiativeOrder.size(); i1++)
						{
							Actor defender = initiativeOrder.get(i1);
							if (defender instanceof PlayerCharacter)
							{
								if (initiativeOrder.get(i).attack(defender))
								{
									defender.setCurrHp(defender.getCurrHp() - 5);
									initiativeOrder.get(i).damage(defender);
									if (defender.getCurrHp() <= 0)
									{
										initiativeOrder.remove(i1);
										if (i > i1)
											i--;

									}
								}
								break;
							}
						}
					}
					else
					{
						for (int i1 = 0; i1 < initiativeOrder.size(); i1++)
						{
							Actor defender = initiativeOrder.get(i1);
							if (defender instanceof Monster)
							{
								if (initiativeOrder.get(i).attack(defender))
								{
									defender.setCurrHp(defender.getCurrHp() - 5);
									initiativeOrder.get(i).damage(defender);
									if (defender.getCurrHp() <= 0)
									{
										initiativeOrder.remove(i1);
										if (i > i1)
											i--;

									}
								}
								break;
							}
						}
					}
					break;
				case MOVE:
				default:
					break;
			}

			//end of round statistics!
			System.out.println("END OF ROUND STATISTICS\n" +
					"============================================");

			for (int j = 0; j < initiativeOrder.size(); j++)
			{
				System.out.println(initiativeOrder.get(j).getName() + " has " + initiativeOrder.get(j).getCurrHp() + " hit points!");
			}

			i += 1;
			if (i >= initiativeOrder.size())
				i = 0;
		}
	}
}
