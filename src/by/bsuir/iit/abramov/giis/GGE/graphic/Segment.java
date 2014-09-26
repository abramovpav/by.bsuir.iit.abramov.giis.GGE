package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class Segment extends JComponent implements GraphicObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	private final List<Point> points;

	public Segment() {
		points = new ArrayList<>();
		startPoint = new Point();
		endPoint = new Point();
	}

	public Segment(final Point start) {
		points = new ArrayList<>();
		startPoint = start;
		points.add(start);
	}

	public Segment(final Point start, final Point end, final List<Point> points) {
		this.points = new ArrayList<>();
		startPoint = start;
		endPoint = end;
		this.points.addAll(points);
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(final Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(final Point endPoint) {
		this.endPoint = endPoint;
		setPreferredSize(new Dimension(getWidth(), getHeight()));
	}

	@Override
	public final int getWidth() {
		return Math.abs(startPoint.getX() - endPoint.getX());
	}

	@Override
	public final int getHeight() {
		return Math.abs(startPoint.getY() - endPoint.getY());
	}

	@Override
	public final List<Point> getPoints() {
		return points;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		System.out.println("Segment-paint");
		Graphics2D g2d = (Graphics2D) g;
		Point firstPoint = getFirstPointOnCanvas();
		Point lastPoint = getLastPointOnCanvas();
		g2d.drawLine(firstPoint.getX(), firstPoint.getY(), lastPoint.getX(),
				lastPoint.getY());
	}

	protected Point getLastPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencepoint();
		point.setX(endPoint.getX() - refPoint.getX());
		point.setY(endPoint.getY() - refPoint.getY());
		return point;
	}

	protected Point getFirstPointOnCanvas() {
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
		return new Point(Math.min(startPoint.getX(), endPoint.getX()),
				Math.min(startPoint.getY(), endPoint.getY()));
	}

}
