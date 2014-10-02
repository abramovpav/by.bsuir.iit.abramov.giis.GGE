package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopWheelMouseListener implements MouseWheelListener {

	private final Controller controller;
	private final Desktop desktop;

	public DesktopWheelMouseListener(final Controller controller,
			final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		System.out.println("rotation= " + e.getWheelRotation());
		System.out.println("modifiers - " + e.getModifiers());
		if (e.getModifiers() == 2) {
			if (e.getWheelRotation() > 0) {
				controller.incScale();
			} else if (e.getWheelRotation() < 0) {
				controller.decScale();
			}
		}

	}

}
