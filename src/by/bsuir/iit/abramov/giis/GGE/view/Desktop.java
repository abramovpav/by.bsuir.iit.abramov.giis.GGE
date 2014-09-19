package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.listeners.DesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;


public class Desktop extends JPanel {
	final MainWindow parent;
	private final Controller controller;
	private final List<GraphicObject> grapthicObjects;
	private Mode mode;
	
	public Desktop(MainWindow parent) {
		this.parent = parent;
		this.controller = parent.getController();
		this.grapthicObjects = new ArrayList<>();
		this.mode = Mode.NONE;
		init();
	}
	
	private void init() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		addMouseListener(new DesktopMouseListener(controller, this));
	}
	
	
}
