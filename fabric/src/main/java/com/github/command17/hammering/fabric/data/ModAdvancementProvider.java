package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder hammerTime = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.IRON_HAMMER.get()),
                        Component.translatable("advancement.hammering.hammer_time.title"),
                        Component.translatable("advancement.hammering.hammer_time.info"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true,
                        true,
                        false))
                .parent(ResourceLocation.withDefaultNamespace("adventure/root")) // No idea how to replace this
                .addCriterion("has_iron_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.IRON_HAMMER.get()))
                .save(consumer, Hammering.resource("hammer_time").toString());

        Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.NETHERITE_HAMMER.get()),
                        Component.translatable("advancement.hammering.hammer_collection.title"),
                        Component.translatable("advancement.hammering.hammer_collection.info"),
                        Optional.empty(),
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false))
                .parent(hammerTime)
                .rewards(AdvancementRewards.Builder.experience(500))
                .addCriterion("has_hammers", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ModItems.NETHERITE_HAMMER.get(), ModItems.IRON_HAMMER.get(), ModItems.GOLD_HAMMER.get(), ModItems.DIAMOND_HAMMER.get()))
                .save(consumer, Hammering.resource("hammer_collection").toString());


    }
}
