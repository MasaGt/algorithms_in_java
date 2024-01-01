package graph;

import java.util.Objects;

public class Node<T> {
	
	private T value; //the value of this node.
	
	public Node(T value) {
		this.value = value;
	}
	
	/**
	 * Getter for value
	 * @return the value of this node
	 */
	public T getValue() {
		return value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		//type check
		if (!(obj instanceof Node)) return false;
		if (this == obj) return true; 
		
		Node<T> target = (Node<T>) obj;
		
		return this.value.equals(target.value);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
