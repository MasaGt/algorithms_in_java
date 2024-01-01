package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SimpleAdjacencyListUndirectedGraphTest {

	private SimpleAdjacencyListUndirectedGraph graph;
	@BeforeEach
	void perp() {
		graph = new SimpleAdjacencyListUndirectedGraph();
	}
	
	@Nested
	class AddEdgeTests {
		@Test
		void addValidEdge() {
			String expect = "0: 1\n1: 0\n2: \n3: \n4: \n";
			graph.addEdge(0, 1);
			/*
			 * Graph should be as follows
			 * 
			 * 0: 1
			 * 1: 0
			 * 2: 
			 * 3: 
			 * 4: 
			 */
			assertEquals(expect, graph.toString());
		}
		@Test
		void addInvalidEdge() {
			//add edge between nodes that is beyond the size of graph (5*5)
			assertThrows(IndexOutOfBoundsException.class, ()-> {graph.addEdge(0, 5);});
		}
	}
	
	@Nested
	class RemoveEdgeTests {
		@Test
		void removeValidEdge() {
			graph.addEdge(1, 0);
			graph.removeEdge(1, 0);
			String expect = "0: \n1: \n2: \n3: \n4: \n";
			assertEquals(expect, graph.toString());
		}
		@Test
		void removeInexstingEdge() {
			graph.addEdge(1, 0);
			String expect = "0: 1\n1: 0\n2: \n3: \n4: \n";
			graph.removeEdge(2, 0);
			assertEquals(expect, graph.toString());
		}
		@Test
		void removeInvalidEdge() {
			//remove edge between two nodes that is boyond the size of the graph
			assertThrows(IndexOutOfBoundsException.class, () -> {graph.removeEdge(0, 5);});
		}
	}
	
	@Nested
	class HasEdgeTests {
		@Test
		void checkExistingEdge() {
			graph.addEdge(1, 0);
			assertTrue(graph.hasEdge(0, 1));
			assertTrue(graph.hasEdge(1, 0));
		}
		@Test
		void checkInexstingEdge() {
			assertFalse(graph.hasEdge(0, 1));
		}
	}
}
