package me.gamrboy4life.paradox.mods;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.gvent.GventManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Mod {
    private boolean isEnabled = true;
    protected final Minecraft mc;
    protected final FontRenderer font;
    protected final Paradox client;

    public Mod() {
        this.mc = Minecraft.getMinecraft();
        this.font = this.mc.fontRendererObj;
        this.client = Paradox.getInstance();
        setEnabled(isEnabled);
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        if (isEnabled) {
            GventManager.register(this);
        } else {
            GventManager.unregister(this);
        }
    }
    
    public boolean isEnable() {
    	return isEnabled;
    }
}