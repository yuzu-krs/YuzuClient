package me.gamrboy4life.paradox.gui;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.module.Module;
import me.gamrboy4life.paradox.utils.ColorUtils;
import me.gamrboy4life.paradox.utils.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class GuiIngameHook extends GuiIngame{

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}
	
	public void renderGameOverlay(float p_175180_1_) {
		super.renderGameOverlay(p_175180_1_);

		if(!mc.gameSettings.showDebugInfo) {
			drawRect(2,2,110,14,0x80000000);
			Wrapper.fr.drawString("YuzuClient", 4,4,0xFFE600);
			Wrapper.fr.drawString("FPS: "+mc.getDebugFPS(),60,4,-1);
			renderArrayList();			

		}
	}

	
	private void renderArrayList() {
		int yCount=10;
		int index=0;
		long x=0;
		for(Module m : Paradox.instance.moduleManager.getModules()) {
			m.onRender();
			
			if(m.isToggled()) {
				
				Wrapper.fr.drawString("- " + m.getName(), 400, yCount -7, ColorUtils.rainbowEffect(index+x*2000000000L, 1.0F).getRGB()); // 赤色
				yCount+=10;
				index++;
				x++;
			}
		}
		
	}
	
}
