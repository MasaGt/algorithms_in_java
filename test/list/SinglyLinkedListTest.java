package list;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
	
	private SinglyLinkedList<String> emptyList;
	private SinglyLinkedList<Integer> nonEmptyList;
	private String valueForEmptyList;
	
	@BeforeEach
	public void prepLists() {
		emptyList = new SinglyLinkedList<String>();
		nonEmptyList = new SinglyLinkedList<Integer>(new Integer[] {1, 5, 10});
		valueForEmptyList = "Hello";
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
			boolean result = emptyList.add(valueForEmptyList);
			assertEquals(valueForEmptyList, emptyList.toString());
			assertTrue(result);
		}
		@Test
		void addFirst() {
			// able to remove either one from the following
			emptyList.addFirst(valueForEmptyList);
			assertEquals(valueForEmptyList, emptyList.get(0));
			
			Integer num = 0;
			nonEmptyList.addFirst(num);
			assertEquals(num, nonEmptyList.get(0));
		}
		@Test
		void addLast() {
			// able to remove either one from the following
			emptyList.addLast(valueForEmptyList);
			assertEquals(valueForEmptyList, emptyList.get(0));
			
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
	class containsTests {
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
	
	@Nested
	class removeTest {
		@Test
		void removeItemAt() {
			int index = 1;
			Integer removedItem = nonEmptyList.remove(index);
			assertEquals(5, removedItem);
			assertEquals("1, 10", nonEmptyList.toString());
		}
		@Test
		void removeItemAtFromEmptyList() {
			int index = 1;
			assertThrows(IndexOutOfBoundsException.class, () -> { emptyList.remove(index); });
		}
	}
	
	@Nested
	class clearTest {
		@Test
		void clearAllNodesFromNonEmptyList() {
			nonEmptyList.clear();
			assertEquals("", nonEmptyList.toString());
		}
		@Test
		void clearAllNodesFromEmptyList() {
			emptyList.clear();
			assertEquals("", emptyList.toString());
		}
	}

}
