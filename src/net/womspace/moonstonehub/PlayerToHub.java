package net.womspace.moonstonehub;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PlayerToHub implements CommandExecutor {
    Logger logger = Bukkit.getLogger();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if(sender instanceof Player && sender.hasPermission("moonstonehub.hub"))
            {//if a player runs /hub
                movePlayer((Player)sender);
            }
            else if (sender.hasPermission("moonstonehub.hub"))
            {//if the plugin is moving a player automatically or the server is moving someone
                movePlayer(Bukkit.getPlayer(args[0]));
            }
            else
            {
                sender.sendMessage("You do not have permission to perform this command");
            }
            return true;
        }
        catch (Exception e)
        {
            logger.warning("PlayerToHub.java is NOT working");
            logger.warning(e.getMessage());
            return false;
        }
    }
    public static void movePlayer(Player target)
    {//move the target to the hub world
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(MoonstoneHub.config.getString("hubWorld"));
        target.sendPluginMessage(JavaPlugin.getProvidingPlugin(MoonstoneHub.class), "BungeeCord", out.toByteArray());
    }
}
