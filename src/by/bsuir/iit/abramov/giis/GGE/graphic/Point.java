package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Color;

public class Point {
	private int x;
	private int y;
	private int z;
	private double dist;
	private Color color = Color.BLACK;

	public Point(final int x, final int y, final int z, final double dist) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dist = dist;
	}

	public Point(final int x, final int y, final int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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

	public Point() {
		x = 0;
		y = 0;
		z = 0;
		dist = 1.0;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(final int z) {
		this.z = z;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(final double dist) {
		this.dist = dist;
	}

}
