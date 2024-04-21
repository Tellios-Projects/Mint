package net.leafenzo.mint.recipe;

import net.leafenzo.mint.block.custom.ModShulkerBoxBlock;
import net.leafenzo.mint.util.ModDyeColor;
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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Arrays;

public class ModShulkerBoxColoringRecipe extends SpecialCraftingRecipe {
    public ModShulkerBoxColoringRecipe(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
    }

    @Override
    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        int i = 0;
        int j = 0;
        boolean isModdedBlock = false;
        boolean isModdedDye = false;

        for(int k = 0; k < recipeInputInventory.size(); ++k) {
            ItemStack itemStack = recipeInputInventory.getStack(k);
            if (!itemStack.isEmpty()) {
                if (Block.getBlockFromItem(itemStack.getItem()) instanceof ShulkerBoxBlock) {
                    ++i;
                    if(Block.getBlockFromItem(itemStack.getItem()) instanceof ModShulkerBoxBlock) {
                        isModdedBlock = true;
                    }
                } else {
                    if (!(itemStack.getItem() instanceof DyeItem)) {
                        return false;
                    }

                    if(Arrays.stream(ModDyeColor.VALUES).anyMatch((x) -> x == ((DyeItem) itemStack.getItem()).getColor())) {
                        isModdedDye = true;
                    }

                    ++j;
                }

                if (j > 1 || i > 1) {
                    return false;
                }
            }
        }

        if (isModdedBlock || isModdedDye) {
            return i == 1 && j == 1;
        }
        else { return false; }
    }

    @Override
    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ItemStack itemStack = ItemStack.EMPTY; //fallback value
        DyeItem dyeItem = (DyeItem) Items.WHITE_DYE; //fallback value

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

        DyeColor ingredientColor = dyeItem.getColor();
        ItemStack itemStack3 = ModShulkerBoxBlock.getItemStack(ingredientColor);
        if (itemStack.hasNbt()) { itemStack3.setNbt(itemStack.getNbt().copy()); }
        return itemStack3;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializer.MOD_SHULKER_BOX_COLORING_RECIPE;
    }
}
