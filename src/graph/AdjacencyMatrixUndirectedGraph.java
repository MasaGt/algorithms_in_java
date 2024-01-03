package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import queue.LinkQueue;
import queue.Queue;

/**
 * This is an undirected graph based on adjacency matrix.
 * This graph allows to have a loop but not a duplicate edge.
 * This graph does not allow to have a duplicate node (node that has the same value as another node).
 * This graph does not allow to have a node whose value is null.
 * @param <T>
 */
public class AdjacencyMatrixUndirectedGraph<T> implements Graph<T> {

	/*
	 * the index of nodeArray correspond to the index of adjacencyMatrix.
	 * nodeArray
	 * [A, B, C, D]
	 * 
	 * adjacencyMatrix
	 *  A, B, C, D
	 * [~, ~, ~, ~] A 
	 * [~, ~, ~, ~] B 
	 * [~, ~, ~, ~] C 
	 * [~, ~, ~, ~] D 
	 */
	private Node<T>[] nodeArray;
	private final int INIT_SIZE = 5; //default matrix size
	private boolean[][] adjacencyMatrix;
	
	/**
	 * Initialize adjacencyMatrix of INIT_SIZE*INIT_SIZE.
	 */
	@SuppressWarnings("unchecked")
	public AdjacencyMatrixUndirectedGraph() {
		adjacencyMatrix = new boolean[INIT_SIZE][INIT_SIZE];
		initMatrix();
		nodeArray = new Node[INIT_SIZE];
	}
	
	/**
	 * Initialize adjacencyMatrix of size*size.
	 * @param size
	 */
	@SuppressWarnings("unchecked")
	public AdjacencyMatrixUndirectedGraph(int size) {
		if (size <= 0) throw new IllegalArgumentException();
		adjacencyMatrix = new boolean[size][size];
		initMatrix();
		nodeArray = new Node[size];
	}
	
