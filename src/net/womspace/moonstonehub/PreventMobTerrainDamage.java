package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.logging.Logger;

public class PreventMobTerrainDamage implements Listener {
    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent kablooie)
    {
        if(kablooie.getEntity() instanceof Creeper)
        {
            for(Block block : kablooie.blockList())
            {
                //Bukkit.getLogger().info(block.getType().name());

                //prevent break somehow ://
                //also don't forget to add endermen, and also update version number

            }
        }
    }
}
