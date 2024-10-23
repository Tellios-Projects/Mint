package net.leafenzo.mint.recipe;

import net.minecraft.block.Block;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class WoolColoringRecipe
extends AbstractColoringRecipe {
    public WoolColoringRecipe(Identifier identifier, CraftingRecipeCategory craftingRecipeCategory) {
        super(identifier, craftingRecipeCategory);
    }
    @Override
    protected TagKey<Block> getDyablesTag() {
        return BlockTags.WOOL;
    }
    @Override
    public RecipeSerializer<?> getSerializer() {
        return ElsDyeModRecipeSerializer.WOOL_COLORING_RECIPE;
    }
}
