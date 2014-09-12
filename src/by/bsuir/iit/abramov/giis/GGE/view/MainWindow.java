package by.bsuir.iit.abramov.giis.GGE.view;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;

public class MainWindow {
	private final JFrame window;
	private final String TITLE = "GGE";
	private final int DEFAULT_WIDTH = 600;
	private final int DEFAULT_HEIGHT = 800;
	
	
	public MainWindow() {
		window = new JFrame(TITLE);
		init();
	}
	
	private void init() {
		window.setBounds(0, 0, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		initMenuBar();
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		for (EMenu emenu: EMenu.values()) {
			JMenu menu = new JMenu(emenu.getName());
			for (EMenuItem eMenuItem: emenu.getItems()) {
				JMenuItem item = new JMenuItem(eMenuItem.getName());
				menu.add(item);
			}
			menuBar.add(menu);
		}
		window.setJMenuBar(menuBar);
		
		
	}
	
	public void setVisible(boolean visible) {
		window.setVisible(true);
	}

}
