package me.sw123.modreq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import me.sw123.modreq.query.IQuery;
import me.sw123.modreq.query.QueryQue;
import me.sw123.modreq.query.StaticQueryManager;
import me.sw123.modreq.query.StaticQuery.QueryType;


public class DataBase extends Thread{
	private Connection conn;
	private boolean enabled = true;
	private QueryQue que;
	public DataBase(Settings settings){
        try {
        	que = new QueryQue();
        	if(settings.DATABASE_USEMYSQL){
	        	String ip = settings.DATABASE_MYSQL_IP;
	        	String port = settings.DATABASE_MYSQL_PORT;
	        	String database = settings.DATABASE_MYSQL_DATABASE;
	        	String user = settings.DATABASE_MYSQL_USER;
	        	String pass = settings.DATABASE_MYSQL_PASSWORD;
	        	String link = "jdbc:mysql://"+ ip + ":" + port + "/" + database;
				conn = DriverManager.getConnection(link, user, pass);
        	}else{
        		conn = DriverManager.getConnection("jdbc:sqlite:plugins/ModReq/DataBase.sql");
        	}
        	this.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}
	public void runQueryNow(IQuery q){
		try {
			que.runQueryNow(conn, q);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		createTables();
		while(enabled){
			if(que.hasNext()){
				try {
					que.executeNext(conn);
				} catch (SQLException e) {
					Logger.getLogger("minecraft").severe("An error occurred while executing a query, thus skipping it");
					que.removeFirst();
					e.printStackTrace();
					try {
						DataBase.sleep(1000);
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
	}
	
	private void createTables() {
		que.addQueryToQue(StaticQueryManager.getQuery(QueryType.CREATEDB));
	}
	public void addQueryToQue(IQuery q){
		que.addQueryToQue(q);
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
