package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopMouseListener implements MouseListener {
	
	private final Controller controller;
	private final Desktop desktop;
	
	public DesktopMouseListener(Controller controller, Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("tuck");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

}
