package by.bsuir.iit.abramov.giis.GGE.utils;

public enum EMenuItem {

	NEW("New...", true), OPEN("Open"), CLOSE("Close"), EXIT("Exit"), LINE_DDA("LineDDA", true), 
	LINE_WY("LineWy", true), LINE_BREZENHEM("LineBrezenhem", true), NONE("Empty", true), 
	LOG("Show log", true), ABOUT("About"), NEXT("Next", true), PREV("Prev", true), 
	LAST("Last", true);

	private String	name	= "DEFAULT";
	private boolean	tool	= false;

	EMenuItem(final String name) {
		this.name = name;
		tool = false;
	}

	EMenuItem(final String name, final boolean tool) {
		this.name = name;
		this.tool = tool;
	}

	public String getName() {
		return name;
	}

	public boolean isTool() {
		return tool;
	}
}