package by.bsuir.iit.abramov.giis.GGE.utils;

import java.util.ArrayList;
import java.util.List;

public enum EMenu {
	FILE("File", EMenuItem.NEW, EMenuItem.OPEN, EMenuItem.CLOSE, EMenuItem.EXIT), 
	EDIT("Edit"), 
	ACTIONS("Actions", EMenuItem.SEGMENT, EMenuItem.SEGMENT_DDA, EMenuItem.NONE);
	
	private List<EMenuItem> items;
	private String name = "DEFAULT";
	
	EMenu(String name, EMenuItem...items) {
		this.name = name;
		this.items = new ArrayList<>();
		for (EMenuItem item: items) {
			this.items.add(item);
		}
	}
	
	public final List<EMenuItem> getItems() {
		return items;
	}
	
	public final String getName() {
		return name;		
	}
}
