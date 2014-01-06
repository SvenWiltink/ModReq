package me.sw123.modreq.query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StaticQuery implements IQuery{
	
	private ArrayList<String> Querry;
	private QueryType type;
	public StaticQuery(QueryType t, String[] q){
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
	public enum QueryType{
		CREATEDB("createDB", "Database created/updated");
		
		private String name;
		private String log;
		private QueryType(String s,String l){
			name = s;
			log = l;
		}
		public String getName(){
			return name;
		}
	}
	@Override
	public void onComplete() {
		System.out.println(type.log);
	}
	@Override
	public void runPost() {
	}
}
