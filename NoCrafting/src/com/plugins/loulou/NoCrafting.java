package com.plugins.loulou;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCrafting extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // 注册事件监听器
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("NoCraftingPlugin已启用！");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoCraftingPlugin已禁用！");
    }

    // 禁止玩家合成物品
    @EventHandler
    public void onPlayerAttemptToCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage(ChatColor.RED + "在这里你无法合成任何物品！请找npc来兑换各类道具吧！");
            event.setCancelled(true);
        }
    }

    // 禁止玩家使用背包的合成栏
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission("nocrafting.bypass")) {
            return;
        }
        if (event.getWhoClicked() instanceof Player && event.getInventory().getType() == InventoryType.WORKBENCH) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage(ChatColor.RED + "在这里你无法合成任何物品！请找npc来兑换各类道具吧！");
            event.setCancelled(true);
        }
    }
}
