package net.leafenzo.mint.compat;

import mezz.jei.common.platform.IPlatformIngredientHelper;
import mezz.jei.common.platform.Services;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.custom.ModShulkerBoxBlock;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;

import java.util.List;

import java.util.Arrays;

import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public final class WoolDyeingRecipeMaker {
    private static final String group = Super.MOD_ID + ".wool.color";

    // From JEI's ShulkerBoxColoringRecipeMaker
    public static List<CraftingRecipe> createRecipes() {
        ItemStack baseShulkerStack = new ItemStack(Blocks.WHITE_WOOL);
        Ingredient baseShulkerIngredient = Ingredient.ofStacks(new ItemStack[]{baseShulkerStack});
        return Arrays.stream(ModDyeColor.VALUES).map((color) -> {
            return createRecipe(color, baseShulkerIngredient);
        }).toList();
    }

    private static CraftingRecipe createRecipe(DyeColor color, Ingredient baseShulkerIngredient) {
        IPlatformIngredientHelper ingredientHelper = Services.PLATFORM.getIngredientHelper();
        Ingredient colorIngredient = ingredientHelper.createShulkerDyeIngredient(color);
        DefaultedList<Ingredient> inputs = DefaultedList.copyOf(Ingredient.EMPTY, new Ingredient[]{baseShulkerIngredient, colorIngredient});
        Block coloredShulkerBox = ModShulkerBoxBlock.get(color);
        ItemStack output = new ItemStack(coloredShulkerBox);
        Identifier id = new Identifier(Super.MOD_ID,group + "." + output.getTranslationKey());
        return new ShapelessRecipe(id, group, CraftingRecipeCategory.MISC, output, inputs);
    }

    private WoolDyeingRecipeMaker() {}


//    public static List<CraftingRecipe> createRecipes() {
//        ItemStack baseWoolStack = new ItemStack(Blocks.WHITE_WOOL);
//        Ingredient baseWoolIngredient = Ingredient.ofStacks(baseWoolStack);
//
//        List<CraftingRecipe> cs = new ArrayList<>();
//        Identifier id = new Identifier(Super.MOD_ID, group + "." + ModBlocks.BANANA_WOOL.getTranslationKey());
//        cs.add(new ShapelessRecipe(id, group, CraftingRecipeCategory.MISC, ModBlocks.BANANA_WOOL.asItem().getDefaultStack(), DefaultedList.copyOf(Ingredient.ofItems(Blocks.WHITE_WOOL), Ingredient.ofItems(ModItems.BANANA_DYE))));
//        return cs;
//    }
//    private static CraftingRecipe createRecipe(DyeColor color, Ingredient baseWoolIngredient) {
//        Ingredient colorIngredient = Ingredient.ofItems(ItemRegistry.DYE_ITEM_FROM_COLOR.get(color));
//        DefaultedList<Ingredient> inputs = DefaultedList.copyOf(Ingredient.EMPTY, baseWoolIngredient, colorIngredient);
//
//        Block coloredWool = BlockRegistry.COLOR_FROM_WOOL.get(color);
//
//        ItemStack output = new ItemStack(coloredWool);
//        Identifier id = new Identifier("minecraft", group + "." + output.getTranslationKey());
//
////        return new ShapelessRecipe(id, group, CraftingRecipeCategory.MISC, output, inputs);
//        return new ShapelessRecipe(id, group, CraftingRecipeCategory.MISC, ModBlocks.BANANA_WOOL.asItem().getDefaultStack(), DefaultedList.copyOf(Ingredient.EMPTY, Ingredient.ofItems(Blocks.WHITE_WOOL), Ingredient.ofItems(ModItems.BANANA_DYE)));
//    }


//
//    public static Stream<CraftingRecipe> createRecipes() {
//        ItemStack baseShulkerStack = Blocks.WHITE_WOOL.asItem().getDefaultStack();
//        Ingredient baseShulkerIngredient = Ingredient.ofStacks(baseShulkerStack);
//
//        return Arrays.stream(DyeColor.values())
//                .filter(dc -> dc != DyeColor.WHITE)
//                .map(color -> {
//                    DyeItem dye = DyeItem.byColor(color);
//                    ItemStack dyeStack = new ItemStack(dye);
//                    TagKey<Item> colorTag = color. ();
//                    Ingredient dyeList = Ingredient.ofStacks(dyeStack);
//                    Ingredient colorList = Ingredient.fromTag(colorTag);
//                    Stream<Ingredient.Value> colorIngredientStream = Stream.of(dyeList, colorList);
//                    Ingredient colorIngredient = Ingredient.fromValues(colorIngredientStream);
//                    NonNullList<Ingredient> inputs =
//                            NonNullList.of(Ingredient.EMPTY, baseShulkerIngredient, colorIngredient);
//                    Block coloredShulkerBox = AllBlocks.TOOLBOXES.get(color)
//                            .get();
//                    ItemStack output = new ItemStack(coloredShulkerBox);
//                    ResourceLocation id = Create.asResource(group + "." + output.getDescriptionId());
//                    return new ShapelessRecipe(id, group, CraftingBookCategory.MISC, output, inputs);
//                });
//    }
}