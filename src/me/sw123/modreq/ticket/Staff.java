package me.sw123.modreq.ticket;

public class Staff {

	private int id;
	private String name;
	private Rank rank;
	
	public Staff(int id, String name, Rank rank){
		this.id = id;
		this.name = name;
		this.rank = rank;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Rank getRank() {
		return rank;
	}
}
