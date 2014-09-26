package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopMouseListener implements MouseListener {

	private final Controller controller;
	private final Desktop desktop;

	public DesktopMouseListener(final Controller controller,
			final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseClicked(final MouseEvent arg0) {
		System.out.println("tuck");
		Point centerDesktop = desktop.getCenterPoint();
		System.out.println(arg0.getX() - centerDesktop.x + " "
				+ (arg0.getY() - centerDesktop.y));
	}

	@Override
	public void mouseEntered(final MouseEvent arg0) {
	}

	@Override
	public void mouseExited(final MouseEvent arg0) {

	}

	@Override
	public void mousePressed(final MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(final MouseEvent arg0) {

	}

}
