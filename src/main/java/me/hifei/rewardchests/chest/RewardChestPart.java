package me.hifei.rewardchests.chest;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public interface RewardChestPart {
    RewardChest getChest();
    ItemStack getIcon();
    RewardChestPartType getType();

    default void tick() {
    }

    default void onClick(InventoryClickEvent clickEvent) {
    }

    default Player getOwner() {
        return getChest().getOwner();
    }

    default void removeFromChest() {
        getChest().removePart(this);
    }
}
