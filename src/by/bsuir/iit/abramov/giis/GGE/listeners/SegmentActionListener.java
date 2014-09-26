package by.bsuir.iit.abramov.giis.GGE.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;

public class SegmentActionListener implements ActionListener {
	
	private final Controller controller;
	
	public SegmentActionListener(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.edge();

	}

}
