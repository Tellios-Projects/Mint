/*
* Sources
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/java/com/remodstudios/voidlands/mixin/BlockEntityTypeMixin.java#L17
*/

package net.leafenzo.mint.mixin;
import net.leafenzo.mint.block.ModBlocks;
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
        if (type == BlockEntityType.SHULKER_BOX) { //TODO FIXME
            if (state.isOf(ModBlocks.MINT_SHULKER_BOX))
                cir.setReturnValue(true);
        } else if (type == BlockEntityType.BED) {
            if (state.isOf(ModBlocks.MINT_BED))
                cir.setReturnValue(true);
        } else if (type == BlockEntityType.BANNER) {
            if (state.isOf(ModBlocks.MINT_BANNER) || state.isOf(ModBlocks.MINT_WALL_BANNER))
                cir.setReturnValue(true);
        }

//        if (BlockEntityType.BED.equals(state) && (state.getBlock() instanceof BedBlock)) {
//            info.setReturnValue(true);
//        }
//        if (BlockEntityType.SHULKER_BOX.equals(state) && (state.getBlock() instanceof ShulkerBoxBlock)) {
//            info.setReturnValue(true);
//        }
//        else if (BlockEntityType.BANNER.equals(state) && (state.getBlock() instanceof BannerBlock ||
//                state.getBlock() instanceof WallBannerBlock)) {
//            info.setReturnValue(true);
//        }
    }
}

//@Mixin(BlockEntityType.class)
//public abstract class BlockEntityTypeMixin {
//    @Shadow @Final public static BlockEntityType<ShulkerBoxBlockEntity> SHULKER_BOX;
//    @Shadow @Final public static BlockEntityType<BedBlockEntity> BED;
//    @Shadow @Final public static BlockEntityType<BannerBlockEntity> BANNER;
//
//    @SuppressWarnings("ConstantConditions")
//    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
//    public void supportCustomBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
//        final BlockEntityType<?> type = ((BlockEntityType<?>) (Object) this);
//        if (type == SHULKER_BOX) { //TODO FIXME
//            if (state.isOf(ModBlocks.MINT_SHULKER_BOX))
//                cir.setReturnValue(true);
//        } else if (type == BED) {
//            if (state.isOf(ModBlocks.MINT_BED))
//                cir.setReturnValue(true);
//        } else if (type == BANNER) {
//            if (state.isOf(ModBlocks.MINT_BANNER) || state.isOf(ModBlocks.MINT_WALL_BANNER))
//                cir.setReturnValue(true);
//        }
//    }

//    @Inject(method = "supports", at = @At("HEAD"), cancellable = true)
//    private void supports(BlockState state, CallbackInfoReturnable<Boolean> info) {
//        if (BlockEntityType.BED.equals(this) && (state.getBlock() instanceof BedBlock ||
//                state.getBlock() instanceof BedBlock)) {
//            info.setReturnValue(true);
//        }
//        else if (BlockEntityType.SHULKER_BOX.equals(this) && (state.getBlock() instanceof ShulkerBoxBlock ||
//                state.getBlock() instanceof ShulkerBoxBlock)) {
//            info.setReturnValue(true);
//        }
//        else if (BlockEntityType.BANNER.equals(this) && (state.getBlock() instanceof BannerBlock ||
//                state.getBlock() instanceof BannerBlock)) {
//            info.setReturnValue(true);
//        }
//        else if (BlockEntityType.BANNER.equals(this) && (state.getBlock() instanceof WallBannerBlock ||
//                state.getBlock() instanceof WallBannerBlock)) {
//            info.setReturnValue(true);
//        }
//    }
//}

