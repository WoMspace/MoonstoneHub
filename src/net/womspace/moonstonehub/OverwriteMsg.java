package net.womspace.moonstonehub;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class OverwriteMsg implements CommandExecutor {
    @SuppressWarnings("StringConcatenationInLoop")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if(MoonstoneHub.config.getBoolean("overwriteMsg"))
            {
                String msgCommand = "minecraft:msg";
                for(String concatString : args)
                {
                    msgCommand = msgCommand + " " + concatString;
                }
                Bukkit.dispatchCommand(sender,msgCommand);
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
