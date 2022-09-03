package me.hifei.rewardchests.chest;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface RewardChestPartType {
    Collection<ItemStack> getCost();
    ItemStack getCraftingIcon();
    RewardChestPart createPart();
}
