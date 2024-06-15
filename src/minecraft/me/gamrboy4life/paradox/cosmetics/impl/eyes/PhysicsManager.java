package me.gamrboy4life.paradox.cosmetics.impl.eyes;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

import me.gamrboy4life.paradox.gvent.GventManager;
import me.gamrboy4life.paradox.gvent.GventTarget;
import me.gamrboy4life.paradox.gvent.impl.ClientTickEvent;
import me.gamrboy4life.paradox.gvent.impl.WorldUnloadEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;

public class PhysicsManager {

	private WeakHashMap<AbstractClientPlayer, EyePhysics> physicsList = new WeakHashMap<AbstractClientPlayer, EyePhysics>();

	private static PhysicsManager instance = null;

	public static PhysicsManager getInstance() {
		if(instance == null) {
			instance = new PhysicsManager();
			GventManager.register(instance);
		}
		return instance;
	}

	public EyePhysics getPhysics(AbstractClientPlayer player) {
		if(!physicsList.containsKey(player)) {
			physicsList.put(player, new EyePhysics(player));
		}
		return physicsList.get(player);
	}

	@GventTarget
	public void onTick(ClientTickEvent event) {

		if(Minecraft.getMinecraft().theWorld != null && !Minecraft.getMinecraft().isGamePaused()) {

			Iterator<Map.Entry<AbstractClientPlayer, EyePhysics>> iterator = physicsList.entrySet().iterator();

			while(iterator.hasNext()) {
				Map.Entry<AbstractClientPlayer, EyePhysics> e = iterator.next();
				EyePhysics ep = e.getValue();

				if(ep.getPlayer().worldObj.getWorldTime() - ep.getLastUpdate() > 3) {
					iterator.remove();
				}
				else {
					ep.update();
				}
			}

		}

	}

	@GventTarget
	public void onWorldUload(WorldUnloadEvent event) {
		Iterator<Map.Entry<AbstractClientPlayer, EyePhysics>> iterator = physicsList.entrySet().iterator();

		while(iterator.hasNext()) {
			Map.Entry<AbstractClientPlayer, EyePhysics> e = iterator.next();
			EyePhysics ep = e.getValue();
			if(ep.getPlayer().worldObj == event.getWorld()) {
				iterator.remove();
			}
		}
	}

}
