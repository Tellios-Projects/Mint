package net.leafenzo.mint.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeRegistration;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.recipe.AbstractColoringRecipe;
import net.leafenzo.mint.recipe.WoolColoringRecipe;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
    private static final Logger LOGGER = LoggerFactory.getLogger(Super.MOD_ID);

    @Override
    public Identifier getPluginUid() {
        return new Identifier(Super.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
//        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
//        List<AbstractColoringRecipe> coloringRecipes = recipeManager.listAllOfType(WoolColoringRecipe.Type.INSTANCE);

        LOGGER.debug("BIG MEOW!  RegisterRecipes = " + Arrays.toString(ModShulkerBoxColoringRecipeMaker.createRecipes().toArray()));
        registration.addRecipes(RecipeTypes.CRAFTING, ModShulkerBoxColoringRecipeMaker.createRecipes());
        registration.addRecipes(RecipeTypes.CRAFTING, WoolDyeingRecipeMaker.createRecipes());
    }

}
