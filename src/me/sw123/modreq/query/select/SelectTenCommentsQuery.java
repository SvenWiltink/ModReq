package me.sw123.modreq.query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import me.sw123.modreq.query.ResultQuery;
import me.sw123.modreq.ticket.Comment;

public class SelectTenCommentsQuery extends ResultQuery{
	
	private ArrayList<Comment> res = new ArrayList<Comment>();
	private static String q = "SELECT ticket, commenter, comment, date FROM comment" +
												" WHERE id IN (SELECT ticket.id" +
																	" FROM ticket" +
																	" OUTER JOIN status ON status.id = ticket.status" +
																	" OUTER JOIN priority ON priority.id = ticket.priority" +
																	" OUTER JOIN staff ON ticket.staff = staff.id" +
																	" WHERE status = ?" +
																	" ORDER BY ticket.id" +
																	" LIMIT 10)";
	public SelectTenCommentsQuery(int status,Runnable post) {
		super(q, new String[]{Integer.toString(status)}, post);
		
	}
	@Override
	public void onComplete() throws SQLException {
		ResultSet result = this.getResult();
		while(result.next()){
			int ticket = result.getInt(1);
			String commenter = result.getString(2);
			String comment = result.getString(3);
			Timestamp time = result.getTimestamp(4);
			Comment c = new Comment(ticket, commenter, comment, time);
			res.add(c);
		}
	}
	
	public ArrayList<Comment> getComments(){
		return res;
	}
	public ArrayList<Comment> getComment(int ticket){
		ArrayList<Comment> result = new ArrayList<Comment>();
		for(Comment comment : getComments()){
			if(comment.getTicket() == ticket){
				result.add(comment);
			}
		}
		return result;
	}
}
