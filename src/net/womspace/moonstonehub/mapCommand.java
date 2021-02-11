package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class mapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if(MoonstoneHub.config.getBoolean("useMapCommand")) {
                sender.sendMessage(MoonstoneHub.config.getString("mapURL"));
            }
            return true;
        }
        catch(Exception e)
        {
            Bukkit.getLogger().warning(e.getMessage());
            return false;
        }
    }
}
