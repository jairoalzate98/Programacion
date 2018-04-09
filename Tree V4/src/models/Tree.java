package models;

public class Tree {

	private Node root;

	public void add(Node node) {
		if (root != null) {
			add(root, node);
		}else {
			root = node;
		}
	}

	private void add(Node base, Node node) {
		if (node.getInformation() < base.getInformation()) {
			if (base.getLeft() != null) {
				add(base.getLeft(), node);
			}else {
				base.setLeft(node);
			}
		}else {
			if (base.getRight() != null) {
				add(base.getRight(), node);
			}else {
				base.setRight(node);
			}
		}
	}

	public void delete(int info) {
		delete(null, root, info);
	}

	public void delete(Node father, Node actual, int info) {
		if (actual.getInformation() == info) {
			if (isComplete(actual)) {
				deleteComplete(actual);
			}else if (hasOneChildren(actual)) {
				deleteOneChild(father, actual);
			}else {
				deleteLeaf(father, actual);
			}
		}else {
			if (info < actual.getInformation()) {
				delete(actual, actual.getLeft(), info);
			}else {
				delete(actual, actual.getRight(), info);
			}
		}
	}

	private void deleteComplete(Node actual) {
		Node maxLeft = getMaxNode(actual.getLeft());
		Node minRight = getMinNode(actual.getRight());
		int data = (Math.abs(maxLeft.getInformation() - actual.getInformation()) 
				< Math.abs(minRight.getInformation() - actual.getInformation())) ?
					maxLeft.getInformation(): minRight.getInformation();
		delete(data);
		actual.setInformation(data);
	}
	
	public Node getMinNode(Node base) {
		Node actual = base;
		while (actual.getLeft() != null) {
			actual = actual.getLeft();
		}
		return actual;
	}
	
	public Node getMaxNode(Node base) {
		Node actual = base;
		while (actual.getRight() != null) {
			actual = actual.getRight();
		}
		return actual;
	}

	private void deleteOneChild(Node father, Node actual) {
		if (actual == root) {
			root = getOneChild(actual);
		}else if (father.getLeft().equals(actual)) {
			father.setLeft(getOneChild(actual));
		}else {
			father.setRight(getOneChild(actual));
		}
	}

	private void deleteLeaf(Node father, Node actual) {
		if (father == null) {
			root = null;
		}else if (father.getLeft() != null && father.getLeft().equals(actual)) {
			father.setLeft(null);
		}else {
			father.setRight(null);
		}
	}
	
	private Node getOneChild(Node actual) {
		return actual.getLeft() != null ? actual.getLeft() : actual.getRight();
	}

	private boolean hasOneChildren(Node actual) {
		return actual.getLeft() != null || actual.getRight() != null;
	}

	private boolean isComplete(Node actual) {
		return actual.getLeft() != null && actual.getRight() != null;
	}

	public void print() {
		System.out.println("------------------");
		print(root);
	}

	private void print(Node node) {
		if(node != null) {
			System.out.println(node.getInformation());
			print(node.getLeft());
			print(node.getRight());
		}
	}
	
	public Node getRoot() {
		return root;
	}
	
	public int balancedTree(Node actual){
		if (actual != null) {
			if(actual.getLeft() == null && actual.getRight() == null){
				return 1;
			}else{
				int heightLeft = balancedTree(actual.getLeft());
				int heightRight = balancedTree(actual.getRight());
				if (actual != root) {
					return heightLeft + heightRight + 1; 
				}  else {
					return heightLeft - heightRight;
				}
			}
		}else{
			return 1;
		}
	}
	
	public void balance(){
		if (root.getLeft() != null && root.getRight() == null) {
			if (root.getLeft().getLeft() != null && root.getLeft().getRight() == null) {
				balanceLeftSimple();
			} else if(root.getLeft().getLeft() == null && root.getLeft().getRight() != null){
				balanaceLeftRight();
			}
		}else if(root.getLeft() == null && root.getRight() != null){
			if (root.getRight().getLeft() != null && root.getRight().getRight() == null) {
				balanceRightSimple();
			} else if(root.getRight().getLeft() == null && root.getRight().getRight() != null){
				balanceRightLeft();
			}
		}
	}

	private void balanceRightLeft() {
		Node auxRoot = root;
		root = auxRoot.getRight();
		root.setLeft(auxRoot);
		auxRoot.setRight(null);
	}

	private void balanceRightSimple() {
		Node auxRightLeft = root.getRight();
		root.setRight(auxRightLeft.getLeft());
		auxRightLeft.setLeft(null);
		root.getRight().setRight(auxRightLeft);
		Node auxRoot = root;
		root = auxRoot.getRight();
		root.setLeft(auxRoot);
		auxRoot.setRight(null);
	}

	private void balanaceLeftRight() {
		Node auxLeftRight = root.getLeft();
		root.setLeft(auxLeftRight.getRight());
		auxLeftRight.setRight(null);
		root.getLeft().setLeft(auxLeftRight);
		Node auxRoot = root;
		root = auxRoot.getLeft();
		root.setRight(auxRoot);
		auxRoot.setLeft(null);
	}

	private void balanceLeftSimple() {
		Node auxRoot = root;
		root = auxRoot.getLeft();
		root.setRight(auxRoot);
		auxRoot.setLeft(null);
	}
}