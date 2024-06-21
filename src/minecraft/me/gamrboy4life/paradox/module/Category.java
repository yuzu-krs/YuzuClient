package me.gamrboy4life.paradox.module;

public enum Category {
	
	COMBAT("Combat"),
	MOVEMENT("Movement"),
	PLAYER("Player"),
	STATUS("Status"),
	RENDER("Render"),
	MISC("Misc");
	
	public String name;
	public int moduleIndex;
	
	Category(String name){
		this.name=name;
	}

}
