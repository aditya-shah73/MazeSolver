package sjsu.ShahRathore.cs146.project3;
import java.util.ArrayList;

/**
 * Solves the maze using breadth first search
 */
public class MazeSolveBFS 
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
	public MazeSolveBFS(ArrayList<String> info,int h,int w) 
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
		Queue Q = new Queue();
		MazeCell startCell = new MazeCell(startx, starty-1, ' ');
		startCell.Mark();
		Queue.enqueue(startCell, Q);

		while(!Queue.is_empty(Q))
		{
			MazeCell CMC = (MazeCell)Queue.dequeue(Q);

			if(CMC.yValue() == height-2 && CMC.xValue() == width-2)
			{   
				printMark(CMC);
				return;
			}

			if ( !((CMC.yValue() - 1) <= 0) )
			{              
				N = (grid[(CMC.yValue())-1][CMC.xValue()]);
				if ( N.getContents() != '|' && N.getContents() != '-' && N.getContents() != '+' && !(N.isMarked()) )
				{          
					N.Mark();
					CMC.changeParent(N);
					Queue.enqueue( N, Q );
				}
			}

			if ( !((CMC.yValue()) + 1 >= height) )
			{             
				S = (grid[(CMC.yValue())+1][CMC.xValue()]);             
				if ( S.getContents() != '|' && S.getContents() != '-' && S.getContents() != '+' && !(S.isMarked()) )
				{         
					S.Mark();				
					CMC.changeParent(S);
					Queue.enqueue( S, Q );
				}
			}

			if ( !((CMC.xValue()) - 1 <= 0) )
			{                  
				E = (grid[CMC.yValue()][(CMC.xValue())-1]);
				if ( E.getContents() != '|' && E.getContents() != '-'&& E.getContents() != '+' && !(E.isMarked()) )
				{       
					E.Mark();	
					CMC.changeParent(E);
					Queue.enqueue( E, Q );
				}
			}

			if ( !((CMC.xValue()) + 1 >= width) )
			{              
				W = (grid[CMC.yValue()][(CMC.xValue())+1]);
				if ( W.getContents() != '|' && W.getContents() != '-' && W.getContents() != '+'&& !(W.isMarked()) )
				{         
					W.Mark();
					CMC.changeParent(W);
					Queue.enqueue( W, Q );
				}
			}
		}
		System.out.println();
		System.out.println("Maze not solvable!");
		System.out.println();
	}
}