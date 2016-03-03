package oi.github.kiocoan.kiochatspy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KioChatSpy extends JavaPlugin implements Listener {

	private boolean pluginEnabled;
	
	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		
		pluginEnabled = getConfig().getBoolean("PluginEnabled");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("KioChatSpy disabled");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		if (!pluginEnabled)
		{
			return;
		}
		getLogger().info(ChatColor.YELLOW + e.getPlayer().getName() + ": "+e.getMessage());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) 
	{
		if (!pluginEnabled || !cmd.getName().equalsIgnoreCase("kcs"))
		{
			return false;
		}
		
		if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase("help")) 
		{
			//HelpSection
			sender.sendMessage(ChatColor.AQUA + "========== KioChatSpy Help Section ===========");
			sender.sendMessage(ChatColor.AQUA + "/kiochatspy/kaf - Comando principal");
			sender.sendMessage(ChatColor.AQUA + "/kcs <enable/on> - Ativa o Plugin");
			sender.sendMessage(ChatColor.AQUA + "/kcs <disable/off> - Desliga o Plugin");
		}
		
		if (args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("enabled"))
		{
			pluginEnabled = true;
			getConfig().set("PluginEnabled", pluginEnabled);
			saveConfig();
			getLogger().info(ChatColor.AQUA + "Plugin ativado com sucesso.");
		}else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("disable"))
		{
			pluginEnabled = false;
			getConfig().set("PluginEnabled", pluginEnabled);
			saveConfig();
			getLogger().info(ChatColor.AQUA + "Plugin desligado com sucesso.");
		}else if(args[0].equalsIgnoreCase("reload"))
		{
			if (sender.hasPermission("kiochatspy.use")) {
				try {
					this.reloadConfig();
					getLogger().info("Plugin was reloaded with sucess");
				} catch (Exception ex) {
					sender.sendMessage(ChatColor.RED + "Failed to reload KioChatSpy");
					getLogger().info("Failed to reload plugin");
					ex.printStackTrace();
				}
			} 
		}
		return false;
	}
}
