package by.bsuir.iit.abramov.giis.GGE.controller;

import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;
import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Controller {
	private MainWindow	window;

	public Controller() {
	}

	public void decScale() {
		Config.decScale();
		window.updateDesktopContent();
	}

	public void exit() {
		window.setVisible(false);
	}

	public void incScale() {
		Config.incScale();
		window.updateDesktopContent();
	}

	public void lineBrezenhem() {
		window.setMode(Mode.LINE_BREZENHEM);
	}

	public void lineDDA() {
		window.setMode(Mode.LINE_DDA);
	}

	public void lineWy() {
		window.setMode(Mode.LINE_WY);
	}

	public void newTab() {
		window.createNewTab();
	}

	public void none() {
		window.setMode(Mode.NONE);
	}

	public void setMainWindow(final MainWindow window) {
		this.window = window;
	}
}
