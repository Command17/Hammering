package com.github.command17.hammering.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class HammeringDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModBlockTagProvider::new);

        // English
        pack.addProvider((output, provider) -> new ModLanguageProviders.EnglishLanguageProvider(output, "en_us", provider));

        // German
        pack.addProvider((output, provider) -> new ModLanguageProviders.GermanLanguageProvider(output, "de_de", provider));

        // Austrian
        pack.addProvider((output, provider) -> new ModLanguageProviders.GermanLanguageProvider(output, "de_at", provider));

        // Swiss German
        pack.addProvider((output, provider) -> new ModLanguageProviders.GermanLanguageProvider(output, "de_ch", provider));

        // Bavarian German
        pack.addProvider((output, provider) -> new ModLanguageProviders.GermanLanguageProvider(output, "bar", provider));

        // Saxon German
        pack.addProvider((output, provider) -> new ModLanguageProviders.GermanLanguageProvider(output, "sxu", provider));
    }
}
