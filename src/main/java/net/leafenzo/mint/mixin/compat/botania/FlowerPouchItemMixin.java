package net.leafenzo.mint.mixin.compat.botania;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vazkii.botania.common.handler.CompostingData;
import vazkii.botania.common.handler.PaintableData;
import vazkii.botania.common.item.FlowerPouchItem;

@Pseudo
@Mixin(FlowerPouchItem.class)
public class FlowerPouchItemMixin {

    @ModifyExpressionValue(
            method = "*",
            at = @At(value = "INVOKE", target = "net/minecraft/util/DyeColor.values ()[Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor[] truncateDyeColors(DyeColor[] original) {
        // Use only vanilla dye colors, assuming that it expects nothing else to be there
        return ModUtil.VANILLA_DYE_COLORS;
    }
}
