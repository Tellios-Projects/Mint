package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.registry.tag.ModTags;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
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
                throw new RuntimeException(Registries.BLOCK.getId((Block)output).toString() + " failed to find associated DyeColor");
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
    public static void offerBedRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible bed : ModBlocks.BED_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(bed);
            ItemConvertible wool = ModBlocks.firstMatchOfColor(ModBlocks.WOOL_BLOCKS, color);
            offerBedRecipe(exporter, bed, wool);
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

    public static void offerWaxingRecipes(Consumer<RecipeJsonProvider> exporter) {
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().forEach((input, output)
                -> ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .input((ItemConvertible)input)
                .input(ModTags.Items.WAX)
                .group(RecipeProvider.getItemPath(output))
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.convertBetween(output, Items.HONEYCOMB)));
    }
    //TODO
    //potential compatibility errors:
    //i overwrite all vanilla honeycomb recipes to use a c:wax input tag, if someone else where to overwrite these after me, waxcap wax could no longer be used to craft things

    public static String groupName(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).toString();
    }

    private static final String breakfastGroup = Super.MOD_ID + ":" + "breakfast";

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //TODO fix all recipe book unlock criterion

        offerWaxingRecipes(exporter);

        // MINT - Special
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINT_COOKIE, 4)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.COCOA_BEANS)
                .input(ModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ModItems.MINT_SPRIG),
                        FabricRecipeProvider.conditionsFromItem(ModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.MINT_COOKIE) + "_shapeless"));
        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.MINT_SPRIG, "mint_dye", 1);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MINT_SPRIG_BLOCK);

        // PEACH - Special
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModBlocks.HYPERICUM, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModItems.PEACH_PIT, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModBlocks.CORAL_ANEMONE, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_SLICE, ModItems.PEACH, "peach_slice", 6);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEACH_LOG, ModItems.PEACH_BRANCH);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GOLDEN_PEACH)
                .pattern("XXX")
                .pattern("XOX")
                .pattern("XXX")
                .input('X', ModTags.Items.GOLD_INGOTS)
                .input('O', ModItems.PEACH)
                .criterion(FabricRecipeProvider.hasItem(ModItems.PEACH), FabricRecipeProvider.conditionsFromItem(ModItems.PEACH))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.GOLDEN_PEACH)));

        // PERIWINKLE - Special
        offerShapelessRecipe(exporter, ModItems.PERIWINKLE_DYE, ModBlocks.PERIWINKLE_PETALS, "periwinkle_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PERIWINKLE_DYE, ModBlocks.HIDCOTE_LAVENDER, "periwinkle_dye", 1);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModItems.SMOKED_LAVENDER, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BUSHEL);
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, ModBlocks.HIDCOTE_LAVENDER, ModItems.SMOKED_LAVENDER, 0.1f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LAVENDER_BREAD)
                .input(Items.BREAD)
                .input(ModItems.SMOKED_LAVENDER)
                .criterion(FabricRecipeProvider.hasItem(ModItems.SMOKED_LAVENDER), FabricRecipeProvider.conditionsFromItem(ModItems.SMOKED_LAVENDER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.LAVENDER_BREAD)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FLOWERING_MELON)
                .input(Items.GLISTERING_MELON_SLICE)
                .input(ModBlocks.PERIWINKLE_PETALS)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PERIWINKLE_PETALS), FabricRecipeProvider.conditionsFromItem(ModBlocks.PERIWINKLE_PETALS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.FLOWERING_MELON)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAVENDER_OIL)
                .input(Items.GLASS_BOTTLE)
                .input(ModItems.SMOKED_LAVENDER)
                .criterion(FabricRecipeProvider.hasItem(ModItems.SMOKED_LAVENDER), FabricRecipeProvider.conditionsFromItem(ModItems.SMOKED_LAVENDER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.LAVENDER_OIL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.LAVENDER_SOAP)
                .input(Items.HONEYCOMB)
                .input(ModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(ModItems.SMOKED_LAVENDER), FabricRecipeProvider.conditionsFromItem(ModItems.SMOKED_LAVENDER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.LAVENDER_SOAP)));

        // ARTICHOKE - Special
        offerShapelessRecipe(exporter, ModItems.WAXCAP_WAX, ModBlocks.WAXCAP_MUSHROOM, groupName(ModItems.WAXCAP_WAX), 1);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.CANDLE)
                .input(Character.valueOf('S'), Items.STRING)
                .input(Character.valueOf('H'), ModItems.WAXCAP_WAX)
                .pattern("S")
                .pattern("H")
                .criterion("has_string", FabricRecipeProvider.conditionsFromItem(Items.STRING)).criterion("has_waxcap_wax", FabricRecipeProvider.conditionsFromItem(ModItems.WAXCAP_WAX))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.TORCH, 4)
                .input(Character.valueOf('#'), Items.STICK)
                .input(Character.valueOf('X'), ModItems.WAXCAP_WAX)
                .pattern("X")
                .pattern("#")
                .criterion(FabricRecipeProvider.hasItem(ModItems.WAXCAP_WAX), FabricRecipeProvider.conditionsFromItem(ModItems.WAXCAP_WAX))
                .group("minecraft:torch") // might not really work but yknow might as well try
                .offerTo(exporter);

        offerShapelessRecipe(exporter, ModItems.ARTICHOKE, ModBlocks.THISTLE_FLOWER, groupName(ModItems.ARTICHOKE), 1);
        offerShapelessRecipe(exporter, ModItems.ARTICHOKE_HEART, ModItems.ARTICHOKE, groupName(ModItems.ARTICHOKE_HEART), 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ARTICHOKE_LAMB)
                .input(ModItems.ARTICHOKE_HEART)
                .input(Items.SWEET_BERRIES)
                .input(Items.COOKED_MUTTON)
                .criterion(FabricRecipeProvider.hasItem(ModItems.ARTICHOKE_HEART), FabricRecipeProvider.conditionsFromItem(ModItems.ARTICHOKE_HEART))
                .group(breakfastGroup)
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.ARTICHOKE_LAMB)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BREAKFAST_PORKCHOP)
                .input(ModItems.ARTICHOKE_HEART)
                .input(Items.COOKED_PORKCHOP)
                .input(Items.EGG)
                .criterion(FabricRecipeProvider.hasItem(ModItems.ARTICHOKE_HEART), FabricRecipeProvider.conditionsFromItem(ModItems.ARTICHOKE_HEART))
                .group(breakfastGroup)
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.BREAKFAST_PORKCHOP)));


