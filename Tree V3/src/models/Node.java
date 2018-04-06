package models;

public class Node {

	private int information;
	private Node left;
	private Node right;
	private Node father;
	
	public Node(int information) {
		this.information = information;
	}

	public int getInformation() {
		return information;
	}

	public void setInformation(int information) {
		this.information = information;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		if (left != null) {			
			left.setFather(this);
		}
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		if (right != null) {			
			right.setFather(this);
		}
		this.right = right;
	}
	
	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	@Override
	public String toString() {
		return String.valueOf(information);
	}
}