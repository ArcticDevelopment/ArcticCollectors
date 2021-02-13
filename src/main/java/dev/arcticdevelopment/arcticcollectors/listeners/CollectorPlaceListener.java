package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.arcticdevelopment.arcticcollectors.ArcticCollectors;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CollectorPlaceListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public static void placedCollector(BlockPlaceEvent event) {

		if(event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		ItemMeta meta = item.getItemMeta();

		if(!(meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector")))) {

			return;
		}

		List<String> locations = AConfig.getStringList("collectors");

		for(String stringTestLocation : locations) {
			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);
			Chunk testChunk = testLocation.getChunk();

			if(testChunk == event.getBlock().getLocation().getChunk()) {

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
}

