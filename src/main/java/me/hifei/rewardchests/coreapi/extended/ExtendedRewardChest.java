package me.hifei.rewardchests.coreapi.extended;

import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public interface ExtendedRewardChest extends RewardChest {
    interface AutoItemConverter<T1, T2> {
        T2 convert(T1 chest);
    }

    void setLootTable(LootTable table);
    void setChestInfoItem(ItemStack item);
    void setChestInfoItemConverter(AutoItemConverter<ExtendedRewardChest, ItemStack> converter);
    @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter();
    List<RewardChestPart> getPartList();
    void setLootForce(Collection<ItemStack> loot);


}
