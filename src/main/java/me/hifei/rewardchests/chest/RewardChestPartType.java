package me.hifei.rewardchests.chest;

import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface RewardChestPartType {
    /**
     * 如果该组件类型可以被合成，输出该组件类型需要什么材料进行合成。<br>
     * 保证当 isCanCraft() 方法输出 false 时不会调用该方法。<br>
     *
     * @apiNote 该接口方法目前无需重写，且未进行任何应用。
     *
     * @return 合成该组件类型所需的材料。
     */
    default Collection<ItemStack> getCost() {
        return null;
    }
    /**
     * 如果该组件类型可以被合成，输出该组件类型合成时显示的图标。<br>
     * 保证当 isCanCraft() 方法输出 false 时不会调用该方法。<br>
     *
     * @apiNote 该接口方法目前无需重写，且未进行任何应用。
     *
     * @return 该组件类型合成时显示的图标。
     */
    default ItemStack getCraftingIcon() {
        return null;
    }
    /**
     * 输出该组件类型是否可以合成。<br>
     * 该组件输出的值应该不变。<br>
     *
     * @apiNote 该接口方法目前无需重写，且未进行任何应用。
     *
     * @return 该组件类型是否可以合成。
     */
    default boolean isCanCraft() {
        return false;
    }
    /**
     * 产生一个该组件类型的组件。<br>
     *
     * @apiNote 你应该产生一个组件 part，且 part.getType() == this && part.getChest() == chest。
     *
     * @return 产生的组件。
     */
    RewardChestPart createPart(RewardChest chest);
}
