package by.bsuir.iit.abramov.giis.GGE.graphic;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;

public class LineDDA extends Line {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public LineDDA(final DesktopController controller) {
		super(controller);
	}

	public LineDDA(final Point start, final DesktopController controller) {
		super(start, controller);
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
