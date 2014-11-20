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
	public void generate() {
		long startTime = System.nanoTime();

		super.generate();
		Point curRefPoint = getRefferencePoint();
		Point start = getLocalCoord(getBasePoint(0));
		Point end = getLocalCoord(getBasePoint(1));
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
		for (double t = 0; t <= 1; t += 0.01) {
			step_multiplier.set(0, 0, Math.pow(t, 3));
			step_multiplier.set(0, 1, Math.pow(t, 2));
			step_multiplier.set(0, 2, t);

			SimpleMatrix res = step_multiplier.mult(multiplier).mult(coord);
			double x = res.get(0, 0);
			double y = res.get(0, 1);
			Point curPoint = new Point((int) x, (int) y);
			addPoint(curPoint);
		}

		// Some points have negative coordinates after generation.
		// To fix it we need to correct coordinates of points by deducting
		// top-left point's coordinates(it's the distance to zero)

		Point leftUpPoint = getLeftUpPoint(getPoints(), true);
		for (Point point : points) {
			point.setX(point.getX() - leftUpPoint.getX());
			point.setY(point.getY() - leftUpPoint.getY());
		}
		updateWidthAndHeight(getPoints(), true);
		// After correction points we have to move form's component to the same
		// distance
		curRefPoint.setX(curRefPoint.getX() + leftUpPoint.getX());
		curRefPoint.setY(curRefPoint.getY() + leftUpPoint.getY());
		setRefPoint(curRefPoint);

		updateBounds(getDesktopCenterPoint());
		generated();
		long endTime = System.nanoTime();

		long duration = (endTime - startTime) / 1000000;
		System.out.println("generated in " + duration + "ms");
	}

	@Override
	public void addPoint(final Point point) {
		super.addPoint(point);
		controller.log(point);
	}

}
