package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Desktop extends JPanel {
	final MainWindow parent;
	
	public Desktop(MainWindow parent) {
		this.parent = parent;
		init();
	}
	
	private void init() {
		JButton button = new JButton("Button");
		add(button);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
	}

}
