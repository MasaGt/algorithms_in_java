package graph;

public class SimpleAdjacencyListDirectedGraph extends SimpleAdjacencyListUndirectedGraph {

	
	/**
	 * Initialize adjacencyMatrix with default size.
	 */
	public SimpleAdjacencyListDirectedGraph() {
		initAdjacencyList(DEFAULT_NUM_VERTICES);
	}

	/**
	 * Initialize adjacencyMatrix with specified size.
	 * @param size size of the graph
	 */
	public SimpleAdjacencyListDirectedGraph(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		initAdjacencyList(size);
	}

	@Override
	public void addEdge(int from, int to) {
		checkNodeIndex(from, to);
		adjacencyList.get(from).add(to);
	}
	
	@Override
	public void removeEdge(int from, int to) {
		checkNodeIndex(from, to);
		adjacencyList.get(from).remove(to);
	}
}
