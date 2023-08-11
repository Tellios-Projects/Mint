package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.util.Util;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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


    public static void offerCarpetRecipesIteratively(Consumer<RecipeJsonProvider> exporter, Block[] carpets, Block[] inputs) {
        if(carpets.length > inputs.length) { throw new RuntimeException();
//            throw new RuntimeException("More carpets than wools");
        }
        for(int i = 0; i < carpets.length; i++) {
//            if(carpets[i] == null) { throw new RuntimeException("Index with null value in carpets"); }
//            if(inputs[i] == null) { throw new RuntimeException("Index with null value in wools"); }
            offerCarpetRecipe(exporter, carpets[i], inputs[i]);
        }
    }
    public static void offerTerracottaDyeingRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerTerracottaDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerConcretePowderDyeingRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerConcretePowderDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerGlazedTerracottaSmeltingRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(inputs[i]), RecipeCategory.BUILDING_BLOCKS, outputs[i], 0.1f, 200);
        }
    }
    public static void offerBannerRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerBannerRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerCandleDyeingRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerCandleDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerBedRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerBedRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassPaneRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassPaneDyeingRecipe(exporter, outputs[i], inputs[i]);
        }
    }
    public static void offerStainedGlassPaneDyeingRecipesIteratively(Consumer<RecipeJsonProvider> exporter, ItemConvertible[] outputs, ItemConvertible[] inputs) {
        if(outputs.length > inputs.length) { throw new RuntimeException(); }
        for(int i = 0; i < inputs.length; i++) {
            offerStainedGlassPaneRecipe(exporter, outputs[i], inputs[i]);
        }
    }


    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // MINT - Special
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINT_COOKIE, 4)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.COCOA_BEANS)
                .input(ModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ModItems.MINT_SPRIG),
                        FabricRecipeProvider.conditionsFromItem(ModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.MINT_COOKIE) + "_shapeless"));
        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.MINT_SPRIG, RecipeCategory.MISC.getName(), 1);
        this.offerShapelessRecipe(exporter, ModItems.MINT_DYE, Ingredient.ofItems(Items.LIME_DYE, Items.LIGHT_BLUE_DYE), RecipeCategory.MISC, 2);
        this.offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_SPRIG_BLOCK);



        // Util
  //      Array All_Dyes =  (ArrayList<ItemConvertible>)Arrays.stream(ModItems.DYE_ITEMS).toList(); //TODO fixme

        // Main
//  WOOL_BLOCKS
        offerDyeableRecipes(exporter, Arrays.stream(ModItems.DYE_ITEMS).toList(), Arrays.stream(ModBlocks.toItems((ModBlocks.WOOL_BLOCKS))).toList(), "wool"); //TODO fixme

//  CARPET_BLOCKS
        offerCarpetRecipesIteratively(exporter, ModBlocks.CARPET_BLOCKS, ModBlocks.WOOL_BLOCKS);
        offerDyeableRecipes(exporter, Arrays.stream(ModItems.DYE_ITEMS).toList(), Arrays.stream(ModBlocks.toItems((ModBlocks.CARPET_BLOCKS))).toList(), "carpet");

//  TERRACOTTA_BLOCKS
        offerTerracottaDyeingRecipesIteratively(exporter, ModBlocks.TERRACOTTA_BLOCKS, ModItems.DYE_ITEMS);
//        offerTerracottaDyeingRecipe(exporter, ModBlocks.MINT_TERRACOTTA, ModItems.MINT_DYE);
//        offerTerracottaDyeingRecipe(exporter, ModBlocks.PEACH_TERRACOTTA, ModItems.PEACH_DYE);
//        offerTerracottaDyeingRecipe(exporter, ModBlocks.PERIWINKLE_TERRACOTTA, ModItems.PERIWINKLE_DYE);
//        offerTerracottaDyeingRecipe(exporter, ModBlocks.ARTICHOKE_TERRACOTTA, ModItems.ARTICHOKE_DYE);

//  CONCRETE_POWDER_BLOCKS
        offerConcretePowderDyeingRecipesIteratively(exporter, ModBlocks.CONCRETE_BLOCKS, ModItems.DYE_ITEMS);

//  GLAZED_TERRACOTTA_BLOCKS
        offerGlazedTerracottaSmeltingRecipesIteratively(exporter, ModBlocks.TERRACOTTA_BLOCKS, ModBlocks.GLAZED_TERRACOTTA_BLOCKS);

//  STAINED_GLASS_BLOCKS
        offerStainedGlassRecipesIteratively(exporter, ModBlocks.STAINED_GLASS_BLOCKS, ModItems.DYE_ITEMS);

//  STAINED_GLASS_PANE_BLOCKS
        offerStainedGlassPaneRecipesIteratively(exporter, ModBlocks.STAINED_GLASS_PANE_BLOCKS, ModBlocks.STAINED_GLASS_BLOCKS);
        offerStainedGlassPaneDyeingRecipesIteratively(exporter, ModBlocks.STAINED_GLASS_PANE_BLOCKS, ModItems.DYE_ITEMS);

//  BED_BLOCKS
        offerBedRecipesIteratively(exporter, ModBlocks.BED_BLOCKS, ModBlocks.WOOL_BLOCKS);
        //offerDyeableRecipes(exporter, (Collections.addAll(  Arrays.stream(ModItems.DYE_ITEMS).toList(), Util.VANILLA_DYES.stream().toList())).toList()

//
//                , Arrays.stream(ModBlocks.toItems((ModBlocks.BED_BLOCKS))).toList(), "bed");

//  CANDLE_BLOCKS
        offerCandleDyeingRecipesIteratively(exporter, ModBlocks.CANDLE_BLOCKS, ModItems.DYE_ITEMS);
//  BANNER_BLOCKS
        offerBannerRecipesIteratively(exporter, ModBlocks.BANNER_BLOCKS, ModBlocks.WOOL_BLOCKS);
    }
}

