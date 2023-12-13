package stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkStack<T> {
	
	private List<T> elements;
	
	public LinkStack() {
		elements = new LinkedList<T>();
	}
	
	public LinkStack(T[] values) {
		this();
		for (T val : values) {
			elements.addFirst(val);
		}
	}
	
	
	public void push(T value) {
		elements.addFirst(value);
	}
	
	@Override
	public String toString() {
		String contents = "";
		
		Iterator<T> iterator = elements.listIterator();
		while (iterator.hasNext()) {
			T value = iterator.next();
			contents += iterator.hasNext() ? value + ", " : value;
		}
		return contents;
	}
	

}
