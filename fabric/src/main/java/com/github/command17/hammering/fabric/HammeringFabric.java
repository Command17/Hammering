package com.github.command17.hammering.fabric;

import com.github.command17.hammering.Hammering;
import net.fabricmc.api.ModInitializer;

public class HammeringFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Hammering.init();
    }
}
