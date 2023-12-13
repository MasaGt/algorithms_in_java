package stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkStack<T> {
	
	private List<T> elements;
	
	/**
	 * initialize an empty stack
	 */
	public LinkStack() {
		elements = new LinkedList<T>();
	}
	
	/**
	 * initialize a stack with the arguments
	 * @param values
	 */
	public LinkStack(T[] values) {
		this();
		for (T val : values) {
			elements.addFirst(val);
		}
	}
	
	/**
	 * push the value at the top of this stack
	 * @param value
	 */
	public void push(T value) {
		elements.addFirst(value);
	}
	
	/**
	 * return and remove the value at the top of this stack
	 * @return 
	 * @throws NoSuchElementException
	 * @throws UnsupportedOperationException
	 */
	public T pop() throws NoSuchElementException {
		return elements.removeFirst();
	}
	
	/**
	 * return the value at the top of this stack but not remove the value
	 * @return
	 * @throws NoSuchElementException
	 */
	public T peek() throws NoSuchElementException {
		return elements.getFirst();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return elements.isEmpty();
	}
	
	/**
	 * remove all the value from this stack
	 */
	public void clear() {
		elements.clear();
	}
	
	/**
	 * return true is passed object is an instance of this class and has equivalent elements 
	 * @param obj another stack instance
	 * @return {@code true} if obj is an instance of this and has equivalent elements
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LinkStack)) {
			return false;
		}
		LinkStack<T> target = (LinkStack<T>)obj;
		return elements.equals(target.elements);
	}
	
	/**
	 * 
	 * @param value
	 * @return {@code true} if this stack has the specified item
	 */
	public boolean contains(T value) {
		return elements.contains(value);
	}
	
	@Override
	public String toString() {
		String contents = "";
		
		Iterator<T> iterator = elements.listIterator();
		while (iterator.hasNext()) {
			T value = iterator.next();
			contents += iterator.hasNext() ? value + ", " : value;
		}
		return contents;
	}
	

}
