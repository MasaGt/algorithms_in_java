package stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LinkStackTest {

	@Nested
	class InitTest {
		@Test
		void initEmptyStack() {
			LinkStack<String> emptyStack = new LinkStack<String>();
			assertEquals("", emptyStack.toString());
		}
		@Test
		void initNonEmptyStack() {
			LinkStack<Integer> nonEmptyStack = new LinkStack<Integer>(new Integer[] {1, 5 ,10});
			assertEquals("10, 5, 1", nonEmptyStack.toString());
		}
	}
}
