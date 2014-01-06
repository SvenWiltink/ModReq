package me.sw123.modreq.runnable;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InformPlayerTicketSubmit implements Runnable{

	private Player p;
	public InformPlayerTicketSubmit(Player p) {
		this.p = p;
	}
	@Override
	public void run() {
		p.sendMessage(ChatColor.GREEN + "Your ticket has been submitted");
	}

}
