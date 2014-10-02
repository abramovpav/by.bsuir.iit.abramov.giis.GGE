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
import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;
import by.bsuir.iit.abramov.giis.GGE.utils.ListenerFactory;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;

public class MainWindow {
	private final JFrame window;
	private final String TITLE = "GGE";
	private final int DEFAULT_WIDTH = 600;
	private final int DEFAULT_HEIGHT = 800;
	private JPanel contentPane;
	private Desktop desktop;
	private ToolPanel toolPanel;
	private final Controller controller;
	private final Dimension desktopSize = new Dimension(1024, 768);

	public MainWindow(final Controller controller) {
		window = new JFrame(TITLE);
		this.controller = controller;
		init();
	}
	
	public void updateDesktop() {
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
		JScrollPane scroll = new JScrollPane(desktop);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		scroll.setViewportView(panel);

		panel.add(desktop);
		desktop.setLayout(null);
		contentPane.add(scroll, BorderLayout.CENTER);
		window.pack();
		window.setExtendedState(Frame.MAXIMIZED_BOTH);

		desktop.setMinimumSize(desktopSize);
		desktop.setMaximumSize(desktopSize);
		desktop.setSize(desktopSize);
		panel.setMinimumSize(desktopSize);
		panel.setPreferredSize(desktopSize);
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
				item.addActionListener(ListenerFactory.getActionListener(
						eMenuItem, controller));
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
