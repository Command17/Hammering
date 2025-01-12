package com.github.command17.hammering.integration.fabric.modmenu;

import com.github.command17.enchantedbooklib.api.util.PlatformHelper;
import com.github.command17.hammering.integration.clothconfig.ClothConfigIntegration;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public final class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (PlatformHelper.isModLoaded("cloth-config")) {
            return (parent) -> ClothConfigIntegration.createConfigScreen().setParentScreen(parent).build();
        }

        return ModMenuApi.super.getModConfigScreenFactory();
    }
}
