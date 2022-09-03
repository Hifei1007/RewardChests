package me.hifei.rewardchests;

import me.hifei.rewardchests.chest.RewardChestManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RewardChestsPlugin extends JavaPlugin {
    private static RewardChestsPlugin instance;
    private final RewardChestManager manager = new RewardChestManager();
    public static void main(String[] args) {
        throw new RuntimeException("What are you doing? YOU ARE TRYING RUN A PLUGIN?");
    }

    public RewardChestManager getManager () {
        return manager;
    }

    public static RewardChestsPlugin instance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable () {

    }
}
