package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Line extends JComponent implements GraphicObject {
	/**
	 *
	 */
	private static final long		serialVersionUID	= 1L;
	private Point					startPoint;
	private Point					endPoint;
	private final List<Point>		points;
	private boolean					generated			= false;
	private final DesktopController	controller;

	public Line(final DesktopController controller) {
		this.controller = controller;
		points = new ArrayList<Point>();
		startPoint = new Point();
		endPoint = new Point();
	}

	public Line(final Point start, final DesktopController controller) {
		this.controller = controller;
		points = new ArrayList<Point>();
		startPoint = start;
	}

	public Line(final Point start, final Point end, final List<Point> points,
			final DesktopController controller) {
		this.controller = controller;
		this.points = new ArrayList<Point>();
		startPoint = start;
		endPoint = end;
		this.points.addAll(points);
	}

	public void addPoint(final Point point) {
		points.add(point);
		controller.log(point, startPoint, endPoint);
	}

	private void draw(final Graphics2D g2d) {
		for (Point point : getPoints()) {
			drawPoint(g2d, point);
		}
	}

	private void drawPoint(final Graphics2D g2d, final Point point) {
		g2d.setColor(point.getColor());
		int x = point.getX() * Config.CURRENT_SCALE;
		int y = point.getY() * Config.CURRENT_SCALE;
		for (int i = x; i < x + Config.CURRENT_SCALE; i++) {
			for (int j = y; j < y + Config.CURRENT_SCALE; j++) {
				g2d.drawLine(i, j, i, j);
			}
		}
	}

	@Override
	public void generate() {
		points.clear();
	}

	protected void generated() {
		generated = true;
	}

	@Override
	public final int getBaseHeight() {
		int heigth = Math.abs(startPoint.getY() - endPoint.getY());
		while (heigth % Config.CURRENT_SCALE != 0 || heigth == 0) {
			heigth++;
		}
		return heigth;
	}

	@Override
	public final int getBaseWidth() {
		int width = Math.abs(startPoint.getX() - endPoint.getX());
		while (width % Config.CURRENT_SCALE != 0 || width == 0) {
			width++;
		}
		return width;
	}

	protected Color getColor(final float intensity) {
		return new Color((int) (255 * intensity), (int) (255 * intensity), (int) (255 * intensity));
	}

	public Point getEndPoint() {
		return endPoint;
	}

	protected Point getFirstPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencePoint();
		int x = startPoint.getX() - refPoint.getX();
		int y = startPoint.getY() - refPoint.getY();
		point.setX(x);
		point.setY(y);
		return point;
	}

	protected Point getLastPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencePoint();
		int x = endPoint.getX() - refPoint.getX();
		int y = endPoint.getY() - refPoint.getY();
		point.setX(x);
		point.setY(y);
		return point;
	}

	@Override
	public final List<Point> getPoints() {
		return points;
	}

	@Override
	public Point getRefferencePoint() {
		if (startPoint == null || endPoint == null) {
			return null;
		}
		return new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(),
				endPoint.getY()));
	}

	private int getScaledCoord(int coord) {
		if (coord % Config.CURRENT_SCALE > 0) {
			coord = coord / Config.CURRENT_SCALE + 1;
		} else {
			coord /= Config.CURRENT_SCALE;
		}
		return coord;
	}

	@Override
	public int getScaledHeight() {
		return getBaseHeight() * Config.CURRENT_SCALE;
	}

	@Override
	public final int getScaledWidth() {
		return getBaseWidth() * Config.CURRENT_SCALE;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public boolean isGenerated() {
		return generated;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		System.out.println(this.getClass().getSimpleName() + "-paint");
		if (!isGenerated()) {
			super.paintComponent(g);
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		// g2d.drawRect(0, 0, getScaledWidth() - 1, getScaledHeight() - 1);
		draw(g2d);
	}

	public void setEndPoint(final Point endPoint) {
		this.endPoint = endPoint;
		setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));
	}

	public void setStartPoint(final Point startPoint) {
		this.startPoint = startPoint;
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

	@Override
	public void updateBounds(final java.awt.Point point) {
		Point refPoint = getRefferencePoint();
		setBounds(refPoint.getX() * Config.CURRENT_SCALE + point.x, refPoint.getY()
				* Config.CURRENT_SCALE + point.y, getScaledWidth(), getScaledHeight());
	}
}
