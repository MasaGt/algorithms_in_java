package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SimpleAdjacencyMatrixUndirectedGraphTest {

	private SimpleAdjacencyMatrixUndirectedGraph graph;
	@BeforeEach
	void prep() {
		graph = new SimpleAdjacencyMatrixUndirectedGraph();
	}
	
	@Nested
	class AddEdgeTests {
		@Test
		void addValidEdge() {
			graph.addEdge(0, 0);
			graph.addEdge(0, 1);
			assertEquals("[0, 0], [0, 1] [1, 0]", graph.toString());
		}
		@Test
		void addInvalidEdge() {
			//add edge between two nodes that boyond the size of the graph
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {graph.addEdge(5, 0);} );
		}
	}
}
