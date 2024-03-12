package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.recipe.ModRecipeSerializer;
import net.leafenzo.mint.registration.ModRegistryHelper;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.registry.tag.ModTags;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Consumer;

import static net.leafenzo.mint.registration.ModRegistryHelper.*;
import static net.leafenzo.mint.util.ModUtil.*;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) { super(output); }
    public static void offerReversibleCompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 9).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem).input(Character.valueOf('#'), baseItem).pattern("###").pattern("###").pattern("###").group(compactingGroup).criterion(RecipeProvider.hasItem(baseItem), RecipeProvider.conditionsFromItem(baseItem)).offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
    }
    public static void offerStairsRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createStairsRecipe(output, Ingredient.ofItems(input))
                .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(output)));
    }
    public static void offerSlabRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output, input);
    }
    public static void offerWallRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, output, input);
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
        for(ItemConvertible output : ModBlocks.DYED_TERRACOTTA_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            Item dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
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
            ItemConvertible dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(ModBlocks.DYECOLOR_FROM_BLOCK.get(output));
            offerConcretePowderDyeingRecipe(exporter, output, dye);
        }
    }
    public static void offerGlazedTerracottaSmeltingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible input : ModBlocks.DYED_TERRACOTTA_BLOCKS) {
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
            ItemConvertible dyeItem = ItemRegistry.DYE_ITEM_FROM_COLOR.get(ModBlocks.DYECOLOR_FROM_BLOCK.get(candle));
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
            DyeItem dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            offerStainedGlassPaneDyeingRecipe(exporter, pane, dye);
        }
    }
    public static void offerStainedGlassDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible glass : ModBlocks.STAINED_GLASS_BLOCKS) {
            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(glass);
            DyeItem dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
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
    //TODO deal with potential compatibility errors
    // due to overwriting all vanilla honeycomb recipes to use a c:wax input tag, if someone else where to overwrite these after me, waxcap wax could no longer be used to craft things

    // Decor Additions
    public static void offerCorrugatedIronDyingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(Block output : ModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            if(ModBlocks.DYECOLOR_FROM_BLOCK.get(output) == null) { continue; }

            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            ItemConvertible dyeItem = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
                    .input(Character.valueOf('#'), ModBlocks.CORRUGATED_IRON)
                    .input(Character.valueOf('X'), dyeItem)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("dyed_corrugated_iron").criterion("has_corrugated_iron", FabricRecipeProvider.conditionsFromItem(ModBlocks.CORRUGATED_IRON))
                    .offerTo(exporter);
        }
    }
    public static void offerCorrugatedIronRecipe(Consumer<RecipeJsonProvider> exporter, Block output) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
                .input(Character.valueOf('I'), Items.IRON_INGOT)
                .input(Character.valueOf('N'), Items.IRON_NUGGET)
                .pattern("III")
                .pattern("NNN")
                .pattern("III")
                .criterion("has_iron_ingot", FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .offerTo(exporter);
    }

    public static void offerMucktuffDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible output : ModBlocks.ALL_MUCKTUFF_BLOCKS) {
            if(ModBlocks.DYECOLOR_FROM_BLOCK.get(output) == null) { continue; }

            DyeColor color = ModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            Item dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
                    .input(Character.valueOf('#'), ModBlocks.MUCKTUFF)
                    .input(Character.valueOf('X'), dye)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("dyed_mucktuff").criterion("has_mucktuff", FabricRecipeProvider.conditionsFromItem(ModBlocks.MUCKTUFF))
                    .offerTo(exporter);
        }
    }


//    public static void offerPlanksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, int count) {
//        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, count).input(input).group("planks").criterion("has_logs", FabricRecipeProvider.conditionsFromItem(input)).offerTo(exporter);
//    }

