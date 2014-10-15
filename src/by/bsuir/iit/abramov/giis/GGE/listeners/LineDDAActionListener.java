package by.bsuir.iit.abramov.giis.GGE.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import by.bsuir.iit.abramov.giis.GGE.controller.Controller;

public class LineDDAActionListener implements ActionListener {

	private final Controller	controller;

	public LineDDAActionListener(final Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(final ActionEvent arg0) {
		controller.lineDDA();
	}

}
