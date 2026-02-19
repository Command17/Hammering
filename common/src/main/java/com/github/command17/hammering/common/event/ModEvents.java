package com.github.command17.hammering.common.event;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.common.util.HammerUtil;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.utils.value.IntValue;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public final class ModEvents {
    private static EventResult breakBlock(Level level, BlockPos pos, BlockState state, ServerPlayer player, @Nullable IntValue xp) {
        ItemStack stack = player.getMainHandItem();
        if (HammerUtil.canPlayerUseAreaMine(player) && !stack.isEmpty()) {
            HammerUtil.findBlocks(stack, player, pos, level).forEach((otherPos) -> {
                BlockState otherState = level.getBlockState(otherPos);
                if (!otherPos.equals(pos) && HammerUtil.canMineOther(stack, state, otherState)) {
                    otherState.getBlock().playerDestroy(level, player, otherPos, otherState, level.getBlockEntity(otherPos), stack);
                    level.destroyBlock(otherPos, false, player);
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
