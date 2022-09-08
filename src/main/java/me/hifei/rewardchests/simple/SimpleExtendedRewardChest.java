package me.hifei.rewardchests.simple;

import me.hifei.rewardchests.RewardChestsPlugin;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import me.hifei.rewardchests.coreapi.extended.ExtendedRewardChest;
import me.hifei.rewardchests.coreapi.extended.ExtendedRewardChestHooked;
import me.hifei.rewardchests.gui.RewardChestsMenuManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class SimpleExtendedRewardChest extends ExtendedRewardChestHooked {
    private LootTable table;
    private Player owner;
    private final List<RewardChestPart> parts = new ArrayList<>();
    private Collection<ItemStack> forceloot = null;
    private ItemStack info;
    private AutoItemConverter<ExtendedRewardChest, ItemStack> converter = null;
    private final SimpleRewardChest cloneNotExtendedInstance = new SimpleRewardChest(this);

    public SimpleExtendedRewardChest (LootTable table, ItemStack info) {
        this.table = table;
        this.info = info;
    }

    public SimpleExtendedRewardChest (LootTable table, ItemStack info, Player player) {
        this.table = table;
        this.owner = player;
        this.info = info;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }

    public SimpleRewardChest getMappingInstance () {
        return cloneNotExtendedInstance;
    }

    @Override
    public Player getOwner_() {
        return owner;
    }

    @Override
    public Collection<RewardChestPart> getParts_() {
        return parts;
    }

    @Override
    public LootTable getLootTable_() {
        return table;
    }

    @Override
    public void setOwner_(Player player) {
        if (owner != null) {
            RewardChestsPlugin.instance().getManager().removeChest(owner, this);
        }
        owner = player;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }

    @Override
    public void removePart_(RewardChestPart part) {
        parts.remove(part);
    }

    @Override
    public ItemStack getChestInfoItem_() {
        if (converter != null) return converter.convert(this);
        return info;
    }

    @Override
    public void addPart_(RewardChestPart part) {
        parts.add(part);
    }

    @Override
    public void give_(Player player) throws RuntimeException {
        if (getOwner() != null) {
            throw new RuntimeException("One player is have the reward chest now. try use setOwner(player)");
        } else {
            setOwner(player);
        }
    }

    @Override
    public void openGUIToOwner_() {
        RewardChestsMenuManager.openChestMainMenu(this);
    }

    @Override
    public Collection<ItemStack> loot_(Player player) {
        if (forceloot != null) return forceloot;
        return SimpleRewardChest.lootRaw(player, getLootTable());
    }

    @Override
    public void tick_() {
        for (RewardChestPart part : getParts()) {
            part.tick();
        }
    }

    @Override
    public void deleteFromPlayer_() {
        RewardChestsPlugin.instance().getManager().removeChest(getOwner(), this);
    }

    @Override
    public void setLootTable_(LootTable table) {
        this.table = table;
    }

    @Override
    public void setChestInfoItem_(ItemStack item) {
        info = item;
    }

    @Override
    public void setChestInfoItemConverter_(AutoItemConverter<ExtendedRewardChest, ItemStack> converter) {
        this.converter = converter;
    }

    @Override
    public @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter_() {
        return converter;
    }

    @Override
    public List<RewardChestPart> getPartList_() {
        return parts;
    }

    @Override
    public void setLootForce_(Collection<ItemStack> loot) {
        this.forceloot = loot;
    }

    @Override
    public Collection<RewardChestPart> getPartUsingHook() {
        return parts;
    }

    @Override
    public @Nullable Collection<ItemStack> getForceLoot() {
        return forceloot;
    }
}
