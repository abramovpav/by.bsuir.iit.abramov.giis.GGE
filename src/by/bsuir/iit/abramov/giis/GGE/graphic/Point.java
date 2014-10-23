package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;

import by.bsuir.iit.abramov.giis.GGE.main.Config;

public class Point {
	public static int getUnscaledCoord(final int input_coord) {
		int coord = input_coord;
		if (Math.abs(coord % Config.CURRENT_SCALE) <= Config.getHalfScale()) {
			coord = coord / Config.CURRENT_SCALE;
		} else {
			coord /= Config.CURRENT_SCALE;
			if (coord > 0) {
				coord += 1;
			} else {
				coord -= 1;
			}
		}
		return coord;
	}

	private int		x;
	private int		y;
	private int		z;
	private double	dist;
	private Color	color			= Color.BLACK;

	private String	generateInfo	= "";

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

	public void addGenerateInfo(final String info) {
		generateInfo = info;
	}

	public Color getColor() {
		return color;
	}

	public double getDist() {
		return dist;
	}

	public final String getGenerateInfo() {
		return generateInfo;
	}

	public int getScaledX() {
		return getUnscaledCoord(x);
	}

	public int getScaledY() {
		return getUnscaledCoord(y);
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

}
