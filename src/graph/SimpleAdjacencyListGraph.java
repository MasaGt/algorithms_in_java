package graph;


public interface SimpleAdjacencyListGraph {

	/**
	 * Add edge between two specified nodes.
	 * @param node1 index of the node1 in this graph
	 * @param node2 index of the node2 in this graph
	 */
	public void addEdge(int node1, int node2);
	
	/**
	 * Remove edge between two specified nodes.
	 * @param node1 index of the node1 in this graph
	 * @param node2 index of the node2 in this graph
	 */
	public void removeEdge(int node1, int node2);
	
	/**
	 * Check if there is edge between two specified nodes.
	 * @param node1 index of the node1 in this graph
	 * @param node2 index of the node2 in this graph
	 */
	public boolean hasEdge(int node1, int node2);
	
	/**
	 * Clear this graph = remove all the edges
	 */
	public void clear();
}
