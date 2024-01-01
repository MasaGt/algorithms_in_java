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
}
