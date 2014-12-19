package by.bsuir.iit.abramov.giis.GGE.graphic.curve;

import java.util.ArrayList;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class Circle extends Curve {
	
	private int radius;

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	public Circle(final DesktopController controller) {
		super(controller);
	}

	public Circle(final int x, final int y, final DesktopController controller) {
		super(x, y, controller);
	}
	
	public void setRadius(int r) {
		this.radius = r;
	}	

	@Override
	public void generate() {
		super.generate();
		Point startPoint = getStartPoint();
		int x = startPoint.getX();
		int y = startPoint.getY();
		Point curPoint = new Point((int) x, (int) y);
		addPoint(curPoint);
		String info = generateInfo(0);
		curPoint.addGenerateInfo(info);
		log(info, true);
		
		
		while(x < startPoint.getX() + radius || y >= startPoint.getY()) {
			double error1 = getErrorValue(x,  y + 1);
			double error2 = getErrorValue(x,  y - 1);
			double error3 = getErrorValue(x + 1,  y + 1);
			int choice = getClosestErrorValue(error1, error2, error3);
			
			switch(choice) {
			case 1:
				y += 1;
				info = generateInfo(error1);
				break;
			case 2:
				y -= 1;
				info = generateInfo(error2);
				break;
			case 3:
				x +=1;
				y +=1;
				info = generateInfo(error3);
				break;
			}
			
			curPoint = new Point((int) x, (int) y);
			addPoint(curPoint);
			
			curPoint.addGenerateInfo(info);
			log(info, true);
			
			

		}


//				curPoint = new Point((int) x, (int) y);
//				addPoint(curPoint);
//				info = generateInfo(e);
//				curPoint.addGenerateInfo(info);
//				log(info, true);

		generated();
		repaint();
	}
	
	private int getClosestErrorValue(double e1, double e2, double e3) {
		int nearest = -1;
		double errors[] = {e1, e2, e3};
		double bestDistanceFoundYet = Double.MAX_VALUE;

		for (int i = 0; i < errors.length; i++) {
			if (errors[i] == 0) {
				return i + 1;
			} else {
				double d = Math.abs(0 - errors[i]);
				if (d < bestDistanceFoundYet) {
					nearest = i;
					bestDistanceFoundYet = d;
				}
			}
		}
		return nearest + 1;
	}
	
	public Point getCenter() {
		return super.getStartPoint();
	}
			
	private double getErrorValue(int x, int y) {
		return Math.pow(x, 2) + Math.pow(y,  2) - Math.pow(radius, 2);
	}
	
	@Override
	public Point getStartPoint() {
		Point startPoint = super.getStartPoint();
		return new Point(Math.abs(startPoint.getX()), Math.abs(startPoint.getY()) + radius);
	}

	private String generateInfo(final double e) {
		return "e = " + e;
	}
}
