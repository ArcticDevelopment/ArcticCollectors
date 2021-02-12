package dev.finn.plugin.commands;

import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class giveCollector implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player)sender;
        Inventory inventory = player.getInventory();
        if (inventory.firstEmpty() == -1) {
            AOutput.color(player, "Your inventory is full");
            return false;
        }
        ItemStack collector = new ItemStack(Material.BEACON,1);
        ItemMeta meta = collector.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lChunk &8&lCollector"));
        collector.setItemMeta(meta);
        inventory.addItem(collector);
        AOutput.color(player,"Given 1x &aCollector");

        return false;
    }
}
