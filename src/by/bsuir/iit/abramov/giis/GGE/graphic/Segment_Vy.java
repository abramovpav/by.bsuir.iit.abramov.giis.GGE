package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;

public class Segment_Vy extends Segment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Segment_Vy() {
		super();
	}

	public Segment_Vy(final Point start) {
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
		Point curPoint = new Point((int)x, (int)y);
		double segmentAngle = 0;
		double distance = 0;
		addPoint(curPoint);
		log("e = ", e);log("distance = ", distance);
		int i = 1;
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
				curPoint = new Point((int)x, (int)y);
				double pixel_y = y;
				if ((x1 == 0 && y1 != 0) || (x2 == 0 && y2 != 0)) {
					pixel_y = (double)dy - y;
				}
				double hypot = Math.hypot(x, pixel_y);
				double angle = Math.abs(segmentAngle - Math.asin((double)pixel_y / hypot));
				
				distance = Math.sin(angle) * hypot;
				setColor(getColor((float)distance));
				addPoint(curPoint);log("e = ", e);log("distance = ", distance);
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
				curPoint = new Point((int)x, (int)y);
				double pixel_y = y;
				if ((x1 == 0 && y1 != 0) || (x2 == 0 && y2 != 0)) {
					pixel_y = (double)dy - y;
				}
				double hypot = Math.hypot(x, pixel_y);
				double angle = Math.abs(segmentAngle - Math.asin((double)pixel_y / hypot));
				
				distance = Math.sin(angle) * hypot;
				setColor(getColor((float)distance));
				addPoint(curPoint);log("e = ", e);log("distance = ", distance);
			}
		}
		generated();
	}
	
	private void log(String str, double e) {
		System.out.println(str + e);
	}
}
