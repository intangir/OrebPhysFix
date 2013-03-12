package com.github.intangir.OrebPhysFix;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import com.lishid.orebfuscator.obfuscation.BlockUpdate;

public class OrebPhysFix extends JavaPlugin implements Listener
{
    public Logger log;
    public PluginDescriptionFile pdfFile;
    public static final int[] passable = {
    	Material.AIR.getId(),
    	Material.FIRE.getId(),
    	Material.WATER.getId(),
    	Material.STATIONARY_WATER.getId(),
    	Material.LAVA.getId(),
    	Material.STATIONARY_LAVA.getId()
    };
    
	public void onEnable()
	{
		log = this.getLogger();
		pdfFile = this.getDescription();

		Bukkit.getPluginManager().registerEvents(this, this);
		
		log.info("v" + pdfFile.getVersion() + " enabled!");
	}
	
	public void onDisable()
	{
		log.info("v" + pdfFile.getVersion() + " disabled.");
	}
	
	@EventHandler(ignoreCancelled=true,priority=EventPriority.MONITOR)
	public void onBlockPhysics(BlockPhysicsEvent event)
	{
		if (event.getBlock().getType() != Material.COBBLESTONE && event.getBlock().getType() != Material.DIRT)
		{
			return;
		}
        
		if(!applyphysics(event.getBlock()))
		{
			return;
		}
		
		BlockUpdate.Update(event.getBlock());
	}
	
	private boolean applyphysics(Block block)
	{
		int blockID = block.getRelative(0, -1, 0).getTypeId();

		for(int id : passable)
		{
			if(id == blockID)
				return true;
		}
		return false;
	}
}
