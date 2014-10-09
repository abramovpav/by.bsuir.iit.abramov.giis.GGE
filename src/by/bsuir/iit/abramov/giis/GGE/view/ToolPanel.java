package by.bsuir.iit.abramov.giis.GGE.view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;
import by.bsuir.iit.abramov.giis.GGE.utils.ListenerFactory;

public class ToolPanel extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final MainWindow parent;
	private final Controller controller;
	private Map<String, JButton> buttons;

	public ToolPanel(final MainWindow parent, final Controller controller) {
		this.parent = parent;
		this.controller = controller;
		init();
		createElements();
	}

	private void createElements() {
		for (EMenuItem tool : EMenuItem.values()) {
			if (!tool.isTool()) {
				continue;
			}
			JButton button = new JButton(tool.getName());
			button.addActionListener(ListenerFactory.getActionListener(tool, controller));
			buttons.put(tool.getName(), button);
			add(button);
		}
	}

	private void init() {
		buttons = new HashMap<String, JButton>();
		// setFloatable(false);
		setOrientation(SwingConstants.VERTICAL);

	}
}
