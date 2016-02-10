package sjsu.ShahRathore.cs146.project3;
import java.util.LinkedList;
import java.util.Random;

/**
 * Recursively generates a random maze
 */
public class RecursiveBacktrackerMazeGenerator extends MazeGenerator 
{
	private int startX;
	private int startY;
	private Random rand = new Random();

	/**
	 * Creates a new Recursive Backtracking maze generator.  A random starting location will be selected.
	 * @param width the maze width
	 * @param height the maze height
	 */
	public RecursiveBacktrackerMazeGenerator(int width, int height) 
	{
		super(width, height);
		this.startX = rand.nextInt(width);
		this.startY = rand.nextInt(height);
	}

	/**
	 * Creates a new Recursive Backtracking maze generator.  This uses the
	 * given starting location.
	 * @param width the maze width
	 * @param height the maze height
	 * @param startX the starting X-coordinate
	 * @param startY the starting Y-coordinate
	 */
	public RecursiveBacktrackerMazeGenerator(int width, int height, int startX, int startY) 
	{
		super(width, height);
		checkLocation(startX, startY);
		this.startX = startX;
		this.startY = startY;
	}

	/**
	 * Generate the maze.
	 */
	protected void generateMaze() 
	{
		int width = getWidth();
		int height = getHeight();
		boolean[] cells = new boolean[width * height];
		LinkedList<Cell> stack = new LinkedList<Cell>();
		Cell cell = new Cell(startX, startY);
		stack.addFirst(cell);
		int[] neighbours = new int[4];
		do 
		{
			cells[cell.y*width + cell.x] = true;
			int freeNeighbourCount = 0;
			for (int i = 0; i < 4; i++) 
			{
				switch (i) 
				{
				case UP:
					if (cell.y > 0 && !cells[(cell.y - 1)*width + cell.x]) 
					{
						neighbours[freeNeighbourCount++] = i;
					}
					break;
				case RIGHT:
					if (cell.x < width - 1 && !cells[cell.y*width + (cell.x + 1)]) 
					{;
					neighbours[freeNeighbourCount++] = i;
					}
					break;
				case DOWN:
					if (cell.y < height - 1 && !cells[(cell.y + 1)*width + cell.x]) 
					{
						neighbours[freeNeighbourCount++] = i;
					}
					break;
				case LEFT:
					if (cell.x > 0 && !cells[cell.y*width + (cell.x - 1)]) 
					{
						neighbours[freeNeighbourCount++] = i;
					}
					break;
				}
			}

			if (freeNeighbourCount > 0) 
			{
				stack.addFirst(cell);
				cell = new Cell(cell.x, cell.y);
				switch (neighbours[rand.nextInt(freeNeighbourCount)]) 
				{
				case UP:
					carve(cell.x, cell.y, UP);
					cell.y--;
					break;
				case RIGHT:
					carve(cell.x, cell.y, RIGHT);
					cell.x++;
					break;
				case DOWN:
					carve(cell.x, cell.y, DOWN);
					cell.y++;
					break;
				case LEFT:
					carve(cell.x, cell.y, LEFT);
					cell.x--;
					break;
				}
			} 
			else 
			{
				cell = stack.removeFirst();
			}
		} while (!stack.isEmpty());
	}

	/**
	 * Returns a string representation of this object.
	 * @return a string representation of this object.
	 */
	public String toString() 
	{
		return "Recursive Backtracker maze generator";
	}
}