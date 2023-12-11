package stack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayStackTest {

	private ArrayStack<Integer> emptyStack;
	private ArrayStack<String> nonEmptyStack;
	private ArrayStack<Integer> fullStack;
	
	@BeforeEach
	void prep() {
		emptyStack = new ArrayStack<Integer>();
		nonEmptyStack = new ArrayStack<String>(new String[] {"Hello", "World"});
		fullStack = new ArrayStack<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}); 
		
	}
	@Nested
	class InitTests {
		@Test
		void emptyStack() {
			assertEquals("", emptyStack.toString());
		}
		@Test
		void nonEmptyStack() {
			assertEquals("World, Hello", nonEmptyStack.toString());
		}
	}
	
	@Nested
	class pushTests {
		@Test
		void push() {
			emptyStack.push(1);
			assertEquals("1", emptyStack.toString());
		}
		@Test
		void pushToNonEmptyStack() {
			nonEmptyStack.push("new item");
			assertEquals("new item, World, Hello", nonEmptyStack.toString());
		}
		@Test
		void pushManyValue() {
			fullStack.push(11);
			fullStack.push(12);
			assertEquals("12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1", fullStack.toString());
		}
	}
	
	@Nested
	class PopTests {
		@Test
		void popFromNonEmptyStack() {
			String result = nonEmptyStack.pop();
			assertEquals("World", result);
			assertEquals("Hello", nonEmptyStack.toString());
		}
		@Test
		void popFromEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> {emptyStack.pop();});
		}
	}
}
