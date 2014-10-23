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
	private int						currentStep			= 1;

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
		for (int index = 0; index <= currentStep; index++) {
			Point point = getPoint(index);
			if (point != null) {
				drawPoint(g2d, point);
			}
		}
	}

	private void drawPoint(final Graphics2D g2d, final Point point) {
		g2d.setColor(point.getColor());
		int x = point.getX() * Config.CURRENT_SCALE;
		int y = point.getY() * Config.CURRENT_SCALE;
		for (int i = x - Config.getHalfScale(); i < x + Config.getHalfScale(); i++) {
			for (int j = y - Config.getHalfScale(); j < y + Config.getHalfScale(); j++) {
				g2d.drawLine(i + Config.getHalfScale(), j + Config.getHalfScale(),
						i + Config.getHalfScale(), j + Config.getHalfScale());
			}
		}
	}

	@Override
	public void generate() {
		points.clear();
	}

	protected void generated() {
		generated = true;
		currentStep = points.size() - 1;
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
		Point refPoint = getRefferencePointLocal();
		int x = startPoint.getX() - refPoint.getX();
		int y = startPoint.getY() - refPoint.getY();
		point.setX(x);
		point.setY(y);
		return point;
	}

	protected Point getLastPointOnCanvas() {
		Point point = new Point();
		Point refPoint = getRefferencePointLocal();
		int x = endPoint.getX() - refPoint.getX();
		int y = endPoint.getY() - refPoint.getY();
		point.setX(x);
		point.setY(y);
		return point;
	}

	public final Point getPoint(final int index) {
		if (index >= 0 && index < points.size()) {
			return points.get(index);
		} else {
			return null;
		}
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

	public Point getRefferencePointLocal() {
		if (startPoint == null || endPoint == null) {
			return null;
		}
		return new Point(Math.min(startPoint.getX(), endPoint.getX()), Math.min(startPoint.getY(),
				endPoint.getY()));
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

	protected void log(final String msg, final boolean offset) {
		controller.log(msg, offset);
	}

	private void logPointInfo(final int index) {
		Point point = getPoint(index);
		if (point != null) {
			controller.log("Last point:", false);
			controller.log(point);
			controller.log(point.getGenerateInfo(), true);
		}
	}

	@Override
	public void next() {
		if (currentStep + 1 < points.size()) {
			currentStep++;
			logPointInfo(currentStep);
			repaint();
		}
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
		// g2d.drawRect(1, 1, getScaledWidth() - 2, getScaledHeight() - 2);
		draw(g2d);
	}

	@Override
	public void prev() {
		if (currentStep - 1 >= 0) {
			currentStep--;
			logPointInfo(currentStep);
			repaint();
		}
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
		setBounds(refPoint.getX() * Config.CURRENT_SCALE + point.x - Config.getHalfScale(),
				refPoint.getY() * Config.CURRENT_SCALE + point.y - Config.getHalfScale(),
				getScaledWidth(), getScaledHeight());
	}
}
