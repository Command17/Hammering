package com.github.command17.hammering.event;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.util.BlockUtil;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.utils.value.IntValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public final class ModEvents {
    private static EventResult breakBlock(Level level, BlockPos pos, BlockState state, ServerPlayer player, @Nullable IntValue xp) {
        ItemStack stack = player.getMainHandItem();
        if (!player.isShiftKeyDown() && !player.isCreative() && !stack.isEmpty() && stack.isEnchanted()) {
            BlockUtil.findBlocks(stack, player, pos, level).forEach((blockPos) -> {
                BlockState blockState = level.getBlockState(blockPos);
                if (blockPos != pos && BlockUtil.canMineOther(stack, state, blockState)) {
                    blockState.getBlock().playerDestroy(level, player, blockPos, blockState, level.getBlockEntity(blockPos), stack);
                    level.destroyBlock(blockPos, false, player);
                    stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
            });
        }

        return EventResult.pass();
    }

    public static void register() {
        BlockEvent.BREAK.register(ModEvents::breakBlock);
        Hammering.LOGGER.info("Registered Events.");
    }
}
