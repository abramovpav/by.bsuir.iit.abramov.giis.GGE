package by.bsuir.iit.abramov.giis.GGE.utils;

public enum EMenuItem {

	NEW("New...", true), OPEN("Open"), CLOSE("Close"), EXIT("Exit"), 
	SEGMENT_DDA("SegmentDDA", true), SEGMENT_VY("SegmentVy", true),
	SEGMENT_BREZENHEM("SegmentBrezenhem", true), NONE("Empty", true);

	private String name = "DEFAULT";
	private boolean tool = false;

	EMenuItem(final String name) {
		this.name = name;
		tool = false;
	}

	EMenuItem(final String name, final boolean tool) {
		this.name = name;
		this.tool = tool;
	}

	public boolean isTool() {
		return tool;
	}

	public String getName() {
		return name;
	}
}