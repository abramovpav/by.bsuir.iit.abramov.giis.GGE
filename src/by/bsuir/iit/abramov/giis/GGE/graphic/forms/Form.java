package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObjectInterface;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.listeners.mouse.FormPointDesktopMouseListener;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Form extends GraphicObject implements GraphicObjectInterface {

	private final List<Point>				basePoints;
	private final Map<GraphicPoint, Point>	graphicPoints;
	private Point							refPoint;
	private final Dimension					form_size;

	public Form(final int x, final int y, final DesktopController controller) {
		super(controller);
		basePoints = new ArrayList<Point>();
		graphicPoints = new HashMap<GraphicPoint, Point>();
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
		addPoint(new Point(x, y));
		form_size = new Dimension();
	}

	public void addBasePoint(final int x, final int y) {
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
		addPoint(new Point(x, y));
		updateWidthAndHeight();
		generateRefPoint();
		setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));
	}

	private void generateRefPoint() {
		if (basePoints.size() <= 0) {
			return;
		}
		refPoint = getLeftUpPoint(basePoints);
	}

	static public Point getLeftUpPoint(final List<Point> points) {
		int minX = 0, minY = 0, maxX = 0, maxY = 0;
		minY = maxY = points.get(0).getY();
		minX = maxX = points.get(0).getX();

		for (Point point : points) {
			if (point.getY() > maxY) {
				maxY = point.getY();
			}
			if (point.getY() < minY) {
				minY = point.getY();
			}
			if (point.getX() > maxX) {
				maxX = point.getX();
			}
			if (point.getX() < minX) {
				minX = point.getX();
			}
		}
		return new Point(minX, minY);
	}

	protected void updateWidthAndHeight() {
		List<Point> points = getPoints();
		int minX = points.get(0).getX();
		int maxX = minX;
		int minY = points.get(0).getY();
		int maxY = minY;
		for (Point point : points) {
			if (point.getX() > maxX) {
				maxX = point.getX();
			}
			if (point.getX() < minX) {
				minX = point.getX();
			}

			if (point.getY() > maxY) {
				maxY = point.getY();
			}
			if (point.getY() < minY) {
				minY = point.getY();
			}
		}
		form_size.setSize(Math.abs(maxX - minX) + 1, Math.abs(maxY - minY) + 1);
	}

	@Override
	public int getBaseHeight() {
		return (int) form_size.getHeight();
	}

	protected Point getBasePoint(final int index) {
		return basePoints.get(index);
	}

	@Override
	public int getBaseWidth() {
		return (int) form_size.getWidth();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
		super.paintComponent(g);

	}

	@Override
	public void select() {
		if (isSelected()) {
			return;
		}
		super.select();
		System.out.println("form2-select");
		GraphicPoint graphicPoint;
		if (graphicPoints.size() > 0) {
			for (GraphicPoint point : graphicPoints.keySet()) {
				add(point);
			}
			repaint();
			return;
		}
		Point refPoint = getRefferencePoint();
		for (Point point : basePoints) {
			graphicPoint = new GraphicPoint();
			FormPointDesktopMouseListener mouseListener = new FormPointDesktopMouseListener(this,
					graphicPoint);
			graphicPoint.addMouseListener(mouseListener);
			graphicPoint.addMouseMotionListener(mouseListener);
			add(graphicPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= graphicPoint.getHalfHeight();
			newX -= graphicPoint.getHalfWidth();
			graphicPoint.setLocation(newX, newY);
			graphicPoints.put(graphicPoint, point);
		}
		repaint();
	}

	@Override
	public void unselect() {
		if (!isSelected()) {
			return;
		}
		super.unselect();

		for (GraphicPoint point : graphicPoints.keySet()) {
			remove(point);
		}
		repaint();
	}

	@Override
	public Point getRefferencePoint() {
		return refPoint;
	}

	protected void setRefPoint(final Point point) {
		if (point == null) {
			return;
		}
		refPoint.setX(point.getX());
		refPoint.setY(point.getY());
	}

	public void updateBasePoint(final GraphicPoint gPoint, final int dx, final int dy) {
		Point basePoint = graphicPoints.get(gPoint);
		if (basePoint == null) {
			return;
		}
		gPoint.setLocation(gPoint.getX() + dx, gPoint.getY() + dy);
		Point refPoint = getRefferencePoint();
		int x = (gPoint.getX() + gPoint.getHalfWidth() + refPoint.getX() * Config.CURRENT_SCALE)
				/ Config.CURRENT_SCALE;
		int y = (gPoint.getY() + gPoint.getHalfHeight() + refPoint.getY() * Config.CURRENT_SCALE)
				/ Config.CURRENT_SCALE;

		basePoint.setX(x);
		basePoint.setY(y);
		generate();
		repaint();
	}

	public void baseUpdateBounds(final java.awt.Point centerPoint) {
		super.updateBounds(centerPoint);
	}

	@Override
	public void updateBounds(final java.awt.Point centerPoint) {
		super.updateBounds(centerPoint);
		Point refPoint = getRefferencePoint();
		for (GraphicPoint gPoint : graphicPoints.keySet()) {
			Point point = graphicPoints.get(gPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= gPoint.getHalfHeight();
			newX -= gPoint.getHalfWidth();
			gPoint.setLocation(newX, newY);
		}
	}

}
