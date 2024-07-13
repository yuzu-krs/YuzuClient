package me.gamrboy4life.paradox.utils.esp;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;

public class ItemESPUtils {

    public static void itemESPBox(EntityItem entityItem, double red, double green, double blue) {
        double x = entityItem.posX - Minecraft.getMinecraft().getRenderManager().renderPosX;
        double y = entityItem.posY - Minecraft.getMinecraft().getRenderManager().renderPosY;
        double z = entityItem.posZ - Minecraft.getMinecraft().getRenderManager().renderPosZ;

        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS); // OpenGLの状態を保存
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(2.0F);
        GL11.glColor4d(red, green, blue, 0.15F); // 指定された色（透明）
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);

        // ボックス
        GL11.glColor4d(red, green, blue, 0.5F); // 指定された色
        RenderGlobal.func_181561_a(new AxisAlignedBB(x - 0.25, y, z - 0.25, x + 0.25, y + 0.5, z + 0.25));

        GL11.glPopAttrib(); // OpenGLの状態を復元
    }
}