package list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DoublyLikedListTest {

	@Nested
	class InitTests {
		@Test
		void initEmptyList() {
			DoublyLikedList<String> emptyStrList = new DoublyLikedList<String>();
			assertEquals("", emptyStrList.toString());
		}
		@Test
		void initNonEmptyList() {
			DoublyLikedList<Integer> nonEmptyIntList = new DoublyLikedList<Integer>(new Integer[] {1,5,10});
			assertEquals("1, 5, 10", nonEmptyIntList.toString());
		}
	}
	
	@Nested
	class AddTests {
		@Test
		void addToNonEmptyList() {
			DoublyLikedList<Integer> nonEmptyIntList = new DoublyLikedList<Integer>(new Integer[] {1,5,10});
			nonEmptyIntList.add(200);
			assertEquals("1, 5, 10, 200", nonEmptyIntList.toString());
		}
	}

}
