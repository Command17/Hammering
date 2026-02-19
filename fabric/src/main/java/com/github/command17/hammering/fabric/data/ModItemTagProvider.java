package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.common.item.ModItems;
import com.github.command17.hammering.common.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NullMarked;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @NullMarked
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ModTags.ItemTags.HAMMER)
                .add(ModItems.IRON_HAMMER.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.DIAMOND_HAMMER.get())
                .add(ModItems.NETHERITE_HAMMER.get());

        valueLookupBuilder(ModTags.ItemTags.MINING_TOOLS)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.VANISHING_ENCHANTABLE)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        // Tools

        valueLookupBuilder(ItemTags.AXES)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.SHOVELS)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.HOES)
                .addOptionalTag(ModTags.ItemTags.HAMMER);

        valueLookupBuilder(ItemTags.PICKAXES)
                .addOptionalTag(ModTags.ItemTags.HAMMER);
    }
}
