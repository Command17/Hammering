package com.github.command17.hammering;

import com.github.command17.hammering.config.ModConfig;
import com.github.command17.hammering.config.ModServerConfig;
import com.github.command17.hammering.enchantment.ModEnchantments;
import com.github.command17.hammering.enchantment.effect.ModEnchantmentEffectComponents;
import com.github.command17.hammering.event.ModEvents;
import com.github.command17.hammering.item.ModItems;
import com.github.command17.hammering.item.tab.ModCreativeModeTabs;
import com.mojang.logging.LogUtils;
import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.slf4j.Logger;

public final class Hammering {
    public static final String MOD_ID = "hammering";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;
    public static final ModServerConfig SERVER_CONFIG;
    public static final ModConfigSpec SERVER_CONFIG_SPEC;

    public static void init() {
        LOGGER.info("Initializing...");

        ModItems.register();
        ModCreativeModeTabs.register();
        ModEnchantmentEffectComponents.register();
        ModEnchantments.register();
        ModEvents.register();
        buildCreativeTabContent();

        LOGGER.info("Initialized.");
    }

    private static void buildCreativeTabContent() {
        var search = CreativeTabRegistry.defer(CreativeModeTabs.SEARCH);
        var tools = CreativeTabRegistry.defer(CreativeModeTabs.TOOLS_AND_UTILITIES);

        if (CONFIG.showTab.get()) {
            return;
        }

        // Food
        CreativeTabRegistry.modify(tools, (f, output, b) -> {
            output.acceptAfter(Items.IRON_HOE, ModItems.IRON_HAMMER.get());
            output.acceptAfter(Items.GOLDEN_HOE, ModItems.GOLDEN_HAMMER.get());
            output.acceptAfter(Items.DIAMOND_HOE, ModItems.DIAMOND_HAMMER.get());
            output.acceptAfter(Items.NETHERITE_HOE, ModItems.NETHERITE_HAMMER.get());
        });

        // Search
        CreativeTabRegistry.modify(search, (f, output, b) -> {
            output.acceptAfter(Items.IRON_HOE, ModItems.IRON_HAMMER.get());
            output.acceptAfter(Items.GOLDEN_HOE, ModItems.GOLDEN_HAMMER.get());
            output.acceptAfter(Items.DIAMOND_HOE, ModItems.DIAMOND_HAMMER.get());
            output.acceptAfter(Items.NETHERITE_HOE, ModItems.NETHERITE_HAMMER.get());
        });
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    static {
        var configPair = new ModConfigSpec.Builder().configure(ModConfig::new);
        CONFIG = configPair.getKey();
        CONFIG_SPEC = configPair.getValue();
        var serverConfigPair = new ModConfigSpec.Builder().configure(ModServerConfig::new);
        SERVER_CONFIG = serverConfigPair.getKey();
        SERVER_CONFIG_SPEC = serverConfigPair.getValue();
    }
}
