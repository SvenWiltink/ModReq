package me.sw123.modreq.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {
	
	public static String join(String[] a, String delimiter, Integer startIndex) {
		try {
			Collection<String> s = Arrays.asList(a);
			StringBuffer buffer = new StringBuffer();
			Iterator<String> iter = s.iterator();

			while (iter.hasNext()) {
				if (startIndex == 0) {
					buffer.append(iter.next());
					if (iter.hasNext()) {
						buffer.append(delimiter);
					}
				} else {
					startIndex--;
					iter.next();
				}
			}

			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String join(List<String> a, String delimiter, Integer startIndex) {
		try {
			StringBuffer buffer = new StringBuffer();
			Iterator<String> iter = a.iterator();

			while (iter.hasNext()) {
				if (startIndex == 0) {
					buffer.append(iter.next());
					if (iter.hasNext()) {
						buffer.append(delimiter);
					}
				} else {
					startIndex--;
					iter.next();
				}
			}

			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String[] stripArray(String[] array){
		ArrayList<String> arguments=new ArrayList<String>();
		try{
		boolean b=false;
		for(String s:array){
			if(!b){b=true;continue;}
			arguments.add(s);
		}
		}catch(Exception e){}
		
		return arguments.toArray(new String[arguments.size()]);
	}
	
	public static String sit(String iStr, char delimiter, int part) {
		if (part == 0) {
			if (!iStr.contains(String.valueOf(delimiter)))
				return iStr;
		} else {
			if (!iStr.contains(String.valueOf(delimiter)))
				return "";
		}
		if (part == 0)
			return iStr.substring(0, (iStr.indexOf(delimiter, 0)));
		return iStr.substring(iStr.indexOf(delimiter, 0) + 1, iStr.length());
	}
	public static Location getLocationFromString(String loc){
		try{
		String w = loc.split("w=")[1].split(",")[0];
		World world = Bukkit.getWorld(w);
		double x = Double.valueOf(loc.split("x=")[1].split(",")[0]);
		double y = Double.valueOf(loc.split("y=")[1].split(",")[0]);
		double z = Double.valueOf(loc.split("z=")[1].split(",")[0]);
		float pitch = Float.valueOf(loc.split("pitch=")[1].split(",")[0]);
		float yaw = Float.valueOf(loc.split("yaw=")[1].split(",")[0]);
		Location l = new Location(world, x, y, z);
		l.setPitch(pitch);
		l.setYaw(yaw);
		return l;
		}catch(Exception e){
			return null;
		}
	}
	public static String locationToString(Location l){
		if(l!=null){
		String w = l.getWorld().getName();
		String x = Double.toString(l.getX());
		String y = Double.toString(l.getY());
		String z = Double.toString(l.getZ());
		String pitch = Float.toString(l.getPitch());
		String yaw = Float.toString(l.getYaw());
		String location = "w=" + w + ",x=" + x + ",y=" + y + ",z=" + z + ",pitch=" + pitch + ",yaw=" + yaw;
		return location;
		}
		return null;
	}
	public static ConfigurationSection itemToSection(ConfigurationSection cs, ItemStack item){
		if(item.getItemMeta().hasDisplayName());
		String name = item.getItemMeta().getDisplayName();
		List<String> lore = item.getItemMeta().getLore();
		Material id = item.getType();
		int amount = item.getAmount();
		int dura = item.getDurability();
		cs.set("name", name);
		cs.set("id", id);
		cs.set("amount", amount);
		cs.set("durability", dura);
		cs.set("lore", lore);
		ConfigurationSection enchants = cs.createSection("enchants");
		for(Enchantment ench : item.getItemMeta().getEnchants().keySet()){
			String enchName = ench.getName();
			int lvl = item.getEnchantmentLevel(ench);
			enchants.set(enchName, lvl);
		}
		return cs;
	}
	public static ItemStack SectionToItem(ConfigurationSection cs){
		if(cs.contains("name"));
		String name = cs.getString("name");
		String id = cs.getString("id");
		int amount = cs.getInt("amount");
		short dura = (short) cs.getInt("durability");
		List<String> lore = cs.getStringList("lore");
		Map<Enchantment,Integer> enchants = SectionToEnchants(cs.getConfigurationSection("enchants"));
		ItemStack item = new ItemStack(Material.valueOf(id));
		item.setAmount(amount);
		item.setDurability(dura);
		item.getItemMeta().setDisplayName(name);
		for(Enchantment ench : enchants.keySet()){
			int lvl = enchants.get(ench);
			item.addEnchantment(ench, lvl);
		}
		item.getItemMeta().setLore(lore);
		return item;
	}
	private static Map<Enchantment, Integer> SectionToEnchants(ConfigurationSection cs) {
		Map<Enchantment, Integer> enchs = new HashMap<Enchantment, Integer>();
		for(String key : cs.getKeys(false)){
			Enchantment ench = Enchantment.getByName(key);
			int lvl = cs.getInt(key);
			enchs.put(ench, lvl);
		}
		return enchs;
	}
	public static Inventory sectionToInventory(ConfigurationSection cs){
		String title = cs.getString("title");
		ItemStack[] contents = sectionToItemStackArray(cs.getConfigurationSection("contents"));
		Inventory inv = Bukkit.createInventory(null, contents.length, title);
		return inv;
	}
	public static ConfigurationSection inventoryToSection(ConfigurationSection cs, Inventory inv){
		String name = inv.getName();
		String title = inv.getTitle();
		cs.set("name", name);
		cs.set("title", title);
		ConfigurationSection cSection = cs.createSection("contents");
		int i = 0;
		for(ItemStack item : inv.getContents()){
			ConfigurationSection iSection = cSection.createSection(Integer.toString(i));
			if(item == null){
				cSection.set(Integer.toString(i), "emty");
			}
			else{
			itemToSection(iSection, item);
			}
			i++;
		}
		return cs;
	}
	public static ItemStack[] sectionToItemStackArray(ConfigurationSection cs){
		Set<String> keys = cs.getKeys(false);
		ItemStack[] items = new ItemStack[keys.size()];
		int i = 0;
		for(String key : keys){
			if(cs.getString(key) != "emty"){
				ItemStack item = SectionToItem(cs.getConfigurationSection(key));
				items[i] = item;
			}
			i++;
		}
		return items;
	}
	public static List<ItemStack> sectionToItemStackList(ConfigurationSection cs){
		Set<String> keys = cs.getKeys(false);
		List<ItemStack> items = new ArrayList<ItemStack>();
		for(String key : keys){
			if(cs.getString(key) != "emty"){
				ItemStack item = SectionToItem(cs.getConfigurationSection(key));
				items.add(item);
			}
		}
		return items;
	}
	public static ConfigurationSection itemArrayToSection(ConfigurationSection cs, ItemStack[] a){
		int i = 0;
		for(ItemStack item : a){
			ConfigurationSection iSection = cs.createSection(Integer.toString(i));
			itemToSection(iSection, item);
		}
		return cs;
	}
	public static List<String> locationListToStringList(List<Location> locs){
		List<String> value = new ArrayList<String>();
		for(Location l : locs)
			value.add(locationToString(l));
		return value;
	}
	public static List<Location> StringListToLocationList(List<String> strings){
		List<Location> locs = new ArrayList<Location>();
		for(String l : strings){
			locs.add(getLocationFromString(l));
		}
		return locs;
	}
	public static void removeEnchants(ItemStack i){
		for(Enchantment ench : i.getItemMeta().getEnchants().keySet()){
			i.removeEnchantment(ench);
		}
	}
}
