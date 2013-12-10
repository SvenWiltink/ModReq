package me.sw123.modreq.querry.select;

import java.sql.SQLException;

import me.sw123.modreq.querry.ResultQuerry;

import org.bukkit.Bukkit;

public class SelectAllQuerry extends ResultQuerry{
	static String q = "SELECT * " +
			"FROM ticket " +
			"WHERE staff IN " + "(SELECT id " +
								"FROM staff " +
								"WHERE name = ?)";
	public SelectAllQuerry() {
		super(q, new String[]{"Sgt_Tailor"});				
	}
	@Override
	public void onComplete() {
		Bukkit.broadcastMessage("querry complete");
		try {
			while(getResult().next()){
				String i = getResult().getInt(1) + "";
				Bukkit.broadcastMessage(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
