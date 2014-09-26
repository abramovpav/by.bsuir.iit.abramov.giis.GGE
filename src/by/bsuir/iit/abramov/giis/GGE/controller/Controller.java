package by.bsuir.iit.abramov.giis.GGE.controller;

import by.bsuir.iit.abramov.giis.GGE.utils.Mode;
import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Controller {
	private MainWindow window;

	public Controller() {
	}

	public void setMainWindow(final MainWindow window) {
		this.window = window;
	}

	public void newTab() {
		window.createNewTab();
	}

	public void exit() {
		window.setVisible(false);
	}

	public void segmentDDA() {
		window.setMode(Mode.SEGMENT_DDA);
	}

	public void segmentBrezenhem() {
		window.setMode(Mode.SEGMENT_BREZENHEM);
	}

	public void none() {
		window.setMode(Mode.NONE);
	}
}
