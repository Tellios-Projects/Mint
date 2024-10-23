package net.leafenzo.mint.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


public abstract class AbstractColoringRecipe
extends SpecialCraftingRecipe {
    public AbstractColoringRecipe(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);

    }

    public static class Type implements RecipeType<AbstractColoringRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "abstract_coloring_recipe_type";
    }

    protected abstract TagKey<Block> getDyablesTag();

    @Override
    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        int i = 0;
        int j = 0;

        for(int k = 0; k < recipeInputInventory.size(); ++k) {
            ItemStack itemStack = recipeInputInventory.getStack(k);
            if (!itemStack.isEmpty()) {
                Block block = Block.getBlockFromItem(itemStack.getItem());
                if (block != null && Registries.BLOCK.getOrCreateEntryList(getDyablesTag()).contains(Registries.BLOCK.getEntry(block))) {
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

    @Override
    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        //return Items.PUFFERFISH.getDefaultStack(); //debug ^w^
        ItemStack itemStack = ItemStack.EMPTY; //fallback
        DyeItem dyeItem = (DyeItem) Items.WHITE_DYE; //fallback

        for(int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack2 = recipeInputInventory.getStack(i);
            if (!itemStack2.isEmpty()) {
                Item item = itemStack2.getItem();
                Block block = Block.getBlockFromItem(item);
                if (Registries.BLOCK.getOrCreateEntryList(getDyablesTag()).contains(Registries.BLOCK.getEntry(block))) {
                    itemStack = itemStack2;
                }
                else if (item instanceof DyeItem) {
                    dyeItem = (DyeItem)item;
                }
            }
        }

        ItemStack itemStack3 = ItemStack.EMPTY; //fallback
        String dyeColorName = dyeItem.getColor().getName();
        for(RegistryEntry<Block> entry : Registries.BLOCK.getOrCreateEntryList(getDyablesTag())) {
            Block block2 = Registries.BLOCK.get(entry.getKey().get());
            if(Registries.BLOCK.getId(block2).getPath().contains(dyeColorName + "_")) {
                itemStack3 = block2.asItem().getDefaultStack();
                if(itemStack.getItem() == itemStack3.getItem()) { return ItemStack.EMPTY; } //Check if its literally just making the same item as the dyeable input
                return itemStack3;
            }
        }
        return itemStack3;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}