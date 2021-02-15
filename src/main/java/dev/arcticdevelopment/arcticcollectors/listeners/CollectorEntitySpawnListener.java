package dev.arcticdevelopment.arcticcollectors.listeners;

import com.bgsoftware.wildstacker.api.WildStackerAPI;
import com.bgsoftware.wildstacker.api.objects.StackedSpawner;
import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.Collector;
import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.CollectorManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class CollectorEntitySpawnListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void mobSpawn(SpawnerSpawnEvent event) {

		if(event.getEntityType() == EntityType.BLAZE) {
			return;
		}

		event.setCancelled(true);
		Location location = event.getEntity().getLocation();
		EntityType entityType = event.getEntityType();
		Collector collector = CollectorManager.collectorList.get(location.getChunk());
		StackedSpawner stackedSpawner = WildStackerAPI.getStackedSpawner(event.getSpawner());
		int stackAmount = stackedSpawner.getStackAmount();

		if (collector == null) return;

		System.out.println("entity spawned in collector chunk");
		switch(entityType) {
			case ENDERMAN:
				collector.addDrop(Material.ENDER_PEARL,stackAmount);
				break;
			case VILLAGER:
				collector.addDrop(Material.EMERALD,stackAmount);
				System.out.println("switch case villager");
				break;
		}
	}
}
