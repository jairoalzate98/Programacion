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

	private void add(Node base, Node newNode) {
		System.out.println(newNode);
		if (newNode.getInformation() < base.getInformation()) {
			if (base.getLeft() != null) {
				add(base.getLeft(), newNode);
			}else {
				base.setLeft(newNode);
			}
		}else {
			if (base.getRight() != null) {
				add(base.getRight(), newNode);
			}else {
				base.setRight(newNode);
			}
		}
	}

	public void delete(int info) {
		if (info == root.getInformation()) {
			root = null;
		}else {
			delete(root, info);
		}
	}

	private void delete(Node actual, int info) {
		if (actual.getInformation() == info) {
			if (actual.getLeft() != null && actual.getRight() != null) {
				deleteFull(actual, info);
			}else if (actual.getLeft() != null || actual.getRight() != null) {
				deleteWithOneChild(actual, info);
			}else{
				deleteLeaf(actual, info);
			}
		}else if (info < actual.getInformation()) {
			delete(actual.getLeft(), info);
		}else {
			delete(actual.getRight(), info);
		}
	}

	private void deleteFull(Node actual, int info) {
		Node numberMax = getNumberMax(actual);
		Node numberMin = getNumberMin(actual);
		int resMax = Math.abs(numberMax.getInformation() - info);
		int resMin = Math.abs(numberMin.getInformation() - info);
		if (resMax < resMin) {
			actual.setInformation(numberMax.getInformation());
			if (numberMax.getLeft() != null) {
				add(numberMax.getLeft());
			}
			numberMax.getFather().setRight(null);
		}else{
			actual.setInformation(numberMin.getInformation());
			if (numberMin.getRight() != null) {
				add(numberMin.getRight());
			}
			numberMin.getFather().setLeft(null);
		}
	}
	
	public Node getNumberMax(Node a) {
		Node max =  null;
		if (root != null) {
			Node actual = a;
			while(actual.getRight() != null) {
				actual = actual.getRight();
			}
			max = actual;
		}
		return max;
	}
	
	public Node getNumberMin(Node a) {
		Node max = null;
		if (root != null) {
			Node actual = a;
			while(actual.getLeft() != null) {
				actual = actual.getLeft();
			}
			max = actual;
		}
		return max;
	}

	private void deleteWithOneChild(Node actual, int info) {
		if (actual.getLeft() != null) {
			if (actual.getInformation() == info) {
				actual.setInformation(actual.getLeft().getInformation());
				actual.setLeft(null);
			}
		}else {
			if (actual.getInformation() == info) {
				actual.setInformation(actual.getRight().getInformation());
				actual.setRight(null);
			}
		}
	}

	private void deleteLeaf(Node actual, int info) {
		if (actual.getFather().getLeft() == actual) {
			actual.getFather().setLeft(null);			
		}else {
			actual.getFather().setRight(null);
		}
	}

	public Node getRoot() {
		return root;
	}
}