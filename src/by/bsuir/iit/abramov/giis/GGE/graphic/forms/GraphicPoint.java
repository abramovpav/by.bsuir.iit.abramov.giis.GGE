package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class GraphicPoint extends JComponent {

	public GraphicPoint() {

	}

	@Override
	public boolean contains(final int x, final int y) {
		// return super.contains(x, y);
		return (x >= 0) && (x < getWidth()) && (y >= 0) && (y < getHeight());
	}

	public int getHalfWidth() {
		return getWidth() / 2;
	}

	public int getHalfHeight() {
		return getHeight() / 2;
	}

	@Override
	public int getHeight() {
		return 2 * Config.CURRENT_SCALE;
	}

	@Override
	public int getWidth() {
		return 2 * Config.CURRENT_SCALE;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}
	
	public void updateLocation() {
		int x = getX();
		int y = getY();
		x = x / Config.PREV_SCALE * Config.CURRENT_SCALE;
		y = y / Config.PREV_SCALE * Config.CURRENT_SCALE;
		setLocation(x, y);
	}

}
