package me.sw123.modreq.querry;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.bukkit.Bukkit;

public class StaticQuerry implements IQuerry{
	
	private ArrayList<String> Querry;
	private QuerryType type;
	public StaticQuerry(QuerryType t, String[] q){
		type = t;
		Querry = new ArrayList<String>();
		for(int i = 0; i < q.length; i++){
			if(q[i] != null && !q[i].equals("")){
				Querry.add(q[i]);
			}
		}
	}
	public void execute(Connection conn) throws SQLException{
		Statement stat = conn.createStatement();
		for(String q : Querry){
			stat.addBatch(q);
		}
		stat.executeBatch();
	}
	public enum QuerryType{
		CREATEDB("createDB", "Database created/updated");
		
		private String name;
		private String log;
		private QuerryType(String s,String l){
			name = s;
			log = l;
		}
		public String getName(){
			return name;
		}
	}
	@Override
	public void onComplete() {
		Bukkit.broadcastMessage(type.log);
	}
}
