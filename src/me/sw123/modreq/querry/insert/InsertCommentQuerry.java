package me.sw123.modreq.querry.insert;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.querry.UpdateQuerry;
import me.sw123.modreq.querry.select.TicketExistsQuerry;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InsertCommentQuerry extends UpdateQuerry{
	
	private static String q = "INSERT INTO comment (ticket,commenter,comment) VALUES (?,?,?)";
	private Player p;
	public InsertCommentQuerry(int ticket, Player p, String comment) {
		super(q, new Object[]{ticket,p.getName(),comment});
		this.p = p;
		TicketExistsQuerry q = new TicketExistsQuerry(ticket);
		ModReq.getDataBase().runQuerryNow(q);
		boolean exists = q.ticketExists();
		if(!exists){
			p.sendMessage(ChatColor.RED + "No ticket with such an id exists");
			this.setCancelled(true);
		}
	}

	@Override
	public void onComplete() {
		if(p.isOnline()){
			p.sendMessage(ChatColor.GREEN + "Comment added");
		}
	}
	

}
