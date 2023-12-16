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
		}
		@Test
		void pushMoreThanInitizalArraySize() {
			Queue<Integer> fullQueue = new ArrayQueue<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
			fullQueue.enqueue(11);
			assertEquals("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11", fullQueue.toString());
		}
	}
	
	@Nested
	class DequeueTests {
		@Test
		void dequeueFromNonEmptyQueue() {
			Integer result = nonEmptyQueue.dequeue();
			assertEquals(1, result);
		}
		@Test
		void dequeueFromEmptyQueue() {
			assertThrows(NoSuchElementException.class, () -> { emptyQueue.dequeue(); });
		}
	}
}
