package by.bsuir.iit.abramov.giis.GGE.graphic.forms;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class GraphicPoint extends JComponent{
	
	private int yOffset = 0;
	private int xOffset = 0;
	
	public GraphicPoint() {
		
	}
	
	@Override
	public boolean contains(int x, int y) {
//		return super.contains(x, y);
		return (x >= 0) && (x < getWidth()) && (y >= 0) && (y < getHeight());
	}

	public int getyOffset() {
		return yOffset;
	}


	public void setYOffset(int yOffset) {
		this.yOffset = yOffset;
	}


	public int getxOffset() {
		return xOffset;
	}


	public void setXOffset(int xOffset) {
		this.xOffset = xOffset;
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	
	
}
