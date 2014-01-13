package me.sw123.modreq.query.select;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.ResultQuery;
import me.sw123.modreq.ticket.Priority;
import me.sw123.modreq.ticket.Rank;
import me.sw123.modreq.ticket.Staff;
import me.sw123.modreq.ticket.Status;
import me.sw123.modreq.ticket.Ticket;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class SelectTenTicketsQuery extends ResultQuery{
	
private ArrayList<Ticket> res = null;
private SelectTenCommentsQuery commentsQuery;
private static String q = "SELECT ticket.id, ticket.submitter, ticket.message, ticket.date," +
											" world, locx, locy, locz, pitch, yaw, status.*, priority.* FROM ticket" +
											" OUTER JOIN status ON status.id = ticket.status" +
											" OUTER JOIN priority ON priority.id = ticket.priority" +
											" OUTER JOIN staff ON ticket.staff = staff.id" +
											" WHERE status = ?" +
											" ORDER BY ticket.id" +
											" LIMIT 10";
/**
 * Selects a ticket with the specified id
 * @param id
 */
public SelectTenTicketsQuery(int status, Runnable post) {
	super(q, new String[]{Integer.toString(status)}, post);
	commentsQuery = new SelectTenCommentsQuery(status,null);
	ModReq.getDataBase().addQueryToQue(commentsQuery);
	
}
@Override
public void onComplete() throws SQLException {
	ResultSet rs = this.getResult();
	while(rs.next()){
		int id = rs.getInt(1);
		String submitter = rs.getString(2);
		String message = rs.getString(3);
		Timestamp time = rs.getTimestamp(4);
		
		Location loc;
		String world = rs.getString(5);
		double x = rs.getDouble(6);
		double y = rs.getDouble(7);
		double z = rs.getDouble(8);
		float pitch = rs.getFloat(9);
		float yaw = rs.getFloat(10);
		
		try{
			World w = Bukkit.getWorld(world);
			loc = new Location(w, x, y, z, yaw, pitch);
		}catch(Exception e){
			Logger.getLogger("minecraft").severe("The world ' + " + world + "' does not exist");
			return;
		}
		
		Status status;
		int status_id = rs.getInt(11);
		String status_name = rs.getString(12);
		String status_desc = rs.getString(13);
		status = new Status(status_id, status_name, status_desc);
		
		Priority priority;
		int priority_id = rs.getInt(14);
		String priority_name = rs.getString(15);
		String priority_desc = rs.getString(16);
		priority = new Priority(priority_id, priority_name, priority_desc);
		
		Staff staff = new Staff(1,"derp",new Rank(1,"test",priority_id));
		Ticket t = new Ticket(id, status, submitter, message, time, priority, staff, loc);
		t.setComments(commentsQuery.getComment(id));
		res.add(t);
	}
}
public ArrayList<Ticket> getTickets(){
	return res;
}
}
