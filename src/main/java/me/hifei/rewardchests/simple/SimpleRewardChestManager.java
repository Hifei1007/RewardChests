package me.hifei.rewardchests.simple;

import me.hifei.rewardchests.coreapi.RewardChestManager;
import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import org.bukkit.entity.Player;

import java.util.*;

public class SimpleRewardChestManager implements RewardChestManager {
    private final Map<Player, List<RewardChest>> rewardChests = new HashMap<>();

    private void checkPlayer(Player player) {
        if (!rewardChests.containsKey(player)) {
            rewardChests.put(player, new ArrayList<>());
        }
    }

    @Override
    public Collection<RewardChest> getPlayerChests(Player player) {
        checkPlayer(player);
        return rewardChests.get(player);
    }

    @Override
    public void giveChest(Player player, RewardChest chest) {
        checkPlayer(player);
        rewardChests.get(player).add(chest);
    }

    @Override
    public void removeChest(Player player, RewardChest chest) {
        checkPlayer(player);
        chest.setOwner(null);
        rewardChests.get(player).remove(chest);
    }
}
