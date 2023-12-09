package list;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		void addToNonEmptyList() {
			nonEmptyIntList.add(200);
			assertEquals("1, 5, 10, 200", nonEmptyIntList.toString());
		}
	}

}
