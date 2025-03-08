package com.github.command17.hammering.mixin;

import com.github.command17.hammering.enchantment.effect.ModEnchantmentEffectComponents;
import com.github.command17.hammering.util.EnchantmentUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void hammering$modifyDestroySpeed(ItemStack stack, BlockState state, CallbackInfoReturnable<Float> cir) {
        float modifier = (float) 1 / (EnchantmentUtil.getTotalOfEnchantmentComponent(stack, ModEnchantmentEffectComponents.AREA_MINE.get()) + 1);
        cir.setReturnValue(cir.getReturnValueF() * modifier);
    }
}
