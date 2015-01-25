package me.Rato.FileRenamer;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	
	@Override
	public void onDisable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " foi Desativado!");
	}
	
	@Override
	public void onEnable(){
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Foi Ativado!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if (sender instanceof ConsoleCommandSender) {
			if(cmd.getName().equalsIgnoreCase("filerename")){
				if (args.length < 3)
					sender.sendMessage("Usage: /filerename oldname.yml newname.yml path/toFileFolder");
				else{
					
					File oldFile = new File(args[2] + args[0]);
					File newFile = new File(args[2] + args[1]);
					
					if(!oldFile.exists()){
						sender.sendMessage("ERROR! Old File not found (" + oldFile.getAbsolutePath() + ")");
						return false;
					} else {
						try{
							oldFile.renameTo(newFile);
							sender.sendMessage("File renamed successfully!!");
							sender.sendMessage("Old File: " + oldFile.getAbsolutePath());
							sender.sendMessage("New File: " + newFile.getAbsolutePath());
							return true;
						}
						catch(Exception e){
							sender.sendMessage("Error: " + e.getMessage());
							return false;
						}
					}
					
				}		
			}
		} else {
			sender.sendMessage("Error: Console only command");
			return false;
		}
		return false;
	}
}
