package list;

import java.util.NoSuchElementException;

public class DoublyLikedList<T> {
	// inner node
	private class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		public Node(T vale, Node<T> prev, Node<T> next) {
			this.value = vale;
			this.next = next;
			this.prev = prev;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size = 0;

	/**
	 * initialize empty list
	 */
	public DoublyLikedList() {
		head = null;
		tail = null;
	}

	/**
	 * initialize a list with values
	 * @param values
	 */
	public DoublyLikedList(T[] values) {
		for (T t : values) {
			add(t);
		}
	}

	/**
	 * 
	 * @param value
	 */
	public void add(T value) {
		if (head == null) {
			// add node to head
			addFirst(value);
		} else {
			// add note to tail
			addLast(value);
		}
	}
	
	/**
	 * 
	 * @param value
	 */
	public void addFirst(T value) {
		if (head == null && tail == null) {
			populateFirstNode(value);
		} else {
			Node<T> newNode = new Node<T>(value, null, head);
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void addLast(T value) {
		if (head == null && tail == null) {
			populateFirstNode(value);
		} else {
			Node<T> newNode = new Node<T>(value, tail, null); 
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}
	
	/**
	 * add the first Node to the list.
	 * this is called internally from methods in this class.
	 * @param value
	 */
	private void populateFirstNode(T value) {
		head = new Node<T>(value, null, null);
		tail = head;
	}
	
	/**
	 * add a new node at the specified index
	 *  _________         ________	       _________
	 * |prev node| ----> |new node| ----> |next node|
	 * |_________| <---- |________| <---- |_________|
	 * 
	 * @throws IndexOutOfBoundsException if the specified index is out of the range
	 */
	public void addAt(int index, T value) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if (index == 0) {
			addFirst(value);
		} else if (index == size) {
			addLast(value);
		} else {
			// add node somewhere in the middle
			Node<T> currentNode = null;
			if (index <= (size/2)) {
				//search from head
				currentNode = head;
				Node<T> prevNode = null;
				
				for (int i = 0; i < index; i++) {
					prevNode = currentNode;
					currentNode = currentNode.next;
				}
				// insert a new node
				/**
				 *  _________         ________	       ____________
				 * |prev node| ----> |new node| ----> |current node|
				 * |_________| <---- |________| <---- |____________|
				 */
				Node<T> newNode = new Node<T>(value, prevNode, currentNode);
				prevNode.next = newNode;
				currentNode.prev = newNode;
			} else {
				// search from tail
				currentNode = tail;
				Node<T> nextNode = null;
				
				for (int i = size; i > index; i--) {
					nextNode = currentNode;
					currentNode = currentNode.prev;
				}
				// insert a new node
				/**
				 *  ____________         ________	       ________
				 * |current node| ----> |new node| ----> |next node|
				 * |____________| <---- |________| <---- |_________|
				 */
				Node<T> newNode = new Node<T>(value, currentNode, nextNode);
				currentNode.next = newNode;
				nextNode.prev = newNode;
			}
			size++;
		}
	}
	
	/**
	 * 
	 * @return value of the head node
	 * @throws NoSuchElementException
	 */
	public T getFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}
		return head.value;
	}
	
	/**
	 * 
	 * @return value of the tail node
	 * @throws NoSuchElementException
	 */
	public T getLast() {
		if (tail == null) {
			throw new NoSuchElementException();
		}
		return tail.value;
	}
	
	/**
	 * 
	 * @param index
	 * @return value of a node at the specified index
	 * @throws IndexOutOfBoundsException if the specified index is out of the range
	 */
	public T get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		T result = null;
		if (index == 0) {
			result = getFirst();
		} else if (index == size - 1) {
			result = getLast();
		} else {
			if (index <= (size/2)) {
				//search from head
				Node<T> currentNode = head;
				for (int i = 0; i < index; i++) {
					currentNode = currentNode.next;
				}
				result = currentNode.value;
			} else {
				//search from tail
				Node<T> currentNode = tail;
				for (int i = size; i > index; i--) {
					currentNode = currentNode.prev;
				}
				result = currentNode.value;
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		String contents = "";

		Node<T> currentNode = head;
		while (currentNode != null) {
			if (currentNode.next != null) {
				contents += currentNode.value + ", ";
			} else {
				contents += currentNode.value;
			}
			currentNode = currentNode.next;
		}

		return contents;
	}
}
