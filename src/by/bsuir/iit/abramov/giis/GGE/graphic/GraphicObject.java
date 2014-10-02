package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.util.List;

public interface GraphicObject {
	public List<Point> getPoints();

	public Point getRefferencePoint();

	public void generate();

	public void setBounds(int x, int y, int width, int height);

	public int getWidth();

	public int getHeight();
	
	public void updateBounds(java.awt.Point point);
}
