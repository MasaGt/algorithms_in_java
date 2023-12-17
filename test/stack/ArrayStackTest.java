package stack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayStackTest {

	private Stack<Integer> emptyStack;
	private Stack<String> nonEmptyStack;
	private Stack<Integer> fullStack;
	
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
			assertEquals(1, emptyStack.size());
		}
		@Test
		void pushToNonEmptyStack() {
			nonEmptyStack.push("new item");
			assertEquals("new item, World, Hello", nonEmptyStack.toString());
			assertEquals(3, nonEmptyStack.size());
		}
		@Test
		void pushManyValue() {
			fullStack.push(11);
			fullStack.push(12);
			assertEquals("12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1", fullStack.toString());
			assertEquals(12, fullStack.size());
		}
	}
	
	@Nested
	class PopTests {
		@Test
		void popFromNonEmptyStack() {
			String result = nonEmptyStack.pop();
			assertEquals("World", result);
			assertEquals("Hello", nonEmptyStack.toString());
			assertEquals(1, nonEmptyStack.size());
		}
		@Test
		void popFromEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> {emptyStack.pop();});
		}
	}
	
	@Nested
	class peekTests {
		@Test
		void peekAtNonEmptyStack() {
			String result = nonEmptyStack.peek();
			assertEquals("World", result);
			assertEquals("World, Hello", nonEmptyStack.toString());
			assertEquals(2, nonEmptyStack.size());
		}
		@Test
		void peekAtEmptyStack() {
			assertThrows(NoSuchElementException.class, () -> {emptyStack.peek();});
		}
	}
	
	@Nested
	class isEmptyTests {
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
	class sizeTests {
		@Test
		void sizeOfFullStack() {
			assertEquals(10, fullStack.size());
		}
	}
	
	@Nested
	class ClearTests{
		@Test
		void clear() {
			nonEmptyStack.clear();
			assertEquals("", nonEmptyStack.toString());
			assertEquals(0, nonEmptyStack.size());
		}
	}
	
	@Nested
	class ContainsTests {
		@Test
		void continas() {
			String msg = "Hello";
			assertTrue(nonEmptyStack.contains(msg));
		}
		@Test
		void notContains() {
			String msg = "stack";
			assertFalse(nonEmptyStack.contains(msg));
		}
	}
	
	@Nested
	class EqualsTests {
		@Test
		void hasEquivalentItems() {
			ArrayStack<String> target = new ArrayStack<String>();
			target.push("Hello");
			target.push("World");
			assertTrue(nonEmptyStack.equals(target));
		}
		@Test
		void haveDifferentItems() {
			ArrayStack<String> target = new ArrayStack<String>();
			target.push("Test");
			target.push("World");
			assertFalse(nonEmptyStack.equals(target));
		}
		@Test
		void hasEquivalentItemsButLonger() {
			ArrayStack<String> target = new ArrayStack<String>();
			target.push("Hello");
			target.push("World");
			target.push("Test");
			assertFalse(nonEmptyStack.equals(target));
		}
	}
}
