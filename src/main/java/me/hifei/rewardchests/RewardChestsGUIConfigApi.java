package me.hifei.rewardchests;

import org.bukkit.entity.Player;

public class RewardChestsGUIConfigApi {
    private static boolean isEnabledGUIListen = true;

    public static boolean isEnabledGUIListen() {
        return isEnabledGUIListen;
    }

    public static void setEnabledGUIListen(boolean isEnabledGUIListen) {
        RewardChestsGUIConfigApi.isEnabledGUIListen = isEnabledGUIListen;
    }

    public static void openGUI(Player player) {

    }
}
