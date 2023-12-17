package stack;

public interface Stack<T> extends Iterable<T> {
	
	/**
	 * add new item at the top of this stack
	 * @param item
	 */
	public void push(T item);
	
	/**
	 * return and remove the item at the top of this stack
	 * @return item at the top of this stack
	 */
	public T pop();
	
	/**
	 * return the item at the top of this stack
	 * @return item at the top of this stack
	 */
	public T peek();
	
	/**
	 * remove all the items from this stack
	 */
	public void clear();
	
	/**
	 * true if the size of this stack is 0.
	 * @return true if the size is 0. otherwise, false.
	 */
	public boolean isEmpty();
	
	/**
	 * return the size of this stack
	 * @return size of this stack
	 */
	public int size();
	
	/**
	 * check if this stack has a equivalent item to the target.
	 * @param target
	 * @return true if this stack has equivalent item to target. otherwise, false.
	 */
	public boolean contains(T target);
	
	/**
	 * check if all the items of the passed target are equals to all the items of this stack
	 * @param obj
	 * @return true if obj has the same items in the same order as this stack
	 */
	public boolean equals(Object obj);
}
