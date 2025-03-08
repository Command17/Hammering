package com.github.command17.hammering.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ModConfig {
    // General
    public final ModConfigSpec.ConfigValue<Boolean> showTab;

    // Tools
    public final ModConfigSpec.ConfigValue<Integer> hammerDurabilityMultiplier;

    public ModConfig(ModConfigSpec.Builder builder) {
        this.showTab = builder
                .comment("If true, sorts all the mod's items into a creative tab.")
                .define(key("general", "showTab"), false);

        this.hammerDurabilityMultiplier = builder
                .comment("Durability modifier of all hammer items from this mod.")
                .define(key("tools", "hammerDurabilityMultiplier"), 2);
    }

    private static List<String> key(String category, String field) {
        return List.of(category, field);
    }

    private static List<String> key(String category, String subcategory, String field) {
        return List.of(category, subcategory, field);
    }
}
