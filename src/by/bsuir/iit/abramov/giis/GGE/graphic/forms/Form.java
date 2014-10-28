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
	
	private List<Point> basePoints;
	private Map<GraphicPoint, Point> graphicPoints;

	public Form(final int x, final int y, DesktopController controller) {
		super(controller);
		basePoints = new ArrayList<Point>();
		graphicPoints = new HashMap<>();
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
	}
	
	public void addBasePoint(final int x, final int y) {
		basePoints.add(new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y)));
		setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));
	}

	@Override
	public int getBaseHeight() {
		int minY = basePoints.get(0).getY();
		int maxY = basePoints.get(0).getY();
		for (Point point: basePoints) {
			if (point.getY() > maxY) {
				maxY = point.getY();
			}
			if (point.getY() < minY) {
				minY = point.getY();
			}
		}
		return Math.abs(maxY - minY);
	}

	@Override
	public int getBaseWidth() {
		int minX = basePoints.get(0).getX();
		int maxX = basePoints.get(0).getX();
		for (Point point: basePoints) {
			if (point.getX() > maxX) {
				maxX = point.getX();
			}
			if (point.getX() < minX) {
				minX = point.getX();
			}
		}
		return Math.abs(maxX - minX);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(1, 1, getWidth()-2, getHeight()-2);
		
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
			for (GraphicPoint point: graphicPoints.keySet()) {
				add(point);
			}
			repaint();
			return;
		}
		Point refPoint = getRefferencePoint();
		for (Point point: basePoints) {
			graphicPoint = new GraphicPoint();
			graphicPoint.addMouseListener(new FormPointDesktopMouseListener(controller, this));
			add(graphicPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= graphicPoint.getHalfHeight();
			newX -= graphicPoint.getHalfWidth();
			graphicPoint.setLocation(newX, newY);
			System.out.println();
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
		
		for (GraphicPoint point: graphicPoints.keySet()) {
			remove(point);
		}
		repaint();
	}

	@Override
	public Point getRefferencePoint() {
		if (basePoints.size() <= 0) {
			return null;
		}
		int minX = 0, minY = 0, maxX = 0, maxY = 0;
		minX = minY = maxX = maxY = basePoints.get(0).getY();
		
		for (Point point: basePoints) {
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
	public void updateBounds(java.awt.Point centerPoint) {
		super.updateBounds(centerPoint);
		Point refPoint = getRefferencePoint();
		for (GraphicPoint gPoint: graphicPoints.keySet()) {
			Point point = graphicPoints.get(gPoint);
			int newX = (point.getX() - refPoint.getX()) * Config.CURRENT_SCALE;
			int newY = (point.getY() - refPoint.getY()) * Config.CURRENT_SCALE;
			newY -= gPoint.getHalfHeight();
			newX -= gPoint.getHalfWidth();
			gPoint.setLocation(newX, newY);
		}
	}

	
}
