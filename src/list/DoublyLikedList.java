package list;

public class DoublyLikedList<T> {
	// inner node
	private class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prev;

		public Node(T vale, Node<T> prev, Node<T> next) {
			this.value = vale;
			next = prev;
			prev = next;
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
