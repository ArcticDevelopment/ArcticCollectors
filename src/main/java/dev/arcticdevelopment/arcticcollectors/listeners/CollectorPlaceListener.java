package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.Collector;
import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.CollectorManager;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CollectorPlaceListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public static void placedCollector(BlockPlaceEvent event) {

		if(event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();
		Location location = event.getBlockPlaced().getLocation();
		ItemStack item = player.getItemInHand();
		ItemMeta meta = item.getItemMeta();

		if(!(meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector")))) {

			return;
		}
		Collector collector = new Collector(location);
		if (CollectorManager.collectorList.containsKey(location.getChunk())) {

			event.setCancelled(true);
			AOutput.error(player, "There is already a chunk collector placed in this chunk!");
			return;
		}

		CollectorManager.collectorList.put(location.getChunk(),collector);
		AOutput.send(player, "Placed collector");
	}
}

