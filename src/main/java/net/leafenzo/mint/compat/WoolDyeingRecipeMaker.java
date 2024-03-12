package net.leafenzo.mint.compat;

import dev.architectury.platform.Mod;
import mezz.jei.common.platform.IPlatformIngredientHelper;
import mezz.jei.common.platform.Services;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registration.ModRegistryHelper;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
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

import net.leafenzo.mint.registration.ModRegistryHelper.*;
public final class WoolDyeingRecipeMaker {
    private static final String group = Super.MOD_ID + ".wool.coloring";

    // From JEI's ShulkerBoxColoringRecipeMaker
    public static List<CraftingRecipe> createRecipes() {
        ItemStack baseWoolStack = new ItemStack(Blocks.WHITE_WOOL);
        Ingredient baseWoolIngredient = Ingredient.ofStacks(baseWoolStack);
        return Arrays.stream(ModDyeColor.VALUES)
//                .filter(dc -> dc != DyeColor.WHITE)
                .map((color) -> createRecipe(color, baseWoolIngredient))
                .toList();
    }
    private static CraftingRecipe createRecipe(DyeColor color, Ingredient baseWoolIngredient) {
        Ingredient colorIngredient = Ingredient.ofItems(ItemRegistry.DYE_ITEM_FROM_COLOR.get(color));
        DefaultedList<Ingredient> inputs = DefaultedList.copyOf(Ingredient.EMPTY, baseWoolIngredient, colorIngredient);

        Block coloredWool = BlockRegistry.COLOR_FROM_WOOL.get(color);

        ItemStack output = new ItemStack(coloredWool);
        Identifier id = new Identifier("minecraft", group + "." + output.getTranslationKey());
        return new ShapelessRecipe(id, group, CraftingRecipeCategory.MISC, output, inputs);
    }

    private WoolDyeingRecipeMaker() {}
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