package net.leafenzo.mint.mixin;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShulkerBoxColoringRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Surrogate;

@Mixin(RecipeSerializer.class)
public class RecipeSerializerMixin {
    private static final RecipeSerializer<ShulkerBoxColoringRecipe> SHULKER_BOX = RecipeSerializer.register("crafting_special_shulkerboxcoloring", new SpecialRecipeSerializer<ShulkerBoxColoringRecipe>(ShulkerBoxColoringRecipe::new));
}