//    public static void offerHangingSignRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
//        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, output, 6).group("hanging_sign")
//        .input(Character.valueOf('#'), input).input(Character.valueOf('X'), Items.CHAIN).pattern("X X").pattern("###").pattern("###")
//        .criterion("has_stripped_logs", RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
//    }

    public static void offerTrapdoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createTrapdoorRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerDoorRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createDoorRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerButtonRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createTransmutationRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerFenceRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerFenceGateRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createFenceGateRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerSignRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        createSignRecipe(output, Ingredient.ofItems(input))
                .criterion("has_planks", FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter);
    }

    public static void offerShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input1, ItemConvertible input2, int  outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount)
                .input(input1)
                .input(input2)
                .criterion(RecipeProvider.hasItem(input1), RecipeProvider.conditionsFromItem(input1))
                .criterion(RecipeProvider.hasItem(input2), RecipeProvider.conditionsFromItem(input2))
                .offerTo(exporter, RecipeProvider.convertBetween(output, input1));
    }

    public static void offerWoodsetRecipes(Consumer<RecipeJsonProvider> exporter, WoodSet woodSet) {
        Block log = woodSet.getLog();
        Block strippedLog = woodSet.getStrippedLog();
        Block strippedWood = woodSet.getStrippedWood();
        Block wood = woodSet.getWood();
        Block planks = woodSet.getPlanks();
        Block stairs = woodSet.getStairs();
        Block slab = woodSet.getSlab();
        Block trapDoor = woodSet.getTrapDoor();
        Block door = woodSet.getDoor();
        Block pressurePlate = woodSet.getPressurePlate();
        Block button = woodSet.getButton();
        Block fence = woodSet.getFence();
        Block fenceGate = woodSet.getFenceGate();
        Item boat = woodSet.getBoatItem();
        Item chestBoat = woodSet.getChestBoatItem();
        Item sign = woodSet.getSignItem();
        Item hangingSign = woodSet.getHangingSignItem();

        // this many checks is a lil stinky, but I just don't really want to deal with a woodset that doesn't have a trapdoor for some reason or another, which might happen
        if(log != null && wood != null) { offerBarkBlockRecipe(exporter, wood, log); }
        if(strippedLog != null && strippedWood != null) { offerBarkBlockRecipe(exporter, strippedWood, strippedLog); }
        if(planks != null) {
            //stick recipes are have already been handled by the item tag
            offerPlanksRecipe(exporter, planks, woodSet.getItemLogsTag(), 4);
            if (mario) {thenOnly(bros);}
            if (trapDoor != null) { offerTrapdoorRecipe(exporter, trapDoor, planks); }
            if (door != null) { offerDoorRecipe(exporter, door, planks); }
            if (pressurePlate != null) { offerPressurePlateRecipe(exporter, pressurePlate, planks); }
            if (button != null) { offerButtonRecipe(exporter, button, planks); }
            if (fence != null) { offerFenceRecipe(exporter, fence, planks); }
            if (fenceGate != null) { offerFenceGateRecipe(exporter, fenceGate, planks); }
            if (boat != null) { offerBoatRecipe(exporter, boat, planks); }
            if (chestBoat != null) { offerChestBoatRecipe(exporter, chestBoat, boat); }
            if (stairs != null) { offerStairsRecipe(exporter, stairs, planks); }
            if (slab != null) { offerSlabRecipe(exporter, slab, planks); }
            if (sign != null) { offerSignRecipe(exporter, sign, planks); }
            if (strippedLog != null && hangingSign != null) { offerHangingSignRecipe(exporter, hangingSign, strippedLog); }
        }
    }

    public static String groupName(ItemConvertible item) {
        return Registries.ITEM.getId(item.asItem()).toString();
    }

    private static final String breakfastGroup = Super.MOD_ID + ":" + "breakfast";

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //TODO fix all recipe book unlock criterion
        offerWaxingRecipes(exporter);

        // MINT - Special
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MINT_TEA, 1)
                .input(Items.POTION)
                .input(ModItems.WINTER_MEDLEY)
                .criterion(FabricRecipeProvider.hasItem(ModItems.WINTER_MEDLEY), FabricRecipeProvider.conditionsFromItem(ModItems.WINTER_MEDLEY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.MINT_TEA) + "_shapeless"));

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

        offerWoodsetRecipes(exporter, ModBlocks.WINTERGREEN_WOODSET);

        offerShapelessRecipe(exporter, ModItems.MINT_DYE, ModItems.WINTERGREEN_SAP, "mint_dye", 1);
        offerShapelessRecipe(exporter, ModItems.WINTER_MEDLEY, ModItems.WINTERGREEN_BERRIES, ModItems.MINT_SPRIG, 1);
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, ModBlocks.WINTERGREEN_WOODSET.getLeaves(), ModItems.WINTERGREEN_SAP, 0.1f);

        // PEACH - Special
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModBlocks.HYPERICUM, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModItems.PEACH_PIT, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_DYE, ModBlocks.CORAL_ANEMONE, "peach_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PEACH_SLICE, ModItems.PEACH, "peach_slice", 4);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PEACH_LOG, ModItems.PEACH_BRANCH);

        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, ModBlocks.CORAL_ANEMONE, ModItems.COOKED_ANEMONE, 0.35f);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 600, ModBlocks.CORAL_ANEMONE, ModItems.COOKED_ANEMONE, 0.35f);
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, ModBlocks.CORAL_ANEMONE, ModItems.PEACH_DYE, 0.35f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.AMBROSIA)
                .input(Items.GOLDEN_APPLE)
                .input(Items.GLISTERING_MELON_SLICE)
                .input(ModItems.GOLDEN_PEACH)
                .input(Items.BOWL)
                .criterion(FabricRecipeProvider.hasItem(Items.GOLDEN_APPLE), FabricRecipeProvider.conditionsFromItem(Items.GOLDEN_APPLE))
                .criterion(FabricRecipeProvider.hasItem(Items.GLISTERING_MELON_SLICE), FabricRecipeProvider.conditionsFromItem(Items.GLISTERING_MELON_SLICE))
                .criterion(FabricRecipeProvider.hasItem(ModItems.GOLDEN_PEACH), FabricRecipeProvider.conditionsFromItem(ModItems.GOLDEN_PEACH))
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL), FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.AMBROSIA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.FRUIT_SALAD)
                 .input(Items.APPLE)
                 .input(Items.MELON_SLICE)
                 .input(ModItems.PEACH_SLICE)
                 .input(Items.BOWL)
                .criterion(FabricRecipeProvider.hasItem(Items.APPLE), FabricRecipeProvider.conditionsFromItem(Items.APPLE))
                .criterion(FabricRecipeProvider.hasItem(Items.MELON_SLICE), FabricRecipeProvider.conditionsFromItem(Items.MELON_SLICE))
                .criterion(FabricRecipeProvider.hasItem(ModItems.PEACH_SLICE), FabricRecipeProvider.conditionsFromItem(ModItems.PEACH_SLICE))
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL), FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.FRUIT_SALAD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.PEACH_COBBLER)
                .pattern("PPP")
                .pattern("SES")
                .pattern("WWW")
                .input('P', ModItems.PEACH_SLICE)
                .input('S', Items.SUGAR)
                .input('E', Items.EGG)
                .input('W', Items.WHEAT)
                .criterion(FabricRecipeProvider.hasItem(ModItems.PEACH_SLICE), FabricRecipeProvider.conditionsFromItem(ModItems.PEACH_SLICE))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .criterion(FabricRecipeProvider.hasItem(Items.EGG), FabricRecipeProvider.conditionsFromItem(Items.EGG))
                .criterion(FabricRecipeProvider.hasItem(Items.WHEAT), FabricRecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.PEACH_COBBLER)));

        // TODO remainder ignoring shaped recipe
