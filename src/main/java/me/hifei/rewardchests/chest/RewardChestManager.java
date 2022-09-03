package me.hifei.rewardchests.chest;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class RewardChestManager {
    private Map<Player, List<RewardChest>> rewardChests;

    private void checkPlayer(Player player) {
        if (!rewardChests.containsKey(player)) {
            rewardChests.put(player, new ArrayList<>());
        }
    }

    public Collection<RewardChest> getPlayerChests(Player player) {
        checkPlayer(player);
        return rewardChests.get(player);
    }

    public void giveChest(Player player, RewardChest chest) {
        checkPlayer(player);
        rewardChests.get(player).add(chest);
    }

    public void removeChest(Player player, RewardChest chest) {
        checkPlayer(player);
        rewardChests.get(player).remove(chest);
    }
}
