package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

	private T[] items;
	private final int INIT_SIZE = 10;
	private int size;
	private int headIndex;
	private int tailIndex;
	
	/**
	 * initialize an empty queue
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		items = (T[])new Object[INIT_SIZE];
		size = 0;
		headIndex = 0;
		tailIndex = 0;
	}
	/**
	 * initialize an queue based on the arguments
	 * @param params
	 */
	public ArrayQueue(T[] params) {
		this();
		for (T item: params) {
			enqueue(item);
		}
	}
	
	@Override
	public void enqueue(T item) {
		if (size >= items.length) {
			expandCapacity();
		}
		int insertIndex = (headIndex + size) % items.length; 
		items[insertIndex] = item;
		size++;
		tailIndex++;
	}
	
	/**
	 * create new larger array that is two times longer than the inner array (items)
	 * and replace the inner array with it
	 */
	private void expandCapacity() {
		@SuppressWarnings("unchecked")
		T[] newArray =(T[]) new Object[items.length * 2];
		Iterator<T> itr = iterator();
		int cursor = 0;
		while (itr.hasNext()) {
			newArray[cursor++] = itr.next();
		}
		headIndex = 0;
		tailIndex = cursor;
		size = tailIndex;
		items = newArray;
	}
	
	/**
	 * return the size of this queue
	 * @return
	 */
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		String contents = "";
		for (int i = 0; i < size; i++) {
			// put , between items
			contents += i != (size - 1) ? items[i] + ", " : items[i];
		}
		return contents;
	}
	
	/**
	 * return an iterator instance
	 * @return
	 */
	public Iterator<T> iterator() {
		return new Itr();
	}
	
	/**
	 * the inner Iterator class to iterate over the queue
	 */
	private class Itr implements Iterator<T> {
		
		// current pointer to the item of items
		private int cursor;
		// the number of items remains
		private int remaining;

		public Itr() {
			cursor = headIndex;
			remaining = size();
		}
		
		/**
		 * 
		 * @return true if there is a next item
		 */
		@Override
		public boolean hasNext() {
			return remaining > 0;
		}

		/**
		 * 
		 * @return next item of this queue
		 */
		@Override
		public T next() {
			if (remaining <= 0) {
				throw new NoSuchElementException();
			}
			remaining--;
			cursor = (cursor)%items.length;
			return items[cursor++];
		}
	}
}
