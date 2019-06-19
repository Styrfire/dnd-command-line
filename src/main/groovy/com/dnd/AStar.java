package com.dnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://rosettacode.org/wiki/A*_search_algorithm#Java
//https://www.youtube.com/watch?v=pKnV6ViDpAI
public class AStar
{
	private final List<Node> open;
	private final List<Node> closed;
	private final List<Node> path;
	private final int[][] maze;
	private Node now;
	private final int xstart;
	private final int ystart;
	private int xend, yend;

	// Node class for convienience
	public static class Node implements Comparable {
		public Node parent;
		public int x, y;
		public int g;
		public int h;
		Node(Node parent, int xpos, int ypos, int g, int h) {
			this.parent = parent;
			this.x = xpos;
			this.y = ypos;
			this.g = g; //cost to travel from start node to current node, regardless of obstruction
			this.h = h; //cost to travel from current node to end node, regardless of obstruction
		}
		// Compare by f value (g + h)
		@Override
		public int compareTo(Object o) {
			Node that = (Node) o;
			return (int)((this.g + this.h) - (that.g + that.h));
		}
	}

	public AStar(int[][] maze, int xstart, int ystart) {
		this.open = new ArrayList<>();
		this.closed = new ArrayList<>();
		this.path = new ArrayList<>();
		this.maze = maze;
		this.now = new Node(null, xstart, ystart, 0, 0);
		this.xstart = xstart;
		this.ystart = ystart;
	}
	/*
	 ** Finds path to xend/yend or returns null
	 **
	 ** @param (int) xend coordinates of the target position
	 ** @param (int) yend
	 ** @return (List<Node> | null) the path
	 */
	public List<Node> findPathTo(int xend, int yend) {
		this.xend = xend;
		this.yend = yend;
		this.closed.add(this.now);
		addNeigborsToOpenList();
		while (this.now.x != this.xend || this.now.y != this.yend) {
			if (this.open.isEmpty()) { // Nothing to examine
				return null;
			}
			this.now = this.open.get(0); // get first node (lowest f score)
			this.open.remove(0); // remove it
			this.closed.add(this.now); // and add to the closed
			addNeigborsToOpenList();
		}
		this.path.add(0, this.now);
		while (this.now.x != this.xstart || this.now.y != this.ystart) {
			this.now = this.now.parent;
			this.path.add(0, this.now);
		}
		return this.path;
	}
	/*
	 ** Looks in a given List<> for a node
	 **
	 ** @return (bool) NeightborInListFound
	 */
	private static boolean findNeighborInList(List<Node> array, Node node) {
		return array.stream().anyMatch((n) -> (n.x == node.x && n.y == node.y));
	}
	/*
	 ** Calulate distance between this.now and xend/yend
	 **
	 ** @return (int) distance
	 */
	private int distance(int dx, int dy/*, boolean fiveFootDiagonalUsed*/) {
		if ((dx != 0) && (dy != 0))
			return 10;
		else
			return 5;
	}
	private void addNeigborsToOpenList() {
		Node node;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				node = new Node(this.now, this.now.x + x, this.now.y + y, this.now.g, this.distance(x, y));
				if ((x != 0 || y != 0) // not this.now
						&& this.now.x + x >= 0 && this.now.x + x < this.maze[0].length // check maze boundaries
						&& this.now.y + y >= 0 && this.now.y + y < this.maze.length
						&& this.maze[this.now.y + y][this.now.x + x] != -1 // check if square is walkable
						&& !findNeighborInList(this.open, node) && !findNeighborInList(this.closed, node)) // if not already done
				{
					node.g = node.parent.g + 5; // Horizontal/vertical cost = 5
					node.g += maze[this.now.y + y][this.now.x + x]; // add movement cost for this square
					if (x != 0 && y != 0) // if diagonal
					{
						Node fiveFootDiagonal = now;
						while (fiveFootDiagonal.parent != null)
						{
							// if the first 5 foot diagonal has already happened add the extra 5 feet for the diagonal
							if (((fiveFootDiagonal.x - fiveFootDiagonal.parent.x) != 0) && ((fiveFootDiagonal.y - fiveFootDiagonal.parent.y) != 0))
							{
								node.g += 5; // Diagonal movement cost = 5
								break;
							}
							fiveFootDiagonal = fiveFootDiagonal.parent;
						}


					}

					this.open.add(node);
				}
			}
		}
		Collections.sort(this.open);
	}

	public static void main(String[] args) {
		// -1 = blocked
		// 0+ = additional movement cost
		int[][] maze = {
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,100,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,100,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,100,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,100,100,100,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,100,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
		};
		AStar as = new AStar(maze, 2, 7);
		List<Node> path = as.findPathTo(7, 2);
		if (path != null) {
			path.forEach((n) -> {
				System.out.print("[" + n.x + ", " + n.y + "] ");
				maze[n.y][n.x] = -1;
			});
			System.out.println("\nTotal cost: " + path.get(path.size() - 1).g);

			for (int[] maze_row : maze) {
				for (int maze_entry : maze_row) {
					switch (maze_entry) {
						case 0:
							System.out.print("_");
							break;
						case -1:
							System.out.print("*");
							break;
						default:
							System.out.print("#");
					}
				}
				System.out.println();
			}
		}
	}
}