package by.bsuir.iit.abramov.giis.GGE.listeners.mouse;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;

public class EdgeDesktopMouseListener implements MouseInputListener {
	
	private final Controller controller;
	private final Desktop desktop;
	
	enum BUTTON {
		LEFT(1), CENTER(2), RIGHT(3);
		
		private final int value;
		BUTTON(int value) {
			this.value = value;
		}
	}
	
	public EdgeDesktopMouseListener(Controller controller, Desktop desktop) {
		this.controller = controller;
		this.desktop = desktop;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("edge-click");
		if (isLeftButtonPressed(e)) {
			desktop.setEdgePoint(e.getX(), e.getY());
		}
		else if (isRightButtonPressed(e)) {
			desktop.cancelTempObject();
		}
//		System.out.println(e.getButton());
//		System.out.println(e.getModifiers());
//		System.out.println(e.getModifiersEx());
		
	}
	
	private boolean isLeftButtonPressed(MouseEvent e) {
		return e.getButton() == BUTTON.LEFT.value;
	}
	
	private boolean isRightButtonPressed(MouseEvent e) {
		return e.getButton() == BUTTON.RIGHT.value;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("edge-drug");
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}