package com.github.command17.hammering.integration.clothconfig;

import com.github.command17.hammering.Hammering;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.network.chat.Component;

public final class ClothConfigIntegration {
    private ClothConfigIntegration() {}

    public static ConfigBuilder createConfigScreen() {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Component.translatable("title.hammering.config"))
                .setSavingRunnable(Hammering.CONFIG::save);

        ConfigCategory mainCategory = builder.getOrCreateCategory(Component.translatable("category.config.hammering.main"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        mainCategory.addEntry(entryBuilder.startIntField(Component.translatable("field.config.hammering.areaMineRadius"), Hammering.CONFIG.areaMineRadius.get())
                        .setDefaultValue(Hammering.CONFIG.areaMineRadius.getDefaultValue())
                        .setSaveConsumer((i) -> Hammering.CONFIG.areaMineRadius.setValue(i))
                        .build());

        mainCategory.addEntry(entryBuilder.startIntField(Component.translatable("field.config.hammering.areaMineDepthPerLevel"), Hammering.CONFIG.areaMineDepthPerLevel.get())
                .setDefaultValue(Hammering.CONFIG.areaMineDepthPerLevel.getDefaultValue())
                .setSaveConsumer((i) -> Hammering.CONFIG.areaMineDepthPerLevel.setValue(i))
                .build());

        return builder;
    }
}
