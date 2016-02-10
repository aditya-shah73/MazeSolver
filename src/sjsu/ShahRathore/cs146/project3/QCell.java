package sjsu.ShahRathore.cs146.project3;

/**
 * The cell of a queue
 */
public class QCell
{
	protected Object data;          
	protected QCell next;         

	/**
	 * Constructor requiring the Object to be stored and the QCell that the next field refers to.
	 */
	public QCell(Object o, QCell next)
	{
		this.data = o;
		this.next = next;  
	}
}