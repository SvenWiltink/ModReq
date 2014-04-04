package me.sw123.modreq;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import org.bukkit.configuration.file.FileConfiguration;

public class Settings{

	@setting(defaultValue = "false") 		public boolean DATABASE_USEMYSQL;
	@setting(defaultValue = "example.com") 	public String DATABASE_MYSQL_IP;
	@setting(defaultValue = "3306") 		public String DATABASE_MYSQL_PORT;
	@setting(defaultValue = "database")		public String DATABASE_MYSQL_DATABASE;
	@setting(defaultValue = "user") 		public String DATABASE_MYSQL_USER;
	@setting(defaultValue = "password") 	public String DATABASE_MYSQL_PASSWORD;


	public void save() throws IllegalArgumentException, IllegalAccessException, IOException{
		System.out.println("saving");
		FileConfiguration config = ModReq.getInstance().getConfig();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields){
			if(f.isAnnotationPresent(setting.class)){
				String name = f.getName();
				String path = name.replaceAll("_", ".").toLowerCase();
				System.out.println("saving field: " + name);
				Object value = f.get(this);
				System.out.println("        path: " + path);
				System.out.println("       value: " + value.toString());
				config.set(path, value);
			}
		}
		File configFile = new File("config.yml");
		if(!configFile.exists()){
			configFile.mkdirs();
			configFile.createNewFile();
		}
		ModReq.getInstance().saveConfig();
	}
	public void load() throws NumberFormatException, IllegalArgumentException, IllegalAccessException{
		FileConfiguration config = ModReq.getInstance().getConfig();
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields){
			if(f.isAnnotationPresent(setting.class)){
				String name = f.getName();
				String path = name.replaceAll("_", ".").toLowerCase();
				String value;
				if(config.isSet(path)){
					value = config.getString(path);
				}else{
					value = f.getAnnotation(setting.class).defaultValue();
				}
				if(f.getType() == int.class){
					f.setInt(this, Integer.parseInt(value));
				}else if(f.getType() == boolean.class){
					f.setBoolean(this, Boolean.parseBoolean(value));
				}
				else if(f.getType().equals(String.class)){
					f.set(this, value);
				}
			}
		}
	}
	@Retention(RetentionPolicy.RUNTIME) public @interface setting{
		String defaultValue() default "";
	}


}