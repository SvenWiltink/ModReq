package me.sw123.modreq.querry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.querry.StaticQuerry.QuerryType;

public class StaticQuerryManager {

	private static HashMap<QuerryType,StaticQuerry> querries = new HashMap<QuerryType,StaticQuerry>();
	
	public static void addQuerry(QuerryType name, InputStream is){
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
			reader.close();
			String[] querry = sb.toString().split(";");
			for(int i=0; i < querry.length; i++){
				querry[i] = querry[i].trim();
			}
			StaticQuerry q = new StaticQuerry(name, querry);
			querries.put(name, q);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  static boolean executeQuerry(QuerryType type, Connection conn) throws SQLException{
		StaticQuerry q = querries.get(type);
		if(q!=null){
			ModReq.getDataBase().addQuerryToQue(q);
			return true;
		}
		return false;
	}
	public static StaticQuerry getQuerry(QuerryType type){
		return querries.get(type);
	}
}
