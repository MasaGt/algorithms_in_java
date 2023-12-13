package queue;

public class ArrayQueue<T> implements Queue<T> {

	private T[] items;
	private int size;
	private final int INIT_SIZE = 10;
	
	/**
	 * initialize an empty queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		items = (T[])new Object[INIT_SIZE];
		size = 0;
	}
	
	public ArrayQueue(T[] paramas) {
		this();
		for (T item: paramas) {
			enqueue(item);
		}
	}
	
	@Override
	public void enqueue(T item) {
		if (size >= items.length) {
			expandCapacity();
		}
		items[size] = item;
		size++;
	}
	
	/**
	 * create new larger array that is two times longer than the inner array (items)
	 * and replace the inner array with it
	 */
	private void expandCapacity() {
		@SuppressWarnings("unchecked")
		T[] newArray =(T[]) new Object[items.length * 2];
		for (int i = 0; i < items.length; i++) {
			newArray[i] = items[i];
		}
		items = newArray;
	}
	
	@Override
	public String toString() {
		String contents = "";
		for (int i = 0; i < size; i++) {
			contents += i != (size - 1) ? items[i] + ", " : items[i];
		}
		return contents;
	}
}
