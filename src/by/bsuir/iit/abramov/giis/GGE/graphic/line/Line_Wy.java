package by.bsuir.iit.abramov.giis.GGE.graphic.line;

import java.awt.Color;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class Line_Wy extends Line {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public Line_Wy(final DesktopController controller) {
		super(controller);
	}

	public Line_Wy(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}

	@Override
	public void generate() {
		super.generate();
		Point firstPoint = getFirstPointOnCanvas();
		Point lastPoint = getLastPointOnCanvas();
		int x1 = firstPoint.getX();
		int y1 = firstPoint.getY();
		int x2 = lastPoint.getX();
		int y2 = lastPoint.getY();
		if (x1 == x2) {
			generateStraightLine(y1, y2, x1, false);
			generated();
			return;
		} else if (y1 == y2) {
			generateStraightLine(x1, x2, y1, true);
			generated();
			return;
		}
		int a = 1;
		int b = 1;
		if (x1 < x2) {
			if (y1 > y2) {
				b = -1;
			}
		} else if (x1 > x2) {
			if (y1 > y2) {
				a = b = -1;
			} else if (y2 > y1) {
				a = -1;
			}
		}
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int e = 2 * dy - dx;
		double x = x1;
		double y = y1;
		Point curPoint = new Point((int) x, (int) y, Color.BLACK);
		double segmentAngle = 0;
		double distance = 0;
		addPoint(curPoint);
		String info = generateinfo(e, distance);
		curPoint.addGenerateInfo(info);
		log(info, true);
		int i = 0;
		if (dx > dy) {
			segmentAngle = Math.atan2(dy, dx);
			while (i < dx) {
				if (e >= 0) {
					y += b;
					e -= 2 * dx;
				}
				x += a;
				e += 2 * dy;
				i++;
				distance = getDistance(x1, y1, x2, y2, dy, x, y, segmentAngle);
				curPoint = new Point((int) x, (int) y, getColor((float) distance));
				addPoint(curPoint);
				info = generateinfo(e, distance);
				curPoint.addGenerateInfo(info);
				log(info, true);
				// System.out.println("second");
				distance = getDistance(x1, y1, x2, y2, dy, x, y + 1, segmentAngle);
				if (distance > 1) {
					distance = getDistance(x1, y1, x2, y2, dy, x, y - 1, segmentAngle);
					curPoint = new Point((int) x, (int) y - 1, getColor((float) distance));
				} else {
					curPoint = new Point((int) x, (int) y + 1, getColor((float) distance));
				}
				addPoint(curPoint);
				info = generateinfo(e, distance);
				curPoint.addGenerateInfo(info);
				log(info, true);
			}
		} else {
			e = 2 * dx - dy;
			segmentAngle = Math.atan2(dy, dx);
			while (i < dy) {
				if (e >= 0) {
					x += a;
					e -= 2 * dy;
				}
				y += b;
				e += 2 * dx;
				i++;
				distance = getDistance(x1, y1, x2, y2, dy, x, y, segmentAngle);
				curPoint = new Point((int) x, (int) y, getColor((float) distance));
				addPoint(curPoint);
				info = generateinfo(e, distance);
				curPoint.addGenerateInfo(info);
				log(info, true);
				distance = getDistance(x1, y1, x2, y2, dy, x + 1, y, segmentAngle);
				System.out.println("second");
				if (distance > 1) {
					distance = getDistance(x1, y1, x2, y2, dy, x - 1, y, segmentAngle);
					curPoint = new Point((int) x - 1, (int) y, getColor((float) distance));
				} else {
					curPoint = new Point((int) x + 1, (int) y, getColor((float) distance));
				}
				addPoint(curPoint);
				info = generateinfo(e, distance);
				curPoint.addGenerateInfo(info);
				log(info, true);
			}
		}
		generated();
	}

	private String generateinfo(final int e, final double distance) {
		return "e = " + e + " | " + "distance = " + distance;
	}

	private void generateStraightLine(int start, int end, final int fixed, final boolean isYFixed) {
		Point tmpPoint;
		if (start > end) {
			int tmp = start;
			start = end;
			end = tmp;
		}
		for (int i = start; i <= end; i++) {
			if (isYFixed) {
				tmpPoint = new Point(i, fixed);
			} else {
				tmpPoint = new Point(fixed, i);
			}
			addPoint(tmpPoint);
		}
	}

	private double getDistance(final int x1, final int y1, final int x2, final int y2,
			final int dy, final double x, final double y, final double segmentAngle) {
		double distance;
		double pixel_y = y;
		if (x1 == 0 && y1 != 0 || x2 == 0 && y2 != 0) {
			pixel_y = dy - y;
		}
		double hypot = Math.hypot(x, pixel_y);
		double angle = Math.abs(segmentAngle - Math.asin(pixel_y / hypot));

		distance = Math.sin(angle) * hypot;
		return distance;
	}
}
