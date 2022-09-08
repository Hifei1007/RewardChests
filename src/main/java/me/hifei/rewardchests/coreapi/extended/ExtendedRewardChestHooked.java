package me.hifei.rewardchests.coreapi.extended;

import me.hifei.rewardchests.coreapi.chestcore.RewardChestPart;
import me.hifei.rewardchests.coreapi.hook.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class ExtendedRewardChestHooked implements ExtendedRewardChest {
    private interface OriginMethodRunner<T> {
        T convert();
    }

    private interface OriginMethodRunnerVoid {
        void convert();
    }

    private static class HookRunningContext {
        private HookLevel level = HookLevel.NORMAL;
        private HookPosition position = HookPosition.AFTER_CALL;
        private boolean args = false;
    }

    private <T> T runHook(HookMethod method, Object[] args, OriginMethodRunner<T> runMethod) {
        for (HookLevel level : HookLevel.values()) {
            runHookInLevel(method, args, HookPosition.FRONT_CALL, level, null);
        }
        T returnValue = runMethod.convert();
        for (HookLevel level : HookLevel.values()) {
            runHookInLevel(method, args, HookPosition.AFTER_CALL, level, returnValue);
        }
        return returnValue;
    }

    private void runHook(HookMethod method, Object[] args, OriginMethodRunnerVoid runMethod) {
        for (HookLevel level : HookLevel.values()) {
            runHookInLevel(method, args, HookPosition.FRONT_CALL, level, null);
        }
        runMethod.convert();
        for (HookLevel level : HookLevel.values()) {
            runHookInLevel(method, args, HookPosition.AFTER_CALL, level, null);
        }
    }

    private void runHookInLevel(HookMethod method, Object[] args, HookPosition position, HookLevel level, Object returnValue) {
        for (RewardChestPart part : getPartUsingHook()) {
            if (!(part instanceof ExtendedRewardChestPart)) continue;
            for (Method m : getHookMethods(part, method)) {
                HookRunningContext context = getHookMethodContext(m);
                if (context.level.equals(level) && context.position.equals(position)) {
                    try {
                        if (context.args) {
                            if (position.equals(HookPosition.AFTER_CALL)) {
                                m.invoke(part, returnValue, args);
                            } else {
                                m.invoke(part, args);
                            }
                        } else {
                            m.invoke(part);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Collection<Method> getHookMethods(RewardChestPart part, HookMethod m) {
        List<Method> methods = new ArrayList<>();
        for (Method method : part.getClass().getMethods()) {
            for (Annotation annotation : Arrays.stream(method.getAnnotations()).toList()) {
                if (annotation instanceof ExtendedPartHook) {
                    if (((ExtendedPartHook) annotation).value() == m) {
                        methods.add(method);
                    }
                    break;
                }
            }
        }
        return methods;
    }

    private HookRunningContext getHookMethodContext(Method method) {
        HookRunningContext context = new HookRunningContext();
        for (Annotation annotation : Arrays.stream(method.getAnnotations()).toList()) {
            if (annotation instanceof DefineArgs) {
                context.args = ((DefineArgs) annotation).value();
            } else if (annotation instanceof DefineLevel) {
                context.level = ((DefineLevel) annotation).value();
            } else if (annotation instanceof DefinePosition) {
                context.position = ((DefinePosition) annotation).value();
            }
        }
        return context;
    }

    public abstract Player getOwner_();
    public abstract Collection<RewardChestPart> getParts_();
    public abstract LootTable getLootTable_();
    public abstract void setOwner_(Player player);
    public abstract void removePart_(RewardChestPart part);
    public abstract ItemStack getChestInfoItem_();
    public abstract void addPart_(RewardChestPart part);
    public abstract void give_(Player player) throws RuntimeException;
    public abstract void openGUIToOwner_();
    public abstract Collection<ItemStack> loot_(Player player);
    public abstract void tick_();
    public abstract void deleteFromPlayer_();
    public abstract void setLootTable_(LootTable table);
    public abstract void setChestInfoItem_(ItemStack item);
    public abstract void setChestInfoItemConverter_(AutoItemConverter<ExtendedRewardChest, ItemStack> converter);
    public abstract @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter_();
    public abstract List<RewardChestPart> getPartList_();
    public abstract void setLootForce_(Collection<ItemStack> loot);
    public abstract Collection<RewardChestPart> getPartUsingHook();


    @Override
    public Player getOwner() {
        return runHook(HookMethod.METHOD_GET_OWNER, new Object[]{}, this::getOwner_);
    }

    @Override
    public Collection<RewardChestPart> getParts() {
        return runHook(HookMethod.METHOD_GET_PARTS, new Object[]{}, this::getParts_);
    }

    @Override
    public LootTable getLootTable() {
        return runHook(HookMethod.METHOD_GET_LOOT_TABLE, new Object[]{}, this::getLootTable_);
    }

    @Override
    public void setOwner(Player player) {
        runHook(HookMethod.METHOD_SET_OWNER, new Object[]{player}, () -> setOwner_(player));
    }

    @Override
    public void removePart(RewardChestPart part) {
        runHook(HookMethod.METHOD_REMOVE_PART, new Object[]{part}, () -> removePart_(part));
    }

    @Override
    public ItemStack getChestInfoItem() {
        return runHook(HookMethod.METHOD_GET_CHEST_INFO_ITEM, new Object[]{}, this::getChestInfoItem_);
    }

    @Override
    public void addPart(RewardChestPart part) {
        runHook(HookMethod.METHOD_ADD_PART, new Object[]{part}, () -> addPart_(part));
    }

    @Override
    public void give(Player player) throws RuntimeException {
        runHook(HookMethod.METHOD_GIVE, new Object[]{player}, () -> give_(player));
    }

    @Override
    public void openGUIToOwner() {
        runHook(HookMethod.METHOD_OPEN_GUI_TO_OWNER, new Object[]{}, this::openGUIToOwner_);
    }

    @Override
    public Collection<ItemStack> loot(Player player) {
        return runHook(HookMethod.METHOD_LOOT, new Object[]{}, () -> loot_(player));
    }

    @Override
    public void tick() {
        runHook(HookMethod.METHOD_TICK, new Object[]{}, this::tick_);
    }

    @Override
    public void deleteFromPlayer() {
        runHook(HookMethod.METHOD_DELETE_FROM_PLAYER, new Object[]{}, this::deleteFromPlayer_);
    }

    @Override
    public void setLootTable(@NotNull LootTable table) {
        runHook(HookMethod.EXTENDED_SET_LOOT_TABLE, new Object[]{table}, () -> setLootTable_(table));
    }

    @Override
    public void setChestInfoItem(ItemStack item) {
        runHook(HookMethod.EXTENDED_SET_CHEST_INFO_ITEM, new Object[]{item}, () -> setChestInfoItem(item));
    }

    @Override
    public void setChestInfoItemConverter(AutoItemConverter<ExtendedRewardChest, ItemStack> converter) {
        runHook(HookMethod.EXTENDED_SET_CHEST_INFO_ITEM_CONVERTER, new Object[]{converter}, () -> setChestInfoItemConverter_(converter));
    }

    @Override
    public @Nullable AutoItemConverter<ExtendedRewardChest, ItemStack> getChestInfoItemConverter() {
        return runHook(HookMethod.EXTENDED_GET_CHEST_INFO_ITEM_CONVERTER, new Object[]{}, this::getChestInfoItemConverter_);
    }

    @Override
    public @NotNull List<RewardChestPart> getPartList() {
        return runHook(HookMethod.EXTENDED_GET_PART_LIST, new Object[]{}, this::getPartList_);
    }

    @Override
    public void setLootForce(Collection<ItemStack> loot) {
        runHook(HookMethod.EXTENDED_SET_LOOT_FORCE, new Object[]{loot}, () -> setLootForce_(loot));
    }
}
