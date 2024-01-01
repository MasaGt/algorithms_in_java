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
}
