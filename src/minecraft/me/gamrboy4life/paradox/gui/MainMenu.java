package me.gamrboy4life.paradox.gui;

import java.awt.Desktop;
import java.net.URI;

import me.gamrboy4life.paradox.Paradox;
import me.gamrboy4life.paradox.alt.GuiAltManager;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen{

	public MainMenu() {
		
	}
	
	public void initGui() {
		
	}
	
	public void drawScreen(int mouseX,int mouseY,float partialticks) {
		mc.getTextureManager().bindTexture(new ResourceLocation("yuzuclient/yuzuclient.png"));
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0,this.width, this.height, this.width, this.height);
		this.drawGradientRect(0, height-120, width, height, 0x00000000, 0xff000000);
		
		String[] buttons= {"シングルプレイ","マルチプレイ","ＡＬＴ","設定","言語","登録","やめる"};
		int count=0;
		for(String name:buttons) {
			float x=(width/buttons.length)*count+(width/buttons.length)/2f+8-mc.fontRendererObj.getStringWidth(name)/2f;
			float y=height-20;
			
			boolean hovered=(mouseX>=x&&mouseY>=y&&mouseX<x+mc.fontRendererObj.getStringWidth(name)&&mouseY<y+mc.fontRendererObj.FONT_HEIGHT);
			
			this.drawCenteredString(mc.fontRendererObj, name, (width/buttons.length)*count+(width/buttons.length/2f+8), y,hovered?0xFFE600:-1 );
			count++;
		}
		
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f,height/2f,0);
		GlStateManager.scale(3,3,1);
		GlStateManager.translate(-(width/2f),-(height/2f),0);
		this.drawCenteredString(mc.fontRendererObj, Paradox.name, width/2f, height/2.5f- mc.fontRendererObj.FONT_HEIGHT/2f, -1);
		GlStateManager.popMatrix();
		
	}
	
	public void mouseClicked(int mouseX,int mouseY,int button) {
		String[] buttons= {"シングルプレイ","マルチプレイ","ＡＬＴ","設定","言語","登録","やめる"};
		
		int count=0;
		for(String name : buttons) {
		    float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8
		              - mc.fontRendererObj.getStringWidth(name) / 2f;
		    float y = height - 20;

		    if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
		        if(name.equals("シングルプレイ")) {
		            mc.displayGuiScreen(new GuiSelectWorld(this));
		        } else if(name.equals("マルチプレイ")) {
		            mc.displayGuiScreen(new GuiMultiplayer(this));
		        } else if(name.equals("ＡＬＴ")) {
		            mc.displayGuiScreen(new GuiAltManager());
		        } else if(name.equals("設定")) {
		            mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		        } else if(name.equals("言語")) {
		            mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
		        }else if(name.equals("登録")) {
		        	 try {
		                    Desktop desktop = Desktop.getDesktop();
		                    URI uri = new URI("https://www.youtube.com/@yuzu_krs");
		                    desktop.browse(uri);
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		        } else if(name.equals("やめる")) {
		            mc.shutdown();
		        }
		    }
		    count++;
		}
	}
}



