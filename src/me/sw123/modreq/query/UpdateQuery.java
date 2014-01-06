package me.sw123.modreq.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateQuery implements IQuery, Cancellable{
	
	private String querry;
	private Object[] values;
	private boolean cancelled;
	private Runnable post;
	public UpdateQuery(String querry, Object[] values, Runnable post){
		this.querry = querry;
		this.values = values;
		this.post = post;
	}
	public final void execute(Connection conn)throws SQLException{
		if(!cancelled){
			PreparedStatement stat = conn.prepareStatement(querry);
			for(int i = 1; i<=values.length; i++){
				stat.setObject(i, values[i-1]);
			}
			stat.addBatch();
			stat.executeUpdate();
		}
	}
	@Override
	public final void setCancelled(boolean a) {
		this.cancelled = a;
	}
	@Override
	public final boolean isCancelled() {
		return cancelled;
	}
	public final void runPost(){
		if(post != null)
		post.run();
	}
	
}
