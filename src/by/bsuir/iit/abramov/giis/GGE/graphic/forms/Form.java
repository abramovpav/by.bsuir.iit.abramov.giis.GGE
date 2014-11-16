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

	public Form(final int x, final int y, final DesktopController controller) {
		super(controller);
		basePoints = new ArrayList<Point>();
		graphicPoints = new HashMap<GraphicPoint, Point>();
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
	}

	public void addBasePoint(final int x, final int y) {
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
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

	@Override
	public int getBaseHeight() {
		List<Point> usedPoints = basePoints;
		int minY = usedPoints.get(0).getY();
		int maxY = usedPoints.get(0).getY();
		for (Point point : usedPoints) {
			if (point.getY() > maxY) {
				maxY = point.getY();
			}
			if (point.getY() < minY) {
				minY = point.getY();
			}
		}
		if (getPoints().size() > 0) {
			usedPoints = getPoints();
			for (Point point : usedPoints) {
				if (point.getX() > maxY) {
					maxY = point.getX();
				}
				if (point.getX() < minY) {
					minY = point.getX();
				}
			}
		}
		return Math.abs(maxY - minY) + 1;
	}

	protected Point getBasePoint(final int index) {
		return basePoints.get(index);
	}

	@Override
	public int getBaseWidth() {
		List<Point> usedPoints = basePoints;
		int minX = usedPoints.get(0).getX();
		int maxX = usedPoints.get(0).getX();
		for (Point point : usedPoints) {
			if (point.getX() > maxX) {
				maxX = point.getX();
			}
			if (point.getX() < minX) {
				minX = point.getX();
			}
		}
		if (getPoints().size() > 0) {
			usedPoints = getPoints();
			for (Point point : usedPoints) {
				if (point.getX() > maxX) {
					maxX = point.getX();
				}
				if (point.getX() < minX) {
					minX = point.getX();
				}
			}
		}
		
		return Math.abs(maxX - minX) + 1;
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
			FormPointDesktopMouseListener mouseListener = new FormPointDesktopMouseListener(this, graphicPoint);
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
	
	public void updateBasePoint(final GraphicPoint gPoint, int dx, int dy) {
		Point basePoint = graphicPoints.get(gPoint);
		if (basePoint == null) {
			return;
		}
		gPoint.setLocation(gPoint.getX() + dx, gPoint.getY() + dy);
		Point refPoint = getRefferencePoint();
		int x = (gPoint.getX() + gPoint.getHalfWidth() + refPoint.getX() * Config.CURRENT_SCALE) / Config.CURRENT_SCALE;
		int y = (gPoint.getY() + gPoint.getHalfHeight() + refPoint.getY() * Config.CURRENT_SCALE) / Config.CURRENT_SCALE;
		
		basePoint.setX(x);
		basePoint.setY(y);
		generate();
		
		for (GraphicPoint graphicPoint: graphicPoints.keySet()) {
			Point point = graphicPoints.get(graphicPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= graphicPoint.getHalfHeight();
			newX -= graphicPoint.getHalfWidth();
			graphicPoint.setLocation(newX, newY);
		}		
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
