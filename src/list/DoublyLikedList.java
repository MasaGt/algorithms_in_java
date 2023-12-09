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
	 * add a new node to the end of the list. always return true
	 * 
	 * @param value
	 * @return {@code true}
	 */
	public boolean add(T value) {

		if (head == null) {
			// add node to head
			head = new Node<T>(value, head, null);
			tail = head;
		} else {
			// add note to tail
			tail.next = new Node<T>(value, tail, null);
			tail = tail.next;
		}
		size++;
		return true;
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
