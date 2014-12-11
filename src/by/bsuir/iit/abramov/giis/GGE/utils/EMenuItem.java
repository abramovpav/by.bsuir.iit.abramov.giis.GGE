package by.bsuir.iit.abramov.giis.GGE.utils;

public enum EMenuItem {

	NEW("New...", true, true), OPEN("Open"), CLOSE("Close"), EXIT("Exit"), LINE_DDA("LineDDA", true), 
	LINE_WY("LineWy", true), LINE_BREZENHEM("LineBrezenhem", true), NONE("Empty", true), 
	LOG("Show log", true), ABOUT("About"), NEXT("Next", true), PREV("Prev", true), 
	LAST("Last", true), ERMIT_FORM("ErmitForm", true), BSPLAIN("BSplain", true),  
	BEZIER_FORM("BezierForm", true);

	private String	name	= "DEFAULT";
	private boolean	tool	= false;
	private boolean enabled = false;

	EMenuItem(final String name) {
		this.name = name;
		tool = false;
		enabled = true;
	}

	EMenuItem(final String name, final boolean tool) {
		this.name = name;
		this.tool = tool;
	}
	
	EMenuItem(final String name, final boolean tool, final boolean enabled) {
		this.name = name;
		this.tool = tool;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public boolean isTool() {
		return tool;
	}
}