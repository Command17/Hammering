package com.github.command17.hammering.fabric;

import com.github.command17.hammering.Hammering;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public final class HammeringFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(Hammering.MOD_ID, ModConfig.Type.STARTUP, Hammering.CONFIG_SPEC);
        NeoForgeConfigRegistry.INSTANCE.register(Hammering.MOD_ID, ModConfig.Type.SERVER, Hammering.SERVER_CONFIG_SPEC);
        Hammering.init();
    }
}
