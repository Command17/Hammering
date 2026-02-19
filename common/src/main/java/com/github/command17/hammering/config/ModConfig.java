package com.github.command17.hammering.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    // General
    public final ModConfigSpec.ConfigValue<Boolean> showTab;

    // Tools
    public final ModConfigSpec.ConfigValue<Float> hammerDurabilityMultiplier;

    public ModConfig(ModConfigSpec.Builder builder) {
        this.showTab = builder
                .comment("If true, sorts all the mod's items into a creative tab.")
                .define("general.showTab", false);

        this.hammerDurabilityMultiplier = builder
                .comment("Durability modifier of all hammer items from this mod.")
                .define("tools.hammerDurabilityMultiplier", 2.5f);
    }
}
