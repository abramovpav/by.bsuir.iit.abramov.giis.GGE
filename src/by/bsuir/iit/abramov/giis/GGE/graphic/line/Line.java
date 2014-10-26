package by.bsuir.iit.abramov.giis.GGE.graphic.line;

import java.awt.Dimension;
import java.util.List;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObjectInterface;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Line extends GraphicObject implements GraphicObjectInterface {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;
	private Point				startPoint;
	private Point				endPoint;

	public Line(final DesktopController controller) {
		super(controller);
		startPoint = new Point();
		endPoint = new Point();
	}

	public Line(final Point start, final DesktopController controller) {
		super(controller);
		startPoint = start;
	}

	public Line(final int x, final int y, final DesktopController controller) {
		super(controller);
		startPoint = new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y));
	}

	public Line(final Point start, final Point end, final List<Point> points,
			final DesktopController controller) {
		super(controller);
		startPoint = start;
		endPoint = end;
		this.points.addAll(points);
	}

	@Override
	public void addPoint(final Point point) {
		super.addPoint(point);
		controller.log(point, startPoint, endPoint);
	}

	@Override
	public void generate() {
		points.clear();
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

	public Point getStartPoint() {
		return startPoint;
	}

	public void setEndPoint(final Point endPoint) {
		this.endPoint = endPoint;
		setPreferredSize(new Dimension(getScaledWidth(), getScaledHeight()));
	}

	public void setStartPoint(final Point startPoint) {
		this.startPoint = startPoint;
	}
}
