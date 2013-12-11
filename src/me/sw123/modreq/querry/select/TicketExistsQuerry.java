package me.sw123.modreq.querry.select;

import java.sql.SQLException;

import me.sw123.modreq.querry.ResultQuerry;

public class TicketExistsQuerry extends ResultQuerry{

	boolean ticketExists = false;
	private static String q = "SELECT id FROM ticket WHERE id = ?";
	public TicketExistsQuerry(int ticket) {
		super(q, new Object[]{ticket});
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
