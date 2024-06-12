package me.gamrboy4life.paradox.gvent;

import java.lang.reflect.Method;

public class GventData {
	public final Object source; 
	public final Method target; 
	public final byte priority;
	
	public GventData(Object source, Method target, byte priority) { 
		this.source = source;
		this.target=target;
		this.priority = priority;
	}
}