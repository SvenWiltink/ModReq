package me.sw123.modreq.ticket;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

public class Ticket {
	
	private int id;
	private Status status;
	private String submitter;
	private String message;
	private Timestamp time;
	private Priority priority;
	
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	private Staff staff;
	private Location location;
	
	public Ticket(int id, Status status, String submitter, String message, Timestamp time, Priority priority,
					Staff staff, Location location){
		this.id = id;
		this.status = status;
		this.submitter = submitter;
		this.message = message;
		this.time = time;
		this.priority = priority;
		this.staff = staff;
		this.location = location;
	}
	public void printSummaray(CommandSender sender){
		StringBuilder sb = new StringBuilder();
		sb.append(ChatColor.GOLD + "#" + id + " ");
		sb.append(new SimpleDateFormat("dd HH:mm").format(time));
		sb.append((Bukkit.getPlayerExact(submitter) == null ? ChatColor.RED : ChatColor.GREEN) + submitter + " - ");
		int left = 53 - sb.length();
		String temp = message;
		if(temp.length() > left){
			temp = message.substring(0, left - 3) + "...";
		}
		sb.append(temp);
		sender.sendMessage(sb.toString());
	}
	public int getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public String getSubmitter() {
		return submitter;
	}

	public String getMessage() {
		return message;
	}

	public Timestamp getTime() {
		return time;
	}

	public Priority getPriority() {
		return priority;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public Staff getStaff() {
		return staff;
	}

	public Location getLocation() {
		return location;
	}
	public void addComment(Comment c){
		comments.add(c);
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public void setComments(ArrayList<Comment> c){
		this.comments = c;
	}
		
}
