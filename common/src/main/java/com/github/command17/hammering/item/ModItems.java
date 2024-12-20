package com.github.command17.hammering.item;

import com.github.command17.hammering.Hammering;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;

public class ModItems {
    private static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(Hammering.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> IRON_HAMMER = createHammer("iron_hammer", Tiers.IRON, 5, -2.8f, of());
    public static final RegistrySupplier<Item> GOLD_HAMMER = createHammer("gold_hammer", Tiers.GOLD, 5, -2.8f, of());
    public static final RegistrySupplier<Item> DIAMOND_HAMMER = createHammer("diamond_hammer", Tiers.DIAMOND, 4, -2.8f, of());
    public static final RegistrySupplier<Item> NETHERITE_HAMMER = createHammer("netherite_hammer", Tiers.NETHERITE, 4, -2.8f, of().fireResistant());

    private static RegistrySupplier<Item> createHammer(String name, Tier tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return REGISTRY.register(name, () -> new HammerItem(tier, properties.attributes(DiggerItem.createAttributes(tier, attackDamage, attackSpeed)), 2));
    }

    private static Item.Properties of() {
        return new Item.Properties();
    }

    public static void register() {
        REGISTRY.register();

        Hammering.LOGGER.info("Registered Items.");
    }
}
