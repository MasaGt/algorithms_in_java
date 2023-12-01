package list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
	
	private SinglyLinkedList<String> emptyList;
	private SinglyLinkedList<Integer> nonEmptyList;
	
	@BeforeEach
	public void prepLists() {
		emptyList = new SinglyLinkedList<String>();
		nonEmptyList = new SinglyLinkedList<Integer>(new Integer[] {1, 5, 10});
	}
	
	@Nested
	class InitTests {
		@Test
		void emptyList() {
			assertEquals("", emptyList.toString());
		}
		@Test
		void nonEmptyList() {
			assertEquals("1, 5, 10", nonEmptyList.toString());
		}
	}
	
	@Nested
	class addTests {
		@Test
		void addValue() {
			String msg = "Hello";
			emptyList.add(msg);
			assertEquals(msg, emptyList.toString());
		}
		@Test
		void addReturn() {
			String msg = "Hello";
			boolean result = emptyList.add(msg);
			assertTrue(result);
		}
		@Test
		void addFirst() {
			String msg = "Hello";
			emptyList.addFirst(msg);
			assertEquals(msg, emptyList.get(0));
			
			Integer num = 0;
			nonEmptyList.addFirst(num);
			assertEquals(num, nonEmptyList.get(0));
		}
		@Test
		void addLast() {
			String msg = "Hello";
			emptyList.addLast(msg);
			assertEquals(msg, emptyList.get(0));
			
			Integer num = 0;
			nonEmptyList.addLast(num);
			assertEquals(num, nonEmptyList.get(3));
		}
//		add test for addAt(int index, T value)
	}
	
	@Nested
	class getTests {
		@Test
		void getValue() {
			assertEquals(1, nonEmptyList.get(0));
		}
		@Test
		void getFirst() {
			assertEquals(1, nonEmptyList.getFirst());
		}
		@Test
		void getLast() {
			assertEquals(10, nonEmptyList.getLast());
		}
		@Test
		void getOutOfRange() {
			assertThrows(IndexOutOfBoundsException.class, () -> { emptyList.get(0); });
		}
		@Test
		void getFirst_NonExistItem() {
			assertThrows(NoSuchElementException.class, () -> { emptyList.getFirst(); });
		}
		@Test
		void getLast_NonExistItem() {
			assertThrows(NoSuchElementException.class, () -> { emptyList.getLast(); });
		}
	}
	
	@Nested
	class containesTests {
		@Test
		void containsEquivalentItem() {
			assertTrue(nonEmptyList.contains(1));
		}
		@Test
		void notContainsEquivalentItem() {
			assertFalse(nonEmptyList.contains(2));
		}
	}
	
	@Nested
	class equalsTests {
		@Test
		void haveDifferentItems() {
			
			SinglyLinkedList<Character> list1 = new SinglyLinkedList<>();
			list1.add('A');
			
			SinglyLinkedList<Character> list2 = new SinglyLinkedList<>();
			list2.add('B');
			
			assertFalse(list1.equals(list2));
		}
		@Test
		void haveSameItems() {
			Character c = 'A';
			
			SinglyLinkedList<Character> list1 = new SinglyLinkedList<>();
			list1.add(c);
			
			SinglyLinkedList<Character> list2 = new SinglyLinkedList<>();
			list2.add(c);
			
			assertTrue(list1.equals(list2));
		}
		@Test
		void haveEquivalentItems() {
			
			SinglyLinkedList<Character> list1 = new SinglyLinkedList<>();
			list1.add('C');
			
			SinglyLinkedList<Character> list2 = new SinglyLinkedList<>();
			list2.add('C');
			
			assertTrue(list1.equals(list2));
		}
	}
	// TODO: add test for remove and clear

}
