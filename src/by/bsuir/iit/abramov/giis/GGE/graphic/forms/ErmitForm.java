package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import org.ejml.simple.SimpleMatrix;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class ErmitForm extends Form {
	private double multiplier[][] = {
										{2, -2, 1, 1}, 
										{-3, 3, -2, -1},
										{0, 0, 1, 0},
										{1, 0, 0, 0}
									};

	public ErmitForm(int x, int y, DesktopController controller) {
		super(x, y, controller);
	}
	
	
	@Override
	public void generate() {
		super.generate();
		SimpleMatrix multiplier = new SimpleMatrix(this.multiplier);
		double coordinates[][] = {
									{0, 0},
									{10, 0},
									{0, 1},
									{10, 0}
								 };
		SimpleMatrix coord = new SimpleMatrix(coordinates);
		
		double step_m[][] = {{0, 0, 0, 1}};
		SimpleMatrix step_multiplier = new SimpleMatrix(step_m);
		for (double t = 0; t < 1; t += 0.1) {
			step_multiplier.set(0, 0, Math.pow(t, 3));
			step_multiplier.set(0, 1, Math.pow(t, 2));
			step_multiplier.set(0, 2, t);
			
			SimpleMatrix res1 = step_multiplier.mult(multiplier);
			SimpleMatrix res2 = res1.mult(coord);
			double x = res2.get(0, 0);
			double y = res2.get(0, 1);
			Point curPoint = new Point((int) x + 5, (int) y+ 5);
			addPoint(curPoint);
		}
		generated();
		
		}
	
	@Override
	public void addPoint(final Point point) {
		super.addPoint(point);
		controller.log(point);
	}

}
