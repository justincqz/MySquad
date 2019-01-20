package io.github.justincqz.MySquad;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MySquad extends JavaPlugin {
  @Override
  public void onEnable() {
    // TODO Insert logic to be performed when the plugin is enabled
    getLogger().info("onEnable has been invoked!");
  }

  @Override
  public void onDisable() {
    // TODO Insert logic to be performed when the plugin is disabled
    getLogger().info("onDisable has been invoked!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("MySquad")
        || command.getName().equalsIgnoreCase("mys")) {
      CustomNPC newNPC = new CustomNPC(((CraftWorld)((Player) sender).getWorld()).getHandle());
      newNPC.spawnCustomEntity(((Player) sender).getLocation());
      return true;
    }
    return false;
  }
}