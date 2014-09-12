package by.bsuir.iit.abramov.giis.GGE.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


public class ToolPanel extends JPanel {
	
	private final MainWindow parent;
	public static final String TAB = "New tab";
	private List<JButton> buttons;

	public ToolPanel(MainWindow parent) {
		this.parent = parent;
		init();
	}
	
	private void init() {
		buttons = new ArrayList<>();
		final JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		add(toolBar);
		// buttons
		JButton button = new JButton(ToolPanel.TAB);
		buttons.put(ToolPanel.TAB, button);
		toolBar.add(button);
	}
}
