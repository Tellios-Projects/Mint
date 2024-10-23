package net.leafenzo.mint.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.leafenzo.mint.Super;
import net.minecraft.util.Identifier;

@JeiPlugin
public class ModJeiPlugin implements IModPlugin {
//    private static final Logger LOGGER = LoggerFactory.getLogger(Super.MOD_ID);

    @Override
    public Identifier getPluginUid() {
        return new Identifier(Super.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Why does nothing work
        // The pain
        // ough
        // agh
        // erohg
        // ough

//        RecipeManager recipeManager = MinecraftClient.getInstance().world.getRecipeManager();
//        List<AbstractColoringRecipe> coloringRecipes = recipeManager.listAllOfType(WoolColoringRecipe.Type.INSTANCE);

//        LOGGER.error("BIG MEOW!  RegisterRecipes = " + Arrays.toString(ModShulkerBoxColoringRecipeMaker.createRecipes().toArray()));
//        registration.addRecipes(RecipeTypes.CRAFTING, ModShulkerBoxColoringRecipeMaker.createRecipes());

//        LOGGER.error("BIG MEOW!  RegisterRecipes = " + Arrays.toString(WoolDyeingRecipeMaker.createRecipes().toArray()));
//        registration.addRecipes(RecipeTypes.CRAFTING, WoolDyeingRecipeMaker.createRecipes());
    }
}
