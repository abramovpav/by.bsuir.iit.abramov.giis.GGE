package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import by.bsuir.iit.abramov.giis.GGE.graphic.forms.Form;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.GraphicPoint;

public class FormPointDesktopMouseListener implements MouseListener, MouseMotionListener {

	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);

		private final int	value;

		BUTTON(final int value) {
			this.value = value;
		}
	}

	private final Form			form;
	private final GraphicPoint	point;
	private final Point			pressPoint;

	public FormPointDesktopMouseListener(final Form form, final GraphicPoint point) {
		this.form = form;
		this.point = point;
		pressPoint = new Point();
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
		pressPoint.x = e.getXOnScreen();
		pressPoint.y = e.getYOnScreen();
	}

	@Override
	public void mouseReleased(final MouseEvent e) {

	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		int dx = e.getXOnScreen() - pressPoint.x;
		int dy = e.getYOnScreen() - pressPoint.y;
		pressPoint.x = e.getXOnScreen();
		pressPoint.y = e.getYOnScreen();
		point.setLocation(point.getX() + dx, point.getY() + dy);
		form.generate();
	}

	@Override
	public void mouseMoved(final MouseEvent e) {

	}
}
