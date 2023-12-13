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
			// TODO: replace return with expandCapacity()
			return;
		}
		items[size] = item;
		size++;
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
