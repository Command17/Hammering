package com.github.command17.hammering.fabric.data;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.enchantment.ModEnchantments;
import com.github.command17.hammering.item.ModItems;
import com.github.command17.hammering.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public final class ModLanguageProviders {
    private ModLanguageProviders() {}

    private static String translationKey(String prefix, String suffix) {
        return prefix + Hammering.MOD_ID + suffix;
    }

    public static class EnglishLanguageProvider extends FabricLanguageProvider {
        public EnglishLanguageProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, languageCode, registryLookup);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
            // Hammers
            builder.add(ModItems.IRON_HAMMER.get(), "Iron Hammer");
            builder.add(ModItems.GOLDEN_HAMMER.get(), "Golden Hammer");
            builder.add(ModItems.DIAMOND_HAMMER.get(), "Diamond Hammer");
            builder.add(ModItems.NETHERITE_HAMMER.get(), "Netherite Hammer");

            // Creative Mode Tabs
            builder.add(translationKey("itemGroup.", ".main"), "Hammers");

            // Advancements
            builder.add(translationKey("advancement.", ".hammer_time.title"), "Hammer Time!");
            builder.add(translationKey("advancement.", ".hammer_time.info"), "Get an Iron Hammer");
            builder.add(translationKey("advancement.", ".hammer_collection.title"), "Hammer Collection");
            builder.add(translationKey("advancement.", ".hammer_collection.info"), "Get every Hammer");

            // Enchantments
            builder.addEnchantment(ModEnchantments.HAMMERING, "Hammering");
            builder.add(translationKey("enchantment.", ".hammering.desc"), "Allows you to mine a large area at once.");

            // Tags
            builder.add(ModTags.ItemTags.HAMMER, "Hammer");
            builder.add(ModTags.BlockTags.MINEABLE_WITH_HAMMER, "Mineable With Hammer");
        }
    }

    public static class GermanLanguageProvider extends FabricLanguageProvider {
        public GermanLanguageProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<HolderLookup.Provider> registryLookup) {
            super(dataOutput, languageCode, registryLookup);
        }

        @Override
        public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder) {
            // Hammers
            builder.add(ModItems.IRON_HAMMER.get(), "Eisenhammer");
            builder.add(ModItems.GOLDEN_HAMMER.get(), "Goldhammer");
            builder.add(ModItems.DIAMOND_HAMMER.get(), "Diamanthammer");
            builder.add(ModItems.NETHERITE_HAMMER.get(), "Netherithammer");

            // Creative Mode Tabs
            builder.add(translationKey("itemGroup.", ".main"), "Hämmer");

            // Advancements
            builder.add(translationKey("advancement.", ".hammer_time.title"), "Hämmer Zeit!");
            builder.add(translationKey("advancement.", ".hammer_time.info"), "Bekomme ein Eisenhammer");
            builder.add(translationKey("advancement.", ".hammer_collection.title"), "Hammer Kollektion");
            builder.add(translationKey("advancement.", ".hammer_collection.info"), "Bekomme jeden Hammer");

            // Enchantments
            builder.addEnchantment(ModEnchantments.HAMMERING, "Hämmern");
            builder.add(translationKey("enchantment.", ".hammering.desc"), "Gibt dir die Möglickeit, einen großen Bereich auf einmal abzubauen.");

            builder.add(ModTags.ItemTags.HAMMER, "Hammer");
            builder.add(ModTags.BlockTags.MINEABLE_WITH_HAMMER, "Abbaubar mit Hammer");
        }
    }
}
