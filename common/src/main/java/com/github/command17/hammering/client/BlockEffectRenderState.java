package com.github.command17.hammering.client;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public record BlockEffectRenderState(BlockPos pos, BlockState state, VoxelShape shape) {
}
