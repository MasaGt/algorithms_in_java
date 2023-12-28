package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixUndirectedGraphTest {

	@Nested
	class InitTests {
		@Test
		void initEmptyGraph() {
			AdjacencyMatrixUndirectedGraph<Integer> graph = new AdjacencyMatrixUndirectedGraph<Integer>();
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class AddNodeTests {
		@Test
		void addNode() {
			
		}
	}
}
