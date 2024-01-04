package graph;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import queue.LinkQueue;
import queue.Queue;

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
		if (!adjacencyList.containsKey(node)) adjacencyList.put(node, new LinkedHashSet<Node<T>>());
	}

	@Override
	public void addEdge(T value1, T value2) {
		nullCheck(value1, value2);
		
		Node<T> node1 = new Node<>(value1);
		Node<T> node2 = new Node<>(value2);
		//do nothing if there is not a node that has the specified value in this graph.
		if (!adjacencyList.containsKey(node1) || !adjacencyList.containsKey(node2)) return;
		
		if (value1.equals(value2)) {
			//add loop edge
			adjacencyList.get(node1).add(node2);
		} else {
			adjacencyList.get(node1).add(node2);
			adjacencyList.get(node2).add(node1);
		}
	}

	@Override
	public boolean removeNode(T value) {
		nullCheck(value);
		Node<T> targetNode = new Node<T>(value);
		if (!adjacencyList.containsKey(targetNode)) return false;
		adjacencyList.remove(targetNode);
		//remove edges that are connected to targetNode from other nodes
		for (Node<T> node : adjacencyList.keySet()) {
			if (adjacencyList.get(node).contains(targetNode)) {
				adjacencyList.get(node).remove(targetNode);
			}
		}
		return true;
	}

	@Override
	public boolean removeEdge(T value1, T value2) {
		nullCheck(value1, value2);
		Node<T> from = new Node<T>(value1);
		Node<T> to = new Node<T>(value2);
		//do nothing is there is not a node that has the specified value in this graph.
		if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
		adjacencyList.get(from).remove(to);
		adjacencyList.get(to).remove(from);
		return true;
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
		nullCheck(value1, value2);
		Node<T> from = new Node<T>(value1);
		Node<T> to = new Node<T>(value2);
		//return false, if there is not a node that has the specfied value.
		if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) return false;
		return adjacencyList.get(from).contains(to);
	}

	@Override
	public boolean hasNode(T value) {
		nullCheck(value);
		return adjacencyList.containsKey(new Node<T>(value));
	}

	@Override
	public int degree(T value) {
		nullCheck(value);
		Node<T> node = new Node<T>(value);
		if (!adjacencyList.containsKey(node)) return 0;
		return adjacencyList.get(node).size();
	}

	@Override
	public void clear() {
		adjacencyList = new LinkedHashMap<Node<T>, Set<Node<T>>>();
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
		Vector<Node<T>> skipNode = new Vector<Node<T>>();
		for (Node<T> from : adjacencyList.keySet()) {
			adjacencyList.get(from).stream().forEach((to) -> {
				/*
				 * This is for printing [A, B] [B, A] if NodeA and NodeB is connected.
				 * Without skipping some nodes, this prints [A, B], [B, A], [B, A] [A, B].
				 * This is because iterating keys in Map and Set no like 2D array.
				 */
				if (skipNode.contains(to)) return;
				
				if (from.equals(to)) {
					//loop (from is connected to itself)
					content.append("[" + from.toString() + ", " + to.toString() + "], ");
				} else {
					content.append("[" + from.toString() + ", " + to.toString() + "] [" + to.toString() + ", " + from.toString() + "], ");
				}
			});
			skipNode.add(from);
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
		Node<T> node = new Node<T>(start);
		if (!adjacencyList.containsKey(node)) throw new NoSuchElementException();

		List<T> visited = new LinkedList<T>();
		Queue<Node<T>> queue = new LinkQueue<Node<T>>();
		queue.enqueue(node);
		
		while (!queue.isEmpty()) {
			Node<T> currentNode = queue.dequeue();
			if (!visited.contains(currentNode.getValue())) {
				visited.add(currentNode.getValue());
				
				//iterate through the neighbor nodes of currentNode.
				adjacencyList.get(currentNode).forEach((e) -> {
					//check if a neighbor node is already visited.
					if (!e.equals(currentNode) && !visited.contains(e.getValue())) {
						queue.enqueue(e); //push non-visited neighbor node into the queue.
					}
				});
			}
		}
		return visited;
	}


	@Override
	public List<T> bfsToDisconnectedGraph(T start) {
		nullCheck(start);
		Node<T> node = new Node<T>(start);
		if (!adjacencyList.containsKey(node)) throw new NoSuchElementException();
		
		//bfs for all nodes connected to "start"
		List<T> searched = bfs(start);
		
		//bfs for rest of the disconnected nodes
		for (Node<T> currentNode : adjacencyList.keySet()) {
			if (!searched.contains(currentNode.getValue())) {
				searched.addAll(bfs(currentNode.getValue()));
			}
		}
		return searched;
	}


	@Override
	public List<T> dfs(T start) {
		nullCheck(start);
		Node<T> node = new Node<T>(start);
		if (!adjacencyList.containsKey(node)) throw new NoSuchElementException();
		
		List<T> visited = new LinkedList<T>();
		Stack<Node<T>> stack = new Stack<Node<T>>();
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node<T> currentNode = stack.pop();
			if (!visited.contains(currentNode.getValue())) {
				visited.add(currentNode.getValue());
				
				/*
				 * Why iterate throgh backwards
				 * -> I want to print the nodes from older to newer
				 * (古く追加された順から出力したいから)
				 * 
				 * e.g.
				 *   older  ----  newer
				 *   node1  ----  node5
				 *             node1
				 *            /     \
				 *           /       \
				 *        node2     node3
				 *         /           \
				 *        /             \
				 *     node4           node5
				 *   
				 * output should be [node1, node2, node4, node3, node5]
				 * 
				 * ★if iterating through forwards, output will be
				 * [node1, node3, node5, node2, node4]
				 * because this uses stack.
				 */				
				LinkedList<Node<T>> list = new LinkedList<>(adjacencyList.get(currentNode));
				Iterator<Node<T>> itr = list.descendingIterator();
				while (itr.hasNext()) {
					Node<T> neighbNode = itr.next();
					if (!neighbNode.equals(currentNode) && !visited.contains(neighbNode.getValue())) {
						stack.push(neighbNode);
					}
				}
			}
		}
		return visited;
	}


	@Override
	public List<T> dfsToDisconnectedGraph(T start) {
		nullCheck(start);
		Node<T> node = new Node<T>(start);
		if (!adjacencyList.containsKey(node)) throw new NoSuchElementException();
		
		List<T> searched = dfs(start);
		
		
		for (Node<T> currentNode : adjacencyList.keySet()) {
			if (!searched.contains(currentNode.getValue())) {
				searched.addAll(dfs(currentNode.getValue()));
			}
		}
		return searched;
	}
}
