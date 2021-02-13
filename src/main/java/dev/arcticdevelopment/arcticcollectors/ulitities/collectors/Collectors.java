package dev.arcticdevelopment.arcticcollectors.ulitities.collectors;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.HashMap;

public class Collectors {

	private final String name;
	private final Location location;
	private final AInventoryBuilder inventoryBuilder;

	private HashMap<Material,Integer> itemsStored = new HashMap<>();

	public Collectors(String name, Location location) {

		this.name = name;
		this.location = location;

		this.inventoryBuilder = new AInventoryBuilder(null,36, ChatColor.translateAlternateColorCodes(
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
	}

	public Location getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public AInventoryBuilder getInventoryBuilder() {
		return inventoryBuilder;
	}

	public HashMap<Material, Integer> getItemsStored() {
		return itemsStored;
	}

	public void setItemsStored(HashMap<Material, Integer> itemsStored) {
		this.itemsStored = itemsStored;
	}

	public Chunk getChunk() {
		return location.getChunk();
	}


}

