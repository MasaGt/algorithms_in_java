package graph;

public interface Graph<T> {
	
	/**
	 * Add a node to this graph
	 * @param value the value of node
	 */
	public void addNode(T value);
	
	/**
	 * Add an edge between two nodes
	 * @param value1 the value of node
	 * @param value2 the value of another node
	 */
	public void addEdge(T value1, T value2);
	
	/**
	 * Remove a node that has a specified value from this graph
	 * @param value
	 * @return true if node is successfully removed. otherwise, false
	 */
	public boolean removeNode(T value);
	
	/**
	 * Remove edge that connects two specified nodes
	 * @return true if edges successfully removed. otherwise, false
	 */
	public boolean removeEdge(T value1, T value2);
	
	/**
	 * Retuen the nodes in this graph
	 * @return Array of the nodes
	 */
	public Node<T>[] getNodes();
}
