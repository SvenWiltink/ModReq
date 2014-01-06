package me.sw123.modreq.query.insert;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.UpdateQuery;
import me.sw123.modreq.query.select.TicketExistsQuerry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InsertCommentQuery extends UpdateQuery{
	
	private static String q = "INSERT INTO comment (ticket,commenter,comment) VALUES (?,?,?)";
	public InsertCommentQuery(int ticket, Player p, String comment, Runnable post) {
		super(q, new Object[]{ticket,p.getName(),comment}, post);
		TicketExistsQuerry q = new me.sw123.modreq.query.select.TicketExistsQuerry(ticket,null);
		ModReq.getDataBase().runQueryNow(q);
		boolean exists = q.ticketExists();
		if(!exists){
			p.sendMessage(ChatColor.RED + "No ticket with such an id exists");
			this.setCancelled(true);
		}
	}

	@Override
	public void onComplete() {
	}
	

}
