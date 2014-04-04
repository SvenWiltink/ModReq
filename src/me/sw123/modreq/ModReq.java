package me.sw123.modreq;

import java.io.IOException;

import me.sw123.modreq.commands.ModReqCommand;
import me.sw123.modreq.commands.TicketCommand;
import me.sw123.modreq.query.StaticQuery.QueryType;
import me.sw123.modreq.query.StaticQueryManager;

import org.bukkit.plugin.java.JavaPlugin;

public class ModReq extends JavaPlugin{
	private static DataBase db;
	private static StaticQueryManager qm;
	private static ModReq instance;
	private static Settings settings;
	public void onEnable() {
		instance = this;
		initqueryManager();
		initConfig();
		initDataBase();
		getCommand("modreq").setExecutor(new ModReqCommand());
		getCommand("ticket").setExecutor(new TicketCommand());
	}
	public void onDisable(){
		try {
			settings.save();
		} catch (IllegalArgumentException | IllegalAccessException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initConfig() {
		settings = new Settings();
		try {
			settings.load();
		} catch (IllegalArgumentException
				| IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void initqueryManager() {
		qm = new StaticQueryManager();
		StaticQueryManager.addQuery(QueryType.CREATEDB, this.getResource("createModReq.sql"));
	}

	private void initDataBase() {
		db = new DataBase(settings);
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
