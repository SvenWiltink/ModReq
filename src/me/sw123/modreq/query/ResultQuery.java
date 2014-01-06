package me.sw123.modreq.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ResultQuery implements IQuery, Cancellable{

	private String querry;
	private Object[] values;
	private ResultSet res;
	private Runnable post;
	private boolean cancelled;
	public ResultQuery(String querry, Object[] values, Runnable post){
		this.querry = querry;
		this.values = values;
		cancelled = false;
	}
	public final void execute(Connection conn)throws SQLException{
		if(!cancelled){
			PreparedStatement stat = conn.prepareStatement(querry);
			for(int i = 1; i<=values.length; i++){
				stat.setObject(i, values[i-1]);
			}
			stat.addBatch();
			res = stat.executeQuery();
		}
	}
	@Override
	public final boolean isCancelled() {
		return cancelled;
	}
	@Override
	public final void setCancelled(boolean a) {
		this.cancelled = a;
	}
	public final ResultSet getResult(){
		return res;
	}
	public final void runPost(){
		if(post != null)
		post.run();
	}
}
