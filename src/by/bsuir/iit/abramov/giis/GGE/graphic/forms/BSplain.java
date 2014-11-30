package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.util.ArrayList;
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
	private Point tmpPoint = new Point();

	public BSplain(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}
	
	@Override
	public void addPoint(final Point point) {
		super.addPoint(point);
		controller.log(point);
	}
	
	private int localCoord(int coord) {
		return Point.getUnscaledCoord(coord);
	}

	@Override
	public void generate() {
		long startTime = System.nanoTime();
		super.generate();
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
		List<GraphicPoint> graphicPoints = new ArrayList<GraphicPoint>();
		graphicPoints.add(getGraphicPoint(0));
		graphicPoints.addAll(getGraphicPoints());
		graphicPoints.add(getLastGraphicPoint());
		for(int index = 0; index < graphicPoints.size() - 3; index++) {
			GraphicPoint point1 = graphicPoints.get(index);
			GraphicPoint point2 = graphicPoints.get(index + 1);
			GraphicPoint point3 = graphicPoints.get(index + 2);
			GraphicPoint point4 = graphicPoints.get(index + 3);
			coord.set(0, 0, localCoord(convertScreenCoordToLocal(point1.getX(), true)));
			coord.set(0, 1, localCoord(convertScreenCoordToLocal(point1.getY(), false)));
			
			coord.set(1, 0, localCoord(convertScreenCoordToLocal(point2.getX(), true)));
			coord.set(1, 1, localCoord(convertScreenCoordToLocal(point2.getY(), false)));
			
			coord.set(2, 0, localCoord(convertScreenCoordToLocal(point3.getX(), true)));
			coord.set(2, 1, localCoord(convertScreenCoordToLocal(point3.getY(), false)));
			
			coord.set(3, 0, localCoord(convertScreenCoordToLocal(point4.getX(), true)));
			coord.set(3, 1, localCoord(convertScreenCoordToLocal(point4.getY(), false)));
			for (double t = 0; t <= 1; t += 0.01) {
				step_multiplier.set(0, 0, Math.pow(t, 3));
				step_multiplier.set(0, 1, Math.pow(t, 2));
				step_multiplier.set(0, 2, t);
	
				SimpleMatrix res = step_multiplier.mult(multiplier).mult(coord);
				double x = res.get(0, 0);
				double y = res.get(0, 1);
				tmpPoint.setX((int) x / 6);
				tmpPoint.setY((int) y / 6);
				if (!existPoint(tmpPoint)) {
					Point curPoint = new Point((int) x / 6, (int) y / 6);
					addPoint(curPoint);
				}
			}
		}

		generated();
		long endTime = System.nanoTime();
		long duration = (endTime - startTime) / 1000000;
		System.out.println("generated in " + duration + "ms");
		repaint();
	}

}
