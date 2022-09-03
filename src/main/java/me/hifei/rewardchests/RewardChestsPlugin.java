package me.hifei.rewardchests;

import me.hifei.rewardchests.chest.RewardChestManager;
import org.bukkit.plugin.java.JavaPlugin;

public class RewardChestsPlugin extends JavaPlugin {
    private RewardChestManager manager = new RewardChestManager();
    public static void main(String[] args) {
        throw new RuntimeException("What are you doing? YOU ARE TRYING RUN A PLUGIN?");
    }

    RewardChestManager getManager () {
        return manager;
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable () {

    }
}
