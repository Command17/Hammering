package com.github.command17.hammering.util;

import com.github.command17.hammering.Hammering;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class BlockTags {
        public static final TagKey<Block> MINEABLE_WITH_HAMMER = key("mineable/hammer");

        private static TagKey<Block> key(String name) {
            return TagKey.create(Registries.BLOCK, Hammering.resource(name));
        }
    }

    public static class ItemTags {
        public static final TagKey<Item> HAMMER = key("hammer");

        private static TagKey<Item> key(String name) {
            return TagKey.create(Registries.ITEM, Hammering.resource(name));
        }
    }
}
