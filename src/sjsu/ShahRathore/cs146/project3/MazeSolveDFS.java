package sjsu.ShahRathore.cs146.project3;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Solves the maze using depth first search 
 */
public class MazeSolveDFS 
{
	private MazeCell[][] grid;
	private int width;
	private int height;
	private int startx;
	private int starty;
	private int spamx;
	private int spamy;

	/** 
	 * This constructor takes in the name of one of the above statically defined String arraylist and creates the maze represented in the specified arraylist.
	 */
	public MazeSolveDFS(ArrayList<String> info,int h,int w) 
	{
		height  = h; 
		width = w;
		grid = new MazeCell[height][width];
		char c;
		
		for (int i=0 ; i<height ; ++i) 
		{
			for (int j=0 ; j<width ; ++j) 
			{
				c = info.get(i).charAt(j);
				grid[i][j] = new MazeCell(j,i,c);
				startx = 1; 
				starty = 1;
				spamx = h-2;
				spamy = w-2;
			}
		}
	}

	/**
	 * The printMark method changes contents of the MazeCell to '#' here invoked Queue is shortest path.
	 */
	public void printMark(MazeCell pathCell) 
	{
		pathCell.hash();
		while (pathCell.getParent() != null) 
		{               
			pathCell = pathCell.getParent();
			pathCell.hash();
		}
	}   

	/**
	 * The toString method allows a user to print
	 * a Maze Object with the System.out.println command
	 */
	public String toString()
	{
		StringBuffer theFullAnswer = new StringBuffer();
		theFullAnswer.append("\n");
		for (int i=0 ; i<height ; ++i) 
		{
			for (int j=0 ; j<width ; ++j) 
			{
				theFullAnswer.append(String.valueOf(grid[i][j].getContents()));
			}
			theFullAnswer.append("\n");
		}
		theFullAnswer.append("\n");
		return theFullAnswer.toString();
	}

	/**
	 * BFS routine 
	 * Using Queue, this solve() method is doing BFS.
	 * enqueue all children and dequeue them until it gets final point.
	 */
	public void solve() 
	{
		
		MazeCell N, S, E, W;
		Stack<Object> s = new Stack<Object>();
		MazeCell startCell = new MazeCell(startx, starty-1, ' ');
		startCell.Mark();
		s.push(startCell);
		while(!s.isEmpty())
		{
			
		}
		System.out.println();
		System.out.println("Maze not solvable!");
		System.out.println();
	}
}
