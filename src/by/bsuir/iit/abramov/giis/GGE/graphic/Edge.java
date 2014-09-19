package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.util.ArrayList;
import java.util.List;

public class Edge implements GraphicObject {
	private final List<Point> points;
	
	public Edge() {
		this.points = new ArrayList<>();
	}
	
	public Edge(List<Point> points) {
		this.points = new ArrayList<>();
		this.points.addAll(points);
	}

	@Override
	public final List<Point> getPoints() {
		return this.points;
	}

}
