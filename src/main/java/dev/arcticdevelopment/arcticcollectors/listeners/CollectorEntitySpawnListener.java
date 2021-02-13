package dev.arcticdevelopment.arcticcollectors.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

public class CollectorEntitySpawnListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
	public static void mobSpawn(SpawnerSpawnEvent event) {

		if(event.getEntity().getType() == EntityType.BLAZE) {
			return;
		}

		Location location = event.getEntity().getLocation();
//		List<String> locationList = AConfig.getStringList("collectors");
//		event.setCancelled(true);
//		StackedSpawner stackedSpawner = WildStackerAPI.getStackedSpawner(event.getSpawner());
//		int stackAmount = stackedSpawner.getStackAmount();
//
//		for(String stringTestLocation : locationList) {
//
//			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);
//			if(location.getChunk().equals(testLocation.getChunk())) {
//
//				return;
//			}
//		}
	}
}
