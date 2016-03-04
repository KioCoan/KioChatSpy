package kiochatspy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class KioChatSpy extends JavaPlugin implements Listener {

	@Override
	public void onEnable()
	{
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("KioChatSpy Enabled");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("KioChatSpy disabled");
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		if (e.getMessage().equals("legendchat"))
		{
			return;
		}
		getLogger().info(ChatColor.YELLOW + e.getPlayer().getName() + ": "+e.getMessage());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) 
	{
		if (!cmd.getName().equalsIgnoreCase("kcs"))
		{
			return false;
		}
		
		if (args.length == 0 || args.length == 1 && args[0].equalsIgnoreCase("help")) 
		{
			//HelpSection
			sender.sendMessage(ChatColor.AQUA + "========== KioChatSpy Help Section ===========");
			sender.sendMessage(ChatColor.AQUA + "não há comandos para este plugin nesta versão");
			return false;
		}
		
		return false;
	}
}
