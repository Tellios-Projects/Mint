package net.leafenzo.mint.mixin.compat.create;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.DyeHelper;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(DyeHelper.class)
public class DyeHelperMixin {
    @ModifyReceiver(method = "<clinit>()V", remap = false, at = @At(value = "INVOKE", target = "com/google/common/collect/ImmutableMap$Builder.build ()Lcom/google/common/collect/ImmutableMap;"))
    private static ImmutableMap.Builder<DyeColor, Couple<Integer>> appendToDyeTable(ImmutableMap.Builder<DyeColor, Couple<Integer>> original) {
        return original
                // DyeColor, ( Front RGB, Back RGB )
                .put(ModDyeColor.MINT, Couple.create(0x53ef8d, 0x27b64f))
                .put(ModDyeColor.PEACH, Couple.create(0xfda06d, 0xe6683e))
                .put(ModDyeColor.PERIWINKLE, Couple.create(0xb7a2f5, 0x635ecd))
                .put(ModDyeColor.ARTICHOKE, Couple.create(0xe0e52f, 0x8cb811))
                .put(ModDyeColor.FUCHSIA, Couple.create(0xd64d8a, 0xbf3964))
                .put(ModDyeColor.VERMILION, Couple.create(0xf05723, 0xce2a0d))
                .put(ModDyeColor.SHAMROCK, Couple.create(0x3bd610, 0x00ac0e))
                .put(ModDyeColor.INDIGO, Couple.create(0x5c2bc3, 0x2c1691))
                .put(ModDyeColor.BANANA, Couple.create(0xe8cd85 , 0xd8ac68))
                .put(ModDyeColor.CERULEAN, Couple.create(0x496f8d, 0x2e3f63))
                .put(ModDyeColor.ACORN, Couple.create(0x473228, 0x311f19))
                .put(ModDyeColor.MAUVE, Couple.create(0x8d4b5c, 0x673137))
                .put(ModDyeColor.MAROON, Couple.create(0x551810, 0x3d090a))
                .put(ModDyeColor.GRAPE, Couple.create(0x491355, 0x220734))
                .put(ModDyeColor.NAVY, Couple.create(0x1a254b, 0x0b0d28))
                .put(ModDyeColor.SAP, Couple.create(0x294a32, 0x172f1b))
                .put(ModDyeColor.AMBER, Couple.create(0xcc8d13, 0xa45604))
                .put(ModDyeColor.SAGE, Couple.create(0x617a54, 0x365834))
                .put(ModDyeColor.VELVET, Couple.create(0x9f0f46, 0x6e0325))
                .put(ModDyeColor.MOLD, Couple.create(0x6a5f24, 0x484110));
    }
}
