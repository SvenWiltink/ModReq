package me.sw123.modreq.query.select;

import java.sql.SQLException;

import me.sw123.modreq.query.ResultQuery;

public class TicketExistsQuerry extends ResultQuery{

	boolean ticketExists = false;
	private static String q = "SELECT id FROM ticket WHERE id = ?";
	public TicketExistsQuerry(int ticket, Runnable post) {
		super(q, new Object[]{ticket}, post);
	}
	@Override
	public void onComplete() {
		try {
			if(getResult().next())
				ticketExists = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean ticketExists(){
		return ticketExists;
	}

}
