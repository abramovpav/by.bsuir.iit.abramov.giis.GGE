package by.bsuir.iit.abramov.giis.GGE.main;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.view.MainWindow;

public class Main {

	public static void main(final String[] args) {
		Controller controller = new Controller();

		MainWindow frame = new MainWindow(controller);
		controller.setMainWindow(frame);
		frame.setVisible(true);
	}

}
