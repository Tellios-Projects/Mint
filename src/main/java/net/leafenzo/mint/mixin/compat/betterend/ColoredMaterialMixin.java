package net.leafenzo.mint.mixin.compat.betterend;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.util.DyeColor;
import org.betterx.betterend.complexmaterials.ColoredMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(ColoredMaterial.class)
public class ColoredMaterialMixin {

    @ModifyExpressionValue(
            method = "*",
            at = @At(value = "INVOKE", target = "net/minecraft/util/DyeColor.values ()[Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor[] truncateDyeColors(DyeColor[] original) {
        // Use only vanilla dye colors, assuming that it expects nothing else to be there
        return ElsDyeModUtil.VANILLA_DYE_COLORS;

        // Ignore the DyeColors we added, as it's not expecting those to be there.
//        return (DyeColor[]) Arrays.stream(original)
//                .filter((DyeColor a) -> !Arrays.asList(modDyeColors).contains(a)) // can't figure out how to get this working. If anyone needs this to work different DM me. - leafenzo
//                .toArray();
    }
}
