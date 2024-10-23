package net.leafenzo.mint.recipe;

import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public interface ElsDyeModRecipeSerializer<T extends Recipe<?>> {
    net.minecraft.recipe.RecipeSerializer<AbstractColoringRecipe> WOOL_COLORING_RECIPE = register("wool_coloring_recipe", new SpecialRecipeSerializer(WoolColoringRecipe::new));
    net.minecraft.recipe.RecipeSerializer<WoolCarpetColoringRecipe> WOOL_CARPET_COLORING_RECIPE = register("wool_carpet_coloring_recipe", new SpecialRecipeSerializer(WoolCarpetColoringRecipe::new));
    net.minecraft.recipe.RecipeSerializer<BedColoringRecipe> BED_COLORING_RECIPE = register("bed_coloring_recipe", new SpecialRecipeSerializer(BedColoringRecipe::new));
    net.minecraft.recipe.RecipeSerializer<BedColoringRecipe> MOD_SHULKER_BOX_COLORING_RECIPE = register("mod_shulker_box_coloring_recipe", new SpecialRecipeSerializer(ElsDyeModShulkerBoxColoringRecipe::new));

    static <S extends net.minecraft.recipe.RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER, id, serializer);
    }

    static void registerModRecipeSerializer() {
        ElsDyeModInit.LOGGER.debug("Registering the recipe serializer for " + ElsDyeMod.MOD_ID);
    }
}
