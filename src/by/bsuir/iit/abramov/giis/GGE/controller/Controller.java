package by.bsuir.iit.abramov.giis.GGE.controller;

import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Controller {
	private MainWindow window;
	
	public Controller() {
	}
	
	public void setMainWindow(MainWindow window) {
		this.window = window;
	}
	
	public void exit() {
		window.setVisible(false);
	}
}
