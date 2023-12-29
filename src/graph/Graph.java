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
	
	/**
	 * Check if there is edges between two specified nodes in this graph
	 * @param value1 the value of node1
	 * @param value2 the value of node2
	 * @return true if there is edges. otherwise, false
	 */
	public boolean hasEdge(T value1, T value2);
	
	/**
	 * Check if there is a node that has the specified value.
	 * @param value
	 * @return
	 */
	public boolean hasNode(T value);
	
	/**
	 * Return the number of node connected to the node that has the specified value
	 * = the number of edges of the node that has specified value
	 * @param value the value of node
	 * @return the number of node connected
	 */
	public int degree(T value);
}
