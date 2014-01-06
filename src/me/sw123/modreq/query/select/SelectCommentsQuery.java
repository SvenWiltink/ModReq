package me.sw123.modreq.query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import me.sw123.modreq.query.ResultQuery;
import me.sw123.modreq.ticket.Comment;

public class SelectCommentsQuery extends ResultQuery{
	
	private ArrayList<Comment> res = new ArrayList<Comment>();
	private static String q = "SELECT commenter, comment, date FROM comment" +
												" WHERE comment.ticket = ?";
	public SelectCommentsQuery(int id, Runnable post) {
		super(q, new String[]{Integer.toString(id)}, post);
		
	}
	@Override
	public void onComplete() throws SQLException {
		ResultSet result = this.getResult();
		while(result.next()){
			String commenter = result.getString(1);
			String comment = result.getString(2);
			Timestamp time = result.getTimestamp(3);
			Comment c = new Comment(commenter, comment, time);
			res.add(c);
		}
	}
	
	public ArrayList<Comment> getComments(){
		return res;
	}
}
