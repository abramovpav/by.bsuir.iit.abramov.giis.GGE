package by.bsuir.iit.abramov.giis.GGE.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;
import by.bsuir.iit.abramov.giis.GGE.utils.ListenerFactory;

public class ToolPanel extends JToolBar {

	/**
	 *
	 */
	private static final long		serialVersionUID	= 1L;
	private final MainWindow		parent;
	private final Controller		controller;
	private Map<EMenuItem, JButton>	buttons;

	public ToolPanel(final MainWindow parent, final Controller controller) {
		this.parent = parent;
		this.controller = controller;
		init();
		createElements();
	}

	public void activateActions() {
		List<EMenuItem> items = EMenu.ACTIONS.getItems();
		setEnabled(true, items);
	}

	public void activateStepButton() {
		List<EMenuItem> items = EMenu.ALGORITHM.getItems();
		setEnabled(true, items);
	}

	private void createElements() {
		for (EMenuItem tool : EMenuItem.values()) {
			if (!tool.isTool()) {
				continue;
			}
			JButton button = new JButton(tool.getName());
			button.addActionListener(ListenerFactory.getActionListener(tool, controller));
			buttons.put(tool, button);
			button.setEnabled(tool.isEnabled());
			add(button);
		}
	}

	private void init() {
		buttons = new HashMap<EMenuItem, JButton>();
		// setFloatable(false);
		setOrientation(SwingConstants.VERTICAL);

	}

	public void setEnabled(final boolean enabled, final List<EMenuItem> items) {
		for (EMenuItem item : items) {
			if (buttons.containsKey(item)) {
				JButton button = buttons.get(item);
				button.setEnabled(enabled);
			}
		}
	}
}
