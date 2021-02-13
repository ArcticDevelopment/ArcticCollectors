package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.arcticdevelopment.arcticcollectors.ArcticCollectors;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CollectorBreakListener implements Listener {
	@EventHandler(ignoreCancelled = true)
	public static void destroyedCollector(BlockBreakEvent event) {

		if(event.getPlayer() == null
				|| event.getBlock().getType() != Material.BEACON) return;

		Player player = event.getPlayer();

		ItemStack collector = new ItemStack(Material.BEACON, 1);
		ItemMeta meta = collector.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"));
		collector.setItemMeta(meta);


		List<String> locations = AConfig.getStringList("collectors");

		for(String stringTestLocation : locations) {

			Location testLocation = ASerializer.deserializeLocation(stringTestLocation);

			if(event.getBlock().getLocation().equals(testLocation)) {

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
}
