package com.github.command17.hammering;

import com.github.command17.enchantedbooklib.api.config.LibConfigs;
import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.hammering.config.ModConfig;
import com.github.command17.hammering.enchantment.ModEnchantments;
import com.github.command17.hammering.enchantment.effect.ModEnchantmentEffectComponents;
import com.github.command17.hammering.event.ModEvents;
import com.github.command17.hammering.item.ModItems;
import com.github.command17.hammering.item.tab.ModCreativeModeTabs;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public final class Hammering {
    public static final String MOD_ID = "hammering";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final ModConfig CONFIG = LibConfigs.registerConfig(resource("config"), new ModConfig());

    public static void init() {
        LOGGER.info("Initializing...");

        ModItems.register();
        ModCreativeModeTabs.register();
        ModEnchantmentEffectComponents.register();
        ModEnchantments.register();

        EventManager.registerListeners(ModEvents.class);

        LOGGER.info("Initialized.");
    }

    public static ResourceLocation resource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
