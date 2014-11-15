package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.Form;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.GraphicPoint;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class FormPointDesktopMouseListener implements MouseListener, MouseMotionListener {

	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);

		private final int	value;

		BUTTON(final int value) {
			this.value = value;
		}
	}

	private final Form form;
	private final GraphicPoint point;
	private Point pressPoint;

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
		pressPoint.x = e.getX();
		pressPoint.y = e.getY();
	}

	@Override
	public void mouseReleased(final MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int dx = e.getX() - pressPoint.x; 
		int dy = e.getY() - pressPoint.y;
		form.updateBasePoint(point, dx, dy);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
