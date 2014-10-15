package by.bsuir.iit.abramov.giis.GGE.controller;

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

	public void decScale() {
		Config.decScale();
		controller.updateDesktopContent();
	}

	public void incScale() {
		Config.incScale();
		controller.updateDesktopContent();
	}

	public void log(final Point point) {
		log.log(point);
	}

	public void log(final String message) {
		log.log(message);
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
}
