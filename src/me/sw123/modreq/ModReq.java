package me.sw123.modreq;

import me.sw123.modreq.commands.ModReqCommand;
import me.sw123.modreq.commands.TicketCommand;
import me.sw123.modreq.query.StaticQuery.QueryType;
import me.sw123.modreq.query.StaticQueryManager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ModReq extends JavaPlugin{
	private static DataBase db;
	private static StaticQueryManager qm;
	private static ModReq instance;
	private static Settings settings;
	public void onEnable() {
		instance = this;
		initqueryManager();
		initDataBase();
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
		String user = "root";
		String pass = "";
		db = new DataBase(ip, port,database, user, pass);
	}
	public static StaticQueryManager getQueryManager(){
		return qm;
	}
	public static DataBase getDataBase(){
		return db;
	}
	public static ModReq getInstance(){
		return instance;
	}
	public static Settings getSettings(){
		return settings;
	}
}
