package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
	
	@Nested
	class AddNodeTests {
		@Test
		void addNode() {
			graph.addNode(10);
			assertEquals("", graph.toString());
		}
	}
	
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
		@Test
		void removeExstingNode() {
			graph.addNode(1);
			assertTrue(graph.removeNode(1));
		}
		@Test
		void removeInexstingNode() {
			graph.addNode(1);
			assertFalse(graph.removeNode(2));
		}
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
}
