package me.hifei.rewardchests.chest;

import org.bukkit.entity.Player;

public interface RewardChestPart {
    RewardChest getChest();

    default void tick() {
    }

    default Player getOwner() {
        return getChest().getOwner();
    }

    default void removeFromChest() {
        getChest().removePart(this);
    }
}
