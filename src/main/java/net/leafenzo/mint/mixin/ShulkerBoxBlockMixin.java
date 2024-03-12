///*
// * SOURCES:
// * ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/java/com/remodstudios/voidlands/mixin/ShulkerBoxBlockMixin.java
// */
//
//package net.leafenzo.mint.mixin;
//
//import dev.architectury.platform.Mod;
//import net.leafenzo.mint.block.ModBlocks;
//import net.leafenzo.mint.block.ModShulkerBoxBlock;
//import net.leafenzo.mint.item.ModItems;
//import net.leafenzo.mint.util.Color;
//import net.leafenzo.mint.util.ModDyeColor;
//import net.minecraft.block.Block;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.ShulkerBoxBlock;
//import net.minecraft.util.DyeColor;
//import org.spongepowered.asm.mixin.Debug;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//import java.util.Arrays;
//
//@Debug(export = true)
//@Mixin(ShulkerBoxBlock.class)
//public abstract class ShulkerBoxBlockMixin {
//    @Inject(method = "get(Lnet/minecraft/util/DyeColor;)Lnet/minecraft/block/Block;", at = @At("TAIL"), cancellable = true)
//    private static void get(DyeColor color, CallbackInfoReturnable<Block> cir) {
////        if (color != null && Arrays.stream(ModDyeColor.VALUES).anyMatch((x) -> x == color)) {
////            cir.setReturnValue(ModShulkerBoxBlock.get(color));
////        } // Doesn't work. At least not for JEI's ShulkerBoxColoringRecipeMaker
//    }
//}
