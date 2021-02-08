package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;

import java.util.logging.Logger;

public class PreventMobTerrainDamage implements Listener {
    Logger debug = Bukkit.getLogger();
    @EventHandler
    public void EntityExplodeEvent(EntityExplodeEvent kablooie)
    {
        try {
            if (kablooie.getEntity() instanceof Creeper && MoonstoneHub.config.getBoolean("noCreeperTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled Creeper Explosion");
            }
            else if(kablooie.getEntity() instanceof WitherSkull && MoonstoneHub.config.getBoolean("noWitherTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled WitherSkull Explosion");
            }
            else if(kablooie.getEntity() instanceof Fireball && MoonstoneHub.config.getBoolean("noGhastTerrainDamage")){
                kablooie.blockList().clear();
                //debug.info("Cancelled Ghast Fireball Explosion");
            }
            else if(kablooie.getEntity() instanceof Wither && MoonstoneHub.config.getBoolean("noWitherTerrainDamage")) {
                kablooie.blockList().clear();
                //debug.info("Cancelled Wither Explosion");
            }
        }
        catch (Exception e)
        {
            debug.warning(e.getMessage());
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
            }

        }
        catch (Exception e)
        {
            debug.warning(e.getMessage());
        }
    }
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent damageEvent)
    {
        try
        {
            if(damageEvent.getEntityType().equals(EntityType.ARMOR_STAND) && damageEvent.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && MoonstoneHub.config.getBoolean("noCreeperTerrainDamage"))
            {
                damageEvent.setCancelled(true);
                //debug.info("Cancelled damageEvent");
            }
        }
        catch (Exception e)
        {
            debug.warning(e.getMessage());
        }
    }
    @EventHandler
    public void HangingBreakEvent(HangingBreakEvent breakEvent)
    {
        try
        {
            if(breakEvent.getCause().equals(HangingBreakEvent.RemoveCause.EXPLOSION) && MoonstoneHub.config.getBoolean("noCreeperTerrainDamage"))
            {
                breakEvent.setCancelled(true);
            }
        }
        catch (Exception e)
        {
            debug.warning(e.getMessage());
        }
    }
}
