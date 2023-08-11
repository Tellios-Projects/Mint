package net.leafenzo.mint.recipe;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.ModShulkerBoxBlock;
import net.leafenzo.mint.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public class WoolColoringRecipe extends SpecialCraftingRecipe {
    public WoolColoringRecipe(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
    }

    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        int i = 0;
        int j = 0;

        for(int k = 0; k < recipeInputInventory.size(); ++k) {
            ItemStack itemStack = recipeInputInventory.getStack(k);
            if (!itemStack.isEmpty()) {
                Block block = Block.getBlockFromItem(itemStack.getItem());
                if (Util.VANILLA_WOOLS.contains(block) || Arrays.stream(ModBlocks.WOOL_BLOCKS).toList().contains(block)) {
                    ++i;
                } else {
                    if (!(itemStack.getItem() instanceof DyeItem)) {
                        return false;
                    }

                    ++j;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        return i == 1 && j == 1;
    }

    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack itemStack = ItemStack.EMPTY; //fallback
        DyeItem dyeItem = (DyeItem) Items.WHITE_DYE; //fallback

        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack2 = recipeInputInventory.getStack(i);
            if (!itemStack2.isEmpty()) {
                Item item = itemStack2.getItem();
                Block block = Block.getBlockFromItem(item);
                if (Util.VANILLA_WOOLS.contains(block) || Arrays.stream(ModBlocks.WOOL_BLOCKS).toList().contains(block)) {
                    itemStack = itemStack2;
                }
                else if (item instanceof DyeItem) {
                    dyeItem = (DyeItem)item;
                }
            }
        }

        //dyeItem.getColor().getName();
        //Block output = Block
        ItemStack itemStack3 = ModShulkerBoxBlock.getItemStack(dyeItem.getColor());

        return itemStack3;
    }

    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.WOOL_COLORING;
    }
}
