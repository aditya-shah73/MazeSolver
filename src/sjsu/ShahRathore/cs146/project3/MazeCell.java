package sjsu.ShahRathore.cs146.project3;

/**
 * The class which constructs the cell of a maze and its properties.
 */
public class MazeCell
{
	private boolean marked;
	private char contents;
	private MazeCell parent;
	private int x;
	private int y;

	/**
	 * The constructor for MazeCells.
	 * @param x The x coordinate of the cell
	 * @param y The y coordinate of the cell
	 * @param c The content of the cell
	 */
	public MazeCell(int x, int y, char c) 
	{
		marked = false;
		contents = c;
		parent =  null;
		this.x = x; 
		this.y = y;
	}

	/**
	 * Returns the contents of the MazeCell 
	 * @return char The content of the cell
	 */
	public char getContents() 
	{
		return this.contents;
	}

	/**
	 * Changes the marked value of a cell
	 */
	public void Mark()
	{
		marked = true;
	}

	/**
	 * Returns the marked value of the MazeCell
	 * @return boolean The marked value
	 */
	public boolean isMarked() 
	{
		return this.marked;
	}

	/**
	 * Returns the parent of the MazeCell
	 * @return MazeCell The parent of the cell
	 */
	public MazeCell getParent() 
	{
		return this.parent;
	}

	/**
	 * Changes the parent value of the MazeCell
	 * @param MazeCell The value to be changed
	 */
	public void changeParent(MazeCell child) 
	{
		child.parent = this;
	}

	/**
	 * Returns the x value of the MazeCell 
	 * @return int The value
	 */
	public int xValue() 
	{
		return this.x;
	}  

	/**
	 * Returns the y value of the MazeCell
	 * @return int The value
	 */
	public int yValue() 
	{
		return this.y;
	}

	/**
	 * Changes the value of the MazeCell to '#' 
	 */
	public void hash() 
	{
		contents = '#';
	}   
}