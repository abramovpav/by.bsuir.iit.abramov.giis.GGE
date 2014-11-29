package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.util.ArrayList;
import java.util.List;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObject;
import by.bsuir.iit.abramov.giis.GGE.graphic.GraphicObjectInterface;

public class Form extends GraphicObject implements GraphicObjectInterface {

	private final List<GraphicPoint>	graphicPoints;

	public Form(final int x, final int y, final DesktopController controller) {
		super(controller);
		graphicPoints = new ArrayList<GraphicPoint>();
		GraphicPoint point = new GraphicPoint();
		point.setLocation(convertLocalCoordToScreen(x, true), convertLocalCoordToScreen(y, false));
		graphicPoints.add(point);
	}

	protected int convertScreenCoordToLocal(final int coord, final boolean x_coord) {
		java.awt.Point center = getDesktopCenterPoint();
		if (x_coord) {
			return coord - center.x;
		} else {
			return coord - center.y;
		}
	}

	protected int convertLocalCoordToScreen(final int coord, final boolean x_coord) {
		java.awt.Point center = getDesktopCenterPoint();
		if (x_coord) {
			return coord + center.x;
		} else {
			return coord + center.y;
		}
	}

	public void addBasePoint(final int x, final int y) {
		GraphicPoint point = new GraphicPoint();
		point.setLocation(convertLocalCoordToScreen(x, true), convertLocalCoordToScreen(y, false));
		graphicPoints.add(point);
	}

	@Override
	public final List<GraphicPoint> getGraphicPoints() {
		return graphicPoints;
	}

	protected GraphicPoint getGraphicPoint(final int index) {
		return graphicPoints.get(index);
	}
}
