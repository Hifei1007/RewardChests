package me.hifei.rewardchests.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class RewardChestsMenuListener implements Listener {
    @EventHandler
    public void onCloseInventory (InventoryCloseEvent event) {
        RewardChestsMenuManager.closeMenu((Player) event.getPlayer());
    }
}
