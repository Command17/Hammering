package com.github.command17.hammering.item;

import com.github.command17.hammering.Hammering;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;

import java.util.function.Supplier;

public final class ModItems {
    private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Hammering.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> IRON_HAMMER = registerHammer("iron_hammer", ToolMaterial.IRON, 5, -3, of());
    public static final RegistrySupplier<Item> GOLDEN_HAMMER = registerHammer("golden_hammer", ToolMaterial.GOLD, 5, -3, of());
    public static final RegistrySupplier<Item> DIAMOND_HAMMER = registerHammer("diamond_hammer", ToolMaterial.DIAMOND, 4, -3, of());
    public static final RegistrySupplier<Item> NETHERITE_HAMMER = registerHammer("netherite_hammer", ToolMaterial.NETHERITE, 6, -2.75f, of().fireResistant());

    private static RegistrySupplier<Item> registerHammer(String name, ToolMaterial tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return register(name,
                () -> new HammerItem(
                        tier,
                        attackDamage,
                        attackSpeed,
                        properties.setId(key(name)),
                        Hammering.CONFIG.hammerDurabilityMultiplier.get()
                ));
    }

    private static RegistrySupplier<Item> register(String name, Supplier<Item> item) {
        return REGISTRY.register(name, item);
    }

    private static Item.Properties of() {
        return new Item.Properties();
    }

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, Hammering.resource(name));
    }

    public static void register() {
        REGISTRY.register();
        Hammering.LOGGER.info("Registered Items.");
    }
}
