package queue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	Queue<String> emptyQueue;
	Queue<Integer> nonEmptyQueue;
	// queue for the comparison of equals() to nonEmptyQueue
	Queue<Integer> targetQueue;
	
	@BeforeEach
	void prep() {
		emptyQueue = new ArrayQueue<String>();
		nonEmptyQueue = new ArrayQueue<Integer>(new Integer[] {1, 5, 10});
		targetQueue = new ArrayQueue<Integer>();
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
			assertEquals("5, 10", nonEmptyQueue.toString());
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

			// expand size
			String contents = "";
			for (int i = 0; i < 25; i++) {
				contents += (i != 24) ? i + ", " : i;
				emptyQueue.enqueue(Integer.toString(i));
			}
			assertEquals(contents, emptyQueue.toString());
			assertEquals(25, emptyQueue.size());
			
		}
	}
}
