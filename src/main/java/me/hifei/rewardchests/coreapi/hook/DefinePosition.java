package me.hifei.rewardchests.coreapi.hook;

public @interface DefinePosition {
    HookPosition value() default HookPosition.AFTER_CALL;
}
