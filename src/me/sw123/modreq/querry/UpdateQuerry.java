package me.sw123.modreq.querry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UpdateQuerry implements IQuerry, Cancellable{
	private String querry;
	private Object[] values;
	private boolean cancelled;
	
	public UpdateQuerry(String querry, Object[] values){
		this.querry = querry;
		this.values = values;
	}
	public void execute(Connection conn)throws SQLException{
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
	public void setCancelled(boolean a) {
		this.cancelled = a;
	}
	@Override
	public boolean isCancelled() {
		return cancelled;
	}
	
}
