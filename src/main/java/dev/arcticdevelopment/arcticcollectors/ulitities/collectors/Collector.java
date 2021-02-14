package dev.arcticdevelopment.arcticcollectors.ulitities.collectors;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class Collector {

	private final Location location;
	public AInventoryBuilder inventoryBuilder;

	private HashMap<Material,Integer> itemsStored = new HashMap<>();
	public CollectorUI collectorUI = new CollectorUI("collector",4);

	public Collector(Location location) {

		this.location = location;

		this.inventoryBuilder = new AInventoryBuilder(null,36, ChatColor.translateAlternateColorCodes(
				'&',"Collector"))
				.createBorder(Material.STAINED_GLASS_PANE,3)
				.setSlot(Material.WOOL, 0,10,null, null)
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

		collectorUI.inventory = inventoryBuilder.inventory;
		}


	public Location getLocation() {
		return location;
	}

	public HashMap<Material, Integer> getItemsStored() {
		return itemsStored;
	}

	public void setItemsStored(HashMap<Material, Integer> itemsStored) {
		this.itemsStored = itemsStored;
	}
	public void addDrop(Material drop, int amount) {

		itemsStored.putIfAbsent(drop, 0);
		itemsStored.put(drop,itemsStored.get(drop)+amount);
		System.out.println("added drop");
		String dropAmount = "x" + itemsStored.get(Material.ENDER_PEARL);
		CollectorUI.updateSlotLore(inventoryBuilder,23,dropAmount);
	}

	public Chunk getChunk() {
		return location.getChunk();
	}


}

