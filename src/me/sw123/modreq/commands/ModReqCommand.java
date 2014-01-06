package me.sw123.modreq.commands;

import me.sw123.modreq.ModReq;
import me.sw123.modreq.query.insert.InsertTicketQuery;
import me.sw123.modreq.runnable.InformPlayerTicketSubmit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModReqCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equals("modreq")){
			if(sender instanceof Player){
				if(args.length > 0){
					Player p = (Player) sender;
					String message = "";
					for(int i = 0; i < args.length; i++){
						message += (i == 0 ? "" : " ") + args[i];
					}
					InsertTicketQuery q = new InsertTicketQuery(p, message, new InformPlayerTicketSubmit(p));
					ModReq.getDataBase().addQueryToQue(q);
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "not enough arguments");
				}
			}
			return true;
		}
		return false;
	}

}
