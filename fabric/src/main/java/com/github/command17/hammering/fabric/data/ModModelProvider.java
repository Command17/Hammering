package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        handheld(ModItems.IRON_HAMMER.get(), generator);
        handheld(ModItems.GOLDEN_HAMMER.get(), generator);
        handheld(ModItems.DIAMOND_HAMMER.get(), generator);
        handheld(ModItems.NETHERITE_HAMMER.get(), generator);
    }

    private void handheld(Item item, ItemModelGenerators generator) {
        generator.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
