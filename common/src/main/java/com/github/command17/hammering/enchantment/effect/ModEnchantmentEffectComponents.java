package com.github.command17.hammering.enchantment.effect;

import com.github.command17.hammering.Hammering;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;

import java.util.function.Supplier;

public final class ModEnchantmentEffectComponents {
    private static final DeferredRegister<DataComponentType<?>> REGISTRY = DeferredRegister.create(Hammering.MOD_ID, Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<Unit>> AREA_MINE = register("area_mine",
            () -> DataComponentType.<Unit>builder().persistent(Unit.CODEC).build());

    private static<T> RegistrySupplier<DataComponentType<T>> register(String name, Supplier<DataComponentType<T>> componentType) {
        return REGISTRY.register(name, componentType);
    }

    public static void register() {
        REGISTRY.register();
        Hammering.LOGGER.info("Registered Enchantment Effect Components.");
    }
}
