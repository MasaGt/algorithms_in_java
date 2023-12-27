package queue;

import java.util.ArrayList;
import java.util.Iterator;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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
