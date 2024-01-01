package graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple version of adjacecny list undirected graph. When this grahp is
 * construced, nodes are already exist. This graph provides some operations
 * about manipulating edges.
 */
public class SimpleAdjacencyListUndirectedGraph implements SimpleAdjacencyListGraph {

	protected List<List<Integer>> adjacencyList;
	private final int DEFAULT_NUM_VERTICES = 5;

	/**
	 * Initialize adjacencyMatrix with default size.
	 */
	public SimpleAdjacencyListUndirectedGraph() {
		initAdjacencyList(DEFAULT_NUM_VERTICES);
	}

	/**
	 * Initialize adjacencyMatrix with specified size.
	 * @param size size of the graph
	 */
	public SimpleAdjacencyListUndirectedGraph(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		initAdjacencyList(size);
	}

	/**
	 * Create a graph with the specified size
	 */
	private void initAdjacencyList(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		adjacencyList = new LinkedList<List<Integer>>();
		for (int i = 0; i < size; i++) {
			adjacencyList.add(new LinkedList<Integer>());
		}
	}

	/**
	 * adjacencyList(index) should be in ascending order order
	 * 
	 *  
	 * [0, 1, 2, 3, 4]
	 * [1, 0, 0, 1, ~]↓ ascending order
	 * [2, 3, ~, ~, ~]
	 * [~, ~, ~, ~, ~]
	 */
	@Override
	public void addEdge(int node1, int node2) {
		checkNodeIndex(node1, node2);

		//add an egde. then, ort the adjacency list in ascending order.
		adjacencyList.get(node1).add(node2);
		Collections.sort(adjacencyList.get(node1));
		adjacencyList.get(node2).add(node1);
		Collections.sort(adjacencyList.get(node2));
	}
	
	@Override
	public void removeEdge(int node1, int node2) {
		checkNodeIndex(node1, node2);
		
		//check if there is a edge between the nodes
		if (adjacencyList.get(node1).contains(node2) &&
			adjacencyList.get(node2).contains(node1))
		{
			int removeIndex1 = adjacencyList.get(node2).indexOf(node1);
			int removeIndex2 = adjacencyList.get(node1).indexOf(node2);
			adjacencyList.get(node1).remove(removeIndex2);
			adjacencyList.get(node2).remove(removeIndex1);
		}
	}
	
	@Override
	public boolean hasEdge(int node1, int node2) {
		checkNodeIndex(node1, node2);
		
		return adjacencyList.get(node2).indexOf(node1) >= 0;
	}
	
	private void checkNodeIndex(int ...indices) {
		for (int index : indices) {
			if (index < 0 || index >= adjacencyList.size()) {
				throw new IndexOutOfBoundsException();
			}
		}
	}
	
	@Override
	public void clear() {
		initAdjacencyList(adjacencyList.size());
	}

	/**
	 * String representation of this graph.
	 * 
	 * If there is NodeA and it is not connected, this returns as follows; 
	 * A:
	 * 
	 * If NodeA is connected to NodeB, this returns as follows;
	 * A: B B: A
	 * 
	 * If NodeA is connected to itself, this returns as follows;
	 * A: A
	 * 
	 * If NodeA is connected to NodeB and NodeB is connected to NodeC, this returns
	 * as follows;
	 * A: B
	 * B: A, C
	 * C: B
	 */
	@Override
	public String toString() {
		String content = "";

		/*
		 * i→ 
		 * k↓
		 * [0|1|2|3|4] 
		 * [~|~|~|~|~]
		 * [~|~|~|~|~]
		 */
		for (int i = 0; i < adjacencyList.size(); i++) {
			content += i + ": ";
			for (int k = 0; k < adjacencyList.get(i).size(); k++) {
				Integer node = adjacencyList.get(i).get(k);
				if (k != adjacencyList.get(i).size() - 1) {
					content += node + ", ";
				} else {
					content += node;
				}
			}
			content += "\n";
		}
		return content;
	}
}
