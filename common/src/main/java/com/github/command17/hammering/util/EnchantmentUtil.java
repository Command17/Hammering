package com.github.command17.hammering.util;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantmentUtil {
    public static int getCountOfEnchantmentComponent(ItemStack stack, DataComponentType<?> componentType) {
        int enchantmentLevel = 0;

        for (var enchantment: stack.getEnchantments().keySet()) {
            if (enchantment.value().effects().has(componentType)) {
                enchantmentLevel += EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
            }
        }

        return enchantmentLevel;
    }
}
