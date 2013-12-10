package me.sw123.modreq.querry.insert;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.sw123.modreq.querry.UpdateQuerry;

public class InsertTicketQuerry extends UpdateQuerry{
	private static String q = 	"insert into ticket (submitter,message,world,locx,locy,locz,pitch,yaw) " +
									"values (?,?,?,?,?,?,?,?)";
	private Player p;
	public InsertTicketQuerry(Player p, String message) {
		super(q, playerMessageToArray(p,message));
		this.p = p;
	}

	private static Object[] playerMessageToArray(Player p, String message) {
		Object[] res = new Object[8];
		res[0] = p.getName();
		res[1] = message;
		res[2] = p.getLocation().getWorld().getName();
		res[3] = p.getLocation().getX();
		res[4] = p.getLocation().getY();
		res[5] = p.getLocation().getZ();
		res[6] = p.getLocation().getPitch();
		res[7] = p.getLocation().getYaw();
		return res;
	}

	@Override
	public void onComplete() {
		if(p.isOnline()){
			p.sendMessage(ChatColor.GREEN + "Ticket submitted");
		}
	}

}
