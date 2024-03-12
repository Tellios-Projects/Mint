package net.leafenzo.mint.mixin.compat.jei;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mezz.jei.library.plugins.vanilla.crafting.replacers.ShulkerBoxColoringRecipeMaker;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;

@Mixin(ShulkerBoxColoringRecipeMaker.class)
public class ShulkerBoxColoringRecipeMakerMixin {

    @ModifyExpressionValue(
            method = "createRecipes()Ljava/util/List;",
            at = @At(value = "INVOKE", target = "net/minecraft/util/DyeColor.values ()[Lnet/minecraft/util/DyeColor;")
    )
    private static DyeColor[] truncateDyeColors(DyeColor[] original) {
        // Use only vanilla dye colors, assuming that it expects nothing else to be there
        return ModUtil.VANILLA_DYE_COLORS;

        // Ignore the DyeColors we added, as it's not expecting those to be there.
//        return (DyeColor[]) Arrays.stream(original)
//                .filter((DyeColor a) -> !Arrays.asList(modDyeColors).contains(a)) // can't figure out how to get this working. If anyone needs this to work different DM me. - leafenzo
//                .toArray();
    }

    //    @Unique
//    private static final DyeColor[] modDyeColors = ModDyeColor.VALUES;
//    @Unique
//    private static final DyeColor[] modDyeColors = {
//            ModDyeColor.MINT,
//            ModDyeColor.PEACH,
//            ModDyeColor.PERIWINKLE,
//            ModDyeColor.ARTICHOKE,
//            ModDyeColor.FUCHSIA,
//            ModDyeColor.VERMILION,
//            ModDyeColor.SHAMROCK,
//            ModDyeColor.INDIGO,
//            ModDyeColor.BANANA,
//            ModDyeColor.CERULEAN,
//            ModDyeColor.ACORN,
//            ModDyeColor.MAUVE,
//            ModDyeColor.MAROON,
//            ModDyeColor.GRAPE,
//            ModDyeColor.NAVY,
//            ModDyeColor.SAP,
//            ModDyeColor.AMBER,
//            ModDyeColor.SAGE,
//            ModDyeColor.VELVET,
//            ModDyeColor.MOLD
//    };
}
