package me.hifei.rewardchests.coreapi.hook;

public @interface DefineLevel {
    HookLevel value() default HookLevel.NORMAL;
}
