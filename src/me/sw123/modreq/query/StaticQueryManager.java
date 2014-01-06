package me.sw123.modreq.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.StaticQuery.QueryType;

public class StaticQueryManager {

	private static HashMap<QueryType,StaticQuery> querries = new HashMap<QueryType,StaticQuery>();
	
	public static void addQuery(QueryType name, InputStream is){
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
			reader.close();
			String[] query = sb.toString().split(";");
			for(int i=0; i < query.length; i++){
				query[i] = query[i].trim();
			}
			StaticQuery q = new StaticQuery(name, query);
			querries.put(name, q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean executeQuery(QueryType type, Connection conn) throws SQLException{
		StaticQuery q = querries.get(type);
		if(q!=null){
			ModReq.getDataBase().addQueryToQue(q);
			return true;
		}
		return false;
	}
	public static StaticQuery getQuery(QueryType type){
		return querries.get(type);
	}
}
