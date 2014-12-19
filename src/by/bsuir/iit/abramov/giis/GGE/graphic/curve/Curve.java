package by.bsuir.iit.abramov.giis.GGE.graphic.curve;

import java.util.List;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.Point;

public class Curve extends GraphicObject {
	
	private Point startPoint;
	
	public Curve(DesktopController controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	public Curve(int x, int y, DesktopController controller) {
		super(controller);
		startPoint = new Point(Point.getUnscaledCoord(x), Point.getUnscaledCoord(y));
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
		

}
