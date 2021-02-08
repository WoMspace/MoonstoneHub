package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.logging.Logger;

public class PreventMobTerrainDamage implements Listener {
    //Logger debug = Bukkit.getLogger();
    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent kablooie)
    {
        try {
            if (kablooie.getEntity() instanceof Creeper && MoonstoneHub.config.getBoolean("noCreeperTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled Creeper Explosion");
                return;
            }
            else if(kablooie.getEntity() instanceof WitherSkull && MoonstoneHub.config.getBoolean("noWitherTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled WitherSkull Explosion");
                return;
            }
            else if(kablooie.getEntity() instanceof Fireball && MoonstoneHub.config.getBoolean("noGhastTerrainDamage")){
                kablooie.blockList().clear();
                //debug.info("Cancelled Ghast Fireball Explosion");
                return;
            }
            else if(kablooie.getEntity() instanceof Wither && MoonstoneHub.config.getBoolean("noWitherTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled Wither Explosion");
                return;
            }
        }
        catch (Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent changeBlockEvent)
    {
        try {
            if (changeBlockEvent.getEntityType().equals(EntityType.ENDERMAN) && MoonstoneHub.config.getBoolean("preventEndermanMoveBlocks")) {
                changeBlockEvent.setCancelled(true);
                //debug.info("Cancelled Enderman Pickup");
                return;
            }
            if(changeBlockEvent.getEntityType().equals(EntityType.RAVAGER) && MoonstoneHub.config.getBoolean("preventRavagerDestruction")) {
                changeBlockEvent.setCancelled(true);
                //debug.info("Cancelled Ravager Terrain Damage");
                return;
            }

        }
        catch (Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
}
