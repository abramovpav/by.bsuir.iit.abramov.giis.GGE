package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.graphic.forms.GraphicPoint;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class GraphicObject implements GraphicObjectInterface {
	/**
	 *
	 */
	private static final long				serialVersionUID	= 1L;
	protected final LinkedHashSet<Point>	points;
	private boolean							generated			= false;
	protected final DesktopController		controller;
	private int								currentStep			= 1;
	private boolean							selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(final boolean selected) {
		this.selected = selected;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(final int currentStep) {
		this.currentStep = currentStep;
	}

	public void setGenerated(final boolean generated) {
		this.generated = generated;
	}

	public GraphicObject(final DesktopController controller) {
		this.controller = controller;
		points = new LinkedHashSet<Point>();
	}

	public GraphicObject(final List<Point> points, final DesktopController controller) {
		this.controller = controller;
		this.points = new LinkedHashSet<Point>();
		this.points.addAll(points);
	}

	public void addPoint(final Point point) {
		points.add(point);
	}
	
	public boolean existPoint(Point point) {
		return points.contains(point);
	}

	@Override
	public void draw(final Graphics2D g2d) {
		int index = 0;

		for (Point point : points) {
			if (index > currentStep) {
				return;
			}
			if (point != null) {
				drawPoint(g2d, point);
			}
			index++;
		}
	}

	private void drawPoint(final Graphics2D g2d, final Point point) {
		g2d.setColor(point.getColor());
		java.awt.Point center = getDesktopCenterPoint();
		int x = point.getX() * Config.CURRENT_SCALE - Config.getHalfScale() + center.x;
		int y = point.getY() * Config.CURRENT_SCALE - Config.getHalfScale() + center.y;

		g2d.fillRect(x, y, Config.CURRENT_SCALE, Config.CURRENT_SCALE);
	}

	@Override
	public void generate() {
		points.clear();
	}

	protected void generated() {
		generated = true;
		currentStep = points.size() - 1;
	}

	protected Color getColor(final float input_intensity) {
		float intensity = input_intensity;
		if (intensity < 0) intensity = 0;
		if (intensity > 1) intensity = 1;
		return new Color((int) (255 * intensity), (int) (255 * intensity), (int) (255 * intensity));
	}

	@Override
	public Set<Point> getPoints() {
		return points;
	}

	public boolean isGenerated() {
		return generated;
	}

	@Override
	public void last() {
		currentStep = points.size() - 1;
	}

	protected void log(final String msg, final boolean offset) {
		controller.log(msg, offset);
	}

	private void logPointInfo(final int index) {
		// Point point = getPoint(index);
		// if (point != null) {
		// controller.log("Last point:", false);
		// controller.log(point, getRefferencePoint());
		// controller.log(point.getGenerateInfo(), true);
		// }
	}

	@Override
	public void next() {
		if (currentStep + 1 < points.size()) {
			currentStep++;
			// logPointInfo(currentStep);
		}
	}

	protected void paintComponent(final Graphics g) {
		System.out.println(this.getClass().getSimpleName() + "-paint " + points.size());
		Graphics2D g2d = (Graphics2D) g;
		draw(g2d);
	}

	@Override
	public void prev() {
		if (currentStep - 1 >= 0) {
			currentStep--;
			logPointInfo(currentStep);
		}
	}

	protected int sign(final double x) {
		if (x == 0) {
			return 0;
		} else if (x > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	public void repaint() {
		controller.desktopRepaint();
	}

	protected java.awt.Point getDesktopCenterPoint() {
		return controller.getDesktopCenterPoint();
	}

	@Override
	public List<GraphicPoint> getGraphicPoints() {
		return new ArrayList<GraphicPoint>();
	}

	@Override
	public GraphicPoint getLastGraphicPoint() {
		return getGraphicPoints().get(getGraphicPoints().size() - 1);
	}
}
