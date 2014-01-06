package me.sw123.modreq.query;

import java.sql.Connection;
import java.sql.SQLException;

public interface IQuery {

	public void execute(Connection c) throws SQLException;
	
	public void onComplete() throws SQLException;
	
	public void runPost();
}
