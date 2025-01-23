package com.github.command17.hammering.item;

import com.github.command17.hammering.util.ModTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class HammerItem extends Item {
    private final ToolMaterial toolMaterial;

    public HammerItem(ToolMaterial tier, int attackDamage, float attackSpeed, Properties properties, float durabilityModifier) {
        super(tier.applyToolProperties(properties, ModTags.BlockTags.MINEABLE_WITH_HAMMER, attackDamage, attackSpeed).durability((int) (tier.durability() * durabilityModifier)));

        this.toolMaterial = tier;
    }

    public HammerItem(ToolMaterial tier, int attackDamage, float attackSpeed, Properties properties) {
        this(tier, attackDamage, attackSpeed, properties, 1);
    }

    public ToolMaterial getToolMaterial() {
        return toolMaterial;
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