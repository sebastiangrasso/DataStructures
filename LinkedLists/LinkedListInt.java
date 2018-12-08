package lmu.cmsi281.assignments;

/**
 * CMSI Assignment 2
 * @author <Grasso, Sebastian>
 *
 */
public class LinkedListInt {

	int  size;
	Node head;
	
	class Node {
		int  data;
		Node next;
		
		public Node(int d) {
			data = d;
			next = null;
		}
	}
	
	public LinkedListInt() {
		head = null;
		size = size();
	}
	
	// Returns the current size of the Linked List 
	public int size() {
		
		Node curr = head;
		size =0;
		
		while (curr != null) {
			curr = curr.next;
			size++;
		}
		
	    return size;
	}

	// Returns the value stored in the data field of the Node at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public int get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		// Iterate to specified position
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		return curr.data;
	}

	// Sets the value stored in the data field of the Node at the given index with element
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void set(int index, int element) {
		// TODO: Implement the functionality of set
		
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// Iterate to specified position
		Node curr = head;
		for (int i = 0; i < index; i++) {
			curr = curr.next;
		}
		curr.data = element;
		
	}

	// Adds a new element to the Linked List by adding a new Node
	public void add(int element) {
		Node node = new Node(element);
		// If there is nothing in the Linked List
		if (head == null) {
			head = node;
		} else {
			Node curr = head;
			// Iterate until we arrive at the last node
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = node;
		}
		size = size + 1;
	}

	// Inserts a new element as a new Node into the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void insert(int index, int element) {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node insert = new Node(element);
		
	    if (index == 0) {
	        insert.next = head;
	        head = insert;
	    }
	    else {
		    Node prev = null;
		    Node curr = head;
		    int i = 0;
		    while (curr !=null && i < index) {
		        prev = curr;
		        curr = curr.next;
		        i++;
		    }
		    insert.next = prev.next;
		    prev.next = insert;
	    }
	    
		size = size + 1;
	}

	// Removes the element at a given index from the Linked List
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void remove(int index) throws IndexOutOfBoundsException {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		
		Node curr = head;
		for (int i = 0; i < index-1; i++) {
			curr = curr.next;
		}
		
		if(index == 0) {
			head = curr.next;
		} else {
			curr.next = curr.next.next;
		}
		
		size = size - 1;
	}
	
	// Returns true if the Linked List contains the element, else false
	public boolean contains(int element) {
		// TODO: Implement the functionality of contains
		// Note: a placeholder of return true is given so the skeleton compiles
		boolean merit = false;
		
	    for(Node n = head; n.next != null; n = n.next) {
	    	if (n.data == element) {
	    		merit = true;
	    	}
	    }
	    	
		
		return merit;
	}
	
	// Add all the given elements as Nodes to the Linked List at the given index
	// If the index is out of bounds then throw an IndexOutOfBoundsException
	public void addAll(int index, int[] elements) {
		for (int i =elements.length-1; i >= 0; i--) {
			insert(index, elements[i]);
		}
		
		size = size + elements.length;
	}
	
	public String toString() {
		if (head == null) {
			return "[ ]";
		}
		String out = "[ ";
		Node curr = head;
		while (curr.next != null) {
			out = out + curr.data + ", ";
			curr = curr.next;
		}
		
		out = out + curr.data + " ]";
		return out;
	}
	public static void main(String[] args) {
		LinkedListInt a = new LinkedListInt();
		LinkedListInt b = new LinkedListInt();
		
		a.add(7);
		a.add(11);
		a.add(93);
		System.out.println("a = " + a.toString());
		b.add(7);
		b.add(11);
		b.add(93);
		System.out.println("b = " + b.toString());
				
		a.insert(0, 15);
		a.insert(3, 44);
		a.insert(a.size(), 20);
		System.out.println("a = " + a.toString());
		
		a.set(0, 20);
		a.set(3, 15);
		a.set(5, 44);
		System.out.println("a = " + a.toString());

		a.remove(0);
		System.out.println("a = " + a.toString()); 
		a.remove(2);
		System.out.println("a = " + a.toString()); 
		a.remove(3);
		System.out.println("a = " + a.toString()); 
		
		a.addAll(0, new int[]{ 0, 1, 2});
		System.out.println("a = " + a.toString());
	
	
	}
}
