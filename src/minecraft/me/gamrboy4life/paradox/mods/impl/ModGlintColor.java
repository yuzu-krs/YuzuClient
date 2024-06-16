package me.gamrboy4life.paradox.mods.impl;

import java.awt.Color;

import me.gamrboy4life.paradox.gvent.GventTarget;
import me.gamrboy4life.paradox.gvent.impl.ClientTickEvent;
import me.gamrboy4life.paradox.mods.Mod;

public class ModGlintColor extends Mod{
	
    private static boolean enchantEffect = false;
	
	public static void setEnchantEffect(boolean enchantEffect) {
		ModGlintColor.enchantEffect = enchantEffect;
	}


	private static final Color DEFAULT_COLOR=new Color(128,64,204);
	
	private Color customColor=new Color(204,64,64);
	
	private Color chromaColor=DEFAULT_COLOR;
	
	public Color getColor() {
		if(enchantEffect) {
			return chromaColor;
		}else {
			return DEFAULT_COLOR;
		}
	}
	
	
	private float hue = 0;

	@GventTarget
	public void onTick(ClientTickEvent e) {
	    hue += 0.01;
	    chromaColor = Color.getHSBColor(hue, 0.69f, 0.8f);
	    if (hue > 1) {
	        hue = 0;
	    }
	}

	

}
