package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopMouseListener implements MouseListener, MouseMotionListener {

	private final DesktopController	controller;
	private final Desktop			desktop;

	public DesktopMouseListener(final DesktopController controller, final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseClicked(final MouseEvent arg0) {
		System.out.println("tuck");
		java.awt.Point centerDesktop = desktop.getCenterPoint();
		System.out.println(Point.getUnscaledCoord(arg0.getX() - centerDesktop.x) + " "
				+ Point.getUnscaledCoord(arg0.getY() - centerDesktop.y));
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(final MouseEvent arg0) {
	}

	@Override
	public void mouseExited(final MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		java.awt.Point centerDesktop = desktop.getCenterPoint();
		controller.updateStatusBar(Point.getUnscaledCoord(e.getX() - centerDesktop.x),
				Point.getUnscaledCoord(e.getY() - centerDesktop.y));

	}

	@Override
	public void mousePressed(final MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(final MouseEvent arg0) {

	}

}
