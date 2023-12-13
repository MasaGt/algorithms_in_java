package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LinkStackTest {

	private LinkStack<String> emptyStack;
	private LinkStack<Integer> nonEmptyStack;
	
	@BeforeEach
	void prep() {
		emptyStack = new LinkStack<String>();
		nonEmptyStack = new LinkStack<Integer>(new Integer[] {1, 5 ,10});
	}
	
	@Nested
	class InitTest {
		@Test
		void initEmptyStack() {
			assertEquals("", emptyStack.toString());
		}
		@Test
		void initNonEmptyStack() {
			assertEquals("10, 5, 1", nonEmptyStack.toString());
		}
	}
	
	@Nested
	class pushTests {
		@Test
		void push() {
			nonEmptyStack.push(100);
			assertEquals("100, 10, 5, 1", nonEmptyStack.toString());
		}
	}
	
	@Nested
	class PopTests {
		@Test
		void popFromEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> { emptyStack.pop(); });
		}
		@Test
		void popFromNonEmptyStack() {
			assertEquals(10, nonEmptyStack.pop());
			assertEquals("5, 1", nonEmptyStack.toString());
		}
	}
	
	@Nested
	class PeekTests {
		@Test
		void peekAtNonEmptyStack() {
			assertEquals(10, nonEmptyStack.peek());
			assertEquals("10, 5, 1", nonEmptyStack.toString());
		}
		@Test
		void peekAtEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> { emptyStack.peek(); });
		}
	}
	
	@Nested
	class IsEmptyTests {
		@Test
		void isEmptyToEmptyStack() {
			assertTrue(emptyStack.isEmpty());
		}
		@Test
		void isEmptyToNonEmptyStack() {
			assertFalse(nonEmptyStack.isEmpty());
		}
	}
	
	@Nested
	class ClearTests{
		@Test
		void clear() {
			nonEmptyStack.clear();
			assertEquals("", nonEmptyStack.toString());
		}
	}
	
	@Nested
	class ContainsTests {
		@Test
		void contains() {
			assertTrue(nonEmptyStack.contains(1));
		}
		@Test
		void notContains() {
			assertFalse(nonEmptyStack.contains(0));
		}
	}
	
	@Nested
	class EqualsTests {
		@Test
		void hasEquivalentItems() {
			LinkStack<Integer> targetStack = new LinkStack<Integer>();
			targetStack.push(1);
			targetStack.push(5);
			targetStack.push(10);
			assertTrue(nonEmptyStack.equals(targetStack));
		}
		@Test
		void haveDifferentItems() {
			LinkStack<Integer> targetStack = new LinkStack<Integer>();
			targetStack.push(0);
			targetStack.push(5);
			targetStack.push(10);
			assertFalse(nonEmptyStack.equals(targetStack));
		}
	}
	
	@Nested
	class sizeTests {
		@Test
		void sizeOfNonEmptyList() {
			nonEmptyStack.push(100);
			assertEquals(4, nonEmptyStack.size());
		}
	}
}
