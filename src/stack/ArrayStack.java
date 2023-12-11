package stack;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack <T>{
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
	public void push(T item) {
		if (size >= INITIAL_SIZE) {
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
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 
	 * @return the size of internal array
	 */
	public int size() {
		return size;
	}
	
//	contains, clear, equals書けば終わり
	public void clear() {
		int repeateNum = size;
		for (int i = 0; i < repeateNum; i++) {
			pop();
		}
	}
	
	public boolean contains(T target) {
		for (T item: elements) {
			if (item == target) {
				return true;
			}
		}
		return false;
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
}
