package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack <T> implements Stack<T>{
	private T[] elements;
	private int size;
	private final int INITIAL_SIZE = 10;
	
	/**
	 * initialize an empty stack
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		size = 0;
		elements = (T[])(new Object[INITIAL_SIZE]);
	}
	
	/**
	 * initialize a stack with specified values
	 * @param items
	 */
	public ArrayStack(T[] items) {
		this();
		for (T item : items) {
			push(item);
		}
	}
	
	/**
	 * add the element at the end of array;
	 */
	@Override
	public void push(T item) {
		if (size >= elements.length) {
			expandCapacity();
		}
		elements[size++] = item;
	}
	
	/**
	 * expand internal array member variable
	 */
	@SuppressWarnings("unchecked")
	private void expandCapacity() {
		T[] newElements = (T[])(new Object[size * 2]);
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
	
	/**
	 * remove the item at the top of this stack
	 * @return removed item
	 * @throws NoSuchElementException if this stack is empty
	 */
	@Override
	public T pop() {
		if (size <= 0) {
			throw new NoSuchElementException();
		}
		T poppedItem = elements[size - 1];
		elements[size - 1] = null;
		size--;
		return poppedItem;
	}
	
	/**
	 * return the item at the top of this stack
	 * @return
	 */
	@Override
	public T peek() {
		if (size <= 0) {
			throw new NoSuchElementException();
		}
		return elements[size - 1];
	}
	
	/**
	 * 
	 * @return {@code true} if internal array is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 
	 * @return the size of internal array
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * remove all the items from this stack
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		elements = (T[])(new Object[INITIAL_SIZE]);
	}
	
	/**
	 * 
	 * @param target
	 * @return {@code true} if this stack have the equivalent item
	 */
	@Override
	public boolean contains(T target) {
		for (T item: elements) {
			if (item == target) {
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ArrayStack)) {
			return false;
		}
		
		ArrayStack<T> target = (ArrayStack<T>)obj;
		for (int i = 0; i < size; i++) {
			if (elements[i] != target.elements[i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		String contents = "";
		for (int i = size - 1; i >= 0; i--) {
			contents += elements[i];
			if (i > 0) {
				contents += ", ";
			}
		}
		return contents;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * the inner Iterator class to iterate over the stack
	 */
	class Itr implements Iterator<T> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
