package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.item.ModItems;
import com.github.command17.hammering.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ModTags.ItemTags.HAMMER)
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get());

        valueLookupBuilder(ModTags.ItemTags.MINING_TOOLS)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        // Tools

        valueLookupBuilder(ItemTags.AXES)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.SHOVELS)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.HOES)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.PICKAXES)
                .forceAddTag(ModTags.ItemTags.HAMMER);
    }
}
