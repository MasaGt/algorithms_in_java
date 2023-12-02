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
	public T get(int index) {
		
		checkIndexInTheRange(index);
		
		int count = 0;
		Node<T> currentNode = headNode;
		
		while (count < index) {
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
	
	/**
	 * remove the first node: change the head node.
	 * remove 'n'th node: make n-1th node next points to n+1th node.
	 * 
	 *   ____________	                         ______________
	 *  | n-1th  node|	                        | n+1th  node  |
	 *  |            |------------------------->|              |	
	 *  |____________|	                     	|______________|
	 *  
	 *                     	_____________
	 *                     | nth   node  |
	 *                     |             |
	 *                     |_____________| 
	 * 
	 * @param index
	 * @return value of the removed node
	 */
	public T remove(int index) {
		
		checkIndexInTheRange(index);
		
		Node<T> currentNode = headNode;
		T returnValue = null;
		
		if (index == 0) {
			headNode = currentNode.next;
		} else {
			Node<T> prevNode = null;
			// move i to the specified index
			for (int i = 0; i < index; i++) {
				prevNode = currentNode;
				currentNode = currentNode.next;
				
			}
			prevNode.next = currentNode.next;
		}
		
		returnValue = currentNode.value;
		// make removed node null so that the garbage collector will delete it
		currentNode.next = null;
		currentNode = null;
		size--;
		
		return returnValue;
	}
	
	
	/**
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException if the specified index is out of the range
	 */
	private void checkIndexInTheRange(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * remove all the node from this list
	 */
	public void clear() {
		int repetitionNumber = size;
		for (int i = 0; i < repetitionNumber; i++) {
			remove(0);
		}
	}

	/**
	 * 
	 * @param index
	 * @param value
	 * @throws IndexOutOfBoundsException if the specified index is out of the range
	 */
	public void addAt(int index, T value) {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			addFirst(value);
		} else if (index == size) { 
			addLast(value);
		} else {
			Node<T> curreNode = headNode;
			/**
			 * why i in the for-loop begins from 1.
			 * this list uses Zero-based array indexing.
			 *    ____     ____
			 *   |node|-->|node|-->
			 *   |____|   |____|
			 * ^        ^        ^
			 * |        |        |
			 * 0        1        2
			 * 
			 * when index = 0 new node is added at first as the code above.
			 * this code considers inserting node at 1 or any later.
			 */
			for (int i = 1; i < index; i++) {
				curreNode = curreNode.next;
			}
			/**
			 *  ____________         ________         ______
			 * |current node|       |new node|       |node  |
			 * |            | ----> |        | ----> |      |
			 * |____________|       |________|       |______|
			 */
			Node<T> newNode = new Node<T>(value);
			newNode.next = curreNode.next;
			curreNode.next = newNode;
			size++;
		}
	}
}


