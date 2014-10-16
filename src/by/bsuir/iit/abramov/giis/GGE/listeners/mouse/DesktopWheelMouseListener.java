package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class DesktopWheelMouseListener implements MouseWheelListener {

	private final DesktopController	controller;
	private final Desktop			desktop;

	public DesktopWheelMouseListener(final DesktopController controller, final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {
		if (e.getModifiers() == 2) {
			if (e.getWheelRotation() < 0) {
				controller.incScale();
			} else if (e.getWheelRotation() > 0) {
				controller.decScale();
			}
		}

	}

}
