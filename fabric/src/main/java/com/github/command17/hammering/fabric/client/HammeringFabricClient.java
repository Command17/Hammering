package com.github.command17.hammering.fabric.client;

import com.github.command17.hammering.client.HammeringClient;
import net.fabricmc.api.ClientModInitializer;

public class HammeringFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HammeringClient.init();
    }
}
