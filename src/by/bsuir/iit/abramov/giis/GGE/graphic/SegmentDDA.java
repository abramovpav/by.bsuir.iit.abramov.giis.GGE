package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class SegmentDDA extends Segment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SegmentDDA() {
		super();
	}

	public SegmentDDA(final Point start) {
		super(start);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		System.out.println("SegmentDDA-paint");
		Graphics2D g2d = (Graphics2D) g;
		Point firstPoint = getFirstPointOnCanvas();
		Point lastPoint = getLastPointOnCanvas();
		drawLine(g2d, firstPoint.getX(), firstPoint.getY(), lastPoint.getX(),
				lastPoint.getY());
	}

	private void drawLine(final Graphics2D g2d, final int x1, final int y1,
			final int x2, final int y2) {

		int length = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
		double dx = (double) (x2 - x1) / length;
		double dy = (double) (y2 - y1) / length;
		double x = x1 + 0.5 * sign(dx);
		double y = y1 + 0.5 * sign(dy);
		drawPoint(g2d, x, y);
		int i = 0;
		while (i < length) {
			x = x + dx;
			y = y + dy;
			drawPoint(g2d, x, y);
			i += 1;
		}
	}

	private void drawPoint(final Graphics2D g2d, final double x, final double y) {
		g2d.drawLine((int) x, (int) y, (int) x, (int) y);
	}

	private int sign(final double x) {
		if (x == 0) {
			return 0;
		} else if (x > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}
