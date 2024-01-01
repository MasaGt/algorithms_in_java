package graph;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * This is an undirected graph based on adjacency list.
 * This graph does not allow to have a duplicate node (node that has the same value as another node).
 * This graph does not allow to have a node whose value is null.
 * @param <T>
 */
public class AdjacencyListUndirectedGraph<T> implements Graph<T> {

	private Map<Node<T>, Set<Node<T>>> adjacencyList;
	
	/**
	 * There is not a limitation of the graph sizes
	 * because this graph is based on adjacency list (Map<Node, List<Node>>).
	 */
	public AdjacencyListUndirectedGraph() {
		//track the order of keys (Node)
		adjacencyList = new LinkedHashMap<Node<T>, Set<Node<T>>>();
	}
	
	
	@Override
	public void addNode(T value) {
		nullCheck(value);
		Node<T> node = new Node<T>(value);
		//if there is not a duplicated node, add that node to the graph
		if (!adjacencyList.containsKey(node)) adjacencyList.put(node, new TreeSet<Node<T>>());
	}

	@Override
	public void addEdge(T value1, T value2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeNode(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEdge(T value1, T value2) {
		// TODO Auto-generated method stub
		return false;
	}

	//better to change return type from Node<T>[] to List<Node<T>>
	@SuppressWarnings({ "unchecked"})
	@Override
	public Node<T>[] getNodes() {
		Node<T>[] array = (Node<T>[])new Node[0]; //<- not apparently instinctive
		return adjacencyList.keySet().toArray(array);
	}

	@Override
	public boolean hasEdge(T value1, T value2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNode(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int degree(T value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
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
		for (Node<T> from : adjacencyList.keySet()) {
			adjacencyList.get(from).stream().forEach((to) -> {
				if (from.equals(to)) {
					//loop (from node is connected to itself)
					content.append("[" + from.toString() + ", " + to.toString() + "], ");
				} else {
					content.append("[" + from.toString() + ", " + to.toString() + "] [" + to.toString() + ", " + from.toString() + "], ");
				}
			});
		}
		//remove ", " at the end
		if (content.length() > 0) {
			content.delete(content.length() - 2, content.length());
		}
		return content.toString();
	}
}
