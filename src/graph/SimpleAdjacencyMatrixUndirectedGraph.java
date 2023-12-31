package graph;

/**
 * Simple version of adjacecny matrix undirected graph.
 * When this grahp is construced, nodes are already exist.
 * This graph provides some operations about manipulating edges.
 */
public class SimpleAdjacencyMatrixUndirectedGraph implements SimpleGraph {

	private boolean[][] adjacencyMatrix; //true means there is an edge between two nodes
	private int numVertices; //the number of vertecies that this graph can has = the size of this graph
	private final int DEFAULT_NUM_VERTICES = 5;
	
	/**
	 * Initialize adjacencyMatrix with default size.
	 */
	public SimpleAdjacencyMatrixUndirectedGraph() {
		numVertices = DEFAULT_NUM_VERTICES;
		adjacencyMatrix = new boolean[numVertices][numVertices];
	}
	
	/**
	 * Initialize adjacencyMatrix with specified size.
	 * @param size size of the graph
	 */
	public SimpleAdjacencyMatrixUndirectedGraph(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		numVertices = size;
		adjacencyMatrix = new boolean[numVertices][numVertices];
	}
	
	@Override
	public void addEdge(int node1, int node2) {
		checkIndex(node1, node2);
		adjacencyMatrix[node1][node2] = true;
		adjacencyMatrix[node2][node1] = true;
	}
	
	@Override
	public void removeEdge(int node1, int node2) {
		checkIndex(node1, node2);
		adjacencyMatrix[node1][node2] = false;
		adjacencyMatrix[node2][node1] = false;
	}
	
	/**
	 * Check if the specified index is within the adjacency matrix or not.
	 * If if the specified index is beyond the matrix, this throws ArrayIndexOutOfBoundsException. 
	 * @param indices
	 */
	private void checkIndex(int... indices) {
		for (int index : indices) {
			if (index < 0 || index >= numVertices) {
				throw new ArrayIndexOutOfBoundsException();
			}
		}
	}
	
	/**
	 * String reqresentation of this graph.
	 * If NodeA is connected to NodeB, this will return "[A, B] [B, A]".
	 * If Node A is connected to itself, this will return "[A, A]";
	 * If NodeA is connected to NodeB and NodeB is connected to NodeC, 
	 * this will return "[A, B] [B, A], [B, C] [C, B]".
	 */
	@Override
	public String toString() {
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int k = i; k < adjacencyMatrix[i].length; k++) {
				if (adjacencyMatrix[i][k]) {
					if (i == k) {
						content.append("[" + i + ", " + k + "], ");
					} else {
						content.append("[" + i + ", " + k + "] [" + k + ", " + i + "], ");
					}
				}
			}
		}
		//remve ", " at the end
		if (content.length() > 0) {
			content.delete(content.length() - 2, content.length());
		}
		return content.toString();
	}
}