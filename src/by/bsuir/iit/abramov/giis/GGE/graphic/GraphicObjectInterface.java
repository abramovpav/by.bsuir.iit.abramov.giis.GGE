package by.bsuir.iit.abramov.giis.GGE.graphic;

import java.util.List;
import java.util.Set;

public interface GraphicObjectInterface {
	public void generate();

	public Set<Point> getPoints();

	public void last();

	public void next();

	public void prev();
	
	public void select();
	
	public void unselect();
}
