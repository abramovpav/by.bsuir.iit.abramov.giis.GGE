package by.bsuir.iit.abramov.giis.GGE.utils;

public enum EMenuItem {
	
	NEW("New..."), OPEN("Open"), CLOSE("Close"), EXIT("Exit"), EDGE("Edge"), 
	NONE("Empty");
	
	private String name = "DEFAULT";
	
	EMenuItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
