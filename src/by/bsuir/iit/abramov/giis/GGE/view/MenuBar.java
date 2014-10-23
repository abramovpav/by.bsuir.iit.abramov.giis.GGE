package by.bsuir.iit.abramov.giis.GGE.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenu;
import by.bsuir.iit.abramov.giis.GGE.utils.EMenuItem;
import by.bsuir.iit.abramov.giis.GGE.utils.ListenerFactory;

public class MenuBar extends JMenuBar {
	private final Map<EMenuItem, JMenuItem> items;
	private Controller controller;
	
	public MenuBar(Controller controller) {
		super();
		this.controller = controller;
		items = new HashMap<EMenuItem, JMenuItem>();
		init();
	}
	
	public void activateActions() {
		List <EMenuItem> items = EMenu.ACTIONS.getItems();
		setEnabled(true, items);
	}
	
	public void activateStepButton() {
		List <EMenuItem> items = EMenu.ALGORITHM.getItems();
		setEnabled(true, items);
	}
	
	private void init() {
		for (EMenu emenu : EMenu.values()) {
			JMenu menu = new JMenu(emenu.getName());
			for (EMenuItem eMenuItem : emenu.getItems()) {
				JMenuItem item = new JMenuItem(eMenuItem.getName());
				items.put(eMenuItem, item);
				item.setEnabled(eMenuItem.isEnabled());
				item.addActionListener(ListenerFactory.getActionListener(eMenuItem, controller));
				menu.add(item);
			}
			add(menu);
		}
	}
	
	public void setEnabled(boolean enabled, List<EMenuItem> items) {
		for(EMenuItem item: items) {
			if (this.items.containsKey(item)) {
				JMenuItem menuItem = this.items.get(item);
				menuItem.setEnabled(enabled);
			}
		}
	}
}
