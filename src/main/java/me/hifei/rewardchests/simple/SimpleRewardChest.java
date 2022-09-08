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
    private final SimpleExtendedRewardChest extendedInstance;
    protected SimpleRewardChest (SimpleExtendedRewardChest extendedChest) {
        info = null;
        table = null;
        extendedInstance = extendedChest;
    }


    public SimpleRewardChest (LootTable table, ItemStack info) {
        this.table = table;
        this.info = info;
        extendedInstance = null;
    }

    public SimpleRewardChest (LootTable table, ItemStack info, Player player) {
        this.table = table;
        this.owner = player;
        this.info = info;
        extendedInstance = null;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }

    public SimpleExtendedRewardChest getExtendedInstance () {
        return extendedInstance;
    }

    @Override
    public Player getOwner() {
        if (extendedInstance != null) {
            return extendedInstance.getOwner();
        }
        return owner;
    }

    @Override
    public Collection<RewardChestPart> getParts() {
        if (extendedInstance != null) {
            return extendedInstance.getParts();
        }
        return parts;
    }

    @Override
    public LootTable getLootTable() {
        if (extendedInstance != null) {
            return extendedInstance.getLootTable();
        }
        return table;
    }

    @Override
    public void setOwner(Player player) {
        if (extendedInstance != null) {
            extendedInstance.setOwner(player);
            return;
        }
        if (owner != null) {
            RewardChestsPlugin.instance().getManager().removeChest(owner, this);
        }
        owner = player;
        RewardChestsPlugin.instance().getManager().giveChest(player, this);
    }

    @Override
    public void removePart(RewardChestPart part) {
        if (extendedInstance != null) {
            extendedInstance.removePart(part);
            return;
        }
        parts.remove(part);
    }

    @Override
    public ItemStack getChestInfoItem() {
        if (extendedInstance != null) {
            return extendedInstance.getChestInfoItem();
        }
        return info;
    }

    @Override
    public void addPart(RewardChestPart part) {
        if (extendedInstance != null) {
            extendedInstance.addPart(part);
            return;
        }
        parts.add(part);
    }

    @Override
    public void give(Player player) throws RuntimeException {
        if (extendedInstance != null) {
            extendedInstance.give(player);
            return;
        }
        if (getOwner() != null) {
            throw new RuntimeException("One player is have the reward chest now. try use setOwner(player)");
        } else {
            setOwner(player);
        }
    }

    @Override
    public void openGUIToOwner() {
        if (extendedInstance != null) {
            extendedInstance.openGUIToOwner();
            return;
        }
        RewardChestsMenuManager.openChestMainMenu(this);
    }

    @Override
    public Collection<ItemStack> loot(Player player) {
        if (extendedInstance != null) {
            return extendedInstance.loot(player);
        }
        return lootRaw(player, getLootTable());
    }

    protected static Collection<ItemStack> lootRaw(Player player, LootTable table) {
        AttributeInstance attr = player.getAttribute(Attribute.GENERIC_LUCK);
        float luck = 0.0f;
        if (attr != null) luck = (float) attr.getValue();
        LootContext lootContext = new LootContext.Builder(player.getLocation())
                .killer(player)
                .luck(luck)
                .lootingModifier(player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS))
                .lootedEntity(null)
                .build();
        return table.populateLoot(new Random(), lootContext);
    }

    @Override
    public void tick() {
        if (extendedInstance != null) {
            extendedInstance.tick();
            return;
        }
        for (RewardChestPart part : getParts()) {
            part.tick();
        }
    }

    @Override
    public void deleteFromPlayer() {
        if (extendedInstance != null) {
            extendedInstance.deleteFromPlayer();
            return;
        }
        RewardChestsPlugin.instance().getManager().removeChest(getOwner(), this);
    }
}
