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
	public T pop() throws NoSuchElementException, UnsupportedOperationException {
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
