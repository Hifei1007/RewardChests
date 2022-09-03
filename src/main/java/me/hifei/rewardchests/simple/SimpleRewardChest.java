package me.hifei.rewardchests.simple;

import me.hifei.rewardchests.RewardChestsPlugin;
import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import me.hifei.rewardchests.gui.RewardChestsMenuManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
        return info;
    }

    @Override
    public void addPart(RewardChestPart part) {
        parts.add(part);
    }

    @Override
    public void give(Player player) throws RuntimeException {
        if (getOwner() != null) {
            throw new RuntimeException("One player is have the reward chest now.");
        } else {
            setOwner(player);
        }
    }

    @Override
    public void openGUIToOwner() {
        RewardChestsMenuManager.openChestMainMenu(this);
    }

    @Override
    public Collection<ItemStack> loot(Player player) {
        AttributeInstance attr = player.getAttribute(Attribute.GENERIC_LUCK);
        float luck = 0.0f;
        if (attr != null) luck = (float) attr.getValue();
        LootContext lootContext = new LootContext.Builder(player.getLocation())
                .killer(player)
                .luck(luck)
                .lootingModifier(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS))
                .lootedEntity(null)
                .build();
        return getLootTable().populateLoot(new Random(), lootContext);
    }

    @Override
    public void tick() {
        for (RewardChestPart part : getParts()) {
            part.tick();
        }
    }

    @Override
    public void deleteFromPlayer() {
        RewardChestsPlugin.instance().getManager().removeChest(getOwner(), this);
    }
}
