package com.github.command17.hammering.util;

import com.github.command17.hammering.enchantment.effect.ModEnchantmentEffectComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.stream.Stream;

public class BlockUtil {
    public static Stream<BlockPos> findBlocksInRadius(int radius, int depth, Player player, BlockPos originPos, Level level) {
        Stream<BlockPos> potentialBrokenBlocks = Stream.of();

        Vec3 eyePosition = player.getEyePosition();
        Vec3 rotation = player.getViewVector(1);

        double reach = player.getAttributeValue(Attributes.BLOCK_INTERACTION_RANGE);

        Vec3 combined = eyePosition.add(rotation.x * reach, rotation.y * reach, rotation.z * reach);

        BlockHitResult result = level.clip(new ClipContext(player.getEyePosition(), combined, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));

        if (result.getType() == HitResult.Type.BLOCK) {
            Direction side = result.getDirection();

            boolean doX = side.getStepX() == 0;
            boolean doY = side.getStepY() == 0;
            boolean doZ = side.getStepZ() == 0;

            BlockPos begin = new BlockPos(doX ? -radius : 0, doY ? -radius : 0, doZ ? -radius : 0);
            BlockPos end = new BlockPos(doX ? radius : depth * -side.getStepX(), doY ? radius : depth * -side.getStepY(), doZ ? radius : depth * -side.getStepZ());

            return BlockPos.betweenClosedStream(originPos.offset(begin), originPos.offset(end));
        }

        return potentialBrokenBlocks;
    }


    public static Stream<BlockPos> findBlocks(ItemStack stack, Player player, BlockPos originPos, Level level) {
        int enchantmentLevel = 0;

        for (var enchantment: stack.getEnchantments().keySet()) {
            if (enchantment.value().effects().has(ModEnchantmentEffectComponents.AREA_MINE.get())) {
                enchantmentLevel += EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
            }
        }

        if (enchantmentLevel > 0) return findBlocksInRadius(1, enchantmentLevel - 1, player, originPos, level);

        return Stream.of();
    }

    public static boolean canMineOther(ItemStack stack, BlockState state, BlockState otherState) {
        return stack.getItem().isCorrectToolForDrops(stack, otherState) && otherState.getBlock().defaultDestroyTime() <= state.getBlock().defaultDestroyTime();
    }
}
