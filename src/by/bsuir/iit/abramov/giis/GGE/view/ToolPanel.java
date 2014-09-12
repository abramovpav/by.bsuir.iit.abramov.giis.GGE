package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import by.bsuir.iit.abramov.giis.GGE.utils.TOOL;


public class ToolPanel extends JToolBar {
	
	private final MainWindow parent;
	public static final String TAB = "New tab";
	private Map<String, JButton> buttons;

	public ToolPanel(MainWindow parent) {
		this.parent = parent;
		init();
		createElements();
	}
	
	private void createElements() {
		for (TOOL tool: TOOL.values()) {
			JButton button = new JButton(tool.getName());
			buttons.put(tool.getName(), button);
			add(button);
		}
	}
	
	private void init() {
		buttons = new  HashMap<>();
//		setFloatable(false);
		setOrientation(SwingConstants.VERTICAL);
		
	}
}
