package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;
import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Point {
	private int		x;
	private int		y;
	private int		z;
	private double	dist;
	private Color	color	= Color.BLACK;

	public Point() {
		x = 0;
		y = 0;
		z = 0;
		dist = 1.0;
	}

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
		z = 0;
		dist = 1.0;
	}

	public Point(final int x, final int y, final Color color) {
		this.x = x;
		this.y = y;
		z = 0;
		dist = 1.0;
		this.color = color;
	}

	public Point(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		dist = 1.0;
	}

	public Point(final int x, final int y, final int z, final double dist) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dist = dist;
	}

	public Color getColor() {
		return color;
	}

	public double getDist() {
		return dist;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public void setDist(final double dist) {
		this.dist = dist;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public void setZ(final int z) {
		this.z = z;
	}
	
	public int getScaledX() {
		return getUnscaledCoord(x);
	}
	
	public int getScaledY() {
		return getUnscaledCoord(y);
	}
	
	public static int getUnscaledCoord(final int input_coord) {
		int coord = input_coord;
		if (coord % Config.CURRENT_SCALE > 0) {
			coord = coord / Config.CURRENT_SCALE + 1;
		} else {
			coord /= Config.CURRENT_SCALE;
		}
		return coord;
	}

}
