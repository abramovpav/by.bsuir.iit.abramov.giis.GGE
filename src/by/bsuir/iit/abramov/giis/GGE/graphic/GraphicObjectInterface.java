package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.awt.Graphics2D;
import java.util.List;
import java.util.Set;

import by.bsuir.iit.abramov.giis.GGE.graphic.forms.GraphicPoint;

public interface GraphicObjectInterface {
	public void generate();

	public Set<Point> getPoints();

	public void last();

	public void next();

	public void prev();

	public List<GraphicPoint> getGraphicPoints();

	public void draw(Graphics2D g2d);
}
