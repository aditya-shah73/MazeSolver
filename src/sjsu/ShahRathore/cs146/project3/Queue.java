package sjsu.ShahRathore.cs146.project3;

/**
 * The Queue class which is the underlying data structure for breadth first search
 */
public class Queue
{
	protected QCell front;
	protected QCell back;

	/**
	 * Constructor for an empty Queue, in which both the front and the back are null references.
	 */
	public Queue()
	{
		front = null;
		back = null;
	}
	
	/**
	 * Checks if the queue is empty
	 * @return boolean True if queues is empty, False otherwise
	 */
	public boolean is_empty() 
	{
		return (front == null);
	}
	
	/**
	 * 
	 * @param Q
	 * @return
	 */
	public static boolean is_empty(Queue Q) 
	{
		return Q.is_empty();
	}

	/**
	 * 
	 * @param o
	 */
	public void enqueue(Object o)
	{
		QCell newCell = new QCell(o,null);

		if (back == null) {
			back = front = newCell;
		} else {
			back.next = newCell;
			back = newCell;    
		}

	}
	
	/**
	 * 
	 * @param o
	 * @param Q
	 */
	public static void enqueue(Object o, Queue Q)
	{
		Q.enqueue(o);  
	}
	
	/**
	 * 
	 * @return
	 */
	public Object dequeue()
	{
		Object retVal = front.data;
		front = front.next;
		if (front == null) back = null;
		return retVal;
	}
	
	/**
	 * 
	 * @param Q
	 * @return
	 */
	public static Object dequeue(Queue Q)
	{
		return Q.dequeue();  
	}
}