/*
* Sources
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/java/com/remodstudios/voidlands/mixin/BlockEntityTypeMixin.java#L17
* Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/1.20.1/src/main/java/net/hibiscus/naturespirit/mixin/BlockEntityTypeMixin.java
*/

package net.leafenzo.mint.mixin;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.ModShulkerBoxBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Debug(export = true)
@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
    private void supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final BlockEntityType<?> type = ((BlockEntityType<?>) (Object) this);
        if (type == BlockEntityType.BED &&
                (state.getBlock() instanceof BedBlock)) {
            cir.setReturnValue(true);
        }
        if (type == BlockEntityType.SHULKER_BOX &&
                    (state.getBlock() instanceof ShulkerBoxBlock ||
                state.getBlock() instanceof ModShulkerBoxBlock)) {
            cir.setReturnValue(true);
        }
        else if (type == BlockEntityType.BANNER &&
                    (state.getBlock() instanceof BannerBlock ||
                state.getBlock() instanceof WallBannerBlock)) {
            cir.setReturnValue(true);
        }

        if(BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof AbstractSignBlock || state.getBlock() instanceof WallSignBlock)) {
            cir.setReturnValue(true);
        }
        if(BlockEntityType.HANGING_SIGN.equals(this) && (state.getBlock() instanceof HangingSignBlock || state.getBlock() instanceof WallHangingSignBlock)) {
            cir.setReturnValue(true);
        }

//        if (type == BlockEntityType.SHULKER_BOX) {
//            if (state.isOf(ModBlocks.MINT_SHULKER_BOX))
//                cir.setReturnValue(true);
//        } else if (type == BlockEntityType.BED) {
//            if (state.isOf(ModBlocks.MINT_BED))
//                cir.setReturnValue(true);
//        } else if (type == BlockEntityType.BANNER) {
//            if (state.isOf(ModBlocks.MINT_BANNER) || state.isOf(ModBlocks.MINT_WALL_BANNER))
//                cir.setReturnValue(true);
//        }
    }
}

