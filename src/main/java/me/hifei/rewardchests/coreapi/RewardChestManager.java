package me.hifei.rewardchests.coreapi;

import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import org.bukkit.entity.Player;

import java.util.Collection;

public interface RewardChestManager {
    Collection<RewardChest> getPlayerChests(Player player);
    void giveChest(Player player, RewardChest chest);
    void removeChest(Player player, RewardChest chest);
}
