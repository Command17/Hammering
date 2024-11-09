package com.github.command17.hammering.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;

public class HammerItem extends TieredItem {
    public HammerItem(Tier tier, Properties properties) {
        super(tier, properties.component(DataComponents.TOOL, createToolProperties(tier,
                BlockTags.MINEABLE_WITH_PICKAXE,
                BlockTags.MINEABLE_WITH_AXE,
                BlockTags.MINEABLE_WITH_SHOVEL,
                BlockTags.MINEABLE_WITH_HOE
        )));
    }

    @SafeVarargs
    public static Tool createToolProperties(Tier tier, TagKey<Block>... tagKeys) {
        ArrayList<Tool.Rule> rules = new ArrayList<>();

        rules.add(Tool.Rule.deniesDrops(tier.getIncorrectBlocksForDrops()));

        for (TagKey<Block> tagKey: tagKeys) {
            rules.add(Tool.Rule.minesAndDrops(tagKey, tier.getSpeed()));
        }

        return new Tool(rules, 1.0F, 1);
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
