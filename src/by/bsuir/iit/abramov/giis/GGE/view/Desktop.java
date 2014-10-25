package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.Line;
import by.bsuir.iit.abramov.giis.GGE.graphic.LineDDA;
import by.bsuir.iit.abramov.giis.GGE.graphic.Line_Brezenhem;
import by.bsuir.iit.abramov.giis.GGE.graphic.Line_Wy;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.DesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.DesktopWheelMouseListener;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.LineDesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.main.Config;
import by.bsuir.iit.abramov.giis.GGE.utils.Mode;

public class Desktop extends JPanel {
	private static final String			NEW_MODE			= "Set new mode: ";
	private static final String			COORD_SEPARATOR		= ", ";
	private static final String			DELETE_TEMP_LINE	= "delete tempLine";
	private static final String			SET_LAST_POINT		= "Set last point of temp Line: ";
	private static final String			CREATE_TEMP_LINE	= "Create temp Line. First point in ";
	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;
	private final MainWindow			parent;
	private final DesktopController		controller;
	private final List<GraphicObject>	graphicObjects;
	private GraphicObject				tempGraphicObject;
	private Mode						mode;
	private final java.awt.Point		centerPoint;

	public Desktop(final MainWindow parent, final DesktopController controller) {
		this.parent = parent;
		this.controller = controller;
		graphicObjects = new ArrayList<GraphicObject>();
		centerPoint = new java.awt.Point(0, 0);
		init();
		setMode(Mode.NONE);
	}

	private void addLineMouseListeners() {
		addMouseListener(new LineDesktopMouseListener(controller, this));
	}

	public void cancelTempObject() {
		tempGraphicObject = null;
		System.out.println("Cancel temp Graphic Object");
		setMode(Mode.NONE);
	}

	public void clearMouseListeners() {
		for (MouseListener listener : getMouseListeners()) {
			removeMouseListener(listener);
		}
	}

	public java.awt.Point getCenterPoint() {
		return centerPoint;
	}

	public final Mode getMode() {
		return mode;
	}

	private void init() {
		System.out.println("Desktop - init");
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		addMouseListener(new DesktopMouseListener(controller, this));
		addMouseWheelListener(new DesktopWheelMouseListener(controller, this));
	}

	public void last() {
		if (graphicObjects.size() > 0) {
			GraphicObject last = getCurrentGraphicObject();
			last.last();
		}
	}

	public void next() {
		GraphicObject last = getCurrentGraphicObject();
		last.next();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// Coordinate's grid
		for (int i = Config.getHalfScale(); i < getWidth(); i += Config.CURRENT_SCALE) {
			g2d.drawLine(i, 0, i, getHeight());
		}
		for (int i = Config.getHalfScale(); i < getHeight(); i += Config.CURRENT_SCALE) {
			g2d.drawLine(0, i, getWidth(), i);
		}
		// Axis
		g2d.drawLine(0, getHeight() / 2 - 1, getWidth(), getHeight() / 2 - 1);
		g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g2d.drawLine(0, getHeight() / 2 + 1, getWidth(), getHeight() / 2 + 1);
		g2d.drawLine(getWidth() / 2 - 1, 0, getWidth() / 2 - 1, getHeight());
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.drawLine(getWidth() / 2 + 1, 0, getWidth() / 2 + 1, getHeight());
	}

	public void prev() {
		GraphicObject last = getCurrentGraphicObject();
		last.prev();
	}

	private GraphicObject getCurrentGraphicObject() {
		return graphicObjects.get(graphicObjects.size() - 1);
	}

	public void setLinePoint(final int x, final int y) {
		if (tempGraphicObject == null) {
			controller.log(
					CREATE_TEMP_LINE + Point.getUnscaledCoord(x) + COORD_SEPARATOR
							+ Point.getUnscaledCoord(y), false);
			switch (mode) {
			case LINE_DDA:
				tempGraphicObject = new LineDDA(x, y, controller);
				break;
			case LINE_BREZENHEM:
				tempGraphicObject = new Line_Brezenhem(x, y, controller);
				break;
			case LINE_WY:
				tempGraphicObject = new Line_Wy(x, y, controller);
				break;
			}
		} else {
			last();
			((Line) tempGraphicObject).setEndPoint(new Point(Point.getUnscaledCoord(x), Point
					.getUnscaledCoord(y)));

			add((JComponent) tempGraphicObject);
			graphicObjects.add(tempGraphicObject);

			tempGraphicObject.generate();
			tempGraphicObject.updateBounds(centerPoint);
			tempGraphicObject = null;

			controller.log(
					SET_LAST_POINT + Point.getUnscaledCoord(x) + COORD_SEPARATOR
							+ Point.getUnscaledCoord(y), false);
			controller.log(DELETE_TEMP_LINE, false);
			controller.activateStepButton();
			setMode(Mode.NONE);
		}
	}

	@Override
	public void setMinimumSize(final Dimension minimumSize) {
		super.setMinimumSize(minimumSize);
		centerPoint.setLocation(minimumSize.getWidth() / 2, minimumSize.getHeight() / 2);
	}

	public void setMode(final Mode mode) {
		if (this.mode != mode) {
			clearMouseListeners();
		}
		System.out.println(NEW_MODE + mode);
		this.mode = mode;
		switch (this.mode) {
		case LINE_DDA:
			addLineMouseListeners();
			break;
		case LINE_BREZENHEM:
			addLineMouseListeners();
			break;
		case LINE_WY:
			addLineMouseListeners();
		case NONE:
			addDesktopMouseListeners();
			break;
		}
	}

	private void addDesktopMouseListeners() {
		addMouseListener(new DesktopMouseListener(controller, this));
		if (getMouseMotionListeners().length == 0) {
			addMouseMotionListener(new DesktopMouseListener(controller, this));
		}
	}

	public void showLog() {
		controller.showLog();
	}

	public void updateGraphics() {
		java.awt.Point centerPoint = getCenterPoint();
		for (GraphicObject object : graphicObjects) {
			object.updateBounds(centerPoint);
		}
	}

}
