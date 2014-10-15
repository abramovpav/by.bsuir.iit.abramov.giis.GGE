package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class LineDesktopMouseListener implements MouseListener {

	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);

		private final int	value;

		BUTTON(final int value) {
			this.value = value;
		}
	}

	private final DesktopController	controller;

	private final Desktop			desktop;

	public LineDesktopMouseListener(final DesktopController controller, final Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	private boolean isLeftButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.LEFT.value;
	}

	private boolean isRightButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.RIGHT.value;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		System.out.println("line-click");
		Point desktopCenter = desktop.getCenterPoint();
		if (isLeftButtonPressed(e)) {
			desktop.setLinePoint(e.getX() - desktopCenter.x, e.getY() - desktopCenter.y);
		} else if (isRightButtonPressed(e)) {
			desktop.cancelTempObject();
		}
		// System.out.println(e.getButton());
		// System.out.println(e.getModifiers());
		// System.out.println(e.getModifiersEx());

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
}
