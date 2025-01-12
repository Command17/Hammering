package com.github.command17.hammering.neoforge;

import com.github.command17.enchantedbooklib.api.util.EnvSide;
import com.github.command17.enchantedbooklib.api.util.EnvSideExecutor;
import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.client.HammeringClient;
import net.neoforged.fml.common.Mod;

@Mod(Hammering.MOD_ID)
public final class HammeringNeoForge {
    public HammeringNeoForge() {
        Hammering.init();
        EnvSideExecutor.runOn(EnvSide.CLIENT, () -> HammeringClient::init);
    }
}
