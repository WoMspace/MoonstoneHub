package net.womspace.moonstonehub;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.util.Vector;

@SuppressWarnings("ConstantConditions")
public class MoonstoneHub extends JavaPlugin implements PluginMessageListener {

    protected static FileConfiguration config;

    @Override
    public void onEnable() {
        //when the server starts the plugin
        config = getConfig();
        config.addDefault("hubWorld", "hub");
        config.addDefault("hubOnStop", true);
        config.addDefault("joinHubAtLocation", false);
        config.addDefault("hubJoinLocation", new Vector(0,0,0));
        config.addDefault("overwriteMsg", true);
        config.addDefault("noCreeperTerrainDamage", true);
        config.addDefault("noWitherTerrainDamage", true);
        config.addDefault("noGhastTerrainDamage", true);
        config.addDefault("preventEndermanMoveBlocks", true);
        config.addDefault("preventRavagerDestruction", true);
        config.addDefault("useMapCommand", true);
        config.addDefault("mapURL", "http://moonstone.womspace.net:8888/");
        config.addDefault("giveSpeedInHub", false);
        config.options().copyDefaults(true);
        saveConfig();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        this.getCommand("hub").setExecutor(new PlayerToHub());
        this.getCommand("stop").setExecutor(new MoveOnStop());

        this.getCommand("map").setExecutor(new mapCommand());

        this.getCommand("msg").setExecutor(new OverwriteMsg());
        this.getCommand("whisper").setExecutor(new OverwriteMsg());
        this.getCommand("tell").setExecutor(new OverwriteMsg());

        getServer().getPluginManager().registerEvents(new hubJoinTeleport(), this);
        getServer().getPluginManager().registerEvents(new PreventMobTerrainDamage(), this);
        getServer().getPluginManager().registerEvents(new hubSpeed(), this);
        //todo: auto server map backup and restart at midnight gmt, kicks players to hub
        //todo: notHere: perhaps a 'banned' role in the server discord which will be given to players who have synced their discord to their mc but have been banned.
        //todo: notHere: legacy requires account syncing to access?
        //todo: notHere: a method to set people's discord nicknames in the discord server to their mc usernames
        //todo: notHere: the possibility for the dynmap to show certain worldguard regions
    }
    @Override
    public void onDisable(){
        //when the server stops and disables all plugins
    }
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message)
    {//something-something bungeecord message channels
        if(!channel.equals("BungeeCord"))
        {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
    }
}
