package by.bsuir.iit.abramov.giis.GGE.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;
	final MainWindow					parent;
	private final DesktopController		controller;
	private final List<GraphicObject>	graphicObjects;
	private GraphicObject				tempGraphicObject;
	private Mode						mode;
	private final java.awt.Point		centerPoint;

	public Desktop(final MainWindow parent, final DesktopController controller) {
		this.parent = parent;
		this.controller = controller;
		graphicObjects = new ArrayList<GraphicObject>();
		mode = Mode.NONE;
		centerPoint = new java.awt.Point(0, 0);
		init();
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
		for (MouseMotionListener listener : getMouseMotionListeners()) {
			removeMouseMotionListener(listener);
		}
	}

	public java.awt.Point getCenterPoint() {
		return centerPoint;
	}

	public final Mode getMode() {
		return mode;
	}

	private int getScaleCoord(final int x) {
		return x / Config.CURRENT_SCALE;
	}

	private void init() {
		System.out.println("Desktop - init");
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		addMouseListener(new DesktopMouseListener(controller, this));
		addMouseWheelListener(new DesktopWheelMouseListener(controller, this));
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	}

	public void setLinePoint(final int x, final int y) {
		if (tempGraphicObject == null) {
			controller.log("Create temp Line. Frist point in (" + x + ", " + y + ")");
			switch (mode) {
			case LINE_DDA:
				tempGraphicObject = new LineDDA(new Point(getScaleCoord(x), getScaleCoord(y)),
						controller);
				break;
			case LINE_BREZENHEM:
				tempGraphicObject = new Line_Brezenhem(
						new Point(getScaleCoord(x), getScaleCoord(y)), controller);
				break;
			case LINE_WY:
				tempGraphicObject = new Line_Wy(new Point(getScaleCoord(x), getScaleCoord(y)),
						controller);
				break;
			}

		} else {
			((Line) tempGraphicObject).setEndPoint(new Point(getScaleCoord(x), getScaleCoord(y)));
			graphicObjects.add(tempGraphicObject);
			controller.log("Set last point of temp Line: (" + x + ", " + y + ")");
			add((JComponent) tempGraphicObject);
			tempGraphicObject.generate();
			Point refPoint = tempGraphicObject.getRefferencePoint();
			tempGraphicObject.setBounds(refPoint.getX() * Config.CURRENT_SCALE + centerPoint.x,
					refPoint.getY() * Config.CURRENT_SCALE + centerPoint.y,
					tempGraphicObject.getScaledWidth(), tempGraphicObject.getScaledHeight());
			tempGraphicObject = null;
			controller.log("delete tempLine");

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
		System.out.println("Set new mode: " + mode);
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
			addMouseListener(new DesktopMouseListener(controller, this));
			addMouseMotionListener(new DesktopMouseListener(controller, this));
			break;
		default:

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
