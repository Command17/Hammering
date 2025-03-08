package com.github.command17.hammering.mixin.client;

import com.github.command17.hammering.util.BlockUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.core.BlockPos;
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
public class LevelRendererMixin {
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "renderHitOutline", at = @At("HEAD"))
    private void hammering$renderHitOutline(PoseStack poseStack, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state, int i, CallbackInfo ci) {
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

                        if (BlockUtil.canMineOther(stack, targetState, blockState) && !blockPos.equals(pos)) {
                            ShapeRenderer.renderShape(
                                    poseStack,
                                    vertexConsumer,
                                    outlineShape,
                                    (double) blockPos.getX() - cameraX,
                                    (double) blockPos.getY() - cameraY,
                                    (double) blockPos.getZ() - cameraZ,
                                    i
                            );
                        }
                    });
                }
            }
        }
    }
}