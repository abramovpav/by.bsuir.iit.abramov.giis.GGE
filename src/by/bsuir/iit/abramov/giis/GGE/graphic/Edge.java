package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.util.ArrayList;
import java.util.List;

public class Edge implements GraphicObject {
	private Point startPoint;
	private Point endPoint;
	private final List<Point> points;
	
	public Edge() {
		this.points = new ArrayList<>();
		startPoint = new Point();
		endPoint = new Point();
	}
	
	public Edge(final Point start, final Point end, final List<Point> points) {
		this.points = new ArrayList<>();
		this.startPoint = start;
		this.endPoint = end;
		this.points.addAll(points);
	}
	

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public final List<Point> getPoints() {
		return this.points;
	}

}
