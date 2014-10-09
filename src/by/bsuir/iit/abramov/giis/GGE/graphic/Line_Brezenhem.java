package by.bsuir.iit.abramov.giis.GGE.graphic;

public class Line_Brezenhem extends Line {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Line_Brezenhem() {
		super();
	}

	public Line_Brezenhem(final Point start) {
		super(start);
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
		log("e = ", e);
		int i = 1;
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
				log("e = ", e);
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
				log("e = ", e);
			}
		}
		generated();
	}

	private void log(final String str, final double e) {
		System.out.println(str + e);
	}
}
