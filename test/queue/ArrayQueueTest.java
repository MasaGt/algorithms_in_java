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
}
