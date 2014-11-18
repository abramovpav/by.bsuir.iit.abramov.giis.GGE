package by.bsuir.iit.abramov.giis.GGE.controller;

import javax.swing.JComponent;

import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObjectInterface;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.view.Desktop;
import by.bsuir.iit.abramov.giis.GGE.view.Log;

public class DesktopController {
	private Desktop		desktop;
	private Controller	controller;
	private Log			log;

	public DesktopController() {
	}

	public void activateStepButton() {
		controller.activateStepButton();
	}

	public void decScale(int x, int y) {
		if (Config.decScale()) {
			controller.updateDesktopContent(x, y);
		}
	}
	
	public java.awt.Point getDesktopCenterPoint() {
		return desktop.getCenterPoint();
	}

	public void incScale(int x, int y) {
		if (Config.incScale()) {
			controller.updateDesktopContent(x, y);
		}
	}

	public void log(final Point point) {
		log.log(point);
	}

	public void log(final Point point, final Point startPoint, final Point endPoint) {
		Point correctivePoint = startPoint;
		if (startPoint.getX() > endPoint.getX()) {
			correctivePoint = endPoint;
		}
		log.log(point, correctivePoint);
	}
	
	public void log(final Point point, final Point correctivePoint) {
		log.log(point, correctivePoint);
	}

	public void log(final String message, final boolean offset) {
		log.log(message, offset);
	}
	
	public void select(GraphicObjectInterface component) {
		desktop.selectComponent(component);
	}

	public void setController(final Controller controller) {
		this.controller = controller;
	}

	public void setDesktop(final Desktop desktop) {
		this.desktop = desktop;
	}

	public void setLog(final Log log) {
		this.log = log;
	}

	public void showLog() {
		log.setVisible(true);
	}

	public void updateStatusBar(final int x, final int y) {
		controller.updateStatusBar(x, y);
	}
}
