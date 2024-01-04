package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixUndirectedGraphTest {

	private AdjacencyMatrixUndirectedGraph<Integer> graph;
	private AdjacencyMatrixUndirectedGraph<Integer> completeGraph;
	private AdjacencyMatrixUndirectedGraph<Integer> disconnectedGraph;
	private Integer node1 = 1;
	private Integer node2 = 2;
	private Integer node3 = 3;
	private Integer node4 = 4;
	private Integer node5 = 5;
	private Integer node6 = 6;
	
	private Graph<String> smallGraph;
	private String nodeA = "A";
	private String nodeB = "B";
	private String nodeC = "C";
	private String nodeD = "D";
	
	@BeforeEach
	void prep() {
		graph = new AdjacencyMatrixUndirectedGraph<Integer>();
		smallGraph = new AdjacencyMatrixUndirectedGraph<String>(3);
		completeGraph = new AdjacencyMatrixUndirectedGraph<Integer>();
		disconnectedGraph = new AdjacencyMatrixUndirectedGraph<Integer>(6);
		
		/*
		 *             node1
		 *            /     \
		 *           /       \
		 *        node2     node3
		 *        /   \
		 *       /     \
		 *    node4   node5
		 */
		completeGraph.addNode(node1);
		completeGraph.addNode(node2);
		completeGraph.addNode(node3);
		completeGraph.addNode(node4);
		completeGraph.addNode(node5);
		completeGraph.addEdge(node1, node2);
		completeGraph.addEdge(node3, node1);
		completeGraph.addEdge(node2, node4);
		completeGraph.addEdge(node5, node2);
		
		/*
		 *             node1
		 *            /     
		 *           /       
		 *        node2     node3
		 *        /   \       \
		 *       /     \       \
		 *    node4   node5    node6
		 */
		disconnectedGraph.addNode(node1);
		disconnectedGraph.addNode(node2);
		disconnectedGraph.addNode(node3);
		disconnectedGraph.addNode(node4);
		disconnectedGraph.addNode(node5);
		disconnectedGraph.addNode(node6);
		disconnectedGraph.addEdge(node1, node2);
		disconnectedGraph.addEdge(node2, node4);
		disconnectedGraph.addEdge(node5, node2);
		disconnectedGraph.addEdge(node3, node6);
	}
	
	@Nested
	class InitTests {
		@Test
		void initEmptyGraph() {
			assertEquals("", graph.toString());
		}
		@Test
		void initGraphWithSize0() {
			//init a graph with size zero
			assertThrows(IllegalArgumentException.class, () -> { new AdjacencyMatrixUndirectedGraph<Object>(0); });
		}
		@Test
		void initGraphWithNegativeSize() {
			//init a graph with negative size
			assertThrows(IllegalArgumentException.class, () -> { new AdjacencyMatrixUndirectedGraph<Object>(-1); });
		}
	}
	
	/**
	 * Check there is not a connecton yet after adding a node
	 * 		 there  is a node added to the graph
	 */
	@Nested
	class AddNodeTests {
		@Test
		void addNode() {
			graph.addNode(node1);
			//there is no connection in the graph
			assertEquals("", graph.toString());
			assertEquals(1, graph.getNodes().length);
			assertEquals("[1]", Arrays.toString(graph.getNodes()));
		}
		@Test
		void addDuplicateNode() {
			graph.addNode(node1);
			//this should be ignored
			graph.addNode(node1);
			assertEquals(1, graph.getNodes().length);
			assertEquals("[1]", Arrays.toString(graph.getNodes()));
		}
	}
	
	/**
	 * Check there is a connection between the specified nodes
	 */
	@Nested
	class AddEdgeTests {
		@Test
		void addEdge() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			assertEquals("[1, 2] [2, 1]", graph.toString());
		}
	}
	
	@Nested
	class RemoveNodeTests {
		/**
		 * Check the specified node is removed from the graph
		 */
		@Test
		void removeExstingNode() {
			graph.addNode(node1);
			assertTrue(graph.removeNode(node1));
			assertEquals(0, graph.getNodes().length);
			assertEquals("[]", Arrays.toString(graph.getNodes()));
		}
		/**
		 * Check the node is not removed if the specified node does not exist in the graph
		 */
		@Test
		void removeInexstingNode() {
			graph.addNode(node1);
			assertFalse(graph.removeNode(node2));
			assertEquals(1, graph.getNodes().length);
			assertEquals("[1]", Arrays.toString(graph.getNodes()));
		}
		@Test
		void removeExistingNodeThatHasConnection() {
			/*
			 * Before remove NodeA
			 * ->		[NodeA] - [NodeB]
			 * After remove nodeA
			 * ->		[NodeB]
			 */
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			graph.removeNode(node1);
			
			//edges between NodeA and NodeB sould be removed too
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class RemoveEdgeTests {
		@Test
		void removeExstingEdge() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			assertTrue(graph.removeEdge(node1, node2));
			assertEquals("", graph.toString());
		}
		@Test
		void removeInexstingEdge() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			//try to remove edges that does not exist in the graph
			assertFalse(graph.removeEdge(node1, node3));
			assertEquals("[1, 2] [2, 1]", graph.toString());
		}
	}
	
	@Nested
	class GetNodesTests {
		@Test
		void getNodes() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addNode(node3);
			assertEquals(3, graph.getNodes().length);
			assertEquals("[1, 2, 3]", Arrays.toString(graph.getNodes()));
		}
	}
	
	@Nested
	class HasEdgeTests {
		@Test
		void checkExistingEdge() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			assertTrue(graph.hasEdge(node1, node2));
		}
		@Test
		void checkInexstingEdge() {
			graph.addNode(node1);
			graph.addNode(node2);
			assertFalse(graph.hasEdge(node1, node2));
		}
	}
	
	@Nested
	class HasNodeTests {
		@Test
		void checkExistingNode() {
			graph.addNode(node1);
			assertTrue(graph.hasNode(node1));
		}
		@Test
		void checkInexistingNode() {
			graph.addNode(node1);
			assertFalse(graph.hasNode(node2));
		}
	}
	
	@Nested
	class DegreeTests {
		@Test
		void zeroAdjacentNode() {
			graph.addNode(node1);
			assertEquals(0, graph.degree(node1));
		}
		void twoAdjacentNode() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addNode(node3);
			//connect 2 to 1
			graph.addEdge(node2, node1);
			//connect 3 to 1
			graph.addEdge(node3, node1);
			assertEquals(2, graph.degree(node1));
		}
		@Test
		void passNull() {
			assertThrows(IllegalArgumentException.class, () -> {graph.degree(null); });
		}
		@Test
		void passInExistingNode() {
			assertThrows(NoSuchElementException.class, () -> { graph.degree(node2); });
		}
	}
	
	@Nested
	class ClearTests {
		@Test
		void clear() {
			graph.addNode(node1);
			graph.addNode(node2);
			graph.addEdge(node1, node2);
			graph.clear();
			assertEquals("", graph.toString());
			assertEquals("[]", Arrays.toString(graph.getNodes()));
		}
	}
	
	@Nested
	class BFSTests {
		@Test
		void bfsToCompleteGraph() {
			//use 3*3 adjacency matrix graph
			smallGraph.addNode(nodeA);
			smallGraph.addNode(nodeB);
			smallGraph.addNode(nodeC);
			smallGraph.addEdge(nodeA, nodeB);
			smallGraph.addEdge(nodeB, nodeC);
			smallGraph.addEdge(nodeC, nodeA);
			assertEquals("[A, B, C]", smallGraph.bfs("A").toString());
		}
		@Test
		void bfsToDisconnectedGraph() {
			//only the nodes that are connected to the specified node should be showed.
			smallGraph.addNode(nodeA);
			smallGraph.addNode(nodeB);
			smallGraph.addNode(nodeC);
			// the node that is connected to node A is only node C
			smallGraph.addEdge(nodeA, nodeC);
			assertEquals("[A, C]", smallGraph.bfs("A").toString());
		}
	}
	
	@Nested
	class BFSToDisconnectedGraphTests {
		@Test
		void bfsToCompleteGraph() {
			//use 3*3 adjacency matrix graph
			smallGraph.addNode(nodeA);
			smallGraph.addNode(nodeB);
			smallGraph.addNode(nodeC);
			smallGraph.addEdge(nodeA, nodeB);
			smallGraph.addEdge(nodeB, nodeC);
			smallGraph.addEdge(nodeC, nodeA);
			assertEquals("[A, B, C]", smallGraph.bfsToDisconnectedGraph("A").toString());
		}
		@Test
		void bfsToDisconnectedGraph() {
			//all the nodes in the graph should be showed
			smallGraph.addNode(nodeA);
			smallGraph.addNode(nodeB);
			smallGraph.addNode(nodeC);
			// node B is disconnected
			smallGraph.addEdge(nodeA, nodeC);
			assertEquals("[A, C, B]", smallGraph.bfsToDisconnectedGraph("A").toString());
		}
	}
	
	@Nested
	class DFSTests {
		@Test
		void dfsToCompleteGraph() {
			/*
			 *             node1
			 *            /     \
			 *           /       \
			 *        node2     node3
			 *        /   \
			 *       /     \
			 *    node4   node5
			 */   
			
			assertEquals("[1, 2, 4, 5, 3]", completeGraph.dfs(node1).toString());
		}
		@Test
		void dfsToDisconnectedGraph() {
			//only the nodes that are connected to the specified node should be showed.
			/*
			 *             node1
			 *            /     
			 *           /       
			 *        node2     node3
			 *        /   \       \
			 *       /     \       \
			 *    node4   node5    node6
			 */  
			
			//node1, 2, and 4 should be searched from node1
			assertEquals("[1, 2, 4, 5]", disconnectedGraph.dfs(node1).toString());
		}
	}
	
	@Nested
	class DFSToDisconnectedGraphTests {
		@Test
		void dfsToCompleteGraph() {
			/*
			 *             node1
			 *            /     \
			 *           /       \
			 *        node2     node3
			 *        /   \
			 *       /     \
			 *    node4   node5
			 */   
			
			assertEquals("[1, 2, 4, 5, 3]", completeGraph.dfsToDisconnectedGraph(node1).toString());
		}
		@Test
		void dfsToDisconnectedGraph() {
			//all the nodes in the graph should be showed
			/*
			 *             node1
			 *            /     
			 *           /       
			 *        node2     node3
			 *        /   \       \
			 *       /     \       \
			 *    node4   node5    node6
			 */ 
			
			//node1, 2, and 4 should be searched first, then node3 and 5.
			assertEquals("[1, 2, 4, 5, 3, 6]", disconnectedGraph.dfsToDisconnectedGraph(node1).toString());
		}
	}
	
	@Nested
	class Operations {
		@Test
		void operationsGraph() {
			//use 3*3 adjacency matrix graph
			smallGraph.addNode(nodeA);
			//add loop edge
			smallGraph.addEdge(nodeA, nodeA);
			assertEquals("[A, A]", smallGraph.toString());
			assertTrue(smallGraph.hasEdge(nodeA, nodeA));
			//remove loop edge
			assertTrue(smallGraph.removeEdge(nodeA, nodeA));
			assertFalse(smallGraph.hasEdge(nodeA, nodeA));
			assertEquals("", smallGraph.toString());
			
			smallGraph.addNode(nodeB);
			smallGraph.addNode(nodeC);

			smallGraph.addEdge(nodeA, nodeB);
			smallGraph.addEdge(nodeA, nodeC);
			smallGraph.addEdge(nodeB, nodeC);
			String completeGraph = "[A, B] [B, A], [A, C] [C, A], [B, C] [C, B]";
			
			assertEquals(completeGraph, smallGraph.toString());
			
			assertTrue(smallGraph.hasEdge(nodeA, nodeB));
			assertTrue(smallGraph.hasNode(nodeB));
			assertEquals(2, smallGraph.degree(nodeA));
			
			//add node more than the graph size(should be ignored)
			
			smallGraph.addNode(nodeD);
			assertFalse(smallGraph.hasNode(nodeD));
			assertEquals(completeGraph, smallGraph.toString());
			assertEquals("[" + nodeA + ", " + nodeB + ", " + nodeC + "]", Arrays.toString(smallGraph.getNodes()));
			
			smallGraph.clear();
			assertEquals("", smallGraph.toString());
			assertEquals("[]", Arrays.toString(smallGraph.getNodes()));
		}
	}
}
