package lmu.cmsi281.assignments;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import org.w3c.dom.Node;
/**
 * CMSI Assignment 4
 * @author <Grasso, Sebastian>
 *
 */

class PreOrderTraversal {
	
	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement an iterative (using control structures e.g. loops) 
		// pre order traversal
		// Store the value of the nodes visited in path
		
		Stack <BinaryTreeNodeString> nodeStorage = new Stack<BinaryTreeNodeString>();
		BinaryTreeNodeString curr = root;
				
		while(! nodeStorage.isEmpty() || curr != null) {
			if (curr!= null) {
				path.add(curr.getData());
				nodeStorage.push(curr);
				curr = curr.getLeft();
			}
			else {
				BinaryTreeNodeString top = nodeStorage.pop();
				curr = top.getRight();
			}
		}
			
		
	}
	
	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement a recursive pre order traversal
		// Store the value of the nodes visited in path (base case = no more nodes)

        // base case
        if (root == null) {
            return;
        }

        path.add(root.getData());
               
        traverseRecursive(root.getLeft(), path);
        traverseRecursive(root.getRight(), path);
		
	}
}

class InOrderTraversal {

	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement an iterative (using control structures e.g. loops) 
		// in order traversal
		// Store the value of the nodes visited in path
	
		Stack <BinaryTreeNodeString> nodeStorage = new Stack <BinaryTreeNodeString>();
		BinaryTreeNodeString curr = root;
		
		while (curr!= null || nodeStorage.isEmpty() == false) {
			while (curr!= null) {
				nodeStorage.push(curr);
				curr = curr.getLeft();
			}
			curr = nodeStorage.pop();
			path.add(curr.getData());
			
			
			curr = curr.getRight();
			
		}
	
	}
	
	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement a recursive in order traversal
		// Store the value of the nodes visited in path
		
		if (root == null) {
			return;
		}
		
		traverseRecursive(root.getLeft(), path);
		path.add(root.getData());
		traverseRecursive(root.getRight(), path);
		
	}
}

class PostOrderTraversal {

	public void traverseIterative(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement an iterative (using control structures e.g. loops) 
		// post order traversal
		// Store the value of the nodes visited in path
		
		Stack <BinaryTreeNodeString> nodeStorage = new Stack <BinaryTreeNodeString>();
		nodeStorage.push(root);		
		BinaryTreeNodeString prev = null;
		
		while (nodeStorage.isEmpty() == false) {
			BinaryTreeNodeString curr = nodeStorage.peek();
			
			if(prev == null || prev.getLeft() == curr || prev.getRight() == curr) {
				
				if (curr.getLeft() != null)
					nodeStorage.push(curr.getLeft());
				else if (curr.getRight() != null)
					nodeStorage.push(curr.getRight());
				else {
					nodeStorage.pop();
					path.add(curr.getData());
				}
			}
			
			else if(curr.getLeft() == prev) {
				if (curr.getRight()!= null)
					nodeStorage.push(curr.getRight());
				else
				{
					nodeStorage.pop();
					path.add(curr.getData());
				}
			}
			
			else if (curr.getRight() == prev) {
				nodeStorage.pop();
				path.add(curr.getData());
			}
			
			prev = curr;
		}
		
	}
	
	public void traverseRecursive(BinaryTreeNodeString root, ArrayList<String> path) {
		// TODO: Implement an recursive order traversal
		// Store the value of the nodes visited in path
		
		if (root == null)
			return;
		
		traverseRecursive(root.getLeft(), path);
		traverseRecursive(root.getRight(), path);
		path.add(root.getData());
		
		
	}
}

class DepthFirstSearch {
	
	public Boolean searchIterative(
			BinaryTreeNodeString root, String value, ArrayList<String> path) {
		// TODO: Implement an iterative (using control structures e.g. loops) 
		// depth first search, if the value exists then return true, else false
		// Store the value of the nodes visited in path, which should be the same nodes 
		// as depth first traversal/pre order traversal if the value is missing
		

		if (root == null) {
			return false;
		}
		Stack <BinaryTreeNodeString> nodeStorage = new Stack <BinaryTreeNodeString>();
		nodeStorage.push(root);
		
		while(nodeStorage.isEmpty() == false) {
			BinaryTreeNodeString curr = nodeStorage.pop();
			path.add(curr.getData());
			
			
			if (curr.getData() == value) {
				return true;
			}
			if (curr.getRight() != null) {
				nodeStorage.push(curr.getRight());
			}
			if (curr.getLeft() != null) {
				nodeStorage.push(curr.getLeft());
			}
		}
		return false;
	}
	
	
	public Boolean searchRecursive(
			BinaryTreeNodeString root, String value, ArrayList<String> path) {
		// TODO: Implement a recursive depth first search, 
		// if the value exists then return true, else false
		// Store the value of the nodes visited in path, which should be the same nodes 
		// as depth first traversal/preorder traversal if the value is missing

	    if(root == null){
	        return false;
	    } else if (root.getData() == value){
	    	path.add(root.getData());
	        return true;
	    } else {
	    	path.add(root.getData());
	        return (searchRecursive(root.getLeft(), value, path) || searchRecursive(root.getRight(), value, path));
	    }
		
	}
}

