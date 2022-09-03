package me.hifei.rewardchests.coreapi.extended;

import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public abstract class ExtendedRewardChestHooked implements ExtendedRewardChest {
    abstract Player getOwner_();
    abstract Collection<RewardChestPart> getParts_();
    abstract LootTable getLootTable_();
    abstract void setOwner_(Player player);
    abstract void removePart_(RewardChestPart part);
    abstract ItemStack getChestInfoItem_();
    abstract void addPart_(RewardChestPart part);
    abstract void give_(Player player) throws RuntimeException;
    abstract void openGUIToOwner_();
    abstract Collection<ItemStack> loot_(Player player);
    abstract void tick_();
    abstract void deleteFromPlayer_();


    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public Collection<RewardChestPart> getParts() {
        return null;
    }

    @Override
    public LootTable getLootTable() {
        return null;
    }

    @Override
    public void setOwner(Player player) {

    }

    @Override
    public void removePart(RewardChestPart part) {

    }

    @Override
    public ItemStack getChestInfoItem() {
        return null;
    }

    @Override
    public void addPart(RewardChestPart part) {

    }

    @Override
    public void give(Player player) throws RuntimeException {

    }

    @Override
    public void openGUIToOwner() {

    }

    @Override
    public Collection<ItemStack> loot(Player player) {
        return null;
    }

    @Override
    public void tick() {

    }

    @Override
    public void deleteFromPlayer() {

    }

    @Override
    public void setLootTable(LootTable table) {

    }

    @Override
    public void setChestInfoItem(ItemStack item) {

    }

    @Override
    public void setChestInfoItemConverter(AutoItemConverter<ExtendedRewardChest, ItemStack> converter) {

    }

    @Override
    public @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter() {
        return null;
    }

    @Override
    public List<RewardChestPart> getPartList() {
        return null;
    }

    @Override
    public void setLootForce(Collection<ItemStack> loot) {

    }
}
