package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.CollectorManager;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CollectorBreakListener implements Listener {
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public static void destroyedCollector(BlockBreakEvent event) {

		if(event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();
		Location location = event.getBlock().getLocation();

		if(!(CollectorManager.collectorList.containsKey(location.getChunk()))) {

			return;
		}

		ItemStack collector = new ItemStack(Material.BEACON, 1);
		ItemMeta meta = collector.getItemMeta();

		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"));
		collector.setItemMeta(meta);

		event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), collector);

		CollectorManager.collectorList.remove(location.getChunk());

		AOutput.send(player, "Destroyed &acollector&7");
	}
}
