package me.gamrboy4life.paradox.gvent.impl;

import me.gamrboy4life.paradox.gvent.GventCancelable;

public class KeyEvent extends GventCancelable{
	
	private final int key;
	
	public KeyEvent(int key) {
		this.key=key;
	}
	
	public int getKey() {
		return key;
	}

}
