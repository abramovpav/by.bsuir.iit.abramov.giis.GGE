package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopMouseListener implements MouseListener, MouseMotionListener {

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
		System.out.println(((arg0.getX() - centerDesktop.x) / Config.DEFAULT_SCALE) + " "
				+ ((arg0.getY() - centerDesktop.y) / Config.DEFAULT_SCALE));
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point centerDesktop = desktop.getCenterPoint();
		System.out.println(((e.getX() - centerDesktop.x) / Config.DEFAULT_SCALE) + " "
				+ ((e.getY() - centerDesktop.y) / Config.DEFAULT_SCALE));
		
	}

}
