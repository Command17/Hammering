package com.github.command17.hammering.util;

import com.github.command17.hammering.Hammering;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class ItemTags {
        public static final TagKey<Item> HAMMER = key("hammer");

        private static TagKey<Item> key(String name) {
            return TagKey.create(Registries.ITEM, Hammering.resource(name));
        }
    }
}
