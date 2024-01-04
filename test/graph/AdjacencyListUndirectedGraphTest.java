package graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdjacencyListUndirectedGraphTest {

	private AdjacencyListUndirectedGraph<String> graph;
	private AdjacencyListUndirectedGraph<String> completeGraph;
	private AdjacencyListUndirectedGraph<String> disconnectedGraph;
	
	private String nodeA = "A";
	private String nodeB = "B";
	private String nodeC = "C";
	private String nodeD = "D";
	private String nodeE = "E";
	private String nodeF = "F";
	
	@BeforeEach
	void prep() {
		graph = new AdjacencyListUndirectedGraph<String>();
		
		completeGraph = new AdjacencyListUndirectedGraph<String>();
		disconnectedGraph = new AdjacencyListUndirectedGraph<String>();
		/*
		 *             nodeA
		 *            /     \
		 *           /       \
		 *        nodeB     nodeC
		 *        /   \
		 *       /     \
		 *    nodeD   nodeE
		 */   
		completeGraph.addNode(nodeA);
		completeGraph.addNode(nodeB);
		completeGraph.addNode(nodeC);
		completeGraph.addNode(nodeD);
		completeGraph.addNode(nodeE);
		completeGraph.addEdge(nodeA, nodeB);
		completeGraph.addEdge(nodeC, nodeA);
		completeGraph.addEdge(nodeB, nodeD);
		completeGraph.addEdge(nodeE, nodeB);
		
		/*
		 *             nodeA
		 *            /     
		 *           /       
		 *        nodeB     nodeC
		 *        /   \       \
		 *       /     \       \
		 *    nodeD   nodeE    nodeF
		 */
		disconnectedGraph.addNode(nodeA);
		disconnectedGraph.addNode(nodeB);
		disconnectedGraph.addNode(nodeC);
		disconnectedGraph.addNode(nodeD);
		disconnectedGraph.addNode(nodeE);
		disconnectedGraph.addNode(nodeF);
		disconnectedGraph.addEdge(nodeA, nodeB);
		disconnectedGraph.addEdge(nodeC, nodeF);
		disconnectedGraph.addEdge(nodeD, nodeB);
		disconnectedGraph.addEdge(nodeB, nodeE);
	}
	
	@Nested
	class InitTests {
		@Test
		void initGraph() {
			//not add nodes and edges yet 
			assertEquals("[]", Arrays.toString(graph.getNodes()));
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class AddNodeTests {
		@Test
		void addValidNode() {
			graph.addNode(nodeA);
			assertEquals("[A]", Arrays.toString(graph.getNodes()));
			assertEquals("", graph.toString());
		}
		@Test
		void addInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.addNode(null);});
		}
		@Test
		void addDuplicateNode() {
			graph.addNode(nodeA);
			//this should be ignored
			graph.addNode(nodeA);
			assertEquals(1, graph.getNodes().length);
			assertEquals("[A]", Arrays.toString(graph.getNodes()));
		}
	}
	
	@Nested
	class AddEdgeTests {
		@Test
		void addValidEdge() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addEdge(nodeA, nodeB);
			graph.addNode(nodeC);
			graph.addEdge(nodeC, nodeA);
			assertEquals("[A, B] [B, A], [A, C] [C, A]", graph.toString());
		}
		@Test
		void addInValidEdge() {
			graph.addNode(nodeA);
			assertThrows(IllegalArgumentException.class, () -> {graph.addEdge(nodeA, null);;});
		}
		@Test
		void addEdgeToinExistingNode() {
			graph.addNode(nodeA);
			graph.addEdge(nodeA, nodeB);
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class RemoveNodeTests {
		@Test
		void removeExistingNode() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addNode(nodeC);
			graph.addEdge(nodeA, nodeB);
			graph.addEdge(nodeC, nodeA);
			graph.removeNode(nodeA);
			assertEquals("", graph.toString());
		}
		@Test
		void removeInxistingNode() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addEdge(nodeA, nodeB);
			graph.removeNode(nodeC);
			assertEquals("[A, B] [B, A]", graph.toString());
		}
		@Test
		void removeInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.removeNode(null);});
		}
		
	}
	
	@Nested
	class RemoveEdgeTests {
		@Test
		void removeExistingEdge() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addEdge(nodeA, nodeB);
			graph.removeEdge(nodeA, nodeB);
			assertEquals("", graph.toString());
		}
		@Test
		void removeInexistingEdge() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addEdge(nodeA, nodeB);
			graph.removeEdge(nodeA, nodeC);
			assertEquals("[A, B] [B, A]", graph.toString());
		}
		@Test
		void removeInvaidEdge() {
			graph.addNode(nodeA);
			assertThrows(IllegalArgumentException.class, () -> {graph.removeEdge(nodeA, null);});
		}
	}
	
	@Nested
	class HasEdgeTests {
		@Test
		void checkExistingEdge() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addEdge(nodeA, nodeB);
			assertTrue(graph.hasEdge(nodeA, nodeB));
		}
		@Test
		void checkInexstingEdge() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			assertFalse(graph.hasEdge(nodeA, nodeB));
		}
		@Test
		void checkInvalidEdge () {
			graph.addNode(nodeA);
			assertThrows(IllegalArgumentException.class, () -> {graph.hasEdge(nodeA, null);});
		}
	}
	
	@Nested
	class HasNodeTests {
		@Test
		void checkExistingNode () {
			graph.addNode(nodeA);
			assertTrue(graph.hasNode(nodeA));
		}
		@Test
		void checkInexistingNode () {
			assertFalse(graph.hasNode(nodeA));
		}
		@Test
		void checkInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.hasNode(null);});
		}
	}
	
	@Nested
	class DegreeTests {
		@Test
		void zeroAdjacentNode() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			assertEquals(0, graph.degree(nodeA));
		}
		void twoAdjacentNode() {
			graph.addNode(nodeA);
			graph.addNode(nodeB);
			graph.addNode(nodeC);
			
			graph.addEdge(nodeB, nodeA);
			graph.addEdge(nodeC, nodeA);
			assertEquals(2, graph.degree(nodeA));
		}
		@Test
		void checkDegreeOfInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.degree(null); });
		}
	}
	
	@Nested
	class ClearTests {
		@Test
		void clear() {
			graph.addNode(nodeA);
			graph.addNode(nodeC);
			graph.addEdge(nodeA, nodeB);
			graph.clear();
			
			assertEquals("[]", Arrays.toString(graph.getNodes()));
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class BFSTests {
		@Test
		void bfsToCompleteGraph() {
			/*
			 *             nodeA
			 *            /     \
			 *           /       \
			 *        nodeB     nodeC
			 *        /   \
			 *       /     \
			 *    nodeD   nodeE
			 */
			assertEquals("[A, B, C, D, E]", completeGraph.bfs(nodeA).toString());
		}
		@Test
		void bfsToDisconnectedGraph() {
			/*
			 *             nodeA
			 *            /     
			 *           /       
			 *        nodeB     nodeC
			 *        /   \       \
			 *       /     \       \
			 *    nodeD   nodeE    nodeF
			 */
			//only the nodes that are connected to the specified node should be showed.
			//node C and F are disconnected from nodeA
			assertEquals("[A, B, D, E]", disconnectedGraph.bfs(nodeA).toString());
		}
	}
	
	@Nested
	class BFSToDisconnectedGraphTests {
		@Test
		void bfsToCompleteGraph() {
			/*
			 *             nodeA
			 *            /     \
			 *           /       \
			 *        nodeB     nodeC
			 *        /   \
			 *       /     \
			 *    nodeD   nodeE
			 */
			//this should works as normal bfs()
			assertEquals("[A, B, C, D, E]", completeGraph.bfs(nodeA).toString());
		}
		@Test
		void bfsToDisconnectedGraph() {
			/*
			 *             nodeA
			 *            /     
			 *           /       
			 *        nodeB     nodeC
			 *        /   \       \
			 *       /     \       \
			 *    nodeD   nodeE    nodeF
			 */
			//all the nodes in the graph should be shown including disconnected nodes.
			//node C and E are disconnected from nodeA
			assertEquals("[A, B, D, E, C, F]", disconnectedGraph.bfsToDisconnectedGraph(nodeA).toString());
		}
	}
	
	@Nested
	class DFSTests {
		@Test
		void dfsToCompleteGraph() {
			/*
			 *             nodeA
			 *            /     \
			 *           /       \
			 *        nodeB     nodeC
			 *        /   \
			 *       /     \
			 *    nodeD   nodeE
			 */
			assertEquals("[A, B, D, E, C]", completeGraph.dfs(nodeA).toString());
		}
		@Test
		void dfsToDisconnectedGraph() {
			//only the nodes that are connected to the specified node should be showed.
			/*
			 *             nodeA
			 *            /     
			 *           /       
			 *        nodeB     nodeC
			 *        /   \       \
			 *       /     \       \
			 *    nodeD   nodeE    nodeF
			 */
			assertEquals("[A, B, D, E]", disconnectedGraph.dfs(nodeA).toString());
		}
	}
	
	@Nested
	class DFSToDisconnectedGraphTests {
		@Test
		void dfsToCompleteGraph() {
			//like dfs(), all the node should be searched.
			/*
			 *             nodeA
			 *            /     \
			 *           /       \
			 *        nodeB     nodeC
			 *        /   \
			 *       /     \
			 *    nodeD   nodeE
			 */   
			assertEquals("[A, B, D, E, C]", completeGraph.dfsToDisconnectedGraph(nodeA).toString());
		}
		@Test
		void dfsToDisconnectedGraph() {
			//nodeA, B, and D should be searched first, then nodeC and E.
			/*
			 *             nodeA
			 *            /     
			 *           /       
			 *        nodeB     nodeC
			 *        /   \       \
			 *       /     \       \
			 *    nodeD   nodeE    nodeF
			 */
			assertEquals("[A, B, D, E, C, F]", disconnectedGraph.dfsToDisconnectedGraph(nodeA).toString());
		}
	}
	
	@Nested
	class Operations {
		@Test
		void operationsGraph() {
			graph.addNode(nodeA);
			//add loop edge
			graph.addEdge(nodeA, nodeA);
			assertEquals("[A, A]", graph.toString());
			assertTrue(graph.hasEdge(nodeA, nodeA));
			//remove loop edge
			assertTrue(graph.removeEdge(nodeA, nodeA));
			assertFalse(graph.hasEdge(nodeA, nodeA));
			assertEquals("", graph.toString());
			
			graph.addNode(nodeB);
			graph.addNode(nodeC);

			graph.addEdge(nodeA, nodeB);
			graph.addEdge(nodeA, nodeC);
			graph.addEdge(nodeB, nodeC);
			String completeGraph = "[A, B] [B, A], [A, C] [C, A], [B, C] [C, B]";
			
			assertEquals(completeGraph, graph.toString());
			
			assertTrue(graph.hasEdge(nodeA, nodeB));
			assertTrue(graph.hasNode(nodeB));
			assertEquals(2, graph.degree(nodeA));
			
			graph.clear();
			assertEquals("", graph.toString());
			assertEquals("[]", Arrays.toString(graph.getNodes()));
		}
	}
}
