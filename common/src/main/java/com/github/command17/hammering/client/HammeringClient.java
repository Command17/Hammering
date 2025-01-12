package com.github.command17.hammering.client;

import com.github.command17.hammering.Hammering;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class HammeringClient {
    public static void init() {
        Hammering.LOGGER.info("Initializing Client...");
        Hammering.LOGGER.info("Initialized Client.");
    }
}
