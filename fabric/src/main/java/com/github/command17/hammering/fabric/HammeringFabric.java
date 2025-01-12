package com.github.command17.hammering.fabric;

import com.github.command17.enchantedbooklib.api.entrypoint.fabric.EnchantedModInitializer;
import com.github.command17.hammering.Hammering;

public final class HammeringFabric implements EnchantedModInitializer {
    @Override
    public void onInitialize() {
        Hammering.init();
    }
}
