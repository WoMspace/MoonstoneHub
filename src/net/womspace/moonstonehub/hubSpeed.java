package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import static org.bukkit.potion.PotionEffectType.SPEED;

public class hubSpeed implements Listener {
    @EventHandler
    public void givePlayersSpeed(PlayerJoinEvent playerJoin)
    {
        try
        {
            playerJoin.getPlayer().sendMessage(playerJoin.getPlayer().getWorld().getName());
            if(MoonstoneHub.config.getBoolean("giveSpeedInHub") && playerJoin.getPlayer().getWorld().getName().equals(MoonstoneHub.config.getString("hubWorld")))
            {
                playerJoin.getPlayer().addPotionEffect(new PotionEffect(SPEED, 10000, 3, false, false));
            }
        }
        catch (Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
}
