package com.github.command17.hammering.common.enchantment;

import com.github.command17.hammering.Hammering;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public final class ModEnchantments {
    public static final ResourceKey<Enchantment> HAMMERING = of("hammering");

    private static ResourceKey<Enchantment> of(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, Hammering.resource(id));
    }

    public static void register() {
        Hammering.LOGGER.info("Registered Enchantments.");
    }
}
