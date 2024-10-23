package net.leafenzo.mint.mixin.compat.create;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.DyeHelper;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
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
                .put(ElsDyeModDyeColor.MINT, Couple.create(0x53ef8d, 0x27b64f))
                .put(ElsDyeModDyeColor.PEACH, Couple.create(0xfda06d, 0xe6683e))
                .put(ElsDyeModDyeColor.PERIWINKLE, Couple.create(0xb7a2f5, 0x635ecd))
                .put(ElsDyeModDyeColor.ARTICHOKE, Couple.create(0xe0e52f, 0x8cb811))
                .put(ElsDyeModDyeColor.FUCHSIA, Couple.create(0xd64d8a, 0xbf3964))
                .put(ElsDyeModDyeColor.VERMILION, Couple.create(0xf05723, 0xce2a0d))
                .put(ElsDyeModDyeColor.SHAMROCK, Couple.create(0x3bd610, 0x00ac0e))
                .put(ElsDyeModDyeColor.INDIGO, Couple.create(0x5c2bc3, 0x2c1691))
                .put(ElsDyeModDyeColor.BANANA, Couple.create(0xe8cd85 , 0xd8ac68))
                .put(ElsDyeModDyeColor.CERULEAN, Couple.create(0x496f8d, 0x2e3f63))
                .put(ElsDyeModDyeColor.ACORN, Couple.create(0x473228, 0x311f19))
                .put(ElsDyeModDyeColor.MAUVE, Couple.create(0x8d4b5c, 0x673137))
                .put(ElsDyeModDyeColor.MAROON, Couple.create(0x551810, 0x3d090a))
                .put(ElsDyeModDyeColor.GRAPE, Couple.create(0x491355, 0x220734))
                .put(ElsDyeModDyeColor.NAVY, Couple.create(0x1a254b, 0x0b0d28))
                .put(ElsDyeModDyeColor.SAP, Couple.create(0x294a32, 0x172f1b))
                .put(ElsDyeModDyeColor.AMBER, Couple.create(0xcc8d13, 0xa45604))
                .put(ElsDyeModDyeColor.SAGE, Couple.create(0x617a54, 0x365834))
                .put(ElsDyeModDyeColor.VELVET, Couple.create(0x9f0f46, 0x6e0325))
                .put(ElsDyeModDyeColor.MOLD, Couple.create(0x6a5f24, 0x484110));
    }
}
