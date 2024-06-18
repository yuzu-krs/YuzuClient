package me.gamrboy4life.paradox.devlogs.cosmetic;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.util.ResourceLocation;

public class Cosmetic
{
    public Cosmetic(final RenderPlayer renderPlayer) {
    }
    
    protected ModelRenderer bindTextureAndColor(final Color color, final ResourceLocation resourceLocation, final ModelRenderer colorModel, final ModelRenderer playerSkinModel) {
        final boolean flag = false;
        if (!flag) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
        }
        return colorModel;
    }
    
    public void render(final AbstractClientPlayer player, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float headYaw, final float headPitch, final float scale) {
    }
}
