package com.github.command17.hammering.item.tab;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.enchantment.ModEnchantments;
import com.github.command17.hammering.item.ModItems;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public final class ModCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Hammering.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<CreativeModeTab> MAIN = REGISTRY.register("main",
            () -> CreativeTabRegistry.create((builder) -> builder
                    .title(Component.translatable("itemGroup.hammering.main"))
                    .icon(() -> new ItemStack(ModItems.NETHERITE_HAMMER.get()))
                    .displayItems((display, output) -> {
                        if (!Hammering.CONFIG.showTab.get()) {
                            return;
                        }

                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.GOLDEN_HAMMER.get());
                        output.accept(ModItems.DIAMOND_HAMMER.get());
                        output.accept(ModItems.NETHERITE_HAMMER.get());

                        // Enchantments
                        display.holders().lookup(Registries.ENCHANTMENT).flatMap((registryLookup) -> registryLookup.get(ModEnchantments.HAMMERING)).ifPresent((enchantment) -> {
                            for (int i = 0; i < enchantment.value().getMaxLevel(); i++) {
                                output.accept(EnchantmentHelper.createBook(new EnchantmentInstance(enchantment, i + 1)));
                            }
                        });
                    })));

    public static void register() {
        REGISTRY.register();
        Hammering.LOGGER.info("Registered Creative Mode Tabs.");
    }
}
