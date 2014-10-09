package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;
import by.bsuir.iit.abramov.giis.GGE.utils.ListenerFactory;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;

public class MainWindow {
	private final JFrame window;
	private final String TITLE = "GGE";
	private final int DEFAULT_WIDTH = 1024;
	private final int DEFAULT_HEIGHT = 768;
	private JPanel contentPane;
	private Desktop desktop;
	private ToolPanel toolPanel;
	private final Controller controller;
	private Dimension desktopSize = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private JScrollPane scroll;
	private JPanel panel;

	public MainWindow(final Controller controller) {
		window = new JFrame(TITLE);
		this.controller = controller;
		init();
	}

	public void updateDesktopContent() {
		desktopSize = new Dimension(DEFAULT_WIDTH * Config.CURRENT_SCALE, DEFAULT_HEIGHT
				* Config.CURRENT_SCALE);
		updateDesktop();
		System.out.println("New center = " + desktop.getCenterPoint().getX() + " "
				+ desktop.getCenterPoint().getY());
		if (desktop != null) {
			desktop.updateGraphics();
		}
	}

	public final Controller getController() {
		return controller;
	}

	public void setMode(final Mode mode) {
		desktop.setMode(mode);
	}

	public void createNewTab() {
		initDesktop(contentPane);
	}

	private void init() {
		window.setBounds(0, 0, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setMinimumSize(new Dimension(DEFAULT_HEIGHT, DEFAULT_WIDTH));
		initMenuBar();
		initContentPane();
		initToolPanel(contentPane);
	}

	private void initContentPane() {
		contentPane = new JPanel();
		window.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	}

	private void initToolPanel(final JPanel contenPane) {
		toolPanel = new ToolPanel(this, controller);
		contentPane.add(toolPanel, BorderLayout.EAST);
	}

	private void initDesktop(final JPanel contentPane) {

		desktop = new Desktop(this);
		desktop.setLayout(null);
		scroll = new JScrollPane(desktop);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(desktop);
		contentPane.add(scroll, BorderLayout.CENTER);
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		// window.pack();

		scroll.setViewportView(panel);

		updateDesktop();

	}

	private void updateDesktop() {

		desktop.setMinimumSize(desktopSize);
		desktop.setMaximumSize(desktopSize);
		desktop.setSize(desktopSize);
		panel.setMinimumSize(desktopSize);
		panel.setPreferredSize(desktopSize);
		window.validate();
		scroll.validate();
		window.repaint();
	}

	public void add(final JComponent component) {
		add(component);
	}

	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		for (EMenu emenu : EMenu.values()) {
			JMenu menu = new JMenu(emenu.getName());
			for (EMenuItem eMenuItem : emenu.getItems()) {
				JMenuItem item = new JMenuItem(eMenuItem.getName());
				item.addActionListener(ListenerFactory.getActionListener(eMenuItem, controller));
				menu.add(item);
			}
			menuBar.add(menu);
		}
		window.setJMenuBar(menuBar);
	}

	public void setVisible(final boolean visible) {
		window.setVisible(visible);
		if (!visible) {
			window.dispose();
		}
	}
}
