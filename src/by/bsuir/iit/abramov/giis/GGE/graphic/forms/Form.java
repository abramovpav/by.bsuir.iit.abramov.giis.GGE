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
		updateWidthAndHeight(basePoints, false);
		generateRefPoint();
	}

	private void generateRefPoint() {
		if (basePoints.size() <= 0) {
			return;
		}
		refPoint = getLeftUpPoint(basePoints, false);
	}
	
	public List<Integer> getWorkAreaBounds(List<Point> points, boolean withBasePoints) {
		int minX = 99999, minY = 99999, maxX = 0, maxY = 0;
		
		if (points !=null) {
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
		}
		if (!withBasePoints) {
			List<Integer> results = new ArrayList<Integer>();
			results.add(minX);
			results.add(minY);
			results.add(maxX);
			results.add(maxY);
			return results;
		}
		List<Integer> results = getLocalBasePointAreaBounds(minX, minY, maxX, maxY);
		return results;
	}
	
	protected List<Integer> getLocalBasePointAreaBounds(int minX, int minY, int maxX, int maxY) {
		for (Point point : basePoints) {
			Point localpoint = /*getLocalCoord(*/point;
			if (localpoint.getY() > maxY) {
				maxY = localpoint.getY();
			}
			if (localpoint.getY() < minY) {
				minY = localpoint.getY();
			}
			if (localpoint.getX() > maxX) {
				maxX = localpoint.getX();
			}
			if (localpoint.getX() < minX) {
				minX = localpoint.getX();
			}
		}
		List<Integer> results = new ArrayList<Integer>();
		results.add(minX);
		results.add(minY);
		results.add(maxX);
		results.add(maxY);
		return results;
	}
	
	public Point getLeftUpPoint(final List<Point> points, boolean withBasePoints) {
		
		List<Integer> bounds = getWorkAreaBounds(points, withBasePoints);
		return new Point(bounds.get(0), bounds.get(1));
	}

	protected void updateWidthAndHeight(List<Point> points, boolean withBasePoints) {
		List<Integer> bounds = getWorkAreaBounds(points, withBasePoints);
		
		form_size.setSize(Math.abs(bounds.get(2) - bounds.get(0)) + 1, Math.abs(bounds.get(3) - bounds.get(1)) + 1);
	}
	
	protected void updateWidthAndHeight(int x1, int y1, int x2, int y2) {
		
		form_size.setSize(Math.abs(x2 - x1) + 1, Math.abs(y2 - y1) + 1);
	}
	
	protected Point getBasePoint(final int index) {
		return basePoints.get(index);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		g2d.drawRect(1, 1, getWidth() - 2, getHeight() - 2);
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
//				add(point);
			}
			return;
		}
//		Point refPoint = getRefferencePoint();
		for (Point point : basePoints) {
			graphicPoint = new GraphicPoint();
			FormPointDesktopMouseListener mouseListener = new FormPointDesktopMouseListener(this,
					graphicPoint);
			graphicPoint.addMouseListener(mouseListener);
			graphicPoint.addMouseMotionListener(mouseListener);
//			add(graphicPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= graphicPoint.getHalfHeight();
			newX -= graphicPoint.getHalfWidth();
			graphicPoint.setLocation(newX, newY);
			graphicPoints.put(graphicPoint, point);
		}
//		repaint();
	}

	@Override
	public void unselect() {
		if (!isSelected()) {
			return;
		}
		super.unselect();

		for (GraphicPoint point : graphicPoints.keySet()) {
//			remove(point);
		}
//		repaint();
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
//		Point refPoint = getRefferencePoint();
		int x = (gPoint.getX() + gPoint.getHalfWidth() + refPoint.getX() * Config.CURRENT_SCALE)
				/ Config.CURRENT_SCALE;
		int y = (gPoint.getY() + gPoint.getHalfHeight() + refPoint.getY() * Config.CURRENT_SCALE)
				/ Config.CURRENT_SCALE;
		
		if (basePoint.getX() != x || basePoint.getY() != y) {
			basePoint.setX(x);
			basePoint.setY(y);
			generate();
//			repaint();
		}
		
	}

}
