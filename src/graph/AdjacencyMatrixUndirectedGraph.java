package graph;

import java.util.List;

/**
 * This is a undirected graph based on adjacency matrix.
 * This graph allows to have a loop but not a duplicate edge.
 * @param <T>
 */
public class AdjacencyMatrixUndirectedGraph<T> implements Graph<T> {

	private List<Node> nodes;
	private int INIT_SIZE = 5; //default matrix size
	private boolean[][] adjacencyMatrix;
	
	/**
	 * Initialize adjacencyMatrix of INIT_SIZE*INIT_SIZE.
	 */
	public AdjacencyMatrixUndirectedGraph() {
		adjacencyMatrix = new boolean[INIT_SIZE][INIT_SIZE];
	}
	
	/**
	 * Initialize adjacencyMatrix of size*size.
	 * @param size
	 */
	public AdjacencyMatrixUndirectedGraph(int size) {
		adjacencyMatrix = new boolean[size][size];
	}
	
	/**
	 * String representation of this graph
	 * If NodeA is connected to NodeB, this will return "[A, B] [B, A]".
	 * If NodeA is connected to NodeB and NodeB is connected to NodeC, 
	 * this will return "[A, B] [B, A], [B, C] [C, B]".
	 */
	@Override
	public String toString() {
		String content = "";
		return content;
	}
}
