package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import javax.swing.JComponent;

public class Edge extends JComponent implements GraphicObject {
	private Point startPoint;
	private Point endPoint;
	private final List<Point> points;
	
	public Edge() {
		this.points = new ArrayList<>();
		startPoint = new Point();
		endPoint = new Point();
	}
	
	public Edge(final Point start) {
		this.points = new ArrayList<>();
		this.startPoint = start;
		this.points.add(start);
	}
	
	public Edge(final Point start, final Point end, final List<Point> points) {
		this.points = new ArrayList<>();
		this.startPoint = start;
		this.endPoint = end;
		this.points.addAll(points);
	}
	

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
		this.setPreferredSize(new Dimension(getWidth(), getHeight()));
	}
	
	@Override
	public final int getWidth() {
		return Math.max(startPoint.getX(), endPoint.getX());
	}
	
	@Override
	public final int getHeight() {
		return Math.max(startPoint.getY(), endPoint.getY());
	}

	@Override
	public final List<Point> getPoints() {
		return this.points;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		Point lastPoint = getLastPointOnCanvas();
		g2d.drawLine(0, 0, lastPoint.getX(), lastPoint.getY());
	}
	
	private Point getLastPointOnCanvas() {
		Point point = new Point();
		point.setX(Math.abs(endPoint.getX() - startPoint.getX()));
		point.setY(Math.abs(endPoint.getY() - startPoint.getY()));
		return point;
	}

	@Override
	public Point getRefferencepoint() {
		if (startPoint == null || endPoint == null) {
			return null;
		}
		return new Point(getWidth(), getHeight());
	}

}
