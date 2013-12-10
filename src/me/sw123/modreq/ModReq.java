package me.sw123.modreq;

import me.sw123.modreq.querry.StaticQuerry.QuerryType;
import me.sw123.modreq.querry.StaticQuerryManager;
import me.sw123.modreq.querry.insert.InsertTicketQuerry;
import me.sw123.modreq.querry.select.SelectAllQuerry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ModReq extends JavaPlugin implements Listener, CommandExecutor{
	private static DataBase db;
	private static StaticQuerryManager qm;
	public void onEnable() {
		initQuerryManager();
		initDataBase();
		this.getServer().getPluginManager().registerEvents(this, this);
		getCommand("modreq").setExecutor(this);
	}

	private void initQuerryManager() {
		qm = new StaticQuerryManager();
		StaticQuerryManager.addQuerry(QuerryType.CREATEDB, this.getResource("createModReq.sql"));
	}

	private void initDataBase() {
		String ip = "localhost";
		String database = "modreq";
		String port = "3306";
		String user = "root";
		String pass = "";
		db = new DataBase(ip, port,database, user, pass);
	}
	public static StaticQuerryManager getQuerryManager(){
		return qm;
	}
	public static DataBase getDataBase(){
		return db;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Bukkit.broadcastMessage("----Event-thrown----");
		SelectAllQuerry querry = new SelectAllQuerry();
		db.addQuerryToQue(querry);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		if(command.getName().equals("modreq")){
			if(sender instanceof Player){
				if(args.length > 0){
					Player p = (Player) sender;
					String message = "";
					for(int i = 0; i < args.length; i++){
						message += (i == 0 ? "" : " ") + args[i];
					}
					InsertTicketQuerry q = new InsertTicketQuerry(p, message);
					db.addQuerryToQue(q);
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "not enough arguments");
				}
			}
		}
		return false;
	}

}
