package mega;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class mega extends JavaPlugin implements Listener {

	ArrayList<Player> i = new ArrayList<Player>(); // changed generics, but
													// storing a player instance
													// should not be done!! Or
													// at least remove them when
													// they log off

	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if ((cmd.getName().equalsIgnoreCase("set") && sender instanceof Player)) {
			Player p = (Player) sender;
			if (p.hasPermission("dg.admin")) {
				if (args.length == 2) {
					if (args[0].equals("schlecht")) {
						getConfig().set("item.schlecht." + args[1],
								p.getItemInHand());
					} else if (args[0].equals("mittel")) {// changed else if
						getConfig().set("item.mittel." + args[1],
								p.getItemInHand());
					} else if (args[0].equals("gut")) {// changed else if
						getConfig().set("item.gut." + args[1],
								p.getItemInHand());
					}
				}
			}
			return true;
		}
		if ((cmd.getName().equalsIgnoreCase("DG"))
				&& ((sender instanceof Player))) {
			Player p = (Player) sender;
			if (p.hasPermission("dg.user")) {
				p.performCommand("warp sg");
				i.remove(p);// wtf, remove and add :P
				i.add(p);
				if (this.i.size() == 1)
					Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
						int number = 90;

						public void run() {
							if (this.number != 0) {
								for (Player pi : mega.this.i)
									if (pi.equals(mega.this.i.get(0))) {
										pi.setLevel(this.number);
										pi.setNoDamageTicks(this.number + 60);
										this.number -= 1;
									}
								if (this.number == 0) {
									if (mega.this.i.size() >= 4) {
										for (Player p : mega.this.i) {
											if (p.equals(mega.this.i.get(0))) {
												p.sendMessage(ChatColor.BLUE
														+ "Das Spiel ist Gestartet");
											}
											Player p1 = (Player) mega.this.i
													.get(0);
											p1.performCommand("warp red");
											Player p1gs = (Player) mega.this.i
													.get(1);
											p1gs.performCommand("warp red");
											Player p1gf = (Player) mega.this.i
													.get(2);
											p1gf.performCommand("warp blue");
											Player p1tr = (Player) mega.this.i
													.get(3);
											p1tr.performCommand("warp blue");
											Player p421 = (Player) mega.this.i
													.get(4);
											p421.performCommand("warp white");
											Player p12 = (Player) mega.this.i
													.get(5);
											p12.performCommand("warp white");
											Player pq1 = (Player) mega.this.i
													.get(6);
											pq1.performCommand("warp gold");
											Player pq = (Player) mega.this.i
													.get(7);
											pq.performCommand("warp gold");
										}
									}

									if (mega.this.i.size() <= 3)
										for (Player pi : mega.this.i) {
											pi.performCommand("hub");
											pi.sendMessage(ChatColor.BLUE
													+ "Zu wenige Spieler");
										}
								}
							}
						}
					}, 20L, 60L);
			}
			return true;
		}
		return false;
	}

	private String args(int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEmptySlots(Inventory inventory) {
		int i = 0;
		for (ItemStack is : inventory.getContents()) {
			if (is == null)
				continue;
			i++;
		}
		return i;
	}

	@EventHandler
	public void onIn(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player p = e.getPlayer();
			if (i.contains(p)) {
				if (e.getClickedBlock().equals(Material.CHEST)) {
					if (getEmptySlots(p.getInventory()) >= 4) {
						Random r = new Random();
						double i = 1 + (3 - 1) * r.nextDouble();
						if (i == 1) {
							double x = 1 + (12 - 1) * r.nextDouble();
							double z = 1 + (12 - 1) * r.nextDouble();
							double y = 1 + (12 - 1) * r.nextDouble();
							int c = this.getConfig()
									.getInt("item.schlecht" + x);
							int d = this.getConfig()
									.getInt("item.schlecht" + y);
							int b = this.getConfig()
									.getInt("item.schlecht" + z);
							p.getInventory().addItem(new ItemStack[c]);
							p.getInventory().addItem(new ItemStack[d]);
							p.getInventory().addItem(new ItemStack[b]);
						}// forgot [b] I guess
						if (i == 2) {
							double x = 1 + (12 - 1) * r.nextDouble();
							double z = 1 + (12 - 1) * r.nextDouble();
							double y = 1 + (12 - 1) * r.nextDouble();
							int c = this.getConfig().getInt("item.mittel" + x);
							int d = this.getConfig().getInt("item.mittel" + y);
							int b = this.getConfig().getInt("item.mittelt" + z);
							p.getInventory().addItem(new ItemStack[c]);
							p.getInventory().addItem(new ItemStack[d]);
							p.getInventory().addItem(new ItemStack[b]);
						}// forgot [b] I guess
						if (i == 3) {
							double x = 1 + (12 - 1) * r.nextDouble();
							double z = 1 + (12 - 1) * r.nextDouble();
							double y = 1 + (12 - 1) * r.nextDouble();
							int c = this.getConfig().getInt("item.mittel" + x);
							int d = this.getConfig().getInt("item.mittel" + y);
							int b = this.getConfig().getInt("item.mittel" + z);
							p.getInventory().addItem(new ItemStack[c]);
							p.getInventory().addItem(new ItemStack[d]);
							p.getInventory().addItem(new ItemStack[b]);
						}// forgot [b] I guess
					} else {
						p.sendMessage(ChatColor.RED
								+ "Du brauchst mindestens 4 lehre Slots");
					}
				}
			}
		}
	}

	private void loadConfig() {
		FileConfiguration fc = this.getConfig();
		fc.options().copyDefaults(true);
		this.saveConfig();
	}
}