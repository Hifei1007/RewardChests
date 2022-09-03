package me.hifei.rewardchests.gui;

import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RewardChestsMenuManager {
    private final static Map<Player, OpeningMenuType> playerMenuData = new HashMap<>();
    private final static Map<Player, RewardChest> openingChest = new HashMap<>();

    public static void updatePlayerMenu(Player player) {

    }

    public static void openMainMenu(Player player) {
        playerMenuData.put(player, OpeningMenuType.MAIN);
        openingChest.remove(player);
    }

    public static void openChestMainMenu(RewardChest chest) {
        playerMenuData.put(chest.getOwner(), OpeningMenuType.CHEST_MAIN);
        openingChest.put(chest.getOwner(), chest);
    }

    public static void lootChest(RewardChest chest) {
        playerMenuData.put(chest.getOwner(), OpeningMenuType.LOOTING);
        openingChest.put(chest.getOwner(), chest);
        chest.deleteFromPlayer();
    }

    public static void openPartsMenu(RewardChest chest) {
        playerMenuData.put(chest.getOwner(), OpeningMenuType.PARTS);
        openingChest.put(chest.getOwner(), chest);
    }

    public static void openOpenChestMenu(RewardChest chest) {
        playerMenuData.put(chest.getOwner(), OpeningMenuType.OPEN_CONFIRM);
        openingChest.put(chest.getOwner(), chest);
    }

    public static void closeMenu(Player player) {
        playerMenuData.put(player, OpeningMenuType.OTHER);
        openingChest.remove(player);
    }
}
