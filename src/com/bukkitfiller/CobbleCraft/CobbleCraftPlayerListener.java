package com.bukkitfiller.CobbleCraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class CobbleCraftPlayerListener extends PlayerListener {
	
	@Override
	public void onPlayerJoin(PlayerEvent event) {
		Player player = event.getPlayer();
		String fileName = CobbleCraft.FILEDIRECTORY + player.getName() + ".stats";
		CobbleCraftFileHandler.writeFile(fileName);
	}
	
	@Override
	public void onPlayerCommandPreprocess(PlayerChatEvent event) {
		String[] message = event.getMessage().split(" ");
		Player player = event.getPlayer();
	    String fileName = CobbleCraft.FILEDIRECTORY + player.getName() + ".stats";
		
		if(message[0].equalsIgnoreCase("/MINING")){
			int playerLevel = CobbleCraftFileHandler.getLevel(fileName,"MINING");
			Double playerExp = CobbleCraftFileHandler.getProperty(fileName, "MINING");
			player.sendMessage("Current 'Mining' level: " + ChatColor.GOLD +
					playerLevel);
			player.sendMessage("Exp left to next level: " + ChatColor.GOLD +
					CobbleCraftFileHandler.getExpToGo(playerExp, playerLevel + 1));
		}
		
	}
	
}