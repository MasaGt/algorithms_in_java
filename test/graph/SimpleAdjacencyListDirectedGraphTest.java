package graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SimpleAdjacencyListDirectedGraphTest {

	private SimpleAdjacencyListDirectedGraph graph;
	
	@BeforeEach
	void prep () {
		graph = new SimpleAdjacencyListDirectedGraph(); //5*5 graph
	}
	
	@Nested
	class InitTests {
		@Test
		void initInvalidGraph() {
			//initialize too small graph
			assertThrows(IllegalArgumentException.class, () -> {new SimpleAdjacencyListDirectedGraph(0);});	
		}
	}
	
	@Nested
	class AddEdgeTests {
		@Test
		void addValidEdge() {
			graph.addEdge(0, 1);
			//there should be a directed edge from 0 to 1
			/*
			 * 0: 1
			 * 1:
			 * 2: 
			 * 3: 
			 * 4: 
			 */
			String expect = "0: 1\n1: \n2: \n3: \n4: \n";
			assertEquals(expect, graph.toString());
		}
		@Test
		void addInvalidEdge() {
			//add edge between nodes that is beyond the size of graph (5*5)
			assertThrows(IndexOutOfBoundsException.class, ()-> {graph.addEdge(0, 5);});
		}
		@Test
		void checkTheOrderOfEdge() {
			/*
			 * There are edges between 0 and 1, and 1 and 2.
			 * But the graph should be as follows,  regardless of when each edge is added.
			 * 
			 * 0: 1, 2
			 * 1:
			 * 2:
			 * 3:
			 * 4:
			 */
			graph.addEdge(0, 2); //directed edge from 0 -> 2
			graph.addEdge(0, 1); //directed edge from 0 -> 1
			
			String expect = "0: 1, 2\n1: \n2: \n3: \n4: \n";
			assertEquals(expect, graph.toString());
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
			graph.removeEdge(2, 0);
			String expect = "0: \n1: 0\n2: \n3: \n4: \n";
			assertEquals(expect, graph.toString());
		}
		@Test
		void removeInvalidEdge() {
			//remove edge between two nodes that is boyond the size of the graph
			assertThrows(IndexOutOfBoundsException.class, () -> {graph.removeEdge(0, 5);});
		}
		@Test
		void removeReverseEdge() {
			graph.addEdge(1, 0);
			//this should be ignored because there is not a edge from 0 -> 1
			graph.removeEdge(0, 1);
			String expect = "0: \n1: 0\n2: \n3: \n4: \n";
			assertEquals(expect, graph.toString());
		}
	}
	
	@Nested
	class Operations {
		@Test
		void manipulateGraph() {
			SimpleAdjacencyListDirectedGraph smallGraph = new SimpleAdjacencyListDirectedGraph(3);
			
			smallGraph.addEdge(0, 0);
			smallGraph.addEdge(1, 2);
			String expect = "0: 0\n1: 2\n2: \n";
			assertEquals(expect, smallGraph.toString());
			
			smallGraph.addEdge(2, 0);
			smallGraph.removeEdge(1, 2);
			expect = "0: 0\n1: \n2: 0\n";
			assertEquals(expect, smallGraph.toString());
			
			assertTrue(smallGraph.hasEdge(0, 0));
			assertFalse(smallGraph.hasEdge(1, 2));
			
			smallGraph.clear();
			expect = "0: \n1: \n2: \n";
			assertEquals(expect, smallGraph.toString());
		}
	}
}
