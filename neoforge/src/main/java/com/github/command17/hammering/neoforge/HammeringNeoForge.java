package com.github.command17.hammering.neoforge;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.client.HammeringClient;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(Hammering.MOD_ID)
public final class HammeringNeoForge {
    public HammeringNeoForge(ModContainer container) {
        container.registerConfig(ModConfig.Type.STARTUP, Hammering.CONFIG_SPEC);
        container.registerConfig(ModConfig.Type.SERVER, Hammering.SERVER_CONFIG_SPEC);
        Hammering.init();
        EnvExecutor.runInEnv(Env.CLIENT, () -> HammeringClient::init);
    }
}
