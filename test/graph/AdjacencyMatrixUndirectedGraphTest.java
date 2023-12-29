package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixUndirectedGraphTest {

	private AdjacencyMatrixUndirectedGraph<Integer> graph;
	@BeforeEach
	void prep() {
		graph = new AdjacencyMatrixUndirectedGraph<Integer>();
	}
	
	@Nested
	class InitTests {
		@Test
		void initEmptyGraph() {
			assertEquals("", graph.toString());
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
			graph.addNode(10);
			//there is no connection in the graph
			assertEquals("", graph.toString());
			assertEquals(1, graph.getNodes().length);
			assertEquals("[10]", Arrays.toString(graph.getNodes()));
		}
	}
	
	/**
	 * Check there is a connection between the specified nodes
	 */
	@Nested
	class AddEdgeTests {
		@Test
		void addEdge() {
			graph.addNode(1);
			graph.addNode(2);
			graph.addEdge(1, 2);
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
			graph.addNode(1);
			assertTrue(graph.removeNode(1));
			assertEquals(0, graph.getNodes().length);
			assertEquals("[]", Arrays.toString(graph.getNodes()));
		}
		/**
		 * Check the node is not removed if the specified node doed not exist in the graph
		 */
		@Test
		void removeInexstingNode() {
			graph.addNode(1);
			assertFalse(graph.removeNode(2));
			assertEquals(1, graph.getNodes().length);
			assertEquals("[1]", Arrays.toString(graph.getNodes()));
		}
		//TODO: try to remove nodeA and check if nodeA and edges between nodeA and another node are removed
		/**
		 * Before remove NodeA
		 * ->		[NodeA] - [NodeB]
		 * After remove nodeA
		 * ->		[NodeB]
		 */
	}
	
	@Nested
	class RemoveEdgeTests {
		@Test
		void removeExstingEdge() {
			graph.addNode(1);
			graph.addNode(2);
			graph.addEdge(1, 2);
			assertTrue(graph.removeEdge(1, 2));
			assertEquals("", graph.toString());
		}
		@Test
		void removeInexstingEdge() {
			graph.addNode(1);
			graph.addNode(2);
			graph.addEdge(1, 2);
			//try to remove edges that does not exist in the graph
			assertFalse(graph.removeEdge(1, 3));
			assertEquals("[1, 2] [2, 1]", graph.toString());
		}
	}
	
	@Nested
	class GetNodesTests {
		@Test
		void getNodes() {
			graph.addNode(1);
			graph.addNode(2);
			graph.addNode(100);
			assertEquals(3, graph.getNodes().length);
			assertEquals("[1, 2, 100]", Arrays.toString(graph.getNodes()));
		}
	}
	
	@Nested
	class HasEdgeTests {
		@Test
		void checkExistingEdge() {
			Integer value1 = 1;
			Integer value2 = 2;
			graph.addNode(value1);
			graph.addNode(value2);
			graph.addEdge(value1, value2);
			assertTrue(graph.hasEdge(value1, value2));
		}
		@Test
		void checkInexstingEdge() {
			Integer value1 = 1;
			Integer value2 = 2;
			graph.addNode(value1);
			graph.addNode(value2);
			assertFalse(graph.hasEdge(value1, value2));
		}
	}
}
