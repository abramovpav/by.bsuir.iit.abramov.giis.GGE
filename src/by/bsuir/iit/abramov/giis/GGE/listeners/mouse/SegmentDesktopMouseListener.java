package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class SegmentDesktopMouseListener implements MouseInputListener {

	private final Controller controller;
	private final Desktop desktop;

	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);

		private final int value;

		BUTTON(final int value) {
			this.value = value;
		}
	}

	public SegmentDesktopMouseListener(final Controller controller,
			final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		System.out.println("segment-click");
		Point desktopCenter = desktop.getCenterPoint();
		if (isLeftButtonPressed(e)) {
			desktop.setSegmentPoint(e.getX() - desktopCenter.x, e.getY()
					- desktopCenter.y);
		} else if (isRightButtonPressed(e)) {
			desktop.cancelTempObject();
		}
		// System.out.println(e.getButton());
		// System.out.println(e.getModifiers());
		// System.out.println(e.getModifiersEx());

	}

	private boolean isLeftButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.LEFT.value;
	}

	private boolean isRightButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.RIGHT.value;
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		System.out.println("segment-drug");

	}

	@Override
	public void mouseMoved(final MouseEvent e) {
//		Point centerDesktop = desktop.getCenterPoint();
//		System.out.println(((e.getX() - centerDesktop.x) / Config.DEFAULT_SCALE) + " "
//				+ ((e.getY() - centerDesktop.y) / Config.DEFAULT_SCALE));
	}

}
