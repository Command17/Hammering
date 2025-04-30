package com.github.command17.hammering.mixin;

import com.github.command17.hammering.Hammering;
import com.github.command17.hammering.enchantment.effect.ModEnchantmentEffectComponents;
import com.github.command17.hammering.util.EnchantmentUtil;
import com.github.command17.hammering.util.ModTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow public abstract Inventory getInventory();

    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void hammering$modifyDestroySpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        ItemStack stack = this.getInventory().getSelected();
        if (!stack.isEmpty() && stack.isEnchanted()) {
            int level = EnchantmentUtil.getTotalOfEnchantmentComponent(stack, ModEnchantmentEffectComponents.AREA_MINE.get());
            float baseModifier = 1f / (level + 1);
            float efficiencyModifier = 1;
            if (level > 0 && this.getAttributeValue(Attributes.MINING_EFFICIENCY) > 1) {
                efficiencyModifier *= Hammering.SERVER_CONFIG.areaMineEfficiencyDebuff.get();
            }

            cir.setReturnValue(cir.getReturnValueF() * baseModifier * efficiencyModifier);
        }
    }
}
