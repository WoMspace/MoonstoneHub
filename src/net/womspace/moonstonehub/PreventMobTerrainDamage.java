package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class PreventMobTerrainDamage implements Listener {
    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent kablooie)
    {
        try {
            if (kablooie.getEntity() instanceof Creeper && MoonstoneHub.config.getBoolean("noCreeperTerrainDamage")) {
                kablooie.blockList().clear();
            }
        }
        catch (Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent pickup)
    {
        try {
            if (pickup.getEntityType().equals(EntityType.ENDERMAN) && MoonstoneHub.config.getBoolean("preventEndermanMoveBlocks")) {
                pickup.setCancelled(true);
            }
        }
        catch (Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
}
