package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) { super(output); }

    public static void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input(Character.valueOf('#'), baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
    }


    public static void offerShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, Ingredient ingredientInput, RecipeCategory recipeCategory, int count) {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output, count).input(ingredientInput);
    }

    public static void offerShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible itemProvider, RecipeCategory recipeCategory, int count) {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output, count).input(itemProvider);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //offerWaxingRecipes(exporter);
        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.MINT_SPRIG, RecipeCategory.MISC.getName(), 1);
        this.offerShapelessRecipe(exporter, ModItems.MINT_DYE, Ingredient.ofItems(Items.LIME_DYE, Items.LIGHT_BLUE_DYE), RecipeCategory.MISC, 2);
        offerCarpetRecipe(exporter, ModBlocks.MINT_CARPET, ModBlocks.MINT_WOOL);
        this.offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_SPRIG_BLOCK);

        offerStainedGlassDyeingRecipe(exporter, ModBlocks.MINT_STAINED_GLASS, ModItems.MINT_DYE);
        offerStainedGlassPaneRecipe(exporter, ModBlocks.MINT_STAINED_GLASS_PANE, ModBlocks.MINT_STAINED_GLASS);
        offerStainedGlassPaneDyeingRecipe(exporter, ModBlocks.MINT_STAINED_GLASS_PANE, ModItems.MINT_DYE);
        offerConcretePowderDyeingRecipe(exporter, ModBlocks.MINT_CONCRETE_POWDER, ModItems.MINT_DYE);

        offerBannerRecipe(exporter, ModBlocks.MINT_BANNER, ModBlocks.MINT_WOOL);






        offerTerracottaDyeingRecipe(exporter, ModBlocks.MINT_TERRACOTTA, ModItems.MINT_DYE);
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(ModBlocks.MINT_TERRACOTTA), RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_GLAZED_TERRACOTTA, 0.1f, 200);


        // offerShapelessRecipe(exporter, ModBlocks.MINT_SHULKER_BOX, );

//
//        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.MINT_SHULKER_BOX)
//                        .input(Items.SHULKER_SHELL)
//                        .input(Items.Sh)

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINT_COOKIE, 4)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.COCOA_BEANS)
                .input(ModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ModItems.MINT_SPRIG),
                        FabricRecipeProvider.conditionsFromItem(ModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.MINT_COOKIE) + "_shapeless"));
    }
}

