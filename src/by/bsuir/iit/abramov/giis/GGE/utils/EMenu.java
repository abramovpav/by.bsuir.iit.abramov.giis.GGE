package by.bsuir.iit.abramov.giis.GGE.utils;

import java.util.ArrayList;
import java.util.List;

public enum EMenu {
	FILE("File", 
			EMenuItem.NEW, EMenuItem.OPEN, EMenuItem.CLOSE, EMenuItem.EXIT), 
	EDIT("Edit"), 
	ACTIONS("Actions",
			EMenuItem.SEGMENT_DDA, EMenuItem.SEGMENT_BREZENHEM, EMenuItem.SEGMENT_VY, EMenuItem.NONE);

	private List<EMenuItem> items;
	private String name = "DEFAULT";

	EMenu(final String name, final EMenuItem... items) {
		this.name = name;
		this.items = new ArrayList<EMenuItem>();
		for (EMenuItem item : items) {
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
