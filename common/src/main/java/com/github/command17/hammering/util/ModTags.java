package com.github.command17.hammering.util;

import com.github.command17.hammering.Hammering;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModTags {
    private ModTags() {}

    public static class BlockTags {
        private BlockTags() {}

        public static final TagKey<Block> MINEABLE_WITH_HAMMER = tag("mineable/hammer");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, Hammering.resource(name));
        }

        private static TagKey<Block> conventionTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }

    public static class ItemTags {
        private ItemTags() {}

        public static final TagKey<Item> HAMMER = tag("hammer");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, Hammering.resource(name));
        }

        private static TagKey<Item> conventionTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", name));
        }
    }
}
