package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CollectorListener implements org.bukkit.event.Listener {
    @EventHandler
    public static void placedCollector(BlockPlaceEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        List<String> locations = AConfig.getStringList("collectors");
        for (String testLocation: locations) {
            Location locationList = ASerializer.deserializeLocation(testLocation);
            Chunk testChunk = locationList.getChunk();
            if (testChunk == event.getBlockPlaced().getLocation().getChunk()) {
                AOutput.color(player, "There is already a chunk collector placed in this chunk!");
                return;
            }
        }
        if (meta.getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"))) {
            AOutput.color(player, "Placed &acollector");
        }
        String stringLocation = ASerializer.serializeLocation(event.getBlockPlaced().getLocation());
        AConfig.addToList("collectors", stringLocation);


    }
}
