package queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkQueueTest {
	@Nested
	class InitTests {
		@Test
		void emptyQueue() {
			LinkQueue<String> emptyQueue = new LinkQueue<String>();
			assertEquals("", emptyQueue.toString());
		}
		@Test
		void nonEmptyQueue() {
			LinkQueue<Integer> nonEmptyQueue = new LinkQueue<Integer>(new Integer[] {1, 5, 10});
			assertEquals("1, 5, 10", nonEmptyQueue.toString());
		}
	}
}
