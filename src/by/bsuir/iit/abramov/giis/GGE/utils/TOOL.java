package by.bsuir.iit.abramov.giis.GGE.utils;

public enum TOOL {
	TAB("New Tab"), SEGMENT("Segment");
	
	private String name;
	
	TOOL(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
