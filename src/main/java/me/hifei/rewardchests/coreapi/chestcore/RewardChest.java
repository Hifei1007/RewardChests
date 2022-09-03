package me.hifei.rewardchests.coreapi.chestcore;

import me.hifei.rewardchests.simple.SimpleRewardChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;

import java.util.Collection;

public interface RewardChest {
    Player getOwner();
    Collection<RewardChestPart> getParts();
    LootTable getLootTable();
    void setOwner(Player player);
    void removePart(RewardChestPart part);
    ItemStack getChestInfoItem();
    void addPart(RewardChestPart part);
    void give(Player player) throws RuntimeException;
    void openGUIToOwner();
    Collection<ItemStack> loot(Player player);
    void tick();
    void deleteFromPlayer();

    static RewardChest of (LootTable table, ItemStack info) {
        return new SimpleRewardChest(table, info);
    }

    static RewardChest of (LootTable table, ItemStack info, Player owner) {
        return new SimpleRewardChest(table, info, owner);
    }
}
