package rege.chemicalcompound.mod115.crnobr;

import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperExplosionListener implements Listener {
	public final CrnobrPlugin plugin;

	public CreeperExplosionListener(CrnobrPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof Creeper && !this.plugin.isLitByPlayer(entity)) {
			event.blockList().clear();
		}
	}
}