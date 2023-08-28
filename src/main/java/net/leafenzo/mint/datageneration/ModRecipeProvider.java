package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Consumer;


public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) { super(output); }
    public static void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input(Character.valueOf('#'), baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
    }


    public static void offerDyeMixingRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2, RecipeCategory recipeCategory, int count) {
        Identifier outputId = Registries.ITEM.getId(output.asItem());
        Identifier input1Id = Registries.ITEM.getId(input1.asItem());
        Identifier input2Id = Registries.ITEM.getId(input2.asItem());

        if(input1 != input2) {
            ShapelessRecipeJsonBuilder.create(recipeCategory, output, count)
                    .input(input1)
                    .input(input2)
                    .group(outputId.getPath())
                    .criterion(FabricRecipeProvider.hasItem(input1), FabricRecipeProvider.conditionsFromItem(input1))
                    .criterion(FabricRecipeProvider.hasItem(input2), FabricRecipeProvider.conditionsFromItem(input2))
                    .offerTo(exporter, outputId.getPath() + "_from_" + input1Id.getPath() + "_and_" + input2Id.getPath());
        }
        else {
            ShapelessRecipeJsonBuilder.create(recipeCategory, output, count)
                    .input(input1)
                    .input(input2)
                    .group(outputId.getPath())
                    .criterion(FabricRecipeProvider.hasItem(input1), FabricRecipeProvider.conditionsFromItem(input1))
                    .offerTo(exporter, outputId.getPath() + "_from_" + input1Id.getPath() + "_and_" + input2Id.getPath());
        }
    }

    public static void offerCarpetRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(Block wool : ModBlocks.WOOL_CARPET_FROM_WOOL.keySet()) {
            offerCarpetRecipe(exporter, ModBlocks.WOOL_CARPET_FROM_WOOL.get(wool), wool);
        }
    }
    public static void offerTerracottaDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible output : ModBlocks.TERRACOTTA_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            Item dye = ModItems.DYE_ITEM_FROM_COLOR.get(color);
            if (dye != null) {
                offerTerracottaDyeingRecipe(exporter, output, dye);
            }
            else {
                throw new RuntimeException(Registries.BLOCK.getId((Block)output).toString());
            }
        }
    }
    public static void offerConcretePowderDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible output : ModBlocks.CONCRETE_POWDER_BLOCKS) {
            ItemConvertible dye = ModItems.DYE_ITEM_FROM_COLOR.get(ModBlocks.DYECOLOR_FROM_BLOCK.get(output));
            offerConcretePowderDyeingRecipe(exporter, output, dye);
        }
    }
    public static void offerGlazedTerracottaSmeltingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible input : ModBlocks.TERRACOTTA_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(input);
            ItemConvertible output = ModBlocks.firstMatchOfColor(ModBlocks.GLAZED_TERRACOTTA_BLOCKS, color);

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input.asItem()), RecipeCategory.DECORATIONS, output.asItem(), 0.1f, 200)
                    .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input.asItem()))
                    .offerTo(exporter);
        }
    }
    public static void offerBannerRecipes(Consumer<RecipeJsonProvider> exporter) { //this looks needlessly overcomplicated
        for(Block banner : ModBlocks.BANNER_BLOCKS) {
            DyeColor bannerColor = ModBlocks.DYECOLOR_FROM_BLOCK.get(banner);
            ItemConvertible wool = ModBlocks.firstMatchOfColor(ModBlocks.WOOL_BLOCKS, bannerColor);

            offerBannerRecipe(exporter, banner, wool);
        }
    }
    public static void offerCandleDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible candle : ModBlocks.CANDLE_BLOCKS) {
            ItemConvertible dyeItem = ModItems.DYE_ITEM_FROM_COLOR.get(ModBlocks.DYECOLOR_FROM_BLOCK.get(candle));
            offerCandleDyeingRecipe(exporter, candle, dyeItem);
        }
    }
    public static void offerBedRecipes(Consumer<RecipeJsonProvider> exporter) { //this looks needlessly overcomplicated
        for(ItemConvertible bed : ModBlocks.BED_BLOCKS) {
            DyeColor a = ModBlocks.DYECOLOR_FROM_BLOCK.get(bed);
            for(Block wool : ModBlocks.WOOL_BLOCKS) {
                DyeColor b = ModBlocks.DYECOLOR_FROM_BLOCK.get(wool);
                if(a == b) {
                    offerBedRecipe(exporter, bed, wool);
                    break; //it only expects one wool color per bed of color
                }
            }
        }
    }
    public static void offerStainedGlassPaneRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(Block glass : ModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.keySet()) {
            offerStainedGlassPaneRecipe(exporter, ModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.get(glass), glass);
        }
    }
    public static void offerStainedGlassPaneDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible pane : ModBlocks.STAINED_GLASS_PANE_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(pane);
            DyeItem dye = ModItems.DYE_ITEM_FROM_COLOR.get(color);
            offerStainedGlassPaneDyeingRecipe(exporter, pane, dye);
        }
    }
    public static void offerStainedGlassDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible glass : ModBlocks.STAINED_GLASS_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(glass);
            DyeItem dye = ModItems.DYE_ITEM_FROM_COLOR.get(color);
            offerStainedGlassDyeingRecipe(exporter, glass, dye);
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
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_SPRIG_BLOCK);

        //Dyes from combining vanilla dyes
        offerDyeMixingRecipe(exporter, ModItems.MINT_DYE, Items.LIME_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.PEACH_DYE, Items.PINK_DYE, Items.ORANGE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.PERIWINKLE_DYE, Items.PURPLE_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.ARTICHOKE_DYE, Items.LIME_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.FUCHSIA_DYE, Items.MAGENTA_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.VERMILION_DYE, Items.ORANGE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.SHAMROCK_DYE, Items.LIME_DYE, Items.LIME_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.INDIGO_DYE, Items.BLUE_DYE, Items.PURPLE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.BANANA_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, RecipeCategory.MISC, 2);

        // Main
//  WOOL_BLOCKS
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_COLORING_RECIPE).offerTo(exporter, "wool_coloring_recipe");

//  CARPET_BLOCKS
        offerCarpetRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_CARPET_COLORING_RECIPE).offerTo(exporter, "wool_carpet_coloring_recipe");

//  TERRACOTTA_BLOCKS
        offerTerracottaDyeingRecipes(exporter);

//  CONCRETE_POWDER_BLOCKS
        //offerConcretePowderDyeingRecipes(exporter);

//  GLAZED_TERRACOTTA_BLOCKS
        //offerGlazedTerracottaSmeltingRecipes(exporter);

//  STAINED_GLASS_BLOCKS
        //offerStainedGlassDyeingRecipes(exporter);

//  STAINED_GLASS_PANE_BLOCKS
        //offerStainedGlassPaneDyeingRecipes(exporter);
        //offerStainedGlassPaneRecipes(exporter);

//  SHULKER_BOX_BLOCKS
        //Handled by the MIXIN to the serialized ShulkerBoxColoringRecipe

//  BED_BLOCKS
        //offerBedRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.BED_COLORING_RECIPE).offerTo(exporter, "bed_coloring_recipe");

//  CANDLE_BLOCKS
        //offerCandleDyeingRecipes(exporter);

//  BANNER_BLOCKS
        //offerBannerRecipes(exporter);
    }
}