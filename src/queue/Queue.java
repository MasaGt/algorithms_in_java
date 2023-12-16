package queue;


public interface Queue<T> extends Iterable<T>{
	
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
	
	/**
	 * check if this queue has a equivalent item to the target.
	 * @param target
	 * @return true if this queue has equivalent item to target. otherwise, false.
	 */
	public boolean contains(T target);
	
	/**
	 * check if all the items of the passed queue are equals to all the items of this queue
	 * @param obj
	 * @return true if obj has the same items in the same order as this queue
	 */
	public boolean equals(Object obj);
}
