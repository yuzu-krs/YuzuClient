package me.gamrboy4life.paradox.mods.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import me.gamrboy4life.paradox.gui.hud.ScreenPosition;
import me.gamrboy4life.paradox.mods.ModDraggable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;

public class TargetHUD extends ModDraggable{

	EntityLivingBase target;
	
	private List<Long> clicks = new ArrayList<Long>();
	private boolean wasPressed;
	private long lastPressed;
	
	EntityLivingBase savedTarget;
	
	@Override
	public int getWidth() {
		return 100;
	}

	@Override
	public int getHeight() {
		return font.FONT_HEIGHT*2+1;
		
	}

	@Override
	public void render(ScreenPosition pos) {
		
		if(!(mc.pointedEntity instanceof EntityItemFrame)) {
			target=(EntityLivingBase)mc.pointedEntity;
		
			if(target!=null) {
				
				font.drawString(target.getName(), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, -1);
				GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()+font.getStringWidth(target.getName())+30, pos.getAbsoluteY()+20, 20, 50, 0, target);
				//自分の体力とターゲットの体力の比較
				if(target.getHealth()>mc.thePlayer.getHealth()) {
					renderTargetHUD(true);
					font.drawString(String.format("§c"+"%.1f", target.getHealth()) + " \u2764", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);
					
				}else {
					renderTargetHUD(false);
					font.drawString(String.format("§a"+"%.1f", target.getHealth()) + " \u2764", pos.getAbsoluteX() + 2, pos.getAbsoluteY() + 2 + font.FONT_HEIGHT, -1);				
				}
			}
		}
	}
	
	@Override
	public void renderDummy(ScreenPosition pos) {
		font.drawString(mc.thePlayer.getName(), pos.getAbsoluteX()+2, pos.getAbsoluteY()+2, -1);
		font.drawString((int)mc.thePlayer.getHealth()+" \u2764", pos.getAbsoluteX()+2, pos.getAbsoluteY()+2+font.FONT_HEIGHT, -1);
		GuiInventory.drawEntityOnScreen(pos.getAbsoluteX()+font.getStringWidth(mc.thePlayer.getName())+30, pos.getAbsoluteY()+30, 20, 50, 0, mc.thePlayer);
	}
	
	
	private void renderTargetHUD(boolean b) {
		
		if(!(mc.pointedEntity instanceof EntityItemFrame)) {
		EntityLivingBase target = (EntityLivingBase)mc.pointedEntity;
			if(target!=null) {
				final boolean pressed = Mouse.isButtonDown(0);
				if(pressed != this.wasPressed) {
					this.lastPressed = System.currentTimeMillis();
					this.wasPressed = pressed;
					if(pressed) {
						this.clicks.add(this.lastPressed);
					}
				}
				
				font.drawString(String.format("§fDistance: %.2f", target.getDistance(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ)), pos.getAbsoluteX() , pos.getAbsoluteY() + 38, new Color(233,55,65).getRGB());
				
				renderHealthBar(target,b);
			}
		}
	}
	private void renderHealthBar(EntityLivingBase target,boolean b) {
		if(b) {
			Gui.drawRect(pos.getAbsoluteX() , pos.getAbsoluteY()+27, pos.getAbsoluteX()+ 120,pos.getAbsoluteY()+35, new Color(25,23,13).getRGB());	
			Gui.drawRect(pos.getAbsoluteX() , pos.getAbsoluteY()+27, pos.getAbsoluteX()+ 27+(int)(93*(target.getHealth()/target.getMaxHealth())),pos.getAbsoluteY()+35, new Color(255,85,85).getRGB());
		}else {
			Gui.drawRect(pos.getAbsoluteX() , pos.getAbsoluteY()+27, pos.getAbsoluteX()+ 120,pos.getAbsoluteY()+35, new Color(25,23,13).getRGB());	
			Gui.drawRect(pos.getAbsoluteX() , pos.getAbsoluteY()+27, pos.getAbsoluteX()+ 27+(int)(93*(target.getHealth()/target.getMaxHealth())),pos.getAbsoluteY()+35, new Color(85,255,85).getRGB());			
			
		}
		

	}





	public static void drawPlayerHead(int x, int y, int width, EntityLivingBase player) {
    	NetworkPlayerInfo playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(player.getUniqueID());
    	if (playerInfo != null){
            Minecraft.getMinecraft().getTextureManager().bindTexture(playerInfo.getLocationSkin());
            GL11.glColor4f(1F, 1F, 1F, 1F);

            Gui.drawScaledCustomSizeModalRect((int) x - 5, (int) y - 5, 8F, 8F, 8, 8, 20, 20, 64F, 64F);
        }
        
    }

	public static void drawRoundedRect(float x, float y, float x1, float y1, float radius, int color) {
	    GL11.glPushAttrib(0);
	    GL11.glScaled(0.5D, 0.5D, 0.5D);
	    x *= 2.0D;
	    y *= 2.0D;
	    x1 *= 2.0D;
	    y1 *= 2.0D;
	    GL11.glEnable(GL11.GL_BLEND);
	    GL11.glDisable(GL11.GL_TEXTURE_2D);
	    GL11.glEnable(GL11.GL_LINE_SMOOTH);
	    GlStateManager.color(0F, 0F, 0F, 0.4F);
	    GL11.glEnable(GL11.GL_LINE_SMOOTH);
	    GL11.glBegin(GL11.GL_POLYGON);
	    int i;
	    for (i = 0; i <= 90; i += 3)
	        GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
	    for (i = 90; i <= 180; i += 3)
	        GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D); 
	    for (i = 0; i <= 90; i += 3)
	        GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y1 - radius + Math.cos(i * Math.PI / 180.0D) * radius); 
	    for (i = 90; i <= 180; i += 3)
	        GL11.glVertex2d(x1 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius); 
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
	    GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);	
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	    GL11.glScaled(2.0D, 2.0D, 2.0D);
	    GL11.glEnable(GL11.GL_BLEND);
	    GL11.glPopAttrib();
	    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	
    private static boolean enabled = true; // 静的フィールド
    

	
    @Override
    public boolean isEnabled() {
        return enabled; // 静的フィールドを使用
    }

    // 静的メソッド
    public void setEnabled(boolean isEnabled) {
        TargetHUD.enabled = isEnabled;
    }


}



