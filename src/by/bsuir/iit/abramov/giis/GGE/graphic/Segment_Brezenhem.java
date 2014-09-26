package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Segment_Brezenhem extends Segment {
	
	public Segment_Brezenhem() {
		super();
	}
	
	public Segment_Brezenhem(final Point start) {
		super(start);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		System.out.println("Edge_Brezenhem-paint");
		Graphics2D g2d = (Graphics2D)g;
		Point firstPoint = getFirstPointOnCanvas();
		Point lastPoint = getLastPointOnCanvas();
		drawLine(g2d, firstPoint.getX(), firstPoint.getY(), lastPoint.getX(), lastPoint.getY());
	}
	
	private void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		int a = 1;
		int b = 1;
		if (x1 < x2) {
			if (y1 > y2) {
				b = -1;
			}
		} else if (x1 > x2) {
			if (y1 > y2) {
				a = b = -1;
			}
			else if (y2 > y1) {
				a = -1;
			}
		}
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int e = 2 * dy - dx;
		double x = x1;
		double y = y1;
		drawPoint(g2d, x, y);
		int i = 1;
		if (dx > dy) {
			while(i < dx) {
				if (e >= 0 ) {
					y += b;
					e -= 2 * dx;
				} 
				x += a;
				e += 2 * dy;
				i++;
				drawPoint(g2d, x, y);
			}
		}
		else {
			e = 2 * dx - dy;
			while(i < dy) {
				if (e >= 0) {
					x += a;
					e -= 2 * dy;
				} 
				y += b;
				e += 2 * dx;
				i++;
				drawPoint(g2d, x, y);
			}
		}
	}
	
	private void drawPoint(Graphics2D g2d, double x, double y) {
		g2d.drawLine((int)x, (int)y, (int)x, (int)y);
	}
	
	private int sign(double x) {
		if (x == 0) {
			return 0;
		}
		else if (x > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
