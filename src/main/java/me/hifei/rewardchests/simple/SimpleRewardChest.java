package me.hifei.rewardchests.simple;

import me.hifei.rewardchests.RewardChestsPlugin;
import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimpleRewardChest implements RewardChest {
    private final LootTable table;
    private Player owner;
    private final List<RewardChestPart> parts = new ArrayList<>();
    private final ItemStack info;

    public SimpleRewardChest (LootTable table, ItemStack info) {
        this.table = table;
        this.info = info;
    }

    public SimpleRewardChest (LootTable table, ItemStack info, Player player) {
        this.table = table;
        this.owner = player;
        this.info = info;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }
    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public Collection<RewardChestPart> getParts() {
        return parts;
    }

    @Override
    public LootTable getLootTable() {
        return table;
    }

    @Override
    public void setOwner(Player player) {
        if (owner != null) {
            RewardChestsPlugin.instance().getManager().removeChest(owner, this);
        }
        owner = player;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }

    @Override
    public void removePart(RewardChestPart part) {
        parts.remove(part);
    }

    @Override
    public ItemStack getChestInfoItem() {
        return null;
    }

    @Override
    public void addPart(RewardChestPart part) {
        parts.add(part);
    }
}
