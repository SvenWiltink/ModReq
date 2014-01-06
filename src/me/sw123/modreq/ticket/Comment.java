package me.sw123.modreq.ticket;

import java.sql.Timestamp;

public class Comment {
	
	private String commenter;
	private String comment;
	private Timestamp date;
	
	public Comment(String commenter, String comment, Timestamp date){
		this.comment = comment;
		this.commenter = commenter;
		this.date = date;
	}
	
	public String getCommenter(){
		return commenter;
	}
	public String getComment(){
		return comment;
	}
	public Timestamp getDate(){
		return date;
	}

}
