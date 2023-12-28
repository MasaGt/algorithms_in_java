package graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
