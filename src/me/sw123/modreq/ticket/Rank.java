package me.sw123.modreq.ticket;

public class Rank {
	private int id;
	private String name;
	private int maxPriority;
	
	public Rank(int id, String name, int maxPriority){
		this.id = id;
		this.name = name;
		this.maxPriority = maxPriority;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getMaxPriority() {
		return maxPriority;
	}	
	
}
