package net.leafenzo.mint.mixin;


import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.ModShulkerBoxBlock;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShulkerBoxColoringRecipe;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(ShulkerBoxColoringRecipe.class)
public class ShulkerBoxColoringRecipeMixin {

    //TODO, figure this out
//    @Inject(method = "craft", at = @At("TAIL"), cancellable = true)
//    private ItemStack itemStack3(ItemStack itemStack, DyeItem dyeItem) {
//        ItemStack itemStack3 = ShulkerBoxBlock.getItemStack(dyeItem.getColor());
//        if (itemStack.hasNbt()) {
//            itemStack3.setNbt(itemStack.getNbt().copy());
//        }
//        return itemStack3;
//    }
//    @Redirect(method = "get", at = @At("TAIL"), cancellable = true) // in case there's a registration timing error
//    private ItemStack itemStack3(ItemStack itemStack, DyeItem dyeItem) {
//
//    }
}
