package net.leafenzo.mint.mixin.compat.botania;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import vazkii.botania.common.entity.ManaSparkEntity;

import java.util.Arrays;

@Pseudo
@Mixin(ManaSparkEntity.class)
public class ManaSparkEntityMixin {

    @ModifyExpressionValue(
            method = "*",
            at = @At(value = "INVOKE", target = "net/minecraft/item/DyeItem.getColor ()Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor getDyeColorIfNotOurs(DyeColor original) {
        if(Arrays.stream(ElsDyeModDyeColor.VALUES).anyMatch(x -> x == original)) {
            return DyeColor.WHITE;
        }
        else {
            return original;
        }
    }
}
