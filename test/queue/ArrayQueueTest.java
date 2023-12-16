package queue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	Queue<String> emptyQueue;
	Queue<Integer> nonEmptyQueue;
	
	@BeforeEach
	void prep() {
		emptyQueue = new ArrayQueue<String>();
		nonEmptyQueue = new ArrayQueue<Integer>(new Integer[] {1, 5, 10});
	}
	
	@Nested
	class InitTests {
		@Test
		void initEmptyQueue() {
			assertEquals("", emptyQueue.toString());
		}
		@Test
		void initNonEmptyQueue() {
			Queue<Integer> nonEmptyQueue = new ArrayQueue<Integer>(new Integer[] {1, 5, 10});
			assertEquals("1, 5, 10", nonEmptyQueue.toString());
		}
	}
	
	@Nested
	class EnqueueTests {
		@Test
		void push() {
			emptyQueue.enqueue("Test");
			assertEquals("Test", emptyQueue.toString());
			assertEquals(1, emptyQueue.size());
		}
		@Test
		void pushMoreThanInitialArraySize() {
			Queue<Integer> fullQueue = new ArrayQueue<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
			fullQueue.enqueue(11);
			assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11", fullQueue.toString());
			assertEquals(11, fullQueue.size());
		}
	}
	
	@Nested
	class DequeueTests {
		@Test
		void dequeueFromNonEmptyQueue() {
			assertEquals(1, nonEmptyQueue.dequeue());
			assertEquals(2, nonEmptyQueue.size());
		}
		@Test
		void dequeueFromEmptyQueue() {
			assertThrows(NoSuchElementException.class, () -> { emptyQueue.dequeue(); });
		}
	}
	
	@Nested
	class PeekTests {
		@Test
		void peekAtNonEmptyQueue() {
			assertEquals(1, nonEmptyQueue.peek());
			assertEquals(3, nonEmptyQueue.size());
		}
		@Test
		void peekAtEmptyQueue() {
			assertThrows(NoSuchElementException.class, () -> { emptyQueue.peek(); });
		}
	}
	
	@Nested
	class ClearTests {
		@Test
		void clearNonEmptyQueue() {
			nonEmptyQueue.clear();
			assertEquals("", nonEmptyQueue.toString());
			assertEquals(0, nonEmptyQueue.size());
		}
		@Test
		void clearEmptyQueue() {
			emptyQueue.clear();
			assertEquals("", emptyQueue.toString());
			assertEquals(0, emptyQueue.size());
		}
	}
	
	@Nested
	class IsEmptyTests {
		@Test
		void isEmptyToNonEmptyQueue() {
			assertFalse(nonEmptyQueue.isEmpty());
		}
		@Test
		void isEmptyToEmptyQueue() {
			nonEmptyQueue.clear();
			assertTrue(nonEmptyQueue.isEmpty());
		}
	}
	
	@Nested
	class ContainsTests {
		@Test
		void continasEquivalentItem() {
			assertTrue(nonEmptyQueue.contains(1));
		}
		@Test
		void notContainsEquivalentItem() {
			assertFalse(nonEmptyQueue.contains(0));
		}
	}
}
