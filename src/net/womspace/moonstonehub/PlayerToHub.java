package net.womspace.moonstonehub;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerToHub implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        try
        {
            if(sender instanceof Player)
            {//if a player runs /hub
                movePlayer((Player)sender);
            }
            else
            {//if the plugin is moving a player automatically
                movePlayer(Bukkit.getPlayer(args[0]));
            }
            return true;
        }
        catch (Exception e)
        {
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
