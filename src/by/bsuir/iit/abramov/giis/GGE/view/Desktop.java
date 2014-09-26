package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.giis.GGE.controller.Controller;
import by.bsuir.iit.abramov.giis.GGE.graphic.Segment;
import by.bsuir.iit.abramov.giis.GGE.graphic.SegmentDDA;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.graphic.Segment_Brezenhem;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.DesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.SegmentDesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;


public class Desktop extends JPanel {
	final MainWindow parent;
	private final Controller controller;
	private final List<GraphicObject> graphicObjects;
	private GraphicObject tempGraphicObject;
	private Mode mode;
	private java.awt.Point centerPoint;
	
	public Desktop(MainWindow parent) {
		this.parent = parent;
		this.controller = parent.getController();
		this.graphicObjects = new ArrayList<>();
		this.mode = Mode.NONE;
		centerPoint = new java.awt.Point(0, 0);
		init();
	}
	
	@Override
	public void setMinimumSize(Dimension minimumSize) {
		super.setMinimumSize(minimumSize);
		centerPoint.setLocation(minimumSize.getWidth() / 2, minimumSize.getHeight() / 2);
	}
	
	public void setMode(final Mode mode) {
		if (this.mode != mode) {
			clearMouseListeners();
		}
		System.out.println("Set new mode: " + mode);
		this.mode = mode;
		switch(this.mode) {
		case EDGE:
			addMouseListener(new SegmentDesktopMouseListener(controller, this));
			addMouseMotionListener(new SegmentDesktopMouseListener(controller, this));
			break;
		case EDGE_DDA:
			addMouseListener(new SegmentDesktopMouseListener(controller, this));
			addMouseMotionListener(new SegmentDesktopMouseListener(controller, this));
			break;
		case NONE:
			addMouseListener(new DesktopMouseListener(controller, this));
			break;
		default:
			
		}
	}
	
	public java.awt.Point getCenterPoint() {
		return centerPoint;
	}
	
	public void setEdgePoint(final int x, final int y) {
		if (tempGraphicObject == null) {
			System.out.println("Create temp Edge. Frist point in (" + x + ", " + y + ")");
			switch(mode) {
			case EDGE:
				tempGraphicObject = new Segment(new Point(x, y));
				break;
			case EDGE_DDA:
				tempGraphicObject = new Segment_Brezenhem(new Point(x, y));
				break;
			}
				
		}
		else {
			((Segment)tempGraphicObject).setEndPoint(new Point(x, y));
			graphicObjects.add(tempGraphicObject);
			System.out.println("Set last point of temp Edge: (" + x + ", " + y + ")");
			add((JComponent) tempGraphicObject);
			Point refPoint = tempGraphicObject.getRefferencepoint();
			tempGraphicObject.setBounds(refPoint.getX() + centerPoint.x, refPoint.getY() + centerPoint.y, tempGraphicObject.getWidth(), tempGraphicObject.getHeight());
			tempGraphicObject = null;
			System.out.println("delete tempEdge");
			
			setMode(Mode.NONE);
		}
	}
	
	public void cancelTempObject() {
		tempGraphicObject = null;
		System.out.println("Cancel temp Graphic Object");
		setMode(Mode.NONE);
	}
	
	public void clearMouseListeners() {
		for (MouseListener listener: getMouseListeners()) {
			removeMouseListener(listener);
		}
		for (MouseMotionListener listener: getMouseMotionListeners()) {
			removeMouseMotionListener(listener);
		}
	}
	
	public final Mode getMode() {
		return mode;
	}
	
	private void init() {
		System.out.println("Desktop - init");
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		addMouseListener(new DesktopMouseListener(controller, this));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	}
	
	
}
