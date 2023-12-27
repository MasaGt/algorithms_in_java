package queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkQueue<T> implements Queue<T> {

	private ArrayList<T> elements;
	
	/**
	 * initialize an empty queue
	 */
	public LinkQueue() {
		elements = new ArrayList<T>();
	}
	
	/**
	 * initialize a queue with the argument
	 */
	public LinkQueue(T[] values) {
		this();
		for (T val: values) {
			elements.add(val);
		}
	}
	
	@Override
	public void enqueue(T item) {
		elements.add(item);
		
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		return elements.removeFirst();
	}

	@Override
	public T peek() throws NoSuchElementException {
		return elements.getFirst();
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public void clear() {
		elements.clear();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public boolean contains(T target) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		String contents = "";
		
		Iterator<T> itr = elements.iterator();
		while (itr.hasNext()) {
			T value = itr.next();
			contents += itr.hasNext() ? value + ", " : value;
		}
		return contents;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
