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
}
