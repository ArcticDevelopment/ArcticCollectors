package dev.finn.plugin.listeners;

import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.FileWriter;
import java.io.IOException;

public class CollectorListener implements org.bukkit.event.Listener {
    @EventHandler
    public static void placedCollector(BlockPlaceEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        if (meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"))) {
            AOutput.color(player, "Placed &acollector");
        }
        Chunk chunk = event.getBlockPlaced().getChunk();
    }
}
