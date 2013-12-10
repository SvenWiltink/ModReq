package me.sw123.modreq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.sw123.modreq.querry.IQuerry;
import me.sw123.modreq.querry.QuerryQue;
import me.sw123.modreq.querry.StaticQuerry.QuerryType;
import me.sw123.modreq.querry.StaticQuerryManager;

import org.bukkit.Bukkit;


public class DataBase extends Thread{
	private Connection conn;
	private boolean enabled = true;
	private QuerryQue que;
	
	public DataBase(String ip, String port,String database, String user, String pass){
        try {
        	que = new QuerryQue();
        	String link = "jdbc:mysql://"+ ip + ":" + port + "/" + database;
        	Bukkit.broadcastMessage(link);
			conn = DriverManager.getConnection(link, user, pass);
        	this.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}
	
	@Override
	public void run() {
		Bukkit.broadcastMessage("Starting thread");
		createTables();
		while(enabled){
			if(que.hasNext()){
				Bukkit.broadcastMessage("Next querry found in que");
				try {
					que.executeNext(conn);
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						DataBase.sleep(1000000);
					} catch (InterruptedException er) {
						er.printStackTrace();
					}
					
				}
			}else{
				try {
					DataBase.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Bukkit.broadcastMessage("Thread disabled");
	}
	
	private void createTables() {
		que.addQuerryToQue(StaticQuerryManager.getQuerry(QuerryType.CREATEDB));
	}
	public void addQuerryToQue(IQuerry q){
		Bukkit.broadcastMessage("Adding querry to que");
		que.addQuerryToQue(q);
	}
	public void terminate(){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		enabled = false;
	}
}