//        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GOLDEN_PEACH)
//                .pattern("XXX")
//                .pattern("XOX")
//                .pattern("XXX")
//                .input('X', ModTags.Items.GOLD_INGOTS)
//                .input('O', ModItems.PEACH)
//                .criterion(FabricRecipeProvider.hasItem(ModItems.PEACH), FabricRecipeProvider.conditionsFromItem(ModItems.PEACH))
//                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.GOLDEN_PEACH)));
        // Silly backup recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GOLDEN_PEACH)
                .pattern("XXX")
                .pattern("SSX")
                .pattern("SSX")
                .input('X', ModTags.Items.GOLD_INGOTS)
                .input('S', ModItems.PEACH_SLICE)
                .criterion(FabricRecipeProvider.hasItem(ModItems.PEACH_SLICE), FabricRecipeProvider.conditionsFromItem(ModItems.PEACH_SLICE))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModItems.GOLDEN_PEACH)));

        //<editor-fold desc ="PERIWINKLE - Special">
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_CLAY, 2)
                .pattern(" C ")
                .pattern("COC")
                .pattern(" C ")
                .input('C', Items.CLAY_BALL)
                .input('O', ModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Items.CLAY_BALL), FabricRecipeProvider.conditionsFromItem(Items.CLAY_BALL))
                .criterion(FabricRecipeProvider.hasItem(ModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.LAVENDER_CLAY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ModBlocks.LAVENDER_CLAY)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LAVENDER_CLAY), FabricRecipeProvider.conditionsFromItem(ModBlocks.LAVENDER_CLAY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.LAVENDER_BRICKS)));

        offerStairsRecipe(exporter, ModBlocks.LAVENDER_BRICK_STAIRS, ModBlocks.LAVENDER_BRICKS);
        offerSlabRecipe(exporter, ModBlocks.LAVENDER_BRICK_SLAB, ModBlocks.LAVENDER_BRICKS);
        offerWallRecipe(exporter, ModBlocks.LAVENDER_BRICK_WALL, ModBlocks.LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BRICK_STAIRS, ModBlocks.LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BRICK_SLAB, ModBlocks.LAVENDER_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BRICK_WALL, ModBlocks.LAVENDER_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_LAVENDER_BRICKS)
                .input(ModBlocks.LAVENDER_BRICKS)
                .input(ModBlocks.PERIWINKLE_PETALS)
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.LAVENDER_BRICKS), FabricRecipeProvider.conditionsFromItem(ModBlocks.LAVENDER_BRICKS))
                .criterion(FabricRecipeProvider.hasItem(ModBlocks.PERIWINKLE_PETALS), FabricRecipeProvider.conditionsFromItem(ModBlocks.PERIWINKLE_PETALS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.MOSSY_LAVENDER_BRICKS)));

        offerStairsRecipe(exporter, ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS, ModBlocks.MOSSY_LAVENDER_BRICKS);
        offerSlabRecipe(exporter, ModBlocks.MOSSY_LAVENDER_BRICK_SLAB, ModBlocks.MOSSY_LAVENDER_BRICKS);
        offerWallRecipe(exporter, ModBlocks.MOSSY_LAVENDER_BRICK_WALL, ModBlocks.MOSSY_LAVENDER_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS, ModBlocks.MOSSY_LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_LAVENDER_BRICK_SLAB, ModBlocks.MOSSY_LAVENDER_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MOSSY_LAVENDER_BRICK_WALL, ModBlocks.MOSSY_LAVENDER_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LAVENDER_OIL_LANTERN)
                .input(Blocks.LANTERN)
                .input(ModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Blocks.LANTERN), FabricRecipeProvider.conditionsFromItem(Blocks.LANTERN))
                .criterion(FabricRecipeProvider.hasItem(ModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.LAVENDER_OIL_LANTERN) + "_from_lantern"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.LAVENDER_OIL_LANTERN)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .input('#', Items.IRON_NUGGET)
                .input('O', ModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Blocks.LANTERN), FabricRecipeProvider.conditionsFromItem(Blocks.LANTERN))
                .criterion(FabricRecipeProvider.hasItem(ModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ModBlocks.LAVENDER_OIL_LANTERN) + "_from_nuggets"));

        offerShapelessRecipe(exporter, ModItems.PERIWINKLE_DYE, ModBlocks.PERIWINKLE_PETALS, "periwinkle_dye", 1);
        offerShapelessRecipe(exporter, ModItems.PERIWINKLE_DYE, ModBlocks.HIDCOTE_LAVENDER, "periwinkle_dye", 1);

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ModBlocks.HIDCOTE_LAVENDER, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LAVENDER_BUSHEL);
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, ModBlocks.HIDCOTE_LAVENDER, ModItems.SMOKED_LAVENDER, 0.1f);

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.LAVENDER_BREAD)
                .pattern("LWL")
                .input(Character.valueOf('L'), ModItems.SMOKED_LAVENDER)
                .input(Character.valueOf('W'), Items.WHEAT)
                .criterion("has_smoked_lavender", FabricRecipeProvider.conditionsFromItem(ModItems.SMOKED_LAVENDER))
                .criterion("has_wheat", FabricRecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter);

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
        //</editor-fold>


        //<editor-fold desc ="ARTICHOKE - Special">
        offerShapelessRecipe(exporter, ModItems.WAXCAP_WAX, ModBlocks.WAXCAP_MUSHROOM, groupName(ModItems.WAXCAP_WAX), 1);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC,  ModItems.WAXCAP_WAX, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAXCAP_WAX_BLOCK);
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, ModBlocks.WAXCAP_MUSHROOM, ModItems.ARTICHOKE_DYE, 0.35f);

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
        //</editor-fold>

