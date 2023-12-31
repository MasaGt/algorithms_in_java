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
			//add edge between two nodes that is boyond the size of the graph
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {graph.addEdge(5, 0);});
		}
	}
	
	@Nested
	class RemoveEdgeTests {
		@Test
		void removeValidEdge() {
			graph.addEdge(0, 1);
			graph.removeEdge(0, 1);
			assertEquals("", graph.toString());
		}
		@Test
		void removeInvalidEdge() {
			//remove edge between two nodes that is boyond the size of the graph
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {graph.removeEdge(0, 5);});
		}
	}
	
	@Nested
	class hasEdgeTests {
		@Test
		void checkExstingEdge() {
			graph.addEdge(0, 1);
			assertTrue(graph.hasEdge(0, 1));
		}
		@Test
		void checkInexstingEdge() {
			assertFalse(graph.hasEdge(0, 1));
		}
		@Test
		void checkInvalidEdge() {
			//check edge between two nodes that is boyond the size of the graph
			assertThrows(ArrayIndexOutOfBoundsException.class, () -> {graph.removeEdge(-1, 0);});
		}
	}
	
	@Nested
	class ClearTests {
		@Test
		void clear() {
			graph.addEdge(0, 0);
			graph.addEdge(0, 1);
			graph.addEdge(1, 2);
			graph.clear();
			assertEquals("", graph.toString());
		}
	}
	
	@Nested
	class Operations {
		@Test
		void manipulateGraph() {
			SimpleAdjacencyMatrixUndirectedGraph smallGraph = new SimpleAdjacencyMatrixUndirectedGraph(3);
			smallGraph.addEdge(0, 0);
			smallGraph.addEdge(1, 2);
			assertEquals("[0, 0], [1, 2] [2, 1]", smallGraph.toString());
			
			smallGraph.addEdge(0, 2);
			smallGraph.removeEdge(1, 2);
			assertEquals("[0, 0], [0, 2] [2, 0]", smallGraph.toString());
			
			assertTrue(smallGraph.hasEdge(2, 0));
			assertFalse(smallGraph.hasEdge(2, 2));
			
			smallGraph.clear();
			assertEquals("", smallGraph.toString());
		}
	}
}
