package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;

public class MainWindow {
	private final JFrame window;
	private final String TITLE = "GGE";
	private final int DEFAULT_WIDTH = 600;
	private final int DEFAULT_HEIGHT = 800;
	private JPanel contentPane;
	private Desktop desktop;
	private ToolPanel toolPanel;
	
	
	public MainWindow() {
		window = new JFrame(TITLE);
		init();
	}
	
	private void init() {
		window.setBounds(0, 0, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		initMenuBar();
		initContentPane();
		initToolPanel(contentPane);
		initDesktop(contentPane);
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		window.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0,0));
	}
	
	private void initToolPanel(JPanel contenPane) {
		toolPanel = new ToolPanel(this);
		contentPane.add(toolPanel, BorderLayout.EAST);
	}
	
	private void initDesktop(JPanel contentPane) {
		Desktop desktop = new Desktop(this);
		contentPane.add(desktop, BorderLayout.CENTER);
	}
	
	public void add(JComponent component) {
		add(component);
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
