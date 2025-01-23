package com.github.command17.hammering.item;

import com.github.command17.enchantedbooklib.api.registry.IRegistrySupplier;
import com.github.command17.enchantedbooklib.api.registry.RegistryHelper;
import com.github.command17.hammering.Hammering;
import net.minecraft.world.item.*;

public class ModItems {
    private static final RegistryHelper.ItemHelper REGISTRY = RegistryHelper.createItemHelper(Hammering.MOD_ID);

    public static final IRegistrySupplier<Item> IRON_HAMMER = createHammer("iron_hammer", ToolMaterial.IRON, 5, -3, of());
    public static final IRegistrySupplier<Item> GOLD_HAMMER = createHammer("gold_hammer", ToolMaterial.GOLD, 5, -3, of());
    public static final IRegistrySupplier<Item> DIAMOND_HAMMER = createHammer("diamond_hammer", ToolMaterial.DIAMOND, 4, -3, of());
    public static final IRegistrySupplier<Item> NETHERITE_HAMMER = createHammer("netherite_hammer", ToolMaterial.NETHERITE, 4, -3, of().fireResistant());

    private static IRegistrySupplier<Item> createHammer(String name, ToolMaterial tier, int attackDamage, float attackSpeed, Item.Properties properties) {
        return REGISTRY.registerItem(name, (props) -> new HammerItem(tier, attackDamage, attackSpeed, properties, 2), properties);
    }

    private static Item.Properties of() {
        return new Item.Properties();
    }

    public static void register() {
        REGISTRY.register();
        Hammering.LOGGER.info("Registered Items.");
    }
}
