package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.util.List;

import org.ejml.simple.SimpleMatrix;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class BSplain extends Form {
	private final double	multiplier[][]	= { 
			                                    { -1, 3, -3, 1 }, 
			                                    { 3, -6, 3, 0 }, 
			                                    { -3, 0, 3, 0 },
			                                    { 1, 4, 1, 0 }				
			                                  };

	public BSplain(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}

	@Override
	public void addPoint(final Point point) {
		super.addPoint(point);
		controller.log(point);
	}

	private Point getFirstBasePoint() {
		GraphicPoint graphicPoint = getGraphicPoint(0);
		return new Point(
				Point.getUnscaledCoord(convertScreenCoordToLocal(graphicPoint.getX(), true)),
				Point.getUnscaledCoord(convertScreenCoordToLocal(graphicPoint.getY(), false)));
	}

	private Point getLastBasePoint() {
		GraphicPoint graphicPoint = getGraphicPoint(1);
		return new Point(
				Point.getUnscaledCoord(convertScreenCoordToLocal(graphicPoint.getX(), true)),
				Point.getUnscaledCoord(convertScreenCoordToLocal(graphicPoint.getY(), false)));
	}
	
	private int localCoord(int coord) {
		return Point.getUnscaledCoord(coord);
	}

	@Override
	public void generate() {
		long startTime = System.nanoTime();
		super.generate();

		Point start = getFirstBasePoint();
		Point end = getLastBasePoint();
		double minX, minY, maxX, maxY;
		minX = minY = 99999;
		maxX = maxY = 0;
		double coordinates[][] = { 
									{ 0, 0 }, 
									{ 0, 0 },
									{ 0, 0 }, 
									{ 0, 0 }
								 };

		SimpleMatrix coord = new SimpleMatrix(coordinates);
		SimpleMatrix multiplier = new SimpleMatrix(this.multiplier);
		SimpleMatrix step_multiplier = new SimpleMatrix(1, 4);
		step_multiplier.set(0, 3, 1);
		List<GraphicPoint> graphicPoints = getGraphicPoints();
		for(int index = 0; index < graphicPoints.size() - 3; index++) {
			GraphicPoint point1 = graphicPoints.get(index);
			GraphicPoint point2 = graphicPoints.get(index + 1);
			GraphicPoint point3 = graphicPoints.get(index + 2);
			GraphicPoint point4 = graphicPoints.get(index + 3);
			coord.set(0, 0, localCoord(point1.getX()));
			coord.set(0, 1, localCoord(point1.getY()));
			
			coord.set(1, 0, localCoord(point1.getX()));
			coord.set(1, 1, localCoord(point1.getY()));
			
			coord.set(2, 0, localCoord(point1.getX()));
			coord.set(2, 1, localCoord(point1.getY()));
			
			coord.set(3, 0, localCoord(point1.getX()));
			coord.set(3, 1, localCoord(point1.getY()));
			for (double t = 0; t <= 1; t += 0.1) {
				step_multiplier.set(0, 0, Math.pow(t, 3));
				step_multiplier.set(0, 1, Math.pow(t, 2));
				step_multiplier.set(0, 2, t);
	
				SimpleMatrix res = step_multiplier.mult(multiplier).mult(coord);
				double x = res.get(0, 0);
				double y = res.get(0, 1);
				Point curPoint = new Point((int) x, (int) y);
				if (y > maxY) {
					maxY = y;
				}
				if (y < minY) {
					minY = y;
				}
				if (x > maxX) {
					maxX = x;
				}
				if (x < minX) {
					minX = x;
				}
				addPoint(curPoint);
			}
		}

		generated();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("generated in " + duration + "ms");
		repaint();
	}

}
