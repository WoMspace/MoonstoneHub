package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.logging.Logger;

public class hubJoinTeleport implements Listener
{
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event)
    {
        try
        {
            Player playerJoined = event.getPlayer();
            if (playerJoined.getWorld().getName().equals(MoonstoneHub.config.getString("hubWorld")) && MoonstoneHub.config.getBoolean("joinHubAtLocation")) {
                playerJoined.teleport(MoonstoneHub.config.getVector("hubJoinLocation").toLocation(playerJoined.getWorld()));
            }
        }
        catch (Exception e)
        {
            Logger consoleLogger = Bukkit.getLogger();
            consoleLogger.info("Couldn't teleport player to specified location.");
        }
    }
}
