package list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
	
	// inner node
	private class Node<T> {
		
		private T value;
		private Node<T> next;
		
		public Node(T vale) {
			this.value = vale;
			next = null;
		}
	}
	
	private Node<T> headNode;
	private Node<T> tailNode;
	private int size = 0;
	

	/**
	 * initialize a empty list
	 */
	public SinglyLinkedList() {
		headNode = null;
		tailNode = null;
	}

	/**
	 * initialize a list with the passed array
	 * @param values
	 */
	public SinglyLinkedList(T[] values) {
		for (T value : values) {
			add(value);
		}
	}
	
	/**
	 * this follows the header(signature + result) of List class. 
	 * this method always returns true.
	 * @param value
	 * @return true
	 */
	public boolean add(T value) {
		if (headNode == null) {
			addFirst(value);
		} else {
			addLast(value);
		}
		return true;
	}
	
	/**
	 * 
	 * @param value
	 * @return true
	 */
	public boolean addFirst(T value) {
		// when a list is empty
		if (headNode == null && tailNode == null) {
			populateFirstNode(value);
		} else {
			Node<T> node = headNode;
			headNode = new Node<T>(value);
			headNode.next = node;
		}
		size++;
		return true;
	}
	
	/**
	 * 
	 * @param value
	 * @return true
	 */
	public boolean addLast(T value) {
		// when a list is empty
		if (headNode == null && tailNode == null) {
			populateFirstNode(value);
		} else {
			tailNode.next = new Node<T>(value);
			tailNode = tailNode.next;
		}
		size++;
		return true;
	}
	
	/**
	 * 
	 * @param value
	 */
	private void populateFirstNode(T value) {
		headNode = new Node<T>(value);
		tailNode = headNode;
	}
	
	/**
	 * 
	 * @param index
	 * @return value of the node at the index
	 * @throws IndexOutOfBoundsException if the specified index is out of the range
	 */
	public T get(int i) {
		
		if (i >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		int count = 0;
		Node<T> currentNode = headNode;
		
		while (count < i) {
			count++;
			currentNode = currentNode.next;
		}
		return currentNode.value;
	}
	
	/**
	 * 
	 * @return value of the head Node
	 * @throws NoSuchElementException if this list does not have a head node
	 */
	public T getFirst() {
		
		if (headNode == null) {
			throw new NoSuchElementException();
		} else {			
			return headNode.value;
		}
	}
	
	/**
	 * 
	 * @return value of the tail node
	 * @throws NoSuchElementException if this list does not have a tail node
	 */
	public T getLast() {
		if (tailNode == null) {
			throw new NoSuchElementException();
		} else {
			return tailNode.value;
		}
	}
	
	/**
	 * 
	 * @param target
	 * @return {@code true} if this list contained the specified element
	 */
	public boolean contains(T target) {
		Node<T> currentNode = headNode;
		
		while(currentNode != null) {
			if (currentNode.value == target) {
				return true;
			}
			currentNode = currentNode.next;
		}
		return false;
	}
	
	/**
	 * nodes are converted to strings.
	 * the representation of a list is "node's value, node's value, ..., node's value"
	 */
	@Override
	public String toString() {
		String contents = "";
		
		Node<T> node = headNode;
		
		while(node != null) {
			if (node.next != null) {
				contents += node.value + ", ";
			} else {
				contents += node.value;
			}
			node = node.next;
		}
		
		return contents;
	}
	
	/**
	 * @return {@code true} if the passed list has equivalent items
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SinglyLinkedList)) {
			return false;
		}
		SinglyLinkedList<T> target = (SinglyLinkedList<T>) obj;
		for (int i = 0; i < size; i++) {
			if (get(i) != target.get(i)) {
				return false;
			}
		}
		
		return true;
		
	}

}


