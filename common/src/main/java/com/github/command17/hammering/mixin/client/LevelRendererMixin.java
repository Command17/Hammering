package com.github.command17.hammering.mixin.client;

import com.github.command17.hammering.client.BlockEffectRenderState;
import com.github.command17.hammering.common.util.HammerUtil;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.SheetedDecalTextureGenerator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ShapeRenderer;
import net.minecraft.client.renderer.state.BlockBreakingRenderState;
import net.minecraft.client.renderer.state.BlockOutlineRenderState;
import net.minecraft.client.renderer.state.LevelRenderState;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.stream.Stream;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    private @Nullable ClientLevel level;

    @Unique
    private final ArrayList<BlockEffectRenderState> hammering$hammerOutlineRenderStates = new ArrayList<>(27); // Initial capacity is 27 as Hammering with max level would break 27 blocks

    @Inject(method = "extractBlockOutline", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;"))
    private void hammering$extractHammerOutlineRenderState(Camera camera, LevelRenderState renderState, CallbackInfo ci, @Local BlockPos pos, @Local BlockState state) {
        this.hammering$hammerOutlineRenderStates.clear();
        Player player = this.minecraft.player;
        if (player == null || this.level == null || !HammerUtil.canPlayerUseAreaMine(player)) {
            return;
        }

        ItemStack stack = player.getMainHandItem();
        if (stack.isEmpty()) {
            return;
        }

        Stream<BlockPos> rawPositions = HammerUtil.findBlocks(stack, player, pos, this.level);
        rawPositions.forEach((otherPos) -> {
            if (pos.equals(otherPos)) {
                return;
            }

            BlockState otherState = this.level.getBlockState(otherPos);
            if (otherState.isAir() || !HammerUtil.canMineOther(stack, state, otherState)) {
                return;
            }

            VoxelShape shape = otherState.getShape(this.level, otherPos);
            BlockPos posClone = new BlockPos(otherPos); // This fixes a bug where the entire list is filled with the same value. No, I don't like this
            this.hammering$hammerOutlineRenderStates.add(new BlockEffectRenderState(posClone, otherState, shape));
        });
    }

    @Inject(method = "renderHitOutline", at = @At("HEAD"))
    private void hammering$renderFakeOutline(PoseStack poseStack, VertexConsumer vertexConsumer, double camX, double camY, double camZ, BlockOutlineRenderState blockOutlineRenderState, int i, float g, CallbackInfo ci) {
        for (BlockEffectRenderState renderState: this.hammering$hammerOutlineRenderStates) {
            BlockPos pos = renderState.pos();
            VoxelShape shape = renderState.shape();
            ShapeRenderer.renderShape(
                    poseStack,
                    vertexConsumer,
                    shape,
                    pos.getX() - camX,
                    pos.getY() - camY,
                    pos.getZ() - camZ,
                    i,
                    g
            );
        }
    }

    @Inject(method = "renderBlockDestroyAnimation", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
    private void hammering$renderFakeBlockBreaking(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, LevelRenderState levelRenderState, CallbackInfo ci, @Local BlockBreakingRenderState blockBreakingRenderState) {
        Vec3 vec3 = levelRenderState.cameraRenderState.pos;
        double camX = vec3.x();
        double camY = vec3.y();
        double camZ = vec3.z();
        for (BlockEffectRenderState renderState: this.hammering$hammerOutlineRenderStates) {
            BlockPos pos = renderState.pos();
            BlockState state = renderState.state();
            poseStack.pushPose();
            poseStack.translate(pos.getX() - camX, pos.getY() - camY, pos.getZ() - camZ);
            PoseStack.Pose pose = poseStack.last();
            VertexConsumer decal = new SheetedDecalTextureGenerator(bufferSource.getBuffer(ModelBakery.DESTROY_TYPES.get(blockBreakingRenderState.progress)), pose, 1);
            this.minecraft.getBlockRenderer().renderBreakingTexture(state, pos, blockBreakingRenderState, poseStack, decal);
            poseStack.popPose();
        }
    }
}
