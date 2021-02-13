package dev.arcticdevelopment.arcticcollectors.listeners;

import com.bgsoftware.wildstacker.api.events.SpawnerSpawnEvent;
import com.bgsoftware.wildstacker.api.objects.StackedEntity;
import dev.arcticdevelopment.arcticcollectors.ArcticCollectors;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CollectorListener implements org.bukkit.event.Listener {

	@EventHandler(ignoreCancelled = true)
	public static void placedCollector(BlockPlaceEvent event) {

		if (event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		ItemMeta meta = item.getItemMeta();

		if (!(meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector")))) {

			return;
		}

		List<String> locations = AConfig.getStringList("collectors");

		for (String stringTestLocation: locations) {
			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);
			Chunk testChunk = testLocation.getChunk();

			if (testChunk == event.getBlock().getLocation().getChunk()) {

				AOutput.error(player, "There is already a chunk collector placed in this chunk!");
				event.setCancelled(true);
				return;
			}
		}



		String stringLocation = ASerializer.serializeLocation(event.getBlock().getLocation());

		List<String> list = AConfig.getStringList("collectors");
		list.add(stringLocation);
		AConfig.set("collectors", list);
		ArcticCollectors.INSTANCE.saveConfig();

		AOutput.color(player, "Placed &acollector");
	}

	@EventHandler(ignoreCancelled = true)
	public static void destroyedCollector(BlockBreakEvent event) {

		if (event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();

		ItemStack collector = new ItemStack(Material.BEACON,1);
		ItemMeta meta = collector.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"));
		collector.setItemMeta(meta);



		List<String> locations = AConfig.getStringList("collectors");

		for (String stringTestLocation: locations) {

			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);

			if (event.getBlock().getLocation().equals(testLocation)) {

				AOutput.send(player, "Destroyed &acollector&7");

				List<String> list = AConfig.getStringList("collectors");
				list.remove(stringTestLocation);
				AConfig.set("collectors", list);
				ArcticCollectors.INSTANCE.saveConfig();
				event.getBlock().setType(Material.AIR);
				event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), collector);


			}
		}
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
	public static void mobSpawn(SpawnerSpawnEvent event) {
		if (event.getEntity().getType() == EntityType.BLAZE) {
			return;
		}
		StackedEntity entity = event.getEntity();
		Location location = event.getEntity().getLocation();
		List<String> locationList = AConfig.getStringList("collectors");
		entity.remove();

		for (String stringTestLocation: locationList) {
			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);
			System.out.println(testLocation.getChunk());
			System.out.println(location.getChunk());
			if (location.getChunk().equals(testLocation.getChunk())) {
				Bukkit.broadcastMessage("collector killed mob");
				return;
			}
		}

		Bukkit.broadcastMessage("event was cancelled because no collector");

	}
}
