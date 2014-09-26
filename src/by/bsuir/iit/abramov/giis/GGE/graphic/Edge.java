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
		Point firstPoint = getFirstPointOnCanvas();
		Point lastPoint = getLastPointOnCanvas();
		drawLine(g2d, firstPoint.getX(), firstPoint.getY(), lastPoint.getX(), lastPoint.getY());
	}
	
	private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {

		int length = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
		double dx = (double)(x2 - x1) / length;
		double dy = (double)(y2 - y1) / length;
		double x = x1 + 0.5 * sign(dx);
		double y = y1 + 0.5 * sign(dy);
		drawPoint(g2d, x, y);
		int i = 0;
		while(i < length) {
			x = x + dx;
			y = y + dy;
			drawPoint(g2d, x, y);
			i += 1;
		}
	}
	
	private void drawPoint(Graphics2D g2d, double x, double y) {
		g2d.drawLine((int)x, (int)y, (int)x, (int)y);
	}
	
	private int sign(double x) {
		if (x == 0) {
			return 0;
		}
		else if (x > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
//	private int getLength(int x1, int y1, int x2, int y2) {
//		return 
//	}
	
	private Point getLastPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencepoint();
		point.setX(endPoint.getX() - refPoint.getX());
		point.setY(endPoint.getY() - refPoint.getY());
		return point;
	}
	
	private Point getFirstPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencepoint();
		point.setX(startPoint.getX() - refPoint.getX());
		point.setY(startPoint.getY() - refPoint.getY());
		return point;
	}

	@Override
	public Point getRefferencepoint() {
		if (startPoint == null || endPoint == null) {
			return null;
		}
		return new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(), endPoint.getY()));
	}

}
