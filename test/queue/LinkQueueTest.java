package queue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LinkQueueTest {
	
	private LinkQueue<String> emptyQueue;
	LinkQueue<Integer> nonEmptyQueue;
	// queue for the comparison of equals() to nonEmptyQueue
	Queue<Integer> targetQueue;

	@BeforeEach
	void prep() {
		emptyQueue = new LinkQueue<String>();
		nonEmptyQueue = new LinkQueue<Integer>(new Integer[] {1, 5, 10});
		targetQueue = new ArrayQueue<Integer>();
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
	
	@Nested
	class ClearTests {
		@Test
		void clear() {
			nonEmptyQueue.clear();
			assertEquals("", nonEmptyQueue.toString());
			assertEquals(0, nonEmptyQueue.size());
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
	
	@Nested
	class EqualsTests {
		@Test
		void hasEquivalentItems() {
			targetQueue.enqueue(1);
			targetQueue.enqueue(5);
			targetQueue.enqueue(10);
			assertTrue(nonEmptyQueue.equals(targetQueue));
		}
		@Test
		void hasDifferentItems() {
			targetQueue.enqueue(1);
			targetQueue.enqueue(5);
			targetQueue.enqueue(0);
			assertFalse(nonEmptyQueue.equals(targetQueue));
		}
		@Test
		void hasEquivalenyItemsButLonger() {
			targetQueue.enqueue(1);
			targetQueue.enqueue(5);
			targetQueue.enqueue(10);
			targetQueue.enqueue(10);
			assertFalse(nonEmptyQueue.equals(targetQueue));
		}
	}
	
	@Nested
	class Operations {
		@Test
		void operationsToEmptyQueue() {
			// enqueue and dequeue
			emptyQueue.enqueue("Hello");
			emptyQueue.enqueue("World");
			assertEquals("Hello, World", emptyQueue.toString());
			
			emptyQueue.dequeue();
			emptyQueue.enqueue("Test");
			assertEquals("World, Test", emptyQueue.toString());
						
			assertEquals("World", emptyQueue.peek());
			assertEquals(2, emptyQueue.size());
						
			// contains and equals
			assertTrue(emptyQueue.contains("Test"));
			assertFalse(emptyQueue.contains("test"));
						
			Queue<String> newQueue = new ArrayQueue<String>();
			newQueue.enqueue("World");
			newQueue.enqueue("Test");
			assertTrue(emptyQueue.equals(newQueue));;
			newQueue.enqueue("Hello");
			assertFalse(emptyQueue.equals(newQueue));
						
			//clear
			emptyQueue.clear();
			assertEquals("", emptyQueue.toString());
			assertEquals(0, emptyQueue.size());
		}
	}
}
