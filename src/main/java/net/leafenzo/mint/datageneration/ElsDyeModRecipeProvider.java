package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.leafenzo.mint.recipe.ElsDyeModRecipeSerializer;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.registry.tag.ElsDyeModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.function.Consumer;

import static net.leafenzo.mint.registration.ElsDyeModRegistryHelper.ItemRegistry;
import static net.leafenzo.mint.util.ElsDyeModUtil.*;

public class ElsDyeModRecipeProvider extends FabricRecipeProvider {
    public ElsDyeModRecipeProvider(FabricDataOutput output) { super(output); }
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
        for(Block wool : ElsDyeModBlocks.WOOL_CARPET_FROM_WOOL.keySet()) {
            offerCarpetRecipe(exporter, ElsDyeModBlocks.WOOL_CARPET_FROM_WOOL.get(wool), wool);
        }
    }
    public static void offerTerracottaDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible output : ElsDyeModBlocks.DYED_TERRACOTTA_BLOCKS) {
            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            Item dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            if (dye != null) {
                offerTerracottaDyeingRecipe(exporter, output, dye);
            }
            else {
                throw new RuntimeException(Registries.BLOCK.getId((Block)output) + " failed to find associated DyeColor");
            }
        }
    }
    public static void offerConcretePowderDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible output : ElsDyeModBlocks.CONCRETE_POWDER_BLOCKS) {
            ItemConvertible dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output));
            offerConcretePowderDyeingRecipe(exporter, output, dye);
        }
    }
    public static void offerGlazedTerracottaSmeltingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible input : ElsDyeModBlocks.DYED_TERRACOTTA_BLOCKS) {
            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(input);
            ItemConvertible output = ElsDyeModBlocks.firstMatchOfColor(ElsDyeModBlocks.GLAZED_TERRACOTTA_BLOCKS, color);

            CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(input.asItem()), RecipeCategory.DECORATIONS, output.asItem(), 0.1f, 200)
                    .criterion(FabricRecipeProvider.hasItem(input), FabricRecipeProvider.conditionsFromItem(input.asItem()))
                    .offerTo(exporter);
        }
    }
    public static void offerBannerRecipes(Consumer<RecipeJsonProvider> exporter) { //this looks needlessly overcomplicated
        for(Block banner : ElsDyeModBlocks.BANNER_BLOCKS) {
            DyeColor bannerColor = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(banner);
            ItemConvertible wool = ElsDyeModBlocks.firstMatchOfColor(ElsDyeModBlocks.WOOL_BLOCKS, bannerColor);

            offerBannerRecipe(exporter, banner, wool);
        }
    }
    public static void offerCandleDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible candle : ElsDyeModBlocks.CANDLE_BLOCKS) {
            ItemConvertible dyeItem = ItemRegistry.DYE_ITEM_FROM_COLOR.get(ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(candle));
            offerCandleDyeingRecipe(exporter, candle, dyeItem);
        }
    }
    public static void offerBedRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible bed : ElsDyeModBlocks.BED_BLOCKS) {
            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(bed);
            ItemConvertible wool = ElsDyeModBlocks.firstMatchOfColor(ElsDyeModBlocks.WOOL_BLOCKS, color);
            offerBedRecipe(exporter, bed, wool);
        }
    }
    public static void offerStainedGlassPaneRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(Block glass : ElsDyeModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.keySet()) {
            offerStainedGlassPaneRecipe(exporter, ElsDyeModBlocks.STAINED_GLASS_PANE_FROM_STAINED_GLASS.get(glass), glass);
        }
    }
    public static void offerStainedGlassPaneDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible pane : ElsDyeModBlocks.STAINED_GLASS_PANE_BLOCKS) {
            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(pane);
            DyeItem dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            offerStainedGlassPaneDyeingRecipe(exporter, pane, dye);
        }
    }
    public static void offerStainedGlassDyeingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(ItemConvertible glass : ElsDyeModBlocks.STAINED_GLASS_BLOCKS) {
            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(glass);
            DyeItem dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            offerStainedGlassDyeingRecipe(exporter, glass, dye);
        }
    }

    public static void offerWaxingRecipes(Consumer<RecipeJsonProvider> exporter) {
        HoneycombItem.UNWAXED_TO_WAXED_BLOCKS.get().forEach((input, output)
                -> ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output)
                .input(input)
                .input(ElsDyeModTags.Items.WAX)
                .group(RecipeProvider.getItemPath(output))
                .criterion(RecipeProvider.hasItem(input), RecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, RecipeProvider.convertBetween(output, Items.HONEYCOMB)));
    }
    //TODO deal with potential compatibility errors
    // due to overwriting all vanilla honeycomb recipes to use a c:wax input tag, if someone else where to overwrite these after me, waxcap wax could no longer be used to craft things

    // Decor Additions
    public static void offerCorrugatedIronDyingRecipes(Consumer<RecipeJsonProvider> exporter) {
        for(Block output : ElsDyeModBlocks.ALL_CORRUGATED_IRON_BLOCKS) {
            if(ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output) == null) { continue; }

            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            ItemConvertible dyeItem = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
                    .input(Character.valueOf('#'), ElsDyeModBlocks.CORRUGATED_IRON)
                    .input(Character.valueOf('X'), dyeItem)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("dyed_corrugated_iron").criterion("has_corrugated_iron", FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.CORRUGATED_IRON))
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
        for(ItemConvertible output : ElsDyeModBlocks.ALL_MUCKTUFF_BLOCKS) {
            if(ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output) == null) { continue; }

            DyeColor color = ElsDyeModBlocks.DYECOLOR_FROM_BLOCK.get(output);
            Item dye = ItemRegistry.DYE_ITEM_FROM_COLOR.get(color);
            ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
                    .input(Character.valueOf('#'), ElsDyeModBlocks.MUCKTUFF)
                    .input(Character.valueOf('X'), dye)
                    .pattern("###")
                    .pattern("#X#")
                    .pattern("###")
                    .group("dyed_mucktuff").criterion("has_mucktuff", FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.MUCKTUFF))
                    .offerTo(exporter);
        }
    }




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

    public static void offerPlanksRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, int count) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, count).input(input).group("planks").criterion("has_logs", RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
    }

    public static void offerReversible2x2CompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        ShapelessRecipeJsonBuilder.create(reverseCategory, baseItem, 4).input(compactItem).group(reverseGroup).criterion(RecipeProvider.hasItem(compactItem), RecipeProvider.conditionsFromItem(compactItem)).offerTo(exporter, new Identifier(reverseId+"_from_"+compactingId));
        ShapedRecipeJsonBuilder.create(compactingCategory, compactItem)
                .input(Character.valueOf('#'), baseItem)
                .pattern("##")
                .pattern("##")
                .group(compactingGroup)
                .criterion(FabricRecipeProvider.hasItem(baseItem), FabricRecipeProvider.conditionsFromItem(baseItem))
                .offerTo(exporter, new Identifier(compactingId+"_from_"+reverseId));
    }
    public static void offerReversible2x2CompactingRecipes(Consumer<RecipeJsonProvider> exporter, RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem) {
        offerReversible2x2CompactingRecipes(exporter, reverseCategory, baseItem, compactingCategory, compactItem, RecipeProvider.getRecipeName(compactItem), ElsDyeMod.MOD_ID + ":" + Registries.ITEM.getId(baseItem.asItem()).getPath(), RecipeProvider.getRecipeName(baseItem), ElsDyeMod.MOD_ID + ":" + Registries.ITEM.getId(baseItem.asItem()).getPath() + "_reverse");
    }
    public static void offerCandyCaneBarkBlockRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 4).input(Character.valueOf('#'), input).pattern("##").pattern("##").group("bark").criterion("has_log", RecipeProvider.conditionsFromItem(input)).offerTo(exporter);
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

    private static final String breakfastGroup = ElsDyeMod.MOD_ID + ":" + "breakfast";

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        //TODO fix all recipe book unlock criterion / figure out if there is even a problem with that still.
        offerWaxingRecipes(exporter);

        //<editor-fold desc ="MINT - Special">
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.MINT_TEA, 1)
//                .input(Ingredient.fromJson(new PotionIngredient(Potions.WATER).toVanilla().toJson())) // Error?
//                .input(Ingredient.ofStacks(PotionUtil.setPotion(Items.POTION.getDefaultStack(), Potions.WATER))) // Doesn't work, just allows any potion to be used
                .input(Items.POTION)
                .input(ElsDyeModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.MINT_SPRIG), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.MINT_TEA) + "_shapeless"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.MINT_COOKIE, 4)
                .input(Items.WHEAT)
                .input(Items.WHEAT)
                .input(Items.COCOA_BEANS)
                .input(ElsDyeModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.MINT_SPRIG),
                        FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.MINT_SPRIG))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.MINT_COOKIE) + "_shapeless"));

        offerShapelessRecipe(exporter, ElsDyeModItems.MINT_DYE, ElsDyeModItems.MINT_SPRIG, "mint_dye", 1);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ElsDyeModItems.MINT_SPRIG, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MINT_SPRIG_BLOCK);

        offerWoodsetRecipes(exporter, ElsDyeModBlocks.WINTERGREEN_WOODSET);

        offerShapelessRecipe(exporter, ElsDyeModItems.MINT_DYE, ElsDyeModItems.WINTERGREEN_SAP, "mint_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.WINTER_MEDLEY, ElsDyeModItems.WINTERGREEN_BERRIES, ElsDyeModItems.MINT_SPRIG, 1);
        offerSmelting(exporter, Collections.singletonList(ElsDyeModBlocks.WINTERGREEN_WOODSET.getLeaves()), RecipeCategory.MISC, ElsDyeModItems.WINTERGREEN_SAP, 0.1f, 200, "wintergreen_sap");

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MINT_BRICKS, 4)
                .pattern("XO")
                .pattern("OX")
                .input('X', ElsDyeModTags.Items.COBBLESTONE)
                .input('O', ElsDyeModItems.WINTERGREEN_SAP)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.WINTERGREEN_SAP), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.WINTERGREEN_SAP))
                .criterion("has_cobblestone", FabricRecipeProvider.conditionsFromTag(ElsDyeModTags.Items.COBBLESTONE))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.MINT_BRICKS)));

        offerStairsRecipe(exporter, ElsDyeModBlocks.MINT_BRICK_STAIRS, ElsDyeModBlocks.MINT_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.MINT_BRICK_SLAB, ElsDyeModBlocks.MINT_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.MINT_BRICK_WALL, ElsDyeModBlocks.MINT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MINT_BRICK_STAIRS, ElsDyeModBlocks.MINT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MINT_BRICK_SLAB, ElsDyeModBlocks.MINT_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MINT_BRICK_WALL, ElsDyeModBlocks.MINT_BRICKS);
        
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK, ElsDyeModItems.WINTERGREEN_CANDY_CANE);
        offerCandyCaneBarkBlockRecipe(exporter, ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BARK, ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.WINTERGREEN_CANDY_CANE, 4)
                .input(ElsDyeModTags.Items.WINTERGREEN_CANDY_CANE_BLOCKS)
                .criterion("has_peppermint_candy_cane_block", FabricRecipeProvider.conditionsFromTag(ElsDyeModTags.Items.WINTERGREEN_CANDY_CANE_BLOCKS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.WINTERGREEN_CANDY_CANE) + "_from_block"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.WINTERGREEN_CANDY_CANE, 4)
                .input(Items.SUGAR)
                .input(ElsDyeModItems.WINTERGREEN_BERRIES)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.WINTERGREEN_BERRIES), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.WINTERGREEN_BERRIES))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.WINTERGREEN_CANDY_CANE)));

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK, ElsDyeModItems.PEPPERMINT_CANDY_CANE);
        offerCandyCaneBarkBlockRecipe(exporter, ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BARK, ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.PEPPERMINT_CANDY_CANE, 4)
                .input(ElsDyeModTags.Items.PEPPERMINT_CANDY_CANE_BLOCKS)
                .criterion("has_peppermint_candy_cane_block", FabricRecipeProvider.conditionsFromTag(ElsDyeModTags.Items.PEPPERMINT_CANDY_CANE_BLOCKS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PEPPERMINT_CANDY_CANE) + "_from_block"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.PEPPERMINT_CANDY_CANE, 4)
                .input(Items.SUGAR)
                .input(ElsDyeModItems.MINT_SPRIG)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.MINT_SPRIG), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.MINT_SPRIG))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PEPPERMINT_CANDY_CANE)));
        //</editor-fold>
        //<editor-fold desc ="PEACH - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.PEACH_DYE, ElsDyeModBlocks.HYPERICUM, "peach_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.PEACH_DYE, ElsDyeModItems.PEACH_PIT, "peach_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.PEACH_DYE, ElsDyeModBlocks.CORAL_ANEMONE, "peach_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.PEACH_SLICE, ElsDyeModItems.PEACH, "peach_slice", 4);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.PEACH_LOG, ElsDyeModItems.PEACH_BRANCH);
        offerShapelessRecipe(exporter, Blocks.JUNGLE_PLANKS, ElsDyeModBlocks.PEACH_LOG, "jungle_planks", 4);
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, ElsDyeModBlocks.CORAL_ANEMONE, ElsDyeModItems.COOKED_ANEMONE, 0.35f);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 600, ElsDyeModBlocks.CORAL_ANEMONE, ElsDyeModItems.COOKED_ANEMONE, 0.35f);
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, ElsDyeModBlocks.CORAL_ANEMONE, ElsDyeModItems.COOKED_ANEMONE, 0.35f);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.AMBROSIA)
                .input(Items.GOLDEN_APPLE)
                .input(Items.GLISTERING_MELON_SLICE)
                .input(ElsDyeModItems.GOLDEN_PEACH)
                .input(ElsDyeModItems.GOLDEN_STRAWBERRY)
                .input(Items.BOWL)
                .criterion(FabricRecipeProvider.hasItem(Items.GOLDEN_APPLE), FabricRecipeProvider.conditionsFromItem(Items.GOLDEN_APPLE))
                .criterion(FabricRecipeProvider.hasItem(Items.GLISTERING_MELON_SLICE), FabricRecipeProvider.conditionsFromItem(Items.GLISTERING_MELON_SLICE))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.GOLDEN_PEACH), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.GOLDEN_PEACH))
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL), FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.AMBROSIA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.FRUIT_SALAD)
                 .input(Items.APPLE)
                 .input(Items.MELON_SLICE)
                 .input(ElsDyeModItems.PEACH_SLICE)
                 .input(Items.BOWL)
                .criterion(FabricRecipeProvider.hasItem(Items.APPLE), FabricRecipeProvider.conditionsFromItem(Items.APPLE))
                .criterion(FabricRecipeProvider.hasItem(Items.MELON_SLICE), FabricRecipeProvider.conditionsFromItem(Items.MELON_SLICE))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PEACH_SLICE), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PEACH_SLICE))
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL), FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.FRUIT_SALAD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.PEACH_COBBLER)
                .pattern("PPP")
                .pattern("SES")
                .pattern("WWW")
                .input('P', ElsDyeModItems.PEACH_SLICE)
                .input('S', Items.SUGAR)
                .input('E', Items.EGG)
                .input('W', Items.WHEAT)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PEACH_SLICE), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PEACH_SLICE))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .criterion(FabricRecipeProvider.hasItem(Items.EGG), FabricRecipeProvider.conditionsFromItem(Items.EGG))
                .criterion(FabricRecipeProvider.hasItem(Items.WHEAT), FabricRecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PEACH_COBBLER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CORALSOIL, 4)
                .pattern("XO")
                .pattern("OX")
                .input('X', Items.CLAY_BALL)
                .input('O', ElsDyeModBlocks.CORAL_ANEMONE)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.CORAL_ANEMONE.asItem()), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.CORAL_ANEMONE.asItem()))
                .criterion(FabricRecipeProvider.hasItem(Items.CLAY_BALL), FabricRecipeProvider.conditionsFromItem(Items.CLAY_BALL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.CORALSOIL)));
        offerSmelting(exporter, Collections.singletonList(ElsDyeModBlocks.CORALSOIL), RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CORALSOIL_BRICKS, 0.1f, 200, "coralsoil_bricks");
        offerCrackingRecipe(exporter, ElsDyeModBlocks.CRACKED_CORALSOIL_BRICKS, ElsDyeModBlocks.CORALSOIL_BRICKS);

        offerStairsRecipe(exporter, ElsDyeModBlocks.CORALSOIL_BRICK_STAIRS, ElsDyeModBlocks.CORALSOIL_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.CORALSOIL_BRICK_SLAB, ElsDyeModBlocks.CORALSOIL_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.CORALSOIL_BRICK_WALL, ElsDyeModBlocks.CORALSOIL_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CORALSOIL_BRICK_STAIRS, ElsDyeModBlocks.CORALSOIL_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CORALSOIL_BRICK_SLAB, ElsDyeModBlocks.CORALSOIL_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CORALSOIL_BRICK_WALL, ElsDyeModBlocks.CORALSOIL_BRICKS);
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
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.GOLDEN_PEACH)
                .pattern("XXX")
                .pattern("XPX")
                .pattern("XXX")
                .input('X', ElsDyeModTags.Items.GOLD_INGOTS)
                .input('P', ElsDyeModItems.PEACH)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PEACH), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PEACH))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.GOLDEN_PEACH)));
        //</editor-fold>
        //<editor-fold desc ="PERIWINKLE - Special">
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_CLAY, 2)
                .pattern(" C ")
                .pattern("COC")
                .pattern(" C ")
                .input('C', Items.CLAY_BALL)
                .input('O', ElsDyeModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Items.CLAY_BALL), FabricRecipeProvider.conditionsFromItem(Items.CLAY_BALL))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.LAVENDER_CLAY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ElsDyeModBlocks.LAVENDER_CLAY)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.LAVENDER_CLAY), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.LAVENDER_CLAY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.LAVENDER_BRICKS)));
        offerStairsRecipe(exporter, ElsDyeModBlocks.LAVENDER_BRICK_STAIRS, ElsDyeModBlocks.LAVENDER_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.LAVENDER_BRICK_SLAB, ElsDyeModBlocks.LAVENDER_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.LAVENDER_BRICK_WALL, ElsDyeModBlocks.LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_BRICK_STAIRS, ElsDyeModBlocks.LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_BRICK_SLAB, ElsDyeModBlocks.LAVENDER_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_BRICK_WALL, ElsDyeModBlocks.LAVENDER_BRICKS);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS)
                .input(ElsDyeModBlocks.LAVENDER_BRICKS)
                .input(ElsDyeModBlocks.PERIWINKLE_PETALS)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.LAVENDER_BRICKS), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.LAVENDER_BRICKS))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.PERIWINKLE_PETALS), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.PERIWINKLE_PETALS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS)));
        offerStairsRecipe(exporter, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_STAIRS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_WALL, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_STAIRS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_WALL, ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModBlocks.LAVENDER_OIL_LANTERN)
                .input(Blocks.LANTERN)
                .input(ElsDyeModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Blocks.LANTERN), FabricRecipeProvider.conditionsFromItem(Blocks.LANTERN))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.LAVENDER_OIL_LANTERN) + "_from_lantern"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModBlocks.LAVENDER_OIL_LANTERN)
                .pattern("###")
                .pattern("#O#")
                .pattern("###")
                .input('#', Items.IRON_NUGGET)
                .input('O', ElsDyeModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(Blocks.LANTERN), FabricRecipeProvider.conditionsFromItem(Blocks.LANTERN))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.LAVENDER_OIL), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.LAVENDER_OIL))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.LAVENDER_OIL_LANTERN) + "_from_nuggets"));
        offerShapelessRecipe(exporter, ElsDyeModItems.PERIWINKLE_DYE, ElsDyeModBlocks.PERIWINKLE_PETALS, "periwinkle_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.PERIWINKLE_DYE, ElsDyeModBlocks.HIDCOTE_LAVENDER, "periwinkle_dye", 1);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, ElsDyeModBlocks.HIDCOTE_LAVENDER, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.LAVENDER_BUSHEL);
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(ElsDyeModBlocks.HIDCOTE_LAVENDER), RecipeCategory.MISC, ElsDyeModItems.SMOKED_LAVENDER, 0.1f, 100, RecipeSerializer.SMOKING).criterion(RecipeProvider.hasItem(ElsDyeModBlocks.HIDCOTE_LAVENDER), RecipeProvider.conditionsFromItem(ElsDyeModBlocks.HIDCOTE_LAVENDER)).offerTo(exporter, RecipeProvider.getItemPath(ElsDyeModItems.SMOKED_LAVENDER) + "_from_" + "smoking");
//        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, ModBlocks.HIDCOTE_LAVENDER, ModItems.SMOKED_LAVENDER, 0.1f);
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.LAVENDER_BREAD)
                .pattern("LWL")
                .input(Character.valueOf('L'), ElsDyeModItems.SMOKED_LAVENDER)
                .input(Character.valueOf('W'), Items.WHEAT)
                .criterion("has_smoked_lavender", FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.SMOKED_LAVENDER))
                .criterion("has_wheat", FabricRecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.FLOWERING_MELON)
                .input(Items.GLISTERING_MELON_SLICE)
                .input(ElsDyeModBlocks.PERIWINKLE_PETALS)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.PERIWINKLE_PETALS), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.PERIWINKLE_PETALS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.FLOWERING_MELON)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.LAVENDER_OIL)
                .input(Items.GLASS_BOTTLE)
                .input(ElsDyeModItems.SMOKED_LAVENDER)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.SMOKED_LAVENDER), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.SMOKED_LAVENDER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.LAVENDER_OIL)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.LAVENDER_SOAP)
                .input(Items.HONEYCOMB)
                .input(ElsDyeModItems.LAVENDER_OIL)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.SMOKED_LAVENDER), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.SMOKED_LAVENDER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.LAVENDER_SOAP)));
        //</editor-fold>
        //<editor-fold desc ="ARTICHOKE - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.WAXCAP_WAX, ElsDyeModBlocks.WAXCAP_MUSHROOM, groupName(ElsDyeModItems.WAXCAP_WAX), 1);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC,  ElsDyeModItems.WAXCAP_WAX, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.WAXCAP_WAX_BLOCK);
        offerSmelting(exporter, Collections.singletonList(ElsDyeModBlocks.WAXCAP_MUSHROOM), RecipeCategory.MISC, ElsDyeModItems.ARTICHOKE_DYE, 0.35f, 200, "artichoke_dye");
        offerShapelessRecipe(exporter, ElsDyeModItems.ARTICHOKE_DYE, ElsDyeModItems.ARTICHOKE_HEART, "artichoke_dye", 1);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Items.CANDLE)
                .input(Character.valueOf('S'), Items.STRING)
                .input(Character.valueOf('H'), ElsDyeModItems.WAXCAP_WAX)
                .pattern("S")
                .pattern("H")
                .criterion("has_string", FabricRecipeProvider.conditionsFromItem(Items.STRING)).criterion("has_waxcap_wax", FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.WAXCAP_WAX))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.TORCH, 4)
                .input(Character.valueOf('#'), Items.STICK)
                .input(Character.valueOf('X'), ElsDyeModItems.WAXCAP_WAX)
                .pattern("X")
                .pattern("#")
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.WAXCAP_WAX), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.WAXCAP_WAX))
                .group("minecraft:torch") // might not really work but yknow might as well try
                .offerTo(exporter);
        offerSlabRecipe(exporter, ElsDyeModBlocks.WAXCAP_GILL_SLAB, ElsDyeModBlocks.WAXCAP_GILLS);

        offerShapelessRecipe(exporter, ElsDyeModItems.ARTICHOKE, ElsDyeModBlocks.THISTLE_FLOWER, groupName(ElsDyeModItems.ARTICHOKE), 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.ARTICHOKE_HEART, ElsDyeModItems.ARTICHOKE, groupName(ElsDyeModItems.ARTICHOKE_HEART), 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.ARTICHOKE_LAMB)
                .input(ElsDyeModItems.ARTICHOKE_HEART, 4)
                .input(Items.SWEET_BERRIES)
                .input(Items.COOKED_MUTTON)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.ARTICHOKE_HEART), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.ARTICHOKE_HEART))
                .group(breakfastGroup)
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.ARTICHOKE_LAMB)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.BREAKFAST_PORKCHOP)
                .input(ElsDyeModItems.ARTICHOKE_HEART, 4)
                .input(Items.COOKED_PORKCHOP)
                .input(Items.EGG)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.ARTICHOKE_HEART), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.ARTICHOKE_HEART))
                .group(breakfastGroup)
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.BREAKFAST_PORKCHOP)));
        //</editor-fold>
        //<editor-fold desc ="MAROON - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.POWDERED_CINNABAR, ElsDyeModItems.CINNABAR, "powdered_cinnabar", 2);
        offerShapelessRecipe(exporter, ElsDyeModItems.MAROON_DYE, ElsDyeModItems.POWDERED_CINNABAR, "maroon_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.MAROON_DYE, ElsDyeModItems.CARMINIC_COCHINEAL_BEETLE, "maroon_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.MAROON_DYE, ElsDyeModItems.MADDER_ROOT, "maroon_dye", 1);
        offerShapelessRecipe(exporter, Items.YELLOW_DYE, ElsDyeModBlocks.MADDER, "yellow_dye", 1);

        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MADDER_WOODSET.getLog(), ElsDyeModItems.MADDER_ROOT);
        offerWoodsetRecipes(exporter, ElsDyeModBlocks.MADDER_WOODSET);
        offerShapelessRecipe(exporter, ElsDyeModItems.CACTUS_CHUNK, Items.CACTUS, "cactus_chunk", 4);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CINNABAR_PILLAR, 2)
                .input('#', ElsDyeModBlocks.CINNABAR_BLOCK)
                .pattern("#")
                .pattern("#")
                .criterion(hasItem(ElsDyeModBlocks.CINNABAR_BLOCK), conditionsFromItem(ElsDyeModBlocks.CINNABAR_BLOCK))
                .criterion(hasItem(ElsDyeModBlocks.CINNABAR_PILLAR), conditionsFromItem(ElsDyeModBlocks.CINNABAR_PILLAR))
                .offerTo(exporter);
        offer2x2CompactingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CINNABAR_BLOCK, ElsDyeModItems.CINNABAR);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CINNAMON_BRICKS)
                .input(ElsDyeModItems.POWDERED_CINNABAR)
                .input(Items.COBBLED_DEEPSLATE)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.POWDERED_CINNABAR), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.POWDERED_CINNABAR))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.CINNAMON_BRICKS)));
        offerStairsRecipe(exporter, ElsDyeModBlocks.CINNAMON_BRICK_STAIRS, ElsDyeModBlocks.CINNAMON_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.CINNAMON_BRICK_SLAB, ElsDyeModBlocks.CINNAMON_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.CINNAMON_BRICK_WALL, ElsDyeModBlocks.CINNAMON_BRICKS);
        offerCrackingRecipe(exporter, ElsDyeModBlocks.CRACKED_CINNAMON_BRICKS, ElsDyeModBlocks.CINNAMON_BRICKS);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModBlocks.CACTUS_FEED)
                .pattern(" C ")
                .pattern("C#C")
                .pattern(" C ")
                .input('#', Blocks.DIRT)
                .input('C', ElsDyeModItems.CACTUS_CHUNK)
                .criterion(hasItem(ElsDyeModItems.CACTUS_CHUNK), conditionsFromItem(ElsDyeModItems.CACTUS_CHUNK))
                .offerTo(exporter);
        //</editor-fold>
        //<editor-fold desc ="AMBER - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.AMBER_DYE, ElsDyeModItems.AMBER, "amber_dye", 2);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ElsDyeModItems.EMBER, 2)
                .input(ElsDyeModItems.AMBER)
                .input(Items.BLAZE_POWDER)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.AMBER), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.AMBER))
                .group("ember")
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.EMBER)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ElsDyeModItems.EMBER_ARROW, 8)
                .input('X', ElsDyeModItems.EMBER)
                .input('#', Items.ARROW)
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.EMBER), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.EMBER))
                .criterion(FabricRecipeProvider.hasItem(Items.ARROW), FabricRecipeProvider.conditionsFromItem(Items.ARROW))
                .group("ember_arrow")
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.EMBER_ARROW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.AMBER_BLOCK, 1)
                .pattern("##")
                .pattern("##")
                .input('#', ElsDyeModItems.AMBER)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.AMBER), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.AMBER))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.AMBER_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.AMBER_BRICKS, 4)
                .pattern("##")
                .pattern("##")
                .input('#', ElsDyeModBlocks.AMBER_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.AMBER_BLOCK), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.AMBER_BLOCK))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.AMBER_BRICKS)));
        offerStairsRecipe(exporter, ElsDyeModBlocks.AMBER_BRICK_STAIRS, ElsDyeModBlocks.AMBER_BRICKS);
        offerSlabRecipe(exporter, ElsDyeModBlocks.AMBER_BRICK_SLAB, ElsDyeModBlocks.AMBER_BRICKS);
        offerWallRecipe(exporter, ElsDyeModBlocks.AMBER_BRICK_WALL, ElsDyeModBlocks.AMBER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.AMBER_BRICK_STAIRS, ElsDyeModBlocks.AMBER_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.AMBER_BRICK_SLAB, ElsDyeModBlocks.AMBER_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.AMBER_BRICK_WALL, ElsDyeModBlocks.AMBER_BRICKS);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.CHISELED_AMBER_BRICKS, 1)
                .pattern("#")
                .pattern("#")
                .input('#', ElsDyeModBlocks.AMBER_BRICK_SLAB)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModBlocks.AMBER_BRICK_SLAB), FabricRecipeProvider.conditionsFromItem(ElsDyeModBlocks.AMBER_BRICK_SLAB))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.CHISELED_AMBER_BRICKS)));

        offerShapelessRecipe(exporter, ElsDyeModItems.AMBER_DYE, ElsDyeModBlocks.SHIMMERING_SAVANNABUDS, "amber_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.AMBER_DYE, ElsDyeModItems.PINEAPPLE_CROWN, "amber_dye", 1);

        offerShapelessRecipe(exporter, ElsDyeModItems.PINEAPPLE_SLICES, ElsDyeModItems.PINEAPPLE, "pineapple_slices", 4);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.PINEAPPLE_KEBAB, 1)
                .input(ElsDyeModItems.PINEAPPLE_SLICES, 2)
                .input(Items.COOKED_BEEF)
                .input(Items.RED_MUSHROOM)
                .input(Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PINEAPPLE_SLICES), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PINEAPPLE_SLICES))
                .criterion(FabricRecipeProvider.hasItem(Items.COOKED_BEEF), FabricRecipeProvider.conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PINEAPPLE_KEBAB) + "_red_mushroom"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.PINEAPPLE_KEBAB, 1)
                .input(ElsDyeModItems.PINEAPPLE_SLICES, 2)
                .input(Items.COOKED_BEEF)
                .input(Items.BROWN_MUSHROOM)
                .input(Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PINEAPPLE_SLICES), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PINEAPPLE_SLICES))
                .criterion(FabricRecipeProvider.hasItem(Items.COOKED_BEEF), FabricRecipeProvider.conditionsFromItem(Items.COOKED_BEEF))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PINEAPPLE_KEBAB) + "brown_mushroom"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.PINEAPPLE_TART)
                .input(ElsDyeModItems.PINEAPPLE_SLICES, 1)
                .input(Items.WHEAT)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.PINEAPPLE_SLICES), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.PINEAPPLE_SLICES))
                .criterion(FabricRecipeProvider.hasItem(Items.WHEAT), FabricRecipeProvider.conditionsFromItem(Items.WHEAT))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.PINEAPPLE_TART)));
        //</editor-fold>
        //<editor-fold desc ="VELVET - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.VELVET_DYE, ElsDyeModItems.STRAWBERRY, "velvet_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.VELVET_DYE, ElsDyeModItems.CHERRIES, "velvet_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.VELVET_DYE, ElsDyeModBlocks.CORDYLINE, "velvet_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.VELVET_DYE, ElsDyeModBlocks.TALL_CORDYLINE, "velvet_dye", 2);
        offerShapelessRecipe(exporter, ElsDyeModItems.VELVET_DYE, ElsDyeModItems.POKEBERRIES, "velvet_dye", 1);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModBlocks.STRAWBERRY_PLANT)
                .input(ElsDyeModItems.STRAWBERRY, 3)
                .input(ItemTags.DIRT)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.STRAWBERRY_PLANT)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.CHOCOLATE_STRAWBERRY)
                .input(ElsDyeModItems.STRAWBERRY)
                .input(Items.COCOA_BEANS)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.CHOCOLATE_STRAWBERRY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.GOLDEN_STRAWBERRY)
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GGG")
                .input('G', Items.GOLD_NUGGET)
                .input('S', ElsDyeModItems.STRAWBERRY)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.GOLDEN_STRAWBERRY)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.STRAWBERRY_MILK)
                .input(ElsDyeModItems.STRAWBERRY, 2)
                .input(Items.MILK_BUCKET)
                .input(Items.GLASS_BOTTLE)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.STRAWBERRY_MILK)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.STRAWBERRY_SHORTCAKE)
                .input(Items.WHEAT)
                .input(Items.SUGAR)
                .input(ElsDyeModItems.STRAWBERRY, 2)
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.STRAWBERRY_SHORTCAKE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.STRAWBERRY_CHEESECAKE)
                .input(ElsDyeModItems.STRAWBERRY_MILK, 3)
                .input(Items.WHEAT, 2)
                .input(Items.SUGAR)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.STRAWBERRY_MILK), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.STRAWBERRY_MILK))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.STRAWBERRY_CHEESECAKE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.ANGEL_FOOD_CAKE)
                .input(ElsDyeModItems.GOLDEN_STRAWBERRY)
                .input(Items.EGG, 2)
                .input(Items.WHEAT)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.GOLDEN_STRAWBERRY), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.GOLDEN_STRAWBERRY))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.ANGEL_FOOD_CAKE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModItems.CHERRY_PIE)
                .input(ElsDyeModItems.CHERRIES, 2)
                .input(Items.EGG)
                .input(Items.SUGAR)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.CHERRIES), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.CHERRIES))
                .criterion(FabricRecipeProvider.hasItem(Items.SUGAR), FabricRecipeProvider.conditionsFromItem(Items.SUGAR))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModItems.CHERRY_PIE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ElsDyeModBlocks.VELVET_CAKE, 1)
                .pattern("MVM")
                .pattern("SES")
                .pattern("WCW")
                .input('M', Items.MILK_BUCKET)
                .input('V', ElsDyeModItems.VELVET_DYE)
                .input('S', Items.SUGAR)
                .input('E', Items.EGG)
                .input('W', Items.WHEAT)
                .input('C', Items.COCOA_BEANS)
                .criterion(FabricRecipeProvider.hasItem(ElsDyeModItems.VELVET_DYE), FabricRecipeProvider.conditionsFromItem(ElsDyeModItems.VELVET_DYE))
                .criterion(FabricRecipeProvider.hasItem(Items.COCOA_BEANS), FabricRecipeProvider.conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(ElsDyeModBlocks.VELVET_CAKE)));
        //</editor-fold>
        //<editor-fold desc ="GRAPE - Special">
        offerShapelessRecipe(exporter, ElsDyeModItems.GRAPE_DYE, ElsDyeModBlocks.PLUM_CORDYLINE, "grape_dye", 1);
        offerShapelessRecipe(exporter, ElsDyeModItems.GRAPE_DYE, ElsDyeModBlocks.TALL_PLUM_CORDYLINE, "grape_dye", 2);
        //</editor-fold>
        //<editor-fold desc ="MOLD - Special">
        // This doesn't work because it does not specify each dye needs to be unique from each other. No idea how to do that however
