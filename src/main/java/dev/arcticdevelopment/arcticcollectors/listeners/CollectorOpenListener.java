package dev.arcticdevelopment.arcticcollectors.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

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
	}
}
