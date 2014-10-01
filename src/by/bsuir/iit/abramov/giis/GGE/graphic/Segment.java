package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Segment extends JComponent implements GraphicObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	private final List<Point> points;
	private boolean generated = false;
	private Color color = Color.BLACK;

	public Segment() {
		points = new ArrayList<Point>();
		startPoint = new Point();
		endPoint = new Point();
	}

	public Segment(final Point start) {
		points = new ArrayList<Point>();
		startPoint = start;
		points.add(start);
	}

	public boolean isGenerated() {
		return generated;
	}

	private void log(final Point point) {
		System.out.println(point.getX() + " " + point.getY());
	}

	protected void generated() {
		generated = true;
	}

	@Override
	public void generate() {
		points.clear();
	}

	public Segment(final Point start, final Point end, final List<Point> points) {
		this.points = new ArrayList<Point>();
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
		int width = Math.abs(startPoint.getX() - endPoint.getX());
		while (width % Config.DEFAULT_SCALE != 0) {
			width++;
		}
		return width;// * Config.DEFAULT_SCALE;
	}

	@Override
	public final int getHeight() {
		int heigth = Math.abs(startPoint.getY() - endPoint.getY());
		while (heigth % Config.DEFAULT_SCALE != 0) {
			heigth++;
		}
		return heigth;// * Config.DEFAULT_SCALE;
	}

	@Override
	public final List<Point> getPoints() {
		return points;
	}

	public void addPoint(final Point point) {
		points.add(point);
		log(point);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		System.out.println(this.getClass().getSimpleName() + "-paint");
		if (!isGenerated()) {
			super.paintComponent(g);
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		draw(g2d);
	}

	private void draw(final Graphics2D g2d) {
		for (Point point : getPoints()) {
			drawPoint(g2d, point);
		}
	}

	protected void setColor(final Color color) {
		this.color = color;
	}

	private void drawPoint(final Graphics2D g2d, final Point point) {
		g2d.setColor(color);
		int x = point.getX() * Config.DEFAULT_SCALE;
		if (x == 0) {
			x = Config.DEFAULT_SCALE / 2;
		}
		int y = point.getY() * Config.DEFAULT_SCALE;
		if (y == 0) {
			y = Config.DEFAULT_SCALE / 2;
		}
		for(int i = x - Config.DEFAULT_SCALE + 1; i <= x; i++) {
			for(int j = y - Config.DEFAULT_SCALE + 1; j <= y; j++) {
				g2d.drawLine(i, j, i, j);
			}
		}
		
	}

	protected Point getLastPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencePoint();
		int x = endPoint.getX() - refPoint.getX();
		x = getScaledCoord(x);
		int y = endPoint.getY() - refPoint.getY();
		y = getScaledCoord(y);
		point.setX(x);
		point.setY(y);
		return point;
	}

	protected Point getFirstPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencePoint();
		int x = startPoint.getX() - refPoint.getX();
		x = getScaledCoord(x);
		int y = startPoint.getY() - refPoint.getY();
		y = getScaledCoord(y);
		point.setX(x);
		point.setY(y);
		return point;
	}

	private int getScaledCoord(int coord) {
		if (coord % Config.DEFAULT_SCALE > 0) {
			coord = coord / Config.DEFAULT_SCALE  + 1;
		} else {
			coord /= Config.DEFAULT_SCALE;
		}
		return coord;
	}

	@Override
	public Point getRefferencePoint() {
		if (startPoint == null || endPoint == null) {
			return null;
		}
		return new Point(Math.min(startPoint.getX(), endPoint.getX()),
				Math.min(startPoint.getY(), endPoint.getY()));
	}

	protected int sign(final double x) {
		if (x == 0) {
			return 0;
		} else if (x > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	protected Color getColor(final float intensity) {

		return new Color((int) (255 * intensity), (int) (255 * intensity),
				(int) (255 * intensity));
	}

}
