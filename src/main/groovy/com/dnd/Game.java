//package com.dnd;
//
//import com.dnd.dto.Actor;
//import com.dnd.dto.monster.Monster;
//import com.dnd.dto.playerCharacter.PlayerCharacter;
//
//import java.util.ArrayList;
//
//class Game {
//	private ArrayList<Actor> playerCharacters;
//	private ArrayList<Actor> monsters;
//	private ArrayList<Actor> initiativeOrder;
//	private char[][] grid;
//
//	Game(ArrayList<Actor> playerCharacters, ArrayList<Actor> monsters)
//	{
//		this.playerCharacters = playerCharacters;
//		this.monsters = monsters;
//		initiativeOrder = new ArrayList<>();
//		// -1 = blocked
//		// 0+ = additional movement cost
//		this.grid = new char[][] {
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//		};
//	}
//
//	private void updateActorsOnGrid()
//	{
//		grid = new char[][]{
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
//		};
//
//		for (Actor actor : initiativeOrder)
//			grid[actor.getY()][actor.getX()] = actor.getLetterId();
//
//		System.out.println("Current grid (# are actors):");
//		for (char[] grid_row : grid) {
//			for (char grid_entry : grid_row) {
//				switch (grid_entry) {
//					case 0:
//						System.out.print("_");
//						break;
//					case '#':
//						System.out.print("#");
//						break;
//					default:
//						System.out.print(grid_entry);
//				}
//			}
//			System.out.println();
//		}
//	}
//
//	void marchingOrder()
//	{
//		System.out.println("\nEstablishing marching order!\n");
//		//set monsters position
//		int i = 0;
//		for (Actor actor : monsters)
//		{
//			if (((i*2) + 2) < 10)
//				actor.setX((i*2) + 2);
//
//			actor.setY(1);
//
//			System.out.println(actor.getName() + " is set at x = " + actor.getX() + " and y = " + actor.getY());
//			i++;
//		}
//
//		i = 0;
//		//set playerCharacters position
//		for (Actor actor : playerCharacters)
//		{
//			if (((i*2) + 3) < 10)
//				actor.setX((i*2) + 2);
//
//			actor.setY(9);
//
//			System.out.println(actor.getName() + " is set at x = " + actor.getX() + " and y = " + actor.getY());
//			i++;
//		}
//
//		System.out.println("============================================");
//	}
//
//	void rollForInitiative()
//	{
//		System.out.println("\nTime to roll for initiative!\n");
//		//add monsters to initiative
//		for (Actor actor : monsters)
//		{
//			actor.setInitiative(actor.rollForInitiative());
//			//insert actor at bottom of list
//			initiativeOrder.add(actor);
//
//			for (int i = initiativeOrder.size() -2; i >= 0; i--)
//			{
//				if (actor.getInitiative() > initiativeOrder.get(i).getInitiative())
//				{
//					Actor temp = initiativeOrder.get(i);
//					initiativeOrder.set(i, actor);
//					initiativeOrder.set(i+1, temp);
//				}
//				else
//					break;
//			}
//		}
//
//		//add playerCharacters to initiative
//		for (Actor actor : playerCharacters)
//		{
//			actor.setInitiative(actor.rollForInitiative());
//			//insert actor at bottom of list
//			initiativeOrder.add(actor);
//
//			for (int i = initiativeOrder.size() -2; i >= 0; i--)
//			{
//				if (actor.getInitiative() > initiativeOrder.get(i).getInitiative())
//				{
//					Actor temp = initiativeOrder.get(i);
//					initiativeOrder.set(i, actor);
//					initiativeOrder.set(i+1, temp);
//				}
//				else
//					break;
//			}
//		}
//
//		updateActorsOnGrid();
//		System.out.println("============================================");
//	}
//
//	private boolean aPlayerCharacterIsAlive()
//	{
//		for (Actor actor: initiativeOrder)
//		{
//			if (actor instanceof PlayerCharacter)
//				if (actor.getCurrHp() > 0)
//					return true;
//		}
//
//		return false;
//	}
//
//	private boolean aMonsterIsAlive()
//	{
//		for (Actor actor: initiativeOrder)
//		{
//			if (actor instanceof Monster)
//				if (actor.getCurrHp() > 0)
//					return true;
//		}
//
//		return false;
//	}
//
//	void combat()
//	{
//		System.out.println("\nLet the battle begin!");
//		int i = 0;
//		while (aPlayerCharacterIsAlive() && aMonsterIsAlive())
//		{
////			System.out.println("i = " + i);
//			System.out.println("\nIt's " + initiativeOrder.get(i).getName() + "'s turn!");
//			switch (initiativeOrder.get(i).determineAction())
//			{
//				case ATTACK:
//					if (initiativeOrder.get(i) instanceof Monster)
//					{
//						initiativeOrder.get(i).act(grid, initiativeOrder);
//					}
//					else
//					{
//						for (Actor defender : initiativeOrder)
//						{
//							if (defender instanceof Monster)
//							{
//								if (initiativeOrder.get(i).attack(defender))
//									initiativeOrder.get(i).damage(defender);
//
//								break;
//							}
//						}
//					}
//					break;
//				case MOVE:
//				default:
//					break;
//			}
//
//			//check if anyone has deid
//			for (int j = 0; j < initiativeOrder.size(); j++)
//			{
//				if (initiativeOrder.get(j).getCurrHp() <= 0)
//				{
//					initiativeOrder.remove(j);
//					if (i > j)
//						i--;
//					j--;
//				}
//			}
//
//			updateActorsOnGrid();
//
//			//end of round statistics!
//			System.out.println("\nEND OF ROUND STATISTICS\n");
//
//			for (Actor actor : initiativeOrder)
//			{
//				System.out.println(actor.getName() + " has " + actor.getCurrHp() + " hit points!");
//			}
//			System.out.println("============================================");
//
//			i += 1;
//			if (i >= initiativeOrder.size())
//				i = 0;
//		}
//	}
//}
