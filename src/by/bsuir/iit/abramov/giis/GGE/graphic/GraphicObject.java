package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.util.List;

public interface GraphicObject {
	public void generate();

	public int getBaseHeight();

	public int getBaseWidth();

	public List<Point> getPoints();

	public Point getRefferencePoint();

	public int getScaledHeight();

	public int getScaledWidth();

	public void setBounds(int x, int y, int width, int height);

	public void updateBounds(java.awt.Point point);
}
