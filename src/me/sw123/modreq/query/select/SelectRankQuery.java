package me.sw123.modreq.query.select;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.sw123.modreq.query.ResultQuery;
import me.sw123.modreq.ticket.Rank;

public class SelectRankQuery  extends ResultQuery{
	
	private Rank res;
	private static String q = "SELECT rank.* FROM rank" +
												" JOIN staff on staff.rank = rank.id" +
												" WHERE staff.id = ?";
	public SelectRankQuery(int id, Runnable post) {
		super(q, new String[]{Integer.toString(id)}, post);
		
	}
	@Override
	public void onComplete() throws SQLException {
		ResultSet rs = this.getResult();
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString(2);
			int max_priority = rs.getInt(3);
			res = new Rank(id, name, max_priority);
		}
	}
	public Rank getRank(){
		return res;
	}


}
