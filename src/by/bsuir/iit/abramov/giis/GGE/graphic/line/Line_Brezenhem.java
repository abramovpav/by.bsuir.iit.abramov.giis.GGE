package by.bsuir.iit.abramov.giis.GGE.graphic.line;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class Line_Brezenhem extends Line {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public Line_Brezenhem(final DesktopController controller) {
		super(controller);
	}

	public Line_Brezenhem(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}

	@Override
	public void generate() {
		super.generate();
		int x1 = getStartPoint().getX();
		int y1 = getStartPoint().getY();
		int x2 = getEndPoint().getX();
		int y2 = getEndPoint().getY();
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
		Point curPoint = new Point((int) x, (int) y);
		addPoint(curPoint);
		String info = generateInfo(e);
		curPoint.addGenerateInfo(info);
		log(info, true);
		int i = 0;
		if (dx > dy) {
			while (i < dx) {
				if (e >= 0) {
					y += b;
					e -= 2 * dx;
				}
				x += a;
				e += 2 * dy;
				i++;
				curPoint = new Point((int) x, (int) y);
				addPoint(curPoint);
				info = generateInfo(e);
				curPoint.addGenerateInfo(info);
				log(info, true);
			}
		} else {
			e = 2 * dx - dy;
			while (i < dy) {
				if (e >= 0) {
					x += a;
					e -= 2 * dy;
				}
				y += b;
				e += 2 * dx;
				i++;
				curPoint = new Point((int) x, (int) y);
				addPoint(curPoint);
				info = generateInfo(e);
				curPoint.addGenerateInfo(info);
				log(info, true);
			}
		}
		generated();
		repaint();
	}

	private String generateInfo(final int e) {
		return "e = " + e;
	}
}
