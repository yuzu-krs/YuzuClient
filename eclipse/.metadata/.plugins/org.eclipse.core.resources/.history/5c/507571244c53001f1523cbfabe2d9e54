package me.gamrboy4life.paradox.gui;

import java.awt.Desktop;
import java.net.URI;

import me.gamrboy4life.paradox.alt.GuiAltManager;
import me.gamrboy4life.paradox.utils.font.FontUtil;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen{

    private boolean useFirstBackground = true;

    public MainMenu() {

    }

    public void initGui() {

    }

    public void drawScreen(int mouseX,int mouseY,float partialticks) {
        String backgroundImage = useFirstBackground ? "yuzuclient/yuzuclient.png" : "yuzuclient/yuzuclient2.png";
        mc.getTextureManager().bindTexture(new ResourceLocation(backgroundImage));
        this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0,this.width, this.height, this.width, this.height);
        this.drawGradientRect(0, height-120, width, height, 0x00000000, 0xff000000);

        String[] buttons= {"SinglePlayer","MultiPlayer","AltManager","Settings","Language","Subscribe","Background","Quit"};
        int count=0;
        for(String name:buttons) {
            float x=(width/buttons.length)*count+(width/buttons.length)/2f+8-mc.fontRendererObj.getStringWidth(name)/2f;
            float y=height-20;

            boolean hovered=(mouseX>=x&&mouseY>=y&&mouseX<x+mc.fontRendererObj.getStringWidth(name)&&mouseY<y+mc.fontRendererObj.FONT_HEIGHT);

            FontUtil.normal.drawCenteredString(name, (width/buttons.length)*count+(width/buttons.length/2f+8), y,hovered?0xFFE600:-1 );
            count++;
        }


        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2f,height/2f,0);
        GlStateManager.scale(2,2,1);
        GlStateManager.translate(-(width/2f),-(height/2f),0);
        this.drawCenteredString(mc.fontRendererObj, "ver3.0", width/2f, height/1.8f- mc.fontRendererObj.FONT_HEIGHT/2f, -1);
        GlStateManager.popMatrix();

    }

    public void mouseClicked(int mouseX,int mouseY,int button) {
        String[] buttons= {"SinglePlayer","MultiPlayer","AltManager","Settings","Language","Subscribe","Background","Quit"};

        int count=0;
        for(String name : buttons) {
            float x = (width / buttons.length) * count + (width / buttons.length) / 2f + 8
                      - mc.fontRendererObj.getStringWidth(name) / 2f;
            float y = height - 20;

            if(mouseX >= x && mouseY >= y && mouseX < x + mc.fontRendererObj.getStringWidth(name) && mouseY < y + mc.fontRendererObj.FONT_HEIGHT) {
                if(name.equals("SinglePlayer")) {
                    mc.displayGuiScreen(new GuiSelectWorld(this));
                } else if(name.equals("MultiPlayer")) {
                    mc.displayGuiScreen(new GuiMultiplayer(this));
                } else if(name.equals("AltManager")) {
                    mc.displayGuiScreen(new GuiAltManager());
                } else if(name.equals("Settings")) {
                    mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                } else if(name.equals("Language")) {
                    mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
                } else if(name.equals("Subscribe")) {
                    try {
                        Desktop desktop = Desktop.getDesktop();
                        URI uri = new URI("https://www.youtube.com/@yuzu_krs");
                        desktop.browse(uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if(name.equals("Background")) {
                    useFirstBackground = !useFirstBackground;
                } else if(name.equals("Quit")) {
                    mc.shutdown();
                }
            }
            count++;
        }
    }
}

