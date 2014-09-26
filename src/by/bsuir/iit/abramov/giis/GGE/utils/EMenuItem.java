package by.bsuir.iit.abramov.giis.GGE.utils;

public enum EMenuItem {
	
	NEW("New...", true), OPEN("Open"), CLOSE("Close"), EXIT("Exit"), 
	SEGMENT("Segment", true), SEGMENT_DDA("SegmentDDA", true), SEGMENT_BREZENHEM("SegmentBrezenhem", true),
	NONE("Empty", true);
	
	private String name = "DEFAULT";
	private boolean tool = false;
	
	EMenuItem(String name) {
		this.name = name;
		this.tool = false;
	}
	
	EMenuItem(String name, boolean tool) {
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