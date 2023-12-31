package graph;


public interface SimpleAdjacencyListGraph {

	/**
	 * Add edge between two specified nodes.
	 * @param node1 index of the node1 in this graph
	 * @param node2 index of the node2 in this graph
	 */
	public void addEdge(int node1, int node2);
}
