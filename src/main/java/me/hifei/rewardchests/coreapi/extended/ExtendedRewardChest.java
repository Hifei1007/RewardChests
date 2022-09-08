package me.hifei.rewardchests.coreapi.extended;

import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import me.hifei.rewardchests.simple.SimpleExtendedRewardChest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface ExtendedRewardChest extends RewardChest {
    interface AutoItemConverter<T1, T2> {
        T2 convert(T1 chest);
    }

    static ExtendedRewardChest of (LootTable table, ItemStack info) {
        return new SimpleExtendedRewardChest(table, info);
    }

    static ExtendedRewardChest of (LootTable table, ItemStack info, Player owner) {
        return new SimpleExtendedRewardChest(table, info, owner);
    }

    default ExtendedRewardChestHooked toHooked() throws RuntimeException {
        if (this instanceof ExtendedRewardChestHooked) {
            return (ExtendedRewardChestHooked) this;
        } else {
            throw new RuntimeException("The ExtendedRewardChest instance is not a ExtendedRewardChestHooked instance!");
        }
    }

    default boolean isCanHook() {
        return this instanceof ExtendedRewardChestHooked;
    }

    default SimpleExtendedRewardChest toExtendedSimple() {
        if (this instanceof SimpleExtendedRewardChest) {
            return (SimpleExtendedRewardChest) this;
        } else {
            throw new RuntimeException("The ExtendedRewardChest instance is not a SimpleExtendedRewardChest instance!");
        }
    }

    void setLootTable(@NotNull LootTable table);
    void setChestInfoItem(@Nullable ItemStack item);
    void setChestInfoItemConverter(@Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> converter);
    @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter();
    @NotNull List<RewardChestPart> getPartList();
    void setLootForce(@Nullable Collection<ItemStack> loot);
    @Nullable Collection<ItemStack> getForceLoot();
}
