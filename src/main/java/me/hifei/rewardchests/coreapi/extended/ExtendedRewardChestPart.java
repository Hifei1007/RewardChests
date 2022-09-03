package me.hifei.rewardchests.coreapi.extended;

import me.hifei.rewardchests.coreapi.chestcore.RewardChest;
import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;

public interface ExtendedRewardChestPart extends RewardChestPart {
    default ExtendedRewardChest getChestExtended () {
        RewardChest chest = getChest();
        if (chest instanceof ExtendedRewardChest ch) {
            return ch;
        } else {
            throw new RuntimeException("The extended reward chest part is in a normal reward chest, but the extended function call.");
        }
    }
}
