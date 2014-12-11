package by.bsuir.iit.abramov.giis.GGE.controller;

import by.bsuir.iit.abramov.giis.GGE.utils.Mode;
import by.bsuir.iit.abramov.giis.GGE.view.AboutDialog;
import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Controller {
	private MainWindow	window;

	public Controller() {
	}

	public void about() {
		AboutDialog aboutDialog = new AboutDialog(window.getWindow());
		aboutDialog.setVisible(true);
	}

	public void activateStepButton() {
		window.activateStepButton();
	}
	
	public void bsplain() {
		window.setMode(Mode.BSPLAIN);
	}
	
	public void bezierForm() {
		window.setMode(Mode.BEZIER_FORM);
	}

	public void exit() {
		window.setVisible(false);
	}

	public void ermitForm() {
		window.setMode(Mode.ERMIT_FORM);
	}

	public void last() {
		window.last();
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

	public void next() {
		window.next();
	}

	public void none() {
		window.setMode(Mode.NONE);
	}

	public void prev() {
		window.prev();
	}

	public void setMainWindow(final MainWindow window) {
		this.window = window;
	}

	public void showLog() {
		window.showLog();
	}

	public void updateDesktopContent(final int x, final int y) {
		window.updateDesktopContent(x, y);
	}

	public void updateStatusBar(final int x, final int y) {
		window.updateStatusBar(x, y);
	}
}
