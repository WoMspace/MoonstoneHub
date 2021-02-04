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
            if(sender.hasPermission("moonstonehub.msg"))
            {
                String msgCommand = "minecraft:msg";
                for(String concatString : args)
                {
                    msgCommand = msgCommand + " " + concatString;
                }
                Bukkit.dispatchCommand(sender,msgCommand);
            }
            else
            {
                sender.sendMessage("You do not have permission to perform this command");
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
