package me.sw123.modreq.query.select;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.ResultQuery;
import me.sw123.modreq.ticket.Rank;
import me.sw123.modreq.ticket.Staff;

public class SelectStaffQuery extends ResultQuery{
	
	private Staff res;
	private SelectRankQuery rankQuery;
	private static String q = "SELECT staff.* FROM staff" +
												" JOIN ticket on staff.id = ticket.staff" +
												" WHERE ticket.id = ?";
	/**
	 * Selects the staff member from the ticket with the given id
	 * @param id
	 */
	public SelectStaffQuery(int id, Runnable post) {
		super(q, new String[]{Integer.toString(id)}, post);
		rankQuery = new SelectRankQuery(id, null);
		ModReq.getDataBase().addQueryToQue(rankQuery);
		
	}
	@Override
	public void onComplete() throws SQLException {
		ResultSet rs = this.getResult();
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Rank rank = rankQuery.getRank();
			res = new Staff(id, name, rank);
		}
	}
	public Staff getStaff(){
		return res;
	}

}