//        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.MOLD_DYE)
//                    .input(ModTags.Items.DYES)
//                    .input(ModTags.Items.DYES)
//                    .input(ModTags.Items.DYES)
//                    .input(ModTags.Items.DYES)
//                    .input(ModTags.Items.DYES)
//                    .input(ModTags.Items.DYES)
//                    .group("mold_dye")
//                    .criterion("has_dyes", FabricRecipeProvider.conditionsFromTag(ModTags.Items.DYES))
//                    .offerTo(exporter, new Identifier(Super.MOD_ID, "mold_dye_from_goobmix"));
        //</editor-fold>

//Dyes from combining vanilla dyes
        // These work for sheep mixing too... somehow
        // Sheep bred with sheep of a non-vanilla dyeColor default to a 50/50 chance to get the color of either parent
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MINT_DYE, Items.LIME_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.PEACH_DYE, Items.PINK_DYE, Items.ORANGE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.PERIWINKLE_DYE, Items.PURPLE_DYE, Items.LIGHT_BLUE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.ARTICHOKE_DYE, Items.LIME_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.FUCHSIA_DYE, Items.MAGENTA_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.VERMILION_DYE, Items.ORANGE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.SHAMROCK_DYE, Items.BLUE_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.INDIGO_DYE, Items.BLUE_DYE, Items.PURPLE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.BANANA_DYE, Items.YELLOW_DYE, Items.WHITE_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.CERULEAN_DYE, Items.BLUE_DYE, Items.LIGHT_GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.CERULEAN_DYE, Items.LIGHT_BLUE_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.ACORN_DYE, Items.BROWN_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.ACORN_DYE, Items.ORANGE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        //offerDyeMixingRecipe(exporter, ModItems.MAUVE_DYE, ModItems.FUCHSIA_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2); // nuh uh bad doesn't work for sheep
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MAUVE_DYE, Items.MAGENTA_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MAUVE_DYE, Items.PINK_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MAUVE_DYE, Items.RED_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MAROON_DYE, Items.RED_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MAROON_DYE, Items.RED_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.GRAPE_DYE, Items.PURPLE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.NAVY_DYE, Items.BLUE_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.NAVY_DYE, Items.CYAN_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.SAP_DYE, Items.GREEN_DYE, Items.CYAN_DYE, RecipeCategory.MISC, 2);
//        offerDyeMixingRecipe(exporter, ModItems.MOLD_DYE, ModItems.ARTICHOKE, Items.BLACK_DYE, RecipeCategory.MISC, 2); // aaawwe, no fiddle
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MOLD_DYE, Items.GREEN_DYE, Items.BROWN_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.MOLD_DYE, Items.YELLOW_DYE, Items.BLACK_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.AMBER_DYE, Items.ORANGE_DYE, Items.YELLOW_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.SAGE_DYE, Items.GREEN_DYE, Items.LIGHT_GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.SAGE_DYE, Items.LIME_DYE, Items.GRAY_DYE, RecipeCategory.MISC, 2);
        offerDyeMixingRecipe(exporter, ElsDyeModItems.VELVET_DYE, Items.PURPLE_DYE, Items.RED_DYE, RecipeCategory.MISC, 2);

        // Decor Additions
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ElsDyeModBlocks.MUCKTUFF, 4)
                        .input(Character.valueOf('T'), Blocks.TUFF)
                        .input(Character.valueOf('M'), Blocks.MUD)
                        .pattern("TM")
                        .pattern("MT")
                        .criterion("has_mud", FabricRecipeProvider.conditionsFromItem(Blocks.MUD))
                        .offerTo(exporter);
        offerMucktuffDyeingRecipes(exporter);

        offerCorrugatedIronRecipe(exporter, ElsDyeModBlocks.CORRUGATED_IRON);
        offerCorrugatedIronDyingRecipes(exporter);

        // Main
//  WOOL_BLOCKS
        ComplexRecipeJsonBuilder.create(ElsDyeModRecipeSerializer.WOOL_COLORING_RECIPE).offerTo(exporter, "wool_coloring_recipe");

//  CARPET_BLOCKS
        offerCarpetRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ElsDyeModRecipeSerializer.WOOL_CARPET_COLORING_RECIPE).offerTo(exporter, "wool_carpet_coloring_recipe");

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
        ComplexRecipeJsonBuilder.create(ElsDyeModRecipeSerializer.MOD_SHULKER_BOX_COLORING_RECIPE).offerTo(exporter, "mod_shulker_box_coloring_recipe");

//  BED_BLOCKS
        offerBedRecipes(exporter);
        ComplexRecipeJsonBuilder.create(ElsDyeModRecipeSerializer.BED_COLORING_RECIPE).offerTo(exporter, "bed_coloring_recipe");

//  CANDLE_BLOCKS
        offerCandleDyeingRecipes(exporter);

//  BANNER_BLOCKS
        offerBannerRecipes(exporter);
    }
}