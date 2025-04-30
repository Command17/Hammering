package com.github.command17.hammering.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class ModServerConfig {
    // Enchantments
    public final ModConfigSpec.ConfigValue<Integer> areaMineRadius;
    public final ModConfigSpec.ConfigValue<Integer> areaMineDepthPerLevel;
    public final ModConfigSpec.ConfigValue<Float> areaMineEfficiencyDebuff;

    public ModServerConfig(ModConfigSpec.Builder builder) {
        this.areaMineRadius = builder
                .comment("Radius of AreaMine (or Hammering).")
                .define(key("enchantments", "areaMineRadius"), 1);

        this.areaMineDepthPerLevel = builder
                .comment("Increase of depth of AreaMine (or Hammering) per enchantment level.")
                .define(key("enchantments", "areaMineDepthPerLevel"), 1);

        this.areaMineEfficiencyDebuff = builder
                .comment("Efficiency modifier when using AreaMine (or Hammering).")
                .define(key("enchantments", "areaMineEfficiencyDebuff"), 0.96f);
    }

    private static List<String> key(String category, String field) {
        return List.of(category, field);
    }

    private static List<String> key(String category, String subcategory, String field) {
        return List.of(category, subcategory, field);
    }
}
