package queue;


public interface Queue<T> {
	
	/**
	 * add new item at the end of this queue
	 * @param item
	 */
	public void enqueue(T item);
	
	/**
	 * return and remove the item at the head of this queue
	 * @return
	 */
	public T dequeue();
	
	/**
	 * return the item at the head of this queue
	 * @return
	 */
	public T peek();
	
	/**
	 * return the size of this queue
	 */
	public int size();
	
	/**
	 * remove all the items from this queue
	 */
	public void clear();
}
