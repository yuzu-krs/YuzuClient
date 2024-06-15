package me.gamrboy4life.paradox.gvent.impl;

import me.gamrboy4life.paradox.gvent.Gvent;
import net.minecraft.world.World;

public class WorldUnloadEvent extends Gvent{
	
	private final World world;
	
	public WorldUnloadEvent(World world) {
		this.world=world;
	}
	
	public World getWorld() {
		return world;
	}

}
