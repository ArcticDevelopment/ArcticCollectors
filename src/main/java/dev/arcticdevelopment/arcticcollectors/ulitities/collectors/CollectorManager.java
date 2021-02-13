package dev.arcticdevelopment.arcticcollectors.ulitities.collectors;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

public class CollectorManager {

	public static HashMap<Chunk,Collector> collectorList = new HashMap<>();

	public static boolean isBlockCollector(Block block) {

		if (block == null || block.getType() != Material.BEACON) return false;

		Location location = block.getLocation();
		Chunk chunk = block.getChunk();

		 if(collectorList.containsKey(chunk)) {

		 	Collector collector = collectorList.get(chunk);
		 	return collector.getLocation().equals(location);
		 }
		return false;
	}

}
