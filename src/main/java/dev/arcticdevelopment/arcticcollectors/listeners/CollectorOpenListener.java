package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.ASerializer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class CollectorOpenListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public static void openCollector(PlayerInteractEvent event) {

		Player player = event.getPlayer();

		if((event.getAction() != Action.RIGHT_CLICK_BLOCK) || (event.getClickedBlock() == null)) {
			return;
		}

		if(event.getClickedBlock().getType() != Material.BEACON) {
			return;
		}

		Location location = event.getClickedBlock().getLocation();
		List<String> locationsList = AConfig.getStringList("collectors");

		for(String testLocationString: locationsList) {

			Location testLocation = ASerializer.deserializeLocation(testLocationString);

			if(testLocation.equals(location)) {
				event.setCancelled(true);
				AInventoryBuilder inventoryBuilder = new AInventoryBuilder(null,36, ChatColor.translateAlternateColorCodes(
						'&',"Collector"))
						.createBorder(Material.STAINED_GLASS_PANE,3)
						.setSlot(Material.WOOL, 0,10,null,null)
						.setSlot(Material.LEATHER, 0,11,null,null)
						.setSlot(Material.RAW_BEEF, 0,12,null,null)
						.setSlot(Material.RAW_CHICKEN, 0,13,null,null)
						.setSlot(Material.PORK, 0,14,null,null)
						.setSlot(Material.FEATHER, 0,15,null,null)
						.setSlot(Material.BONE, 0,16,null,null)
						.setSlot(Material.ROTTEN_FLESH, 0,19,null,null)
						.setSlot(Material.SPIDER_EYE, 0,20,null,null)
						.setSlot(Material.STRING, 0,21,null,null)
						.setSlot(Material.IRON_INGOT, 0,22,null,null)
						.setSlot(Material.ENDER_PEARL, 0,23,null,null)
						.setSlot(Material.EMERALD, 0,24,null,null)
						.setSlot(Material.TNT, 0,25,null,null);
				player.openInventory(inventoryBuilder.inventory);
			}
		}

	}
}
