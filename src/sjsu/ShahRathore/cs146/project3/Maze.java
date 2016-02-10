package sjsu.ShahRathore.cs146.project3;

import java.util.Scanner;

/**
 * The class with the main method
 */
public class Maze 
{
	public static void main(String[] arg)
	{
		int dimension = 0;
		System.out.println("Enter the size of the maze: ");
		Scanner in = null;
		try
		{
			in = new Scanner(System.in);
			String input = in.next();
			dimension = Integer.parseInt(input);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		MazeGenerator m = new RecursiveBacktrackerMazeGenerator(dimension,dimension);
		m.generate();
		m.print(System.out);
		int i = 0;
		while(m.al.size() != i)
		{
			System.out.println(m.al.get(i));
			i++;
		}
		MazeSolveBFS bfs = new MazeSolveBFS(m.al,(2*dimension)+1,(2*dimension)+1);
		bfs.solve();
		System.out.println("BFS: ");
		System.out.println(bfs); 
	}
}