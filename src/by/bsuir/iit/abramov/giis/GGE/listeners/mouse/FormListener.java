package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.Form;

public class FormListener implements MouseInputListener {
	
	private final DesktopController controller;
	private final Form form;

	public FormListener(final Form form, final DesktopController controller) {
		super();
		this.controller = controller;
		this.form = form;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("form-click");
		controller.select(form);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
