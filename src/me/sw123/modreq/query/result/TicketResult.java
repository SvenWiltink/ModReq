package me.sw123.modreq.query.result;

import java.util.ArrayList;
import java.util.List;

import me.sw123.modreq.ticket.Ticket;

public abstract class TicketResult implements Runnable{
	private List<Ticket> resultTickets;
	private boolean acceptsNew = true;
	public TicketResult(){
		resultTickets = new ArrayList<Ticket>();
		acceptsNew = false;
	}
	public TicketResult(List<Ticket> tickets){
		this.resultTickets = tickets;
		acceptsNew = false;
	}
	public void setResult(List<Ticket> tickets){
		if(acceptsNew){
			this.resultTickets = tickets;
			this.acceptsNew = false;
		}
	}
	public List<Ticket> getResult(){
		return resultTickets;
	}
}
