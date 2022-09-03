package me.hifei.rewardchests.chest;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.Collection;
import java.util.Random;

public interface RewardChest {
    Player getOwner();
    Collection<RewardChestPart> getParts();
    LootTable getLootTable();
    void setOwner(Player player);
    void removePart(RewardChestPart part);

    default void give(Player player) throws RuntimeException {
        if (getOwner() != null) {
            throw new RuntimeException("One player is have the reward chest now.");
        } else {
            setOwner(player);
        }
    }

    default void openGUI(Player player) {
    }

    default Collection<ItemStack> loot(Player player) {
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

    default void tick() {
        for (RewardChestPart part : getParts()) {
            part.tick();
        }
    }

    default void deleteFromPlayer() {

    }
}
