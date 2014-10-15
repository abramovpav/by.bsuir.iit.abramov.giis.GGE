package by.bsuir.iit.abramov.giis.GGE.view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;

public class StatusBar extends JLabel{
	private static final String	UNDEFINED	= "UNDEFINED";
	private static final String	CURRENT_POSITION	= "Current position: ";
	private final Controller controller;
	
	public StatusBar(Controller controller) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		setText(CURRENT_POSITION + UNDEFINED);
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	public void updateCoordinates(int x, int y) {
		setText(CURRENT_POSITION + x + " " + y);
		repaint();
	}
}
