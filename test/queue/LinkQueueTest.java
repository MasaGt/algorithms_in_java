package queue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkQueueTest {
	
	private LinkQueue<String> emptyQueue;
	LinkQueue<Integer> nonEmptyQueue;

	@BeforeEach
	void prep() {
		emptyQueue = new LinkQueue<String>();
		nonEmptyQueue = new LinkQueue<Integer>(new Integer[] {1, 5, 10});
	}
	
	@Nested
	class InitTests {
		@Test
		void emptyQueue() {
			assertEquals("", emptyQueue.toString());
		}
		@Test
		void nonEmptyQueue() {
			assertEquals("1, 5, 10", nonEmptyQueue.toString());
		}
	}
	
	@Nested
	class EnqueueTests {
		@Test
		void enqueue() {
			nonEmptyQueue.enqueue(100);
			assertEquals("1, 5, 10, 100", nonEmptyQueue.toString());
			assertEquals(4, nonEmptyQueue.size());
		}
	}
	
	@Nested
	class DequeueTests {
		@Test
		void dequeueFromNonEmptyQueue() {
			Integer removed = nonEmptyQueue.dequeue();
			assertEquals(1, removed);
			assertEquals("5, 10", nonEmptyQueue.toString());
			assertEquals(2, nonEmptyQueue.size());
		}
		@Test
		void dequeueFromEmptyuQueue() {
			assertThrows(NoSuchElementException.class, () -> { emptyQueue.dequeue(); });
		}
	}
	
	@Nested
	class PeekTests {
		@Test
		void peekAtNonEmptyQueue() {
			Integer peeked = nonEmptyQueue.peek();
			assertEquals(1, peeked);
			assertEquals("1, 5, 10", nonEmptyQueue.toString());
			assertEquals(3, nonEmptyQueue.size());
		}
		@Test
		void peekAtEmptyQueue() {
			assertThrows(NoSuchElementException.class, () -> { emptyQueue.peek(); } );
		}
	}
}
