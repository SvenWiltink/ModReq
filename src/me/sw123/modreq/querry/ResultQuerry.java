package me.sw123.modreq.querry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultQuerry implements IQuerry{

	String querry;
	Object[] values;
	ResultSet res;
	public ResultQuerry(String querry, Object[] values){
		this.querry = querry;
		this.values = values;
	}
	public void execute(Connection conn)throws SQLException{
		PreparedStatement stat = conn.prepareStatement(querry);
		for(int i = 1; i<=values.length; i++){
			stat.setObject(i, values[i-1]);
		}
		stat.addBatch();
		res = stat.executeQuery();
	}
	
	public ResultSet getResult(){
		return res;
	}
}
