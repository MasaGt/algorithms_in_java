package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
