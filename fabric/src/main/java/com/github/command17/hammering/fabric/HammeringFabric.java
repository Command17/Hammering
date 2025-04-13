package com.github.command17.hammering.fabric;

import com.github.command17.hammering.Hammering;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public final class HammeringFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ConfigRegistry.INSTANCE.register(Hammering.MOD_ID, ModConfig.Type.STARTUP, Hammering.CONFIG_SPEC);
        ConfigRegistry.INSTANCE.register(Hammering.MOD_ID, ModConfig.Type.SERVER, Hammering.SERVER_CONFIG_SPEC);
        Hammering.init();
    }
}
