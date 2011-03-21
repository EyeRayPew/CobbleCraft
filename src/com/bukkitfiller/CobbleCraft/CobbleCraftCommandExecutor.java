package com.bukkitfiller.CobbleCraft;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CobbleCraftCommandExecutor implements CommandExecutor {
	private CobbleCraft plugin;	
	
	CobbleCraftCommandExecutor(CobbleCraft plugin) {
		this.plugin = plugin;
	}
	
	private void showStats(Player player, int[] levelArray, String type) {
	    String fileName = plugin.FILEDIRECTORY + player.getName() + ".stats";
		
		int playerLevel = plugin.fileHandler.getLevel(fileName, type.toUpperCase());
		double playerExp = plugin.fileHandler.getNumProperty(fileName, type.toUpperCase());
		player.sendMessage("Current '" + type + "' level: " + ChatColor.GOLD + playerLevel);
		player.sendMessage("Exp left to next level: " + ChatColor.GOLD + plugin.fileHandler.getExpToGo(playerExp, levelArray, playerLevel + 1));
	}

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	Player player = (Player)sender;
    	String fileName = plugin.FILEDIRECTORY + player.getName() + ".stats";
    	if (label.equalsIgnoreCase("mining")) {
    		showStats(player,plugin.lvlValues.MiningLevels,"Mining");
		} else if (label.equalsIgnoreCase("digging")) {
    		showStats(player,plugin.lvlValues.DiggingLevels,"Digging");
		} else if (label.equalsIgnoreCase("fishing")) {
    		showStats(player,plugin.lvlValues.FishingLevels,"Fishing");
		} else if (label.equalsIgnoreCase("slaying")) {
    		showStats(player,plugin.lvlValues.SlayingLevels,"Slaying");
		} else if (label.equalsIgnoreCase("achievements")) {
    		plugin.fileHandler.getAchievements(fileName, player);
		}
		return false;
    }
}
