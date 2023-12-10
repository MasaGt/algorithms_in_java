package list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DoublyLikedListTest {

	private DoublyLikedList<String> emptyStrList;
	private DoublyLikedList<Integer> nonEmptyIntList;
	
	@BeforeEach
	public void prep() {
		emptyStrList = new DoublyLikedList<String>();
		nonEmptyIntList = new DoublyLikedList<Integer>(new Integer[] {1,5,10});
	}
	
	@Nested
	class InitTests {
		@Test
		void initEmptyList() {
			assertEquals("", emptyStrList.toString());
		}
		@Test
		void initNonEmptyList() {
			assertEquals("1, 5, 10", nonEmptyIntList.toString());
		}
	}
	
	@Nested
	class AddTests {
		@Test
		void add() {
			nonEmptyIntList.add(200);
			assertEquals("1, 5, 10, 200", nonEmptyIntList.toString());
		}
		@Test
		void addFirst() {
			emptyStrList.add("Test");
			assertEquals("Test", emptyStrList.toString());
		}
		@Test
		void addLast() {
			emptyStrList.addLast("Last Node");
			assertEquals("Last Node", emptyStrList.toString());
		}
		@Test
		void addAtFirstHalf() {
			nonEmptyIntList.addAt(1, 4);
			assertEquals("1, 4, 5, 10", nonEmptyIntList.toString());
		}
		@Test
		void addAtSecondHalf() {
			nonEmptyIntList.addAt(2, 9);
			assertEquals("1, 5, 9, 10", nonEmptyIntList.toString());
		}
		@Test
		void addAtHead() {
			nonEmptyIntList.addAt(0, 0);
			assertEquals("0, 1, 5, 10", nonEmptyIntList.toString());
		}
		@Test
		void addAtTail() {
			nonEmptyIntList.addAt(3, 100);
			assertEquals("1, 5, 10, 100", nonEmptyIntList.toString());
		}
		@Test
		void addAtBeyondTheRange() {
			assertThrows(IndexOutOfBoundsException.class, () -> { nonEmptyIntList.addAt(5, 100); });
		}
	}
	
	@Nested
	class GetTest {
		@Test
		void getFirst() {
			int result = nonEmptyIntList.getFirst();
			assertEquals(1, result);
		}
		@Test
		void getLast() {
			int result = nonEmptyIntList.getLast();
			assertEquals(10, result);
		}
		@Test
		void get() {
			int result = nonEmptyIntList.get(1);
			assertEquals(5, result);
		}
		@Test
		void getFromEmptyList() {
			assertThrows(IndexOutOfBoundsException.class, () -> { emptyStrList.get(0); });
		}
	}
	
	@Nested
	class RemoveAt {
		@Test
		void removeFromNonEmptyList() {
			Integer removedItem = nonEmptyIntList.remove(1);
			assertEquals(5, removedItem);
			assertEquals("1, 10", nonEmptyIntList.toString());
		}
		@Test
		void removeFromEmptyList() {
			assertThrows(IndexOutOfBoundsException.class, () -> { emptyStrList.remove(0); });
		}
	}
	
	@Nested
	class clearTests {
		@Test
		void clearAllNodesFromNonEmptyList() {
			nonEmptyIntList.clear();
			assertEquals("", nonEmptyIntList.toString());
		}
		@Test
		void clearAllNodesFromEmptyList() {
			emptyStrList.clear();
			assertEquals("", emptyStrList.toString());
		}
	}
}
