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
		tree.add(new Node(5));
		tree.add(new Node(1));
		tree.add(new Node(100));
		tree.add(new Node(200));
		tree.add(new Node(90));
		
		window = new WindowTree(this);
		window.paintTree(tree.getRoot());
	}
	
	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ADD")) {
			tree.add(new Node(Integer.valueOf(JOptionPane.showInputDialog("id"))));			
		}else{
			tree.delete(Integer.parseInt(JOptionPane.showInputDialog("Información del nodo a borrar")));
		}
		window.paintTree(tree.getRoot());
	}
}