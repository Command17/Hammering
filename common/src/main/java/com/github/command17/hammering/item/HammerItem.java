package com.github.command17.hammering.item;

import com.github.command17.hammering.util.ModTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class HammerItem extends Item {
    private final Tier tier;

    public HammerItem(Tier tier, Properties properties, float durabilityModifier) {
        super(properties.component(
                DataComponents.TOOL, tier.createToolProperties(ModTags.BlockTags.MINEABLE_WITH_HAMMER)
        ).durability((int) (tier.getUses() * durabilityModifier)));

        this.tier = tier;
    }

    public HammerItem(Tier tier, Properties properties) {
        this(tier, properties, 1);
    }

    public Tier getTier() {
        return tier;
    }

    @Override
    public int getEnchantmentValue() {
        return this.tier.getEnchantmentValue();
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack otherStack) {
        return this.tier.getRepairIngredient().test(otherStack) || super.isValidRepairItem(stack, otherStack);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity enemy, LivingEntity entity) {
        return true;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity enemy, LivingEntity entity) {
        stack.hurtAndBreak(2, entity, EquipmentSlot.MAINHAND);
    }
}
