package me.gamrboy4life.paradox.devlogs.cosmetic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CosmeticModelBase extends ModelBase
{
    protected final ModelBiped playerModel;
    
    public CosmeticModelBase(final RenderPlayer player) {
        this.playerModel = player.getMainModel();
    }
}