package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import org.ejml.simple.SimpleMatrix;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class ErmitForm extends Form {
	private final double	multiplier[][]	= { 
			                                    { 2, -2, 1, 1 }, 
			                                    { -3, 3, -2, -1 }, 
			                                    { 0, 0, 1, 0 },
			                                    { 1, 0, 0, 0 }				
			                                  };

	public ErmitForm(final int x, final int y, final DesktopController controller) {
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
									{ start.getX(), start.getY() }, 
									{ end.getX(), end.getY() },
									{ 0, 100 }, 
									{ 100, 0 }
								 };

		SimpleMatrix coord = new SimpleMatrix(coordinates);
		SimpleMatrix multiplier = new SimpleMatrix(this.multiplier);
		SimpleMatrix step_multiplier = new SimpleMatrix(1, 4);
		step_multiplier.set(0, 3, 1);
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

		generated();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("generated in " + duration + "ms");
		repaint();
	}

}
