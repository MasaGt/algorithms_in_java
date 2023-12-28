package graph;

public class Node<E> {
	
	private E value;
	
	public Node(E value) {
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		//type check
		if (!(obj instanceof Node)) return false;
		if (this == obj) return true; 
		
		Node<E> target = (Node<E>) obj;
		
		return this.value.equals(target.value);
	}
}
