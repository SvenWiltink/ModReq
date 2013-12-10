package me.sw123.modreq.querry;

import java.sql.Connection;
import java.sql.SQLException;

public interface IQuerry {

	public void execute(Connection c) throws SQLException;
	
	public void onComplete();
}
