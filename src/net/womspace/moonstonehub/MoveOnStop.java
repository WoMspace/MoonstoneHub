package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MoveOnStop implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if(MoonstoneHub.config.getBoolean("hubOnStop") && !Bukkit.getServer().getName().equals(MoonstoneHub.config.getString("hubWorld")))
            {
                //get a list of all players
                Player[] onlinePlayers = new Player[Bukkit.getOnlinePlayers().toArray().length];
                Bukkit.getOnlinePlayers().toArray(onlinePlayers);
                sender.sendMessage("Moving all players to the hubworld.");
                for (Player player : onlinePlayers) {//move all players to the hubworld
                    PlayerToHub.movePlayer(player);
                }
            }

            //actually stop the server xd
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String stopServerCommand = "minecraft:stop";
            Bukkit.dispatchCommand(console, stopServerCommand);

            return true;
        }
        catch (Exception e)
        {
            sender.sendMessage("Could not move all players to the hubworld.");
            return false;
        }
    }
}