package com.github.command17.hammering.config;

import com.github.command17.enchantedbooklib.api.config.ConfigData;
import com.github.command17.enchantedbooklib.api.config.annotation.Config;
import com.github.command17.enchantedbooklib.api.config.annotation.Entry;
import com.github.command17.enchantedbooklib.api.config.entry.IntConfigEntry;
import com.github.command17.hammering.Hammering;

@Config(name = Hammering.MOD_ID + "-config")
public class ModConfig extends ConfigData {
    @Entry(name = "areaMineRadius", comment = "Radius of AreaMine (or Hammering). Default Value: 1", synced = true)
    public IntConfigEntry areaMineRadius = new IntConfigEntry(1);

    @Entry(name = "areaMineDepthPerLevel", comment = "Increase of depth of AreaMine (or Hammering) per enchantment level. Default Value: 1", synced = true)
    public IntConfigEntry areaMineDepthPerLevel = new IntConfigEntry(1);
}
