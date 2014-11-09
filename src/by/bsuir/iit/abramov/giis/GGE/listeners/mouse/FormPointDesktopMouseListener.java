package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.Form;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class FormPointDesktopMouseListener implements MouseListener {

	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);

		private final int	value;

		BUTTON(final int value) {
			this.value = value;
		}
	}

	private final DesktopController	controller;
	private final Form form;

	public FormPointDesktopMouseListener(final DesktopController controller, final Form form) {
		this.controller = controller;
		this.form = form;
	}

	private boolean isLeftButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.LEFT.value;
	}

	private boolean isRightButtonPressed(final MouseEvent e) {
		return e.getButton() == BUTTON.RIGHT.value;
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		System.out.println("formPoint-click");
	}

	@Override
	public void mouseEntered(final MouseEvent e) {

	}

	@Override
	public void mouseExited(final MouseEvent e) {

	}

	@Override
	public void mousePressed(final MouseEvent e) {

	}

	@Override
	public void mouseReleased(final MouseEvent e) {

	}
}
