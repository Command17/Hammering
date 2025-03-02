package com.github.command17.hammering.mixin.client;

import com.github.command17.hammering.util.BlockUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.BlockDestructionProgress;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    @Shadow
    private static void renderShape(PoseStack poseStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double d, double e, double f, float g, float h, float i, float j) {
    }

    @Shadow @Final private Int2ObjectMap<BlockDestructionProgress> destroyingBlocks;

    @Shadow public abstract void destroyBlockProgress(int breakerId, BlockPos pos, int progress);

    @Inject(method = "renderHitOutline", at = @At("HEAD"), cancellable = true)
    private void hammering$renderHitOutline(PoseStack poseStack, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (this.minecraft.player == null || this.minecraft.level == null) {
            return;
        }

        ItemStack stack = this.minecraft.player.getMainHandItem();

        if (this.minecraft.hitResult instanceof BlockHitResult target) {
            BlockPos targetPos = target.getBlockPos();
            BlockState targetState = this.minecraft.level.getBlockState(targetPos);

            if (!entity.isShiftKeyDown() && !stack.isEmpty()) {
                if (entity instanceof Player player) {
                    if (player.isCreative()) {
                        return;
                    }

                    BlockUtil.findBlocks(
                            stack,
                            this.minecraft.player,
                            targetPos,
                            this.minecraft.level
                    ).forEach((blockPos) -> {
                        BlockState blockState = this.minecraft.level.getBlockState(blockPos);
                        VoxelShape outlineShape = blockState.getShape(this.minecraft.level, blockPos, CollisionContext.of(entity));

                        if (BlockUtil.canMineOther(stack, targetState, blockState)) {
                            renderShape(
                                    poseStack,
                                    vertexConsumer,
                                    outlineShape,
                                    (double) blockPos.getX() - cameraX,
                                    (double) blockPos.getY() - cameraY,
                                    (double) blockPos.getZ() - cameraZ,
                                    0,
                                    0,
                                    0,
                                    0.4f
                            );
                        }
                    });
                    ci.cancel();
                }
            }
        }
    }
}
