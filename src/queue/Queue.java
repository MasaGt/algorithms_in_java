package queue;

public interface Queue<T> {
	
	/**
	 * add new item at the end of this queue
	 * @param item
	 */
	public void enqueue(T item);
}
