package queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Nested
	class InitTests {
		@Test
		void initEmptyQueue() {
			Queue<String> emptyQueue = new ArrayQueue<String>();
			assertEquals("", emptyQueue.toString());
		}
		@Test
		void initNonEmptyQueue() {
			Queue<Integer> nonEmptyQueue = new ArrayQueue<Integer>(new Integer[] {1, 5, 10});
			assertEquals("1, 5, 10", nonEmptyQueue.toString());
		}
	}
	
	@Nested
	class ExpandCapacityTests {
		@Test
		void addMoreThanInitizalArraySize() {
			Queue<Integer> fullQueue = new ArrayQueue<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
			fullQueue.enqueue(11);
			assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11", fullQueue.toString());
		}
	}
}