//Dyes from combining vanilla dyes
        // These work for sheep mixing too... somehow
        // Sheep bred with sheep of a non-vanilla dyeColor default to a 50/50 chance to get the color of either parent
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
        //offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, ModItems.FUCHSIA_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2); // nuh uh bad doesn't work for sheep
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.MAGENTA_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.PINK_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, Items.RED_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.MAROON_DYE, Items.RED_DYE, Items.BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.GRAPE_DYE, Items.PURPLE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.NAVY_DYE, Items.BLUE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.NAVY_DYE, Items.CYAN_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.SAP_DYE, Items.GREEN_DYE, Items.CYAN_DYE, RecipeCategory.MISC, 2);
//        offerDyeMixingRecipe(exporter, ModItems.MOLD_DYE, ModItems.ARTICHOKE, Items.BLACK_DYE, RecipeCategory.MISC, 2); // aaawwe, no fiddle
        offerDyeMixingRecipe(exporter, ModItems.MOLD_DYE, Items.GREEN_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.AMBER_DYE, Items.ORANGE_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.SAGE_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ModItems.VELVET_DYE, Items.PURPLE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);

        // Decor Additions
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MUCKTUFF, 4)
                        .input(Character.valueOf('T'), Blocks.TUFF)
                        .input(Character.valueOf('M'), Blocks.MUD)
                        .pattern("TM")
                        .pattern("MT")
                        .criterion("has_mud", FabricRecipeProvider.conditionsFromItem(Blocks.MUD))
                        .offerTo(exporter);
        offerMucktuffDyeingRecipes(exporter);

        offerCorrugatedIronRecipe(exporter, ModBlocks.CORRUGATED_IRON);
        offerCorrugatedIronDyingRecipes(exporter);

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
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.MOD_SHULKER_BOX_COLORING_RECIPE).offerTo(exporter, "mod_shulker_box_coloring_recipe");

//  BED_BLOCKS
        offerBedRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ModRecipeSerializer.BED_COLORING_RECIPE).offerTo(exporter, "bed_coloring_recipe");

//  CANDLE_BLOCKS
        offerCandleDyeingRecipes(exporter);

//  BANNER_BLOCKS
        offerBannerRecipes(exporter);
    }
}