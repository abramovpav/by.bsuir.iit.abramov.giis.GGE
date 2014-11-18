package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import by.bsuir.iit.abramov.giis.GGE.controller.DesktopController;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class GraphicObject extends JComponent implements GraphicObjectInterface {
	/**
	 *
	 */
	private static final long			serialVersionUID	= 1L;
	protected final List<Point>			points;
	private boolean						generated			= false;
	protected final DesktopController	controller;
	private int							currentStep			= 1;
	private boolean						selected;

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
		points = new ArrayList<Point>();
		setLayout(null);
	}

	public GraphicObject(final List<Point> points, final DesktopController controller) {
		this.controller = controller;
		this.points = new ArrayList<Point>();
		this.points.addAll(points);
		setLayout(null);
	}

	public void addPoint(final Point point) {
		points.add(point);
	}

	private void draw(final Graphics2D g2d) {
		for (int index = 0; index <= currentStep; index++) {
			Point point = getPoint(index);
			if (point != null) {
				drawPoint(g2d, point);
			}
		}
	}

	private void drawPoint(final Graphics2D g2d, final Point point) {
		g2d.setColor(point.getColor());
		int x = point.getX() * Config.CURRENT_SCALE;
		int y = point.getY() * Config.CURRENT_SCALE;
		for (int i = x - Config.getHalfScale(); i < x + Config.getHalfScale(); i++) {
			for (int j = y - Config.getHalfScale(); j < y + Config.getHalfScale(); j++) {
				g2d.drawLine(i + Config.getHalfScale(), j + Config.getHalfScale(),
						i + Config.getHalfScale(), j + Config.getHalfScale());
			}
		}
	}

	@Override
	public void generate() {
		points.clear();
	}

	protected void generated() {
		generated = true;
		currentStep = points.size() - 1;
	}

	@Override
	public int getBaseHeight() {
		return getHeight();
	}

	@Override
	public int getBaseWidth() {
		return getWidth();
	}

	protected Color getColor(final float intensity) {
		return new Color((int) (255 * intensity), (int) (255 * intensity), (int) (255 * intensity));
	}

	public Point getPoint(final int index) {
		if (index >= 0 && index < points.size()) {
			return points.get(index);
		} else {
			return null;
		}
	}

	protected Point getLocalCoord(final Point point) {
		Point local = new Point();
		Point refPoint = getRefferencePoint();
		local.setX(point.getX() - refPoint.getX());
		local.setY(point.getY() - refPoint.getY());
		return local;
	}

	@Override
	public List<Point> getPoints() {
		return points;
	}

	@Override
	public Point getRefferencePoint() {
		return new Point(0, 0);
	}

	@Override
	public int getScaledHeight() {
		return getBaseHeight() * Config.CURRENT_SCALE;
	}

	@Override
	public int getScaledWidth() {
		return getBaseWidth() * Config.CURRENT_SCALE;
	}

	public boolean isGenerated() {
		return generated;
	}

	@Override
	public void last() {
		currentStep = points.size() - 1;
		repaint();
	}

	protected void log(final String msg, final boolean offset) {
		controller.log(msg, offset);
	}

	private void logPointInfo(final int index) {
		Point point = getPoint(index);
		if (point != null) {
			controller.log("Last point:", false);
			controller.log(point, getRefferencePoint());
			controller.log(point.getGenerateInfo(), true);
		}
	}

	@Override
	public void next() {
		if (currentStep + 1 < points.size()) {
			currentStep++;
			logPointInfo(currentStep);
			repaint();
		}
	}

	@Override
	protected void paintComponent(final Graphics g) {
		System.out.println(this.getClass().getSimpleName() + "-paint " + points.size());
		if (!isGenerated()) {
			super.paintComponent(g);
			return;
		}
		Graphics2D g2d = (Graphics2D) g;
		// g2d.drawRect(0, 0, getScaledWidth() - 1, getScaledHeight() - 1);
		// g2d.drawRect(1, 1, getScaledWidth() - 2, getScaledHeight() - 2);
		draw(g2d);
	}

	@Override
	public void prev() {
		if (currentStep - 1 >= 0) {
			currentStep--;
			logPointInfo(currentStep);
			repaint();
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

	@Override
	public void updateBounds(final java.awt.Point point) {
		Point refPoint = getRefferencePoint();
		setBounds(refPoint.getX() * Config.CURRENT_SCALE + point.x - Config.getHalfScale(),
				refPoint.getY() * Config.CURRENT_SCALE + point.y - Config.getHalfScale(),
				getScaledWidth(), getScaledHeight());
	}

	protected java.awt.Point getDesktopCenterPoint() {
		return controller.getDesktopCenterPoint();
	}

	@Override
	public void select() {
		setSelected(true);
	}

	@Override
	public void unselect() {
		setSelected(false);
	}
}
