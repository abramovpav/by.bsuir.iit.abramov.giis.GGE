package by.bsuir.iit.abramov.giis.GGE.graphic.line;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class LineDDA extends Line {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public LineDDA(final DesktopController controller) {
		super(controller);
	}

	public LineDDA(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}

	@Override
	public void generate() {
		super.generate();
		int x1 = getStartPoint().getX();
		int y1 = getStartPoint().getY();
		int x2 = getEndPoint().getX();
		int y2 = getEndPoint().getY();

		int length = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
		double dx = (double) (x2 - x1) / length;
		double dy = (double) (y2 - y1) / length;
		double x = x1;
		double y = y1;
		Point curPoint = new Point((int) x, (int) y);
		addPoint(curPoint);
		int i = 0;
		while (i < length) {
			x = x + dx;
			y = y + dy;
			curPoint = new Point((int) x, (int) y);
			addPoint(curPoint);
			i += 1;
		}
		generated();
	}

}
