package queue;


public interface Queue<T> {
	
	/**
	 * add new item at the end of this queue
	 * @param item
	 */
	public void enqueue(T item);
	
	/**
	 * return and remove the item at the head of this queue
	 * @return item at the head of this queue
	 */
	public T dequeue();
	
	/**
	 * return the item at the head of this queue
	 * @return item at the head of this queue
	 */
	public T peek();
	
	/**
	 * return the size of this queue
	 * @return size of this queue
	 */
	public int size();
	
	/**
	 * remove all the items from this queue
	 */
	public void clear();
	
	/**
	 * true if the size of this queue is 0.
	 * @return true if the size is 0. otherwise, false.
	 */
	public boolean isEmpty();
}
