package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.ModShulkerBoxBlock;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShulkerBoxColoringRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Debug(export = true)
@Mixin(ShulkerBoxColoringRecipe.class)
public class ShulkerBoxColoringRecipeMixin {
    @Inject(method = "craft", at = @At("TAIL"), cancellable = true)
    private void craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager, CallbackInfoReturnable<ItemStack> cir) {
        //Just copying most things over cuz I don't wanna capture or overwrite any locals
        ItemStack itemStack = ItemStack.EMPTY; //fallback value
        DyeItem dyeItem = (DyeItem)Items.WHITE_DYE; //fallback value
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack2 = recipeInputInventory.getStack(i);
            if (itemStack2.isEmpty()) continue;
            Item item = itemStack2.getItem();
            if (Block.getBlockFromItem(item) instanceof ShulkerBoxBlock) {
                itemStack = itemStack2;
                continue;
            }
            if (!(item instanceof DyeItem)) continue;
            dyeItem = (DyeItem)item;
        }
        //Only change the return value if it's from one of the ModDyeColors, this is for compatibility with other dye mods of ours that inject this same mixin
        DyeColor ingredientColor = dyeItem.getColor();
        ItemStack itemStack3 = ShulkerBoxBlock.getItemStack(ingredientColor); //fallback value
        if (itemStack.hasNbt()) { itemStack3.setNbt(itemStack.getNbt().copy()); }
        for (DyeColor color : ModDyeColor.VALUES) {
            if(color == ingredientColor) {
                itemStack3 = ModShulkerBoxBlock.getItemStack(ingredientColor);
                if (itemStack.hasNbt()) { itemStack3.setNbt(itemStack.getNbt().copy()); }
                cir.setReturnValue(itemStack3);
            }
        }
    }
}
