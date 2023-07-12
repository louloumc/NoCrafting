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
        // ע���¼�������
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("NoCraftingPlugin�����ã�");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoCraftingPlugin�ѽ��ã�");
    }

    // ��ֹ��Һϳ���Ʒ
    @EventHandler
    public void onPlayerAttemptToCraft(CraftItemEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage(ChatColor.RED + "���������޷��ϳ��κ���Ʒ������npc���һ�������߰ɣ�");
            event.setCancelled(true);
        }
    }

    // ��ֹ���ʹ�ñ����ĺϳ���
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission("nocrafting.bypass")) {
            return;
        }
        if (event.getWhoClicked() instanceof Player && event.getInventory().getType() == InventoryType.WORKBENCH) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage(ChatColor.RED + "���������޷��ϳ��κ���Ʒ������npc���һ�������߰ɣ�");
            event.setCancelled(true);
        }
    }
}