//Dyes from combining vanilla dyes
        offerDyeMixingRecipe(exporter, ModItems.MINT_DYE, Items.LIME_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.PEACH_DYE, Items.PINK_DYE, Items.ORANGE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.PERIWINKLE_DYE, Items.PURPLE_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.ARTICHOKE_DYE, Items.LIME_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.FUCHSIA_DYE, Items.MAGENTA_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.VERMILION_DYE, Items.ORANGE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.SHAMROCK_DYE, Items.BLUE_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.INDIGO_DYE, Items.BLUE_DYE, Items.PURPLE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.BANANA_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.CERULEAN_DYE, Items.BLUE_DYE, Items.LIGHT_GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.ACORN_DYE, Items.BROWN_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        //offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, ModItems.FUCHSIA_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.MAGENTA_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.PINK_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.RED_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAROON_DYE, Items.RED_DYE, Items.BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.GRAPE_DYE, Items.PURPLE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.NAVY_DYE, Items.BLUE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.NAVY_DYE, Items.CYAN_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.SAP_DYE, Items.GREEN_DYE, Items.CYAN_DYE, RecipeCategory.MISC, 2);

        // Main
//  WOOL_BLOCKS
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_COLORING_RECIPE).offerTo(exporter, "wool_coloring_recipe");

//  CARPET_BLOCKS
        offerCarpetRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.WOOL_CARPET_COLORING_RECIPE).offerTo(exporter, "wool_carpet_coloring_recipe");

//  TERRACOTTA_BLOCKS
        offerTerracottaDyeingRecipes(exporter);

//  CONCRETE_POWDER_BLOCKS
        offerConcretePowderDyeingRecipes(exporter);

//  GLAZED_TERRACOTTA_BLOCKS
        offerGlazedTerracottaSmeltingRecipes(exporter);

//  STAINED_GLASS_BLOCKS
        offerStainedGlassDyeingRecipes(exporter);

//  STAINED_GLASS_PANE_BLOCKS
        offerStainedGlassPaneDyeingRecipes(exporter);
        offerStainedGlassPaneRecipes(exporter);

//  SHULKER_BOX_BLOCKS
        //Handled by the MIXIN to the serialized ShulkerBoxColoringRecipe

//  BED_BLOCKS
        offerBedRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.BED_COLORING_RECIPE).offerTo(exporter, "bed_coloring_recipe");

//  CANDLE_BLOCKS
        offerCandleDyeingRecipes(exporter);

//  BANNER_BLOCKS
        offerBannerRecipes(exporter);
    }
}