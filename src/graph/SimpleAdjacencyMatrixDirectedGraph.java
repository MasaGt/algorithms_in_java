package graph;

/**
 * Simple version of adjacecny matrix directed graph.
 * When this grahp is construced, nodes are already exist.
 * This graph provides some operations about manipulating edges.
 */
public class SimpleAdjacencyMatrixDirectedGraph extends SimpleAdjacencyMatrixUndirectedGraph {

	/**
	 * Initialize adjacencyMatrix with default size.
	 */
	public SimpleAdjacencyMatrixDirectedGraph() {
		super();
	}
	
	/**
	 * Initialize adjacencyMatrix with specified size.
	 * @param size size of the graph
	 */
	public SimpleAdjacencyMatrixDirectedGraph(int size) {
		super(size);
	}

	@Override
	public void addEdge(int from, int to) {
		checkIndex(from, to);
		adjacencyMatrix[from][to] = true;
	}

	@Override
	public void removeEdge(int from, int to) {
		checkIndex(from, to);
		adjacencyMatrix[from][to] = false;
	}

	@Override
	public boolean hasEdge(int from, int to) {
		checkIndex(from, to);
		return adjacencyMatrix[from][to];
	}
	
	/**
	 * String representation of this directed graph.
	 * If NodeA is connected to NodeB, this returns "[A -> B]"
	 * If NodeA is connected to itself, this returns "[A -> A]"
	 * If NodeA is connected to NodeB and NodeB is connected to NodeC, 
	 * this will return "[A -> B], [B -> C]".
	 */
	@Override
	public String toString() {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int k = 0; k < adjacencyMatrix[i].length; k++) {
				if (adjacencyMatrix[i][k]) {
					if (i == k) {
						content.append("[" + i + " -> " + k + "], ");
					} else {
						content.append("[" + i + " -> " + k + "], ");
					}
				}
			}
		}
		//remve ", " at the end
		if (content.length() > 0) content.delete(content.length() - 2, content.length());
		return content.toString();
	}
}
