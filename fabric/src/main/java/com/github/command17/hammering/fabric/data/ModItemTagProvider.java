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
        getOrCreateTagBuilder(ModTags.ItemTags.HAMMER)
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLD_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get());

        getOrCreateTagBuilder(ModTags.ItemTags.MINING_TOOLS)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .forceAddTag(ModTags.ItemTags.HAMMER);
    }
}
