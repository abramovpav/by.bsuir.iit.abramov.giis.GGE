package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;

public class MainWindow {
	private final JFrame		window;
	private final String		TITLE			= "GGE";
	private final int			DEFAULT_WIDTH	= 1024;
	private final int			DEFAULT_HEIGHT	= 768;
	private JPanel				contentPane;
	private Desktop				desktop;
	private ToolPanel			toolPanel;
	private final Controller	controller;
	private Dimension			desktopSize		= new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	private JScrollPane			scroll;
	private JPanel				panel;
	private StatusBar			statusBar;
	private MenuBar				menuBar;

	public MainWindow(final Controller controller) {
		window = new JFrame(TITLE);
		this.controller = controller;
		init();
	}

	public void activateActions() {
		menuBar.activateActions();
		toolPanel.activateActions();
	}

	public void activateStepButton() {
		menuBar.activateStepButton();
		toolPanel.activateStepButton();
	}

	public void add(final JComponent component) {
		add(component);
	}

	public void createNewTab() {
		initDesktop(contentPane);
	}

	public final Controller getController() {
		return controller;
	}

	public final JFrame getWindow() {
		return window;
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
		statusBar = new StatusBar(controller);
		contentPane.add(statusBar, BorderLayout.SOUTH);
	}

	private void initDesktop(final JPanel contentPane) {
		DesktopController controller = new DesktopController();
		controller.setController(this.controller);
		desktop = new Desktop(this, controller);
		controller.setDesktop(desktop);
		Log log = new Log();
		controller.setLog(log);

		desktop.setLayout(null);
		scroll = new JScrollPane(desktop);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(desktop);
		contentPane.add(scroll, BorderLayout.CENTER);
		window.setExtendedState(Frame.MAXIMIZED_BOTH);
		// window.pack();

		scroll.setViewportView(panel);

		updateDesktop(DEFAULT_WIDTH / 2, DEFAULT_HEIGHT / 2);
		activateActions();
	}

	private void initMenuBar() {
		menuBar = new MenuBar(controller);
		window.setJMenuBar(menuBar);
	}

	private void initToolPanel(final JPanel contenPane) {
		toolPanel = new ToolPanel(this, controller);
		contentPane.add(toolPanel, BorderLayout.EAST);
	}

	public void last() {
		desktop.last();
	}

	public void next() {
		desktop.next();
	}

	public void prev() {
		desktop.prev();
	}

	public void setMode(final Mode mode) {
		desktop.setMode(mode);
	}

	public void setVisible(final boolean visible) {
		window.setVisible(visible);
		if (!visible) {
			window.dispose();
		}
	}

	public void showLog() {
		desktop.showLog();
	}

	private void updateDesktop(int x, int y) {

		desktop.setMinimumSize(desktopSize);
		desktop.setMaximumSize(desktopSize);
		desktop.setSize(desktopSize);
		panel.setMinimumSize(desktopSize);
		panel.setPreferredSize(desktopSize);
		window.validate();
		int scrollWidth = scroll.getViewportBorderBounds().width;
		int newX = x * Config.CURRENT_SCALE - scrollWidth / 2;
		int scrollHeight = scroll.getViewportBorderBounds().height;
		int newY = y * Config.CURRENT_SCALE - scrollHeight / 2;
		newX = normalizeCoord(desktopSize.width - scrollWidth, newX);
		newY = normalizeCoord(desktopSize.height - scrollHeight, newY);
		scroll.getViewport().setViewPosition(new Point(newX, newY));
		scroll.validate();
		window.repaint();
	}

	private int normalizeCoord(int max, int coord) {
		if (coord < 0) {
			coord = 0;
		} else if (coord > max) {
			coord = max;
		}
		return coord;
	}

	public void updateDesktopContent(int x, int y) {
		desktopSize = new Dimension(DEFAULT_WIDTH * Config.CURRENT_SCALE / Config.MIN_SCALE,
				DEFAULT_HEIGHT * Config.CURRENT_SCALE / Config.MIN_SCALE);
		updateDesktop(x, y);
		System.out.println("New center = " + desktop.getCenterPoint().getX() + " "
				+ desktop.getCenterPoint().getY());
		if (desktop != null) {
			desktop.updateGraphics();
		}
	}

	public void updateStatusBar(final int x, final int y) {
		statusBar.updateCoordinates(x, y);
	}
}
