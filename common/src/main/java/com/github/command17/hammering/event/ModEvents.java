package com.github.command17.hammering.event;

import com.github.command17.enchantedbooklib.api.event.EventManager;
import com.github.command17.enchantedbooklib.api.event.annotation.EventListener;
import com.github.command17.enchantedbooklib.api.events.level.BlockEvent;
import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.util.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModEvents {
    @EventListener
    private static void breakBlock(BlockEvent.Break event) {
        Player player = event.getPlayer();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();

        ItemStack stack = player.getMainHandItem();

        if (!player.isShiftKeyDown() && !player.isCreative() && !stack.isEmpty()) {
            BlockUtil.findBlocks(stack, player, pos, level).forEach((blockPos) -> {
                BlockState blockState = level.getBlockState(blockPos);

                if (blockPos != pos && BlockUtil.canMineOther(stack, state, blockState)) {
                    blockState.getBlock().playerDestroy(level, player, blockPos, blockState, level.getBlockEntity(blockPos), stack);

                    level.destroyBlock(blockPos, false, player);
                    stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                }
            });
        }
    }

    public static void register() {
        EventManager.registerListeners(ModEvents.class);
        Hammering.LOGGER.info("Registered Events.");
    }
}