	/**
	 * Fill the adjacencyMatrix will false.
	 */
	private void initMatrix() {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int k = 0; k < adjacencyMatrix[i].length; k++) {
				adjacencyMatrix[i][k] = false;
			}
		}
	}
	
	@Override
	public void addNode(T value) {
		nullCheck(value);
		//duplicate value check
		if (!(indexOf(value) < 0)) return; 
		//if this graph is full, do nothing.
		int availableIndex = findAvailableIndex();
		if (availableIndex< 0) return;
		
		nodeArray[availableIndex] = new Node<T>(value);
	}
	
	/**
	 * Find and return an index at which a node can be stored in nodeArray.
	 * Retun -1 if the nodeArray is full, which means this graph is full.
	 * @return index which is available in nodeArray
	 */
	private int findAvailableIndex() {
		for (int i = 0; i < nodeArray.length; i++) {
			if (nodeArray[i] == null) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public void addEdge(T value1, T value2) {
		nullCheck(value1, value2);
		int i = indexOf(value1);
		int k = indexOf(value2);
		//do nothing if there is not a node that has the specified value in this graph.
		if (i < 0 || k < 0) return;
		
		if (i == k) {
			//loop edge
			adjacencyMatrix[i][i] = true;
		} else {
			adjacencyMatrix[i][k] = true;
			adjacencyMatrix[k][i] = true;
		}
	}
	
	/**
	 * Return the index of a node that has the specified value in nodeArray.
	 * Return -1 if there is not a node that has the specified value in nodeArray.
	 * @param value the index of a node that has the specified value.
	 */
	private int indexOf(T value) { 
		for (int i = 0; i < nodeArray.length; i++) {
			if (nodeArray[i] !=null && nodeArray[i].getValue().equals(value)) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public boolean removeNode(T value) {
		nullCheck(value);
		int targetIndex = indexOf(value);
		//do nothing if there is not a node that has the specified value in this graph.
		if (targetIndex < 0) return false;
		
		nodeArray[targetIndex] = null;
		//remove edge which is connected to the removed node
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int k = i; k < adjacencyMatrix[i].length; k++) {
				if (i == targetIndex || k == targetIndex) {
					adjacencyMatrix[i][k] = false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean removeEdge(T value1, T value2) {
		nullCheck(value1, value2);
		//return false if there is not a node that has the specified value.
		int indexNode1 = indexOf(value1);
		int indexNode2 = indexOf(value2);
		if (indexNode1 < 0 || indexNode2 < 0) return false;
		
		//remove edges from adjacency matrix
		if (indexNode1 == indexNode2) {
			//remove loop edge
			adjacencyMatrix[indexNode1][indexNode2] = false;
		} else {
			adjacencyMatrix[indexNode1][indexNode2] = false;
			adjacencyMatrix[indexNode2][indexNode1] = false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Node<T>[] getNodes() {
		//create Node Array that only has exsting node (remove null)
		return Arrays.stream(nodeArray).filter((a) -> a != null).toArray(Node[]::new);
	}
	
	@Override
	public boolean hasEdge(T value1, T value2) {
		nullCheck(value1, value2);
		//return false if there is not a node that has the specified value.
		int indexNode1 = indexOf(value1);
		int indexNode2 = indexOf(value2);
		if (indexNode1 < 0 || indexNode2 < 0) return false;
		return adjacencyMatrix[indexNode1][indexNode2];
	}
	
	@Override
	public boolean hasNode(T value) {
		nullCheck(value);
		return !(indexOf(value) < 0);
	}
	
	@Override
	public int degree(T value){
		nullCheck(value);
		int indexOfNode = indexOf(value);
		//node existance check
		if (indexOfNode < 0) throw new NoSuchElementException("There is not such a node.");
		int cnt = 0;
		for (int i = 0;  i < adjacencyMatrix.length; i++) {
			if (adjacencyMatrix[i][indexOfNode]) {
				cnt++;
			}
		}
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		initMatrix();
		nodeArray = new Node[nodeArray.length];
	}
	
	/**
	 * Null check. If value is null, throw IllegalArgumentException.
	 * @param values the values of nodes
	 */
	@SafeVarargs
	private void nullCheck(T... values) {
		for (T value : values) {
			if (value == null) throw new IllegalArgumentException("This graph can not have a node whose value is null.");
		}
	}
	
	/**
	 * String representation of this graph
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
				if (adjacencyMatrix[i][k] == true) {
					if (i == k) {
						content.append("[" + nodeArray[i] + ", " + nodeArray[k] + "], ");
					} else {
						content.append("[" + nodeArray[i] + ", " + nodeArray[k] + "] [" + nodeArray[k] + ", " + nodeArray[i] + "], ");
					}
				}
			}
		}
		//remove ", " at the end
		if (content.length() > 0) {
			content.delete(content.length() - 2, content.length());
		}
		return content.toString();
	}

	@Override
	public List<T> bfs(T start) {
		nullCheck(start);
		int nodeIndex = indexOf(start);
		if (nodeIndex < 0) throw new NoSuchElementException();
		
		//correspond to the indices of adjacencyMatrix
		boolean[] visited = new boolean[adjacencyMatrix.length];
		/**
		 * visited
		 * [0, 1, 2, 3, 4]
		 * 
		 * adjacencyMatrix
		 *  0, 1, 2, 3, 4
		 * [~, ~, ~, ~, ~] 0
		 * [~, ~, ~, ~, ~] 1
		 * ...
		 */
		Queue<Integer> queue = new LinkQueue<Integer>(); //queue of the index of node
		List<T> searched = new ArrayList<T>(); //list that stored visited node in order
		
		//add a node to a queue.
		queue.enqueue(nodeIndex);

		while (!queue.isEmpty()) {
			int currentNode = queue.dequeue();
			if (!visited[currentNode]) {
				searched.add(nodeArray[currentNode].getValue());
				visited[currentNode] = true;
				
				//iterate through the neighbor nodes of currentNode
				for (int i = 0; i < adjacencyMatrix[currentNode].length; i++) {
					/*
					 * adjNode != i: ignore loop
					 * adjacencyMatrix[current][i]: if node at i index is connected to current node
					 * visited[i]: if node at i is not visited yet
					*/
					if (currentNode != i && adjacencyMatrix[currentNode][i] && !visited[i]) {
						queue.enqueue(i);
					}
				}
			}
		}
		return searched;
	}

	@Override
	public List<T> bfsToDisconnectedGraph(T start) {
		nullCheck(start);
		int nodeIndex = indexOf(start);
		if (nodeIndex < 0) throw new NoSuchElementException();
		
		//bfs for all nodes connected to "start"
		List<T> searched = bfs(start);
		
		//bfs for rest of the disconnected nodes
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (!searched.contains(nodeArray[i].getValue())) {
				searched.addAll(bfs(nodeArray[i].getValue()));
			}
		}
		return searched;
	}

	@Override
	public List<T> dfs(T start) {
		nullCheck(start);
		int nodeIndex = indexOf(start);
		if (nodeIndex < 0) throw new NoSuchElementException();
		
		boolean[] visited = new boolean[adjacencyMatrix.length];
		Stack<Integer> stack = new Stack<Integer>(); //stack of the index of node
		List<T> searched = new ArrayList<T>(); //list that stored visited node in order
		
		stack.push(nodeIndex);
		
		while (!stack.isEmpty()) {
			int currentNode = stack.pop();
			if (!visited[currentNode]) {
				searched.add(nodeArray[currentNode].getValue());
				visited[currentNode] = true;
				
				//traverse from right to left
				for (int i = adjacencyMatrix.length - 1; i > 0; i--) {
					if (currentNode != i && adjacencyMatrix[currentNode][i] && !visited[i]) {
						stack.push(i);
					}
				}
			}
		}
		return searched;
	}

	@Override
	public List<T> dfsToDisconnectedGraph(T start) {
		nullCheck(start);
		int nodeIndex = indexOf(start);
		if (nodeIndex < 0) throw new NoSuchElementException();
		
		List<T> searched = dfs(start);
		
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			if (!searched.contains(nodeArray[i].getValue())) {
				searched.addAll(bfs(nodeArray[i].getValue()));
			}
		}
		
		return searched;
	}
}
