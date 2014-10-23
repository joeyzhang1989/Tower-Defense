package com.soen6441.core.map;


/**
 * This class will represent Road which is a MapItem. It extends MapItem and also defines additional methods 
 * specific to it. 
 * 
 * @author Zhe Zhao
 * @author Mohammad Ali
 */

public class Road extends MapItem {
	
	public enum Type{
		NORMAL, START, END
	}
	
	private Type type;

	public Road() {
		super();
		this.type = Type.NORMAL;
	}
	
	public Road(Type type) {
		super();
		this.type = type;
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
