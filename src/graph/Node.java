package graph;

public class Node<T> {
	
	private T value;
	
	public Node(T value) {
		this.value = value;
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
}
