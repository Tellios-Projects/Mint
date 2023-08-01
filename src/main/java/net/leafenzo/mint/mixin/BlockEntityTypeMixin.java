/*
* Sources
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/java/com/remodstudios/voidlands/mixin/BlockEntityTypeMixin.java#L17
*/

package net.leafenzo.mint.mixin;
import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public abstract class BlockEntityTypeMixin {
    @Shadow
    @Final
    public static BlockEntityType<ShulkerBoxBlockEntity> SHULKER_BOX;
    @Shadow
    @Final
    public static BlockEntityType<BedBlockEntity> BED;

    @SuppressWarnings("ConstantConditions")
    @Inject(method = "supports", at = @At("RETURN"), cancellable = true)
    public void supportCustomBlocks(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        final BlockEntityType<?> type = ((BlockEntityType<?>) (Object) this);
//        if (type == SHULKER_BOX) { //TODO FIXME
//            if (state.isOf(ModBlocks.MINT_SHULKER_BOX))
//                cir.setReturnValue(true);
//        } else if (type == BED) {
//            if (state.isOf(ModBlocks.MINT_BED))
//                cir.setReturnValue(true);
//        }
    }
}

