/*
*   Source:
*   ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/java/com/remodstudios/voidlands/mixin/ShulkerBoxBlockMixin.java
 */

package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private static void getCustomShulkerBoxes(DyeColor color, CallbackInfoReturnable<Block> cir) {
        // thank you mojank, very cool          // fr though
        if (color == ModDyeColor.MINT)
            cir.setReturnValue(ModBlocks.MINT_SHULKER_BOX);
    }
}
