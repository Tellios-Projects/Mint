package net.leafenzo.mint.compat;

import net.leafenzo.mint.block.custom.ElsDyeModShulkerBoxBlock;
import net.leafenzo.mint.registration.ElsDyeModRegistryHelper.ItemRegistry;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.Arrays;
import java.util.List;
public class ElsDyeModShulkerBoxColoringRecipeMaker {
    private static final String group = "jei.shulker.color";

//    private static final Logger LOGGER = LoggerFactory.getLogger(Super.MOD_ID);

    public static List<CraftingRecipe> createRecipes() {
        ItemStack baseShulkerStack = new ItemStack(Blocks.SHULKER_BOX);
        Ingredient baseShulkerIngredient = Ingredient.ofStacks(baseShulkerStack);

//        LOGGER.info("BIG MEOW!  Starting to create JEI shulker box recipes using these dye colors: " + Arrays.toString(ModDyeColor.VALUES));
        return Arrays.stream(ElsDyeModDyeColor.VALUES).map((color) -> createRecipe(color, baseShulkerIngredient)).toList();
    }

    private static CraftingRecipe createRecipe(DyeColor color, Ingredient baseShulkerIngredient) {
        Ingredient colorIngredient = Ingredient.ofItems(ItemRegistry.DYE_ITEM_FROM_COLOR.get(color));
        DefaultedList<Ingredient> inputs = DefaultedList.copyOf(Ingredient.EMPTY, baseShulkerIngredient, colorIngredient);

        Block coloredShulkerBox = ElsDyeModShulkerBoxBlock.get(color);

        ItemStack output = new ItemStack(coloredShulkerBox);
        Identifier id = new Identifier("minecraft", "jei.shulker.color." + output.getTranslationKey());

//        LOGGER.info("BIG MEOW!  Created JEI shulker box recipe : " + "jei.shulker.color " + " : " + "output = " + output.getTranslationKey() + " inputs = " + Arrays.toString(inputs.toArray()));

        return new ShapelessRecipe(id, "jei.shulker.color", CraftingRecipeCategory.MISC, output, inputs);
//        return new ShapelessRecipe(id, "jei.shulker.color", CraftingRecipeCategory.MISC, output, inputs);
    }

    private ElsDyeModShulkerBoxColoringRecipeMaker() {
    }
}
