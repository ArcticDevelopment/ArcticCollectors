package dev.arcticdevelopment.arcticcollectors.listeners;

import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.Collector;
import dev.arcticdevelopment.arcticcollectors.ulitities.collectors.CollectorManager;
import dev.kyro.arcticapi.hooks.AFactionsHook;
import dev.kyro.arcticapi.hooks.enums.FactionRank;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CollectorOpenListener implements Listener {

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public static void openCollector(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		FactionRank rank = AFactionsHook.getFactionRank(player);

		if (!AFactionsHook.inOwnTerritory(player)){
			return;
		}

		if((event.getAction() != Action.RIGHT_CLICK_BLOCK) || (event.getClickedBlock() == null)) {
			return;
		}

		if(event.getClickedBlock().getType() != Material.BEACON) {
			return;
		}

		switch(rank) {
			case RECRUIT:
			case MEMBER:
			case MODERATOR:
				return;
		}


		if (CollectorManager.isBlockCollector(block)) {

			event.setCancelled(true);
			Collector collector = CollectorManager.collectorList.get(block.getChunk());
			player.openInventory(collector.collectorUI.inventory);
		}

	}
}
