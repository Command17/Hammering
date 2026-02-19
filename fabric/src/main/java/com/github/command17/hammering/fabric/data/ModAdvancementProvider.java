package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.common.item.ModItems;
import com.github.command17.hammering.common.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NullMarked;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @SuppressWarnings("removal")
    @NullMarked
    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        HolderGetter<Item> itemLookup = provider.lookupOrThrow(Registries.ITEM);
        ItemPredicate anyHammerPredicate = ItemPredicate.Builder.item().of(itemLookup, ModTags.ItemTags.HAMMER).build();

        AdvancementHolder hammerTime = Advancement.Builder.advancement()
                .display(new DisplayInfo(new ItemStack(ModItems.IRON_HAMMER.get()),
                        Component.translatable("advancement.hammering.hammer_time.title"),
                        Component.translatable("advancement.hammering.hammer_time.info"),
                        Optional.empty(),
                        AdvancementType.TASK,
                        true,
                        true,
                        false))
                .parent(Identifier.withDefaultNamespace("adventure/root"))
                .addCriterion("has_any_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(anyHammerPredicate))
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
                        ModItems.NETHERITE_HAMMER.get(), ModItems.IRON_HAMMER.get(), ModItems.GOLDEN_HAMMER.get(), ModItems.DIAMOND_HAMMER.get()))
                .save(consumer, Hammering.resource("hammer_collection").toString());


    }
}
