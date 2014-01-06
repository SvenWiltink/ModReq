package me.sw123.modreq.query.insert;

import me.sw123.modreq.query.UpdateQuery;

import org.bukkit.entity.Player;

public class InsertTicketQuery extends UpdateQuery{
	private static String q = 	"insert into ticket (submitter,message,world,locx,locy,locz,pitch,yaw) " +
									"values (?,?,?,?,?,?,?,?)";
	public InsertTicketQuery(Player p, String message, Runnable post) {
		super(q, playerMessageToArray(p,message), post);
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
	}

}
