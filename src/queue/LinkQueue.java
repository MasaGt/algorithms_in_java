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
		return elements.contains(target);
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean equals(Object obj) {
		//type check
		if (!(obj instanceof Queue)) return false;
		if (this == obj) return true;
		
		Queue<T> target = (Queue<T>) obj;
		//size check
		if (this.size() != target.size()) return false;
		
		//contents check
		Iterator<T> targetItr = target.iterator();
		Iterator<T> itr = iterator();
		while (itr.hasNext()) {
			if (!itr.next().equals(targetItr.next())) {
				return false;
			}
		}
		return true;
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
		return elements.iterator();
	}
}
