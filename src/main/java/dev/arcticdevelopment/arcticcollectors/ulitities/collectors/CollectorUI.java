package dev.arcticdevelopment.arcticcollectors.ulitities.collectors;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import dev.kyro.arcticapi.ui.AInventoryUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CollectorUI extends AInventoryUI {


	public CollectorUI(Inventory inventory) {

		super(inventory);
	}

	public CollectorUI(String name, int rows) {

		super(name, rows);
	}

	@Override
	public boolean onClick(InventoryClickEvent event) {

		if (!(event.getWhoClicked() instanceof Player)) return true;

		Player player = (Player) event.getWhoClicked();
		player.updateInventory();
		return true;
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
	public void updateSlotLore(AInventoryBuilder inventoryBuilder, int slot, String lore,Collector collector) {

		ItemStack itemStack = inventoryBuilder.inventory.getItem(slot);
		ItemMeta itemMeta = itemStack.getItemMeta();
		ALoreBuilder loreBuilder = new ALoreBuilder(new ItemStack(Material.DIAMOND))
				.addLore(lore)
				.colorize();

		itemMeta.setLore(loreBuilder.lore);
		itemStack.setItemMeta(itemMeta);

		inventoryBuilder.inventory.setItem(slot,itemStack);
		collector.collectorUI.inventory = inventoryBuilder.inventory;

	}
}
