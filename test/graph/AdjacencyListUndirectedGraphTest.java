package graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AdjacencyListUndirectedGraphTest {

	private AdjacencyListUndirectedGraph<String> graph;
	
	@BeforeEach
	void prep() {
		graph = new AdjacencyListUndirectedGraph<String>();
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
			graph.addNode("A");
			assertEquals("[A]", Arrays.toString(graph.getNodes()));
			assertEquals("", graph.toString());
		}
		@Test
		void addInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.addNode(null);});
		}
		@Test
		void addDuplicateNode() {
			graph.addNode("A");
			//this should be ignored
			graph.addNode("A");
			assertEquals(1, graph.getNodes().length);
			assertEquals("[A]", Arrays.toString(graph.getNodes()));
		}
	}
	
	@Nested
	class AddEdgeTests {
		@Test
		void addValidEdge() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addEdge("A", "B");
			graph.addNode("C");
			graph.addEdge("C", "A");
			assertEquals("[A, B] [B, A], [A, C] [C, A]", graph.toString());
		}
		@Test
		void addInValidEdge() {
			graph.addNode("A");
			assertThrows(IllegalArgumentException.class, () -> {graph.addEdge("A", null);;});
		}
		@Test
		void addEdgeToinExistingNode() {
			graph.addNode("A");
			graph.addEdge("A", "B");
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class RemoveNodeTests {
		@Test
		void removeExistingNode() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addNode("C");
			graph.addEdge("A", "B");
			graph.addEdge("C", "A");
			graph.removeNode("A");
			assertEquals("", graph.toString());
		}
		@Test
		void removeInxistingNode() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addEdge("A", "B");
			graph.removeNode("C");
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
			graph.addNode("A");
			graph.addNode("B");
			graph.addEdge("A", "B");
			graph.removeEdge("A", "B");
			assertEquals("", graph.toString());
		}
		@Test
		void removeInexistingEdge() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addEdge("A", "B");
			graph.removeEdge("A", "C");
			assertEquals("[A, B] [B, A]", graph.toString());
		}
		@Test
		void removeInvaidEdge() {
			graph.addNode("A");
			assertThrows(IllegalArgumentException.class, () -> {graph.removeEdge("A", null);});
		}
	}
	
	@Nested
	class HasEdgeTests {
		@Test
		void checkExistingEdge() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addEdge("A", "B");
			assertTrue(graph.hasEdge("A", "B"));
		}
		@Test
		void checkInexstingEdge() {
			graph.addNode("A");
			graph.addNode("B");
			assertFalse(graph.hasEdge("A", "B"));
		}
		@Test
		void checkInvalidEdge () {
			graph.addNode("A");
			assertThrows(IllegalArgumentException.class, () -> {graph.hasEdge("A", null);});
		}
	}
	
	@Nested
	class HasNodeTests {
		@Test
		void checkExistingNode () {
			graph.addNode("A");
			assertTrue(graph.hasNode("A"));
		}
		@Test
		void checkInexistingNode () {
			assertFalse(graph.hasNode("A"));
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
			graph.addNode("A");
			graph.addNode("B");
			assertEquals(0, graph.degree("A"));
		}
		void twoAdjacentNode() {
			graph.addNode("A");
			graph.addNode("B");
			graph.addNode("C");
			
			graph.addEdge("B", "A");
			graph.addEdge("C", "A");
			assertEquals(2, graph.degree("A"));
		}
		@Test
		void checkDegreeOfInvalidNode() {
			assertThrows(IllegalArgumentException.class, () -> {graph.degree(null); });
		}
	}
}
