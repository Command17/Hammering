package com.github.command17.hammering.enchantment.effect;

import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.hammering.Hammering;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;

public final class ModEnchantmentEffectComponents {
    private static final RegistryHelper<DataComponentType<?>> REGISTRY = RegistryHelper.create(Hammering.MOD_ID, Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);

    public static final IRegistrySupplier<DataComponentType<Unit>> AREA_MINE = REGISTRY.register("area_mine",
            () -> DataComponentType.<Unit>builder().persistent(Unit.CODEC).build());

    public static void register() {
        REGISTRY.register();

        Hammering.LOGGER.info("Registered Enchantment Effect Components.");
    }
}
