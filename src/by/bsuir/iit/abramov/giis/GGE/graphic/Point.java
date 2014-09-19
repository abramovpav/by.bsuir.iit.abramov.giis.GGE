package by.bsuir.iit.abramov.giis.GGE.graphic;

public class Point {
	private int x;
	private int y;
	private int z;
	private double dist;
	
	public Point(int x, int y,int z, double dist) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dist = dist;
	}
	
	public Point(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dist = 1.0;
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.z = 0;
		this.dist = 1.0;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.dist = 1.0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}
	
	
}
