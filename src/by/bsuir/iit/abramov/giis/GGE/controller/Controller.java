package by.bsuir.iit.abramov.giis.GGE.controller;

import by.bsuir.iit.abramov.giis.GGE.utils.Mode;
import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Controller {
	private MainWindow window;
	
	public Controller() {
	}
	
	public void setMainWindow(MainWindow window) {
		this.window = window;
	}
	
	public void newTab() {
		window.createNewTab();
	}
	
	public void exit() {
		window.setVisible(false);
	}
	
	public void edge() {
		window.setMode(Mode.EDGE);
	}
	
	public void edgeDDA() {
		window.setMode(Mode.EDGE_DDA);
	}
	
	public void none() {
		window.setMode(Mode.NONE);
	}
}
