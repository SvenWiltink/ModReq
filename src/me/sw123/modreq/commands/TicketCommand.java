package me.sw123.modreq.commands;

import java.util.InputMismatchException;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.insert.InsertCommentQuery;
import me.sw123.modreq.query.select.SelectTicketQuery;
import me.sw123.modreq.runnable.InformPlayerTicketSubmit;
import me.sw123.modreq.utils.SubCommandExecutor;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TicketCommand extends SubCommandExecutor{
	
	
	@command
	public void list(CommandSender sender, String[] args){
		try{
			int id = Integer.parseInt(args[0]);
			if(id > 0){
				SelectTicketQuery query = new SelectTicketQuery(id,null);
				ModReq.getDataBase().addQueryToQue(query);
			}else{
				sender.sendMessage(ChatColor.RED + "The ticket id must be greater than 0");
			}
		}catch(InputMismatchException e){
			sender.sendMessage(ChatColor.RED + "That is not a number");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@command
	public void show(CommandSender sender, String[] args){
		try{
			int id = Integer.parseInt(args[0]);
			if(id > 0){
				SelectTicketQuery query = new SelectTicketQuery(id,null);
				ModReq.getDataBase().addQueryToQue(query);
			}else{
				sender.sendMessage(ChatColor.RED + "The ticket id must be greater than 0");
			}
		}catch(InputMismatchException e){
			sender.sendMessage(ChatColor.RED + "That is not a number");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	@command(minimumArgsLength = 2, playerOnly = true)
	public void comment(CommandSender sender, String[] args){
			Player p = (Player) sender;
			String message = "";
			int ticket = Integer.parseInt(args[0]);
			for(int i = 1; i < args.length; i++){
				message += (i == 0 ? "" : " ") + args[i];
			}
			InsertCommentQuery q = new InsertCommentQuery(ticket, p, message, new InformPlayerTicketSubmit(p));
			ModReq.getDataBase().addQueryToQue(q);
	}

}
