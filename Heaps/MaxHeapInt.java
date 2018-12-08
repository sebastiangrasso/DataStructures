package lmu.cmsi281.assignments;

import java.util.ArrayList;

class HeapNodeInt {
	private int data;
	private int index;
	
	public HeapNodeInt(int element, int n) {
		data = element;
		index = n;
	}
	
	public int getData() {
		return data;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setData(int element) {
		data = element;
	}
	
	public void setIndex(int n) {
		index = n;
	}
}

/**
 * @author sebas
 *
 */
public class MaxHeapInt {
	private ArrayList<HeapNodeInt> heap;
	
	public MaxHeapInt() {
		heap = new ArrayList<HeapNodeInt>();
	}
	
	public HeapNodeInt getRoot() {
		// TODO: returns the root of the heap
		return heap.get(0);
	}
	
	public HeapNodeInt getLeft(HeapNodeInt node) {
		// TODO: returns the left child of the given node if it exists
		if (node.getIndex()*2+1 < heap.size()){
			return heap.get(node.getIndex()*2+1);
		}
		else return null;				
	}
	
	public HeapNodeInt getRight(HeapNodeInt node) {
		// TODO: returns the right child of the given node if it exists
		
		if (node.getIndex()*2+2 < heap.size()){
			return heap.get(node.getIndex()*2+2);
		}
		else return null;
	}
	
	public HeapNodeInt getParent(HeapNodeInt node) {
		// TODO: returns the parent of the given if it exists
		
		return heap.get((int)(Math.floor((node.getIndex()-1)/2)));
	}
	
	public void addNode(int element) {
		// TODO: adds a new HeapNodeInt to the heap with the given input element
		// as its data
		
		HeapNodeInt curr = new HeapNodeInt(element, heap.size());
		heap.add(curr);
		
		int index = heap.size();
		
		while(curr.getData() > getParent(curr).getData()) {
			index = getParent(curr).getIndex();
			swap(curr, getParent(curr));
			
			curr = heap.get(index);
		}
	}
	
	public void deleteNode(int element) {
		// TODO: deletes the specified element from the heap while maintaining
		// the heap data structure constraints
		
		int location = -1;
		for (int i = 0; i < heap.size(); i++) {
			if (heap.get(i).getData()== element) {
				location = i;
			}
		}	
		if (location == -1) {
			throw new IllegalArgumentException("Element not in heap");
		}
		
		HeapNodeInt curr = heap.get(location);		
		swap(curr, heap.get(heap.size()-1));
		heap.remove(heap.get(heap.size()-1));

		while (location < heap.size()) {
			if (getLeft(curr)!= null) {
				if(getLeft(curr).getData() > curr.getData()) {
					swap(curr, getLeft(curr));
					curr = getLeft(curr);
					continue;
				}
			}
			if (getRight(curr)!= null) {
				if(getRight(curr).getData() > curr.getData()) {
					swap(curr, getRight(curr));
					curr = getRight(curr);
					continue;
				}
			}
		    else break;
			
		}

		
	}
	
	void swap(HeapNodeInt nodeA, HeapNodeInt nodeB) {
		// TODO: swaps nodeA and nodeB in the heap
		HeapNodeInt temp = new HeapNodeInt(0,0);
		
		temp.setData(nodeA.getData());
		nodeA.setData(nodeB.getData());
		nodeB.setData(temp.getData());
	}
	
	public ArrayList<Integer> toArray() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for (int index = 0; index < heap.size(); index++) {
			arr.add(heap.get(index).getData());
		}
		return arr;
	}
	
	public static void main(String[] args) {
		MaxHeapInt maxHeap = new MaxHeapInt();
		maxHeap.addNode(35);
		maxHeap.addNode(33);
		maxHeap.addNode(42);
		maxHeap.addNode(10);
		maxHeap.addNode(14);
		maxHeap.addNode(19);
		maxHeap.addNode(27);
		maxHeap.addNode(44);
		maxHeap.addNode(26);
		maxHeap.addNode(31);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(44);
		System.out.println(maxHeap.toArray().toString());
		maxHeap.deleteNode(31);
		System.out.println(maxHeap.toArray().toString());
	}
}
