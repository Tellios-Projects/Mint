package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Debug(export = true)
@Mixin(ShulkerBoxBlock.class)
public abstract class ShulkerBoxBlockMixin {
    // UNUSED
//    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
//    private static void get(DyeColor color, CallbackInfoReturnable<Block> cir) {
//        // thank you mojank, very cool
//        if (color == ModDyeColor.MINT)
//            cir.setReturnValue(ModBlocks.MINT_SHULKER_BOX); // This won't work unless I somehow make the ModBlocks.MINT_SHULKER_BOX be registered earlier. which is hard and would cause problems.
//    }
}
