package com.github.command17.hammering.fabric.client;

import com.github.command17.enchantedbooklib.api.client.entrypoint.fabric.ClientEnchantedModInitializer;
import com.github.command17.hammering.client.HammeringClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class HammeringFabricClient implements ClientEnchantedModInitializer {
    @Override
    public void onInitializeClient() {
        HammeringClient.init();
    }
}
