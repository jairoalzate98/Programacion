package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Node;
import models.Tree;
import views.WindowTree;

public class Controller implements ActionListener{
	
	private Tree tree;
	private WindowTree window;

	public Controller() {
		tree = new Tree();
		tree.add(new Node(10));
		tree.add(new Node(7));
//		tree.add(new Node(8));
//		tree.add(new Node(12));
		tree.add(new Node(11));
//		tree.add(new Node(8));
//		tree.add(new Node(9));
//		tree.add(new Node(1));
		window = new WindowTree(this);
		window.paintTree(tree.getRoot());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			tree.add(new Node(Integer.valueOf(JOptionPane.showInputDialog("id"))));			
		}else if(e.getActionCommand().equals("DELETE")){
			tree.delete(Integer.parseInt(JOptionPane.showInputDialog("Informacion del nodo a borrar")));
		} else {
			balance();
		}
		window.paintTree(tree.getRoot());
	}
	
	private void balance() {
		if (tree.balancedTree(tree.getRoot()) != 0 && tree.balancedTree(tree.getRoot()) != 1 && tree.balancedTree(tree.getRoot()) != -1) {
			JOptionPane.showMessageDialog(window, "Factor de balanceo uno => " + String.valueOf(tree.balancedTree(tree.getRoot())));
			tree.balance();
			JOptionPane.showMessageDialog(window, "Factor de balanceo dos => " + String.valueOf(tree.balancedTree(tree.getRoot())));
		} else {
			JOptionPane.showMessageDialog(window, "Factor de balanceo => " + String.valueOf(tree.balancedTree(tree.getRoot())));
		}
	}

	public static void main(String[] args) {
		new Controller();
	}
}