package me.sw123.modreq;

import me.sw123.modreq.commands.ModReqCommand;
import me.sw123.modreq.commands.TicketCommand;
import me.sw123.modreq.query.StaticQuery.QueryType;
import me.sw123.modreq.query.StaticQueryManager;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ModReq extends JavaPlugin implements Listener, CommandExecutor{
	private static DataBase db;
	private static StaticQueryManager qm;
	public void onEnable() {
		initqueryManager();
		initDataBase();
		this.getServer().getPluginManager().registerEvents(this, this);
		getCommand("modreq").setExecutor(new ModReqCommand());
		getCommand("ticket").setExecutor(new TicketCommand());
	}

	private void initqueryManager() {
		qm = new StaticQueryManager();
		StaticQueryManager.addQuery(QueryType.CREATEDB, this.getResource("createModReq.sql"));
	}

	private void initDataBase() {
		String ip = "localhost";
		String database = "modreq";
		String port = "3306";
		String user = "ModReq";
		String pass = "KoEkJe5991";
		db = new DataBase(ip, port,database, user, pass);
	}
	public static StaticQueryManager getqueryManager(){
		return qm;
	}
	public static DataBase getDataBase(){
		return db;
	}
}