class BreadthFirstSearch {
	
	public Boolean searchIterative(
			BinaryTreeNodeString root, String value, ArrayList<String> path) {
		// TODO: Implement an iterative (using control structures e.g. loops) 
		// breadth first search, if the value exists then return true, else false
		// Store the value of the nodes visited in path, which should be the same nodes 
		// as breadth first traversal if the value is missing
		
		if (root == null) {
			return false;
		}
		
		Queue<BinaryTreeNodeString> nodeStorage = new LinkedBlockingQueue<BinaryTreeNodeString>() ;
		nodeStorage.add(root);
		
		while(nodeStorage.isEmpty()== false) {
			BinaryTreeNodeString curr = nodeStorage.remove();;
			path.add(curr.getData());
		
			if (curr.getData() == value) {
				return true;
			}
			
			if (curr.getLeft()!= null) {
				nodeStorage.add(curr.getLeft());
			}
				
			if (curr.getRight()!= null) {
				nodeStorage.add(curr.getRight());			
			}
		
		}
		return false;		
	}
}

public class BinaryTreeString {

	public static void main(String[] args) {
		BinaryTreeNodeString root = new BinaryTreeNodeString("A");
		root.setLeft("B");
		root.setRight("C");
		root.getLeft().setLeft("D");
		root.getLeft().setRight("E");
		root.getRight().setLeft("F");
		root.getRight().setRight("G");
		root.getLeft().getLeft().setLeft("H");
		root.getLeft().getLeft().setRight("I");
		root.getLeft().getRight().setLeft("J");
		
		ArrayList<String> path = new ArrayList<String>();
		
		PreOrderTraversal pre = new PreOrderTraversal();
		pre.traverseIterative(root, path);
		System.out.println("Using iterative pre order traversal:");
		// [A, B, D, H, I, E, J, C, F, G]	
		System.out.println(path);
		path.clear();
		pre.traverseRecursive(root, path);
		System.out.println("Using recursive pre order traversal:");
		// [A, B, D, H, I, E, J, C, F, G]
		System.out.println(path);
		
		InOrderTraversal in = new InOrderTraversal();
		path.clear();
		in.traverseIterative(root, path);
		System.out.println("Using iterative in order traversal:");
		// [H, D, I, B, J, E, A, F, C, G]
		System.out.println(path);
		path.clear();
		in.traverseRecursive(root, path);
		System.out.println("Using recursive in order traversal:");
		// [H, D, I, B, J, E, A, F, C, G]
		System.out.println(path);
		

		PostOrderTraversal post = new PostOrderTraversal();
		path.clear();
		post.traverseIterative(root, path);
		System.out.println("Using iterative post order traversal:");
		// [H, I, D, J, E, B, F, G, C, A]
		System.out.println(path);
		path.clear();
		post.traverseRecursive(root, path);
		System.out.println("Using recursive post order traversal:");
		// [H, I, D, J, E, B, F, G, C, A]
		System.out.println(path);
		
		Boolean found;
		
		BreadthFirstSearch bfs = new BreadthFirstSearch();
		
		System.out.println("Using iterative breadth first search:");
		path.clear();
		found = bfs.searchIterative(root, "H", path);
		// Searching for H... Found=true
		// path=[A, B, C, D, E, F, G, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);
		
		path.clear();
		found = bfs.searchIterative(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, C, D, E, F, G]
		System.out.println("Searching for G... " + "Found=" + found);	
		System.out.println("path=" + path);
		
		DepthFirstSearch dfs = new DepthFirstSearch();
		
		System.out.println("Using iterative depth first search:");
		path.clear();
		found = dfs.searchIterative(root, "H", path);
		// Searching for H... Found=true
		// path=[A, B, D, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);
		
		path.clear();
		found = dfs.searchIterative(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, D, H, I, E, J, C, F, G]	
		System.out.println("Searching for G... " + "Found=" + found);
		System.out.println("path=" + path);
		
		path.clear();
		found = dfs.searchIterative(root, "Z", path);
		// Searching for Z... Found=false
		// path=[A, B, D, H, I, E, J, C, F, G]
		System.out.println("Searching for Z... " + "Found=" + found);
		System.out.println("path=" + path);
		
		System.out.println("Using recursive search:");
		path.clear();
		found = dfs.searchRecursive(root, "H", path);
		// Searching for H... Found=true
		//path=[A, B, D, H]
		System.out.println("Searching for H... " + "Found=" + found);
		System.out.println("path=" + path);
		
		path.clear();
		found = dfs.searchRecursive(root, "G", path);
		// Searching for G... Found=true
		// path=[A, B, D, H, I, E, J, C, F, G]
		System.out.println("Searching for G... " + "Found=" + found);
		System.out.println("path=" + path);
	}
}

