package com.github.command17.hammering.neoforge;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.client.HammeringClient;
import dev.architectury.platform.Platform;
import net.neoforged.fml.common.Mod;

@Mod(Hammering.MOD_ID)
public class HammeringNeoForge {
    public HammeringNeoForge() {
        Hammering.init();

        if (Platform.getEnv().isClient()) {
            HammeringClient.init();
        }
    }
}
