package dev.arcticdevelopment.arcticcollectors.ulitities.collectors;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.ui.AInventoryUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CollectorUI extends AInventoryUI {


	public CollectorUI(Inventory inventory) {
		super(inventory);
	}

	public CollectorUI(String name, int rows) {
		super(name, rows);
	}

	@Override
	public boolean onClick(InventoryClickEvent inventoryClickEvent) {
		return false;
	}

	@Override
	public void onOpen(InventoryOpenEvent inventoryOpenEvent) {

	}

	@Override
	public void onClose(InventoryCloseEvent inventoryCloseEvent) {

	}
	public static void updateSlotLore(AInventoryBuilder inventoryBuilder, int slot, String lore) {

		ArrayList<String> loreList = new ArrayList<>();
		loreList.add(lore);
		System.out.println(loreList);
		ItemStack itemStack = inventoryBuilder.inventory.getItem(slot);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setLore(loreList);

		inventoryBuilder.inventory.setItem(slot,itemStack);
		System.out.println("set inventory");
	}
}
