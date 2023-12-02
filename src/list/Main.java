package list;

public class Main {

	public static void main(String[] args) {

		SinglyLinkedList<String> list = new SinglyLinkedList<String>();
		list.add("Hello");
		list.addAt(1, "World");
		System.out.println(list);
		list.addAt(0, "Test");
		list.add("!");
		System.out.println(list);
		
		System.out.println(list.getFirst() + " is the first node in " + list);
		System.out.println(list.getLast() + " is the last node in " + list);
		System.out.println(list.get(2) +" is the 3 node in " + list);
		
		System.out.println("after remove " + list.remove(0) + ", list becomes " + list);
		System.out.println("after remove " + list.remove(2) + ", list becomes " + list);
		
		String target = "Hello";
		System.out.println("does the list conatins " + target + "?" + " -> " + list.contains(target));
		
		target = "test";
		System.out.println("does the list conatins " + target + "?" + " -> " + list.contains(target));
		
		list.clear();
		System.out.println("after calling clear -> " + list);
	}

}

