package sjsu.ShahRathore.cs146.project3;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintStream;

/**
 * Implements the basic requirements of a rectangular maze generator.Subclasses provide a specific generation algorithm.
 */
public abstract class MazeGenerator 
{
	ArrayList<String> al = new ArrayList<String>();
	static {

		Class[] c = new Class[] 
				{
				RecursiveBacktrackerMazeGenerator.class
				};
	}

	public static final int UP    = 0;
	public static final int RIGHT = 1;
	public static final int DOWN  = 2;
	public static final int LEFT  = 3;
	private int width;
	private int height;
	private boolean[] horizWalls;
	private boolean[] vertWalls;

	/**
	 * A convenience structure that represents one cell.  It contains a cell's coordinates.
	 */
	protected static class Cell 
	{
		protected int x;
		protected int y;

		/**
		 * Creates a new cell object having the given coordinates.
		 *
		 * @param x the cell's X-coordinate
		 * @param y the cell's Y-coordinate
		 */
		protected Cell(int x, int y) 
		{
			this.x = x;
			this.y = y;
		}

		public String toString() 
		{
			return "(" + x + ", " + y + ")";
		}
	}

	/**
	 * Create a new maze generator.  The height and width in cells is specified.
	 *
	 * @param width the maze width, in cells
	 * @param width the maze height, in cells
	 * @throws IllegalArgumentException if either size non-positive.
	 */
	protected MazeGenerator(int width, int height)
	{
		if (width <= 0 || height <= 0) 
		{
			throw new IllegalArgumentException("Size must be positive");
		}
		this.width = width;
		this.height = height;
		horizWalls = new boolean[width * (height + 1)];
		vertWalls  = new boolean[(width + 1) * height];
		reset();
	}

	/**
	 * Resets the maze.
	 */
	public final void reset() 
	{
		Arrays.fill(horizWalls, true);
		Arrays.fill(vertWalls, true);
	}

	/**
	 * Generates the maze.
	 */
	public final void generate() 
	{
		reset();
		generateMaze();
	}

	/**
	 * Generates the maze using a specific algorithm.
	 */
	protected abstract void generateMaze();

	/**
	 * Checks the direction, and throws an <code>IllegalArgumentException</code>
	 * if it is invalid.
	 * @param direction the direction value to check
	 * @throws IllegalArgumentException if the direction value is invalid.
	 */
	private static void checkDirection(int direction) 
	{
		switch (direction)
		{
		case UP:
		case RIGHT:
		case DOWN:
		case LEFT:
			break;
		default:
			throw new IllegalArgumentException("Bad direction: " + direction);
		}
	}
	
	/**
	 * Checks that the given cell location is valid.
	 *
	 * @param x the cell's X-coordinate
	 * @param y the cell's Y-coordinate
	 * @throws IndexOutOfBoundsException if the coordinate is out of range.
	 */
	protected void checkLocation(int x, int y) 
	{
		if (x < 0 || width <= x) 
		{
			throw new IndexOutOfBoundsException("X out of range: " + x);
		}
		if (y < 0 || height <= y)
		{
			throw new IndexOutOfBoundsException("Y out of range: " + y);
		}
	}
	
	/**
	 * Carves a path in the given direction from the given cell.
	 *
	 * @param x the starting cell's X-coordinate
	 * @param y the starting cell's Y-coordinate
	 * @param direction the direction to carve
	 * @return whether the wall existed and was removed.  If the wall was already gone, then this returns <code>false</code>.
	 * @throws IllegalArgumentException if the direction value is invalid.
	 * @throws IndexOutOfBoundsException if the coordinate is out of range.
	 */
	protected boolean carve(int x, int y, int direction) 
	{
		checkDirection(direction);
		checkLocation(x, y);
		int index = -1;
		boolean[] array = null;
		
		switch (direction) 
		{
		case UP:
			index = y*width + x;
			array = horizWalls;
			break;
		case DOWN:
			index = (y + 1)*width + x;
			array = horizWalls;
			break;
		case LEFT:
			index = y*(width + 1) + x;
			array = vertWalls;
			break;
		case RIGHT:
			index = y*(width + 1) + (x + 1);
			array = vertWalls;
			break;
		}
		boolean b = array[index];
		array[index] = false;
		return b;
	}
	
	/**
	 * Checks if the specified wall is present.
	 *
	 * @param x the starting cell's X-coordinate
	 * @param y the starting cell's Y-coordinate
	 * @param direction the direction to carve
	 * @return whether the specified wall is present.
	 * @throws IllegalArgumentException if the direction value is invalid.
	 * @throws IndexOutOfBoundsException if the coordinate is out of range.
	*/
	public boolean isWallPresent(int x, int y, int direction) 
	{
		checkDirection(direction);
		checkLocation(x, y);
		int index = -1;
		boolean[] array = null;
		
		switch (direction) 
		{
		case UP:
			index = y*width + x;
			array = horizWalls;
			break;
		case DOWN:
			index = (y + 1)*width + x;
			array = horizWalls;
			break;
		case LEFT:
			index = y*(width + 1) + x;
			array = vertWalls;
			break;
		case RIGHT:
			index = y*(width + 1) + (x + 1);
			array = vertWalls;
			break;
		}
		return array[index];
	}
	
	/**
	 * Gets the maze width, in cells.
	 * @return the maze width in cells.
	 */
	public int getWidth() 
	{
		return width;
	}
	
	/**
	 * Gets the maze height, in cells.
	 * @return the maze height in cells.
	 */
	public int getHeight() 
	{
		return height;
	}
	
	/**
	 * Prints the maze.  The following characters are used for each part.
	 * @param out the target {@link PrintStream}
	 */
	public void print(PrintStream out) 
	{
		String maze = "";
		for (int y = 0; y < height; y++) 
		{
			int rowBase = y * width;
			for (int x = 0; x < width; x++) 
			{
				maze = maze +'+';
				maze = maze +(horizWalls[rowBase + x]  ? '-' : ' ');
			}
			maze  = maze + '+';
			al.add(maze);
			maze = "";
			rowBase = y*(width + 1);
			for (int x = 0; x < width; x++)
			{
				maze = maze +(vertWalls[rowBase + x] ? '|' : ' ');
				maze = maze + ' ';
			}
			maze = maze + (vertWalls[rowBase + width] ? '|' : ' ');
			al.add(maze);
			maze = "";
		}
		maze = "";
		int rowBase = height * width;
		for (int x = 0; x < width; x++) 
		{
			maze = maze +'+';
			maze = maze +(horizWalls[rowBase + x] ? '-' : ' ');
		}
		maze = maze +'+';
		al.add(maze);
	}
}