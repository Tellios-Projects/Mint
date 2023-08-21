package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

public class ModBlocks {
    // MINT - Main
    public static final Block MINT_WOOL = registerBlock("mint_wool", createWoolBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", createCarpetBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", createTerracottaBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", createConcreteBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", createConcretePowderBlock(ModDyeColor.MINT, MINT_CONCRETE), ModItemGroups.MINT);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", createStainedGlassBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ModDyeColor.MINT));
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", createCandleBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CANDLE_CAKE = registerBlock("mint_candle_cake", createCandleCakeBlock(ModDyeColor.MINT, MINT_CANDLE), ModItemGroups.MINT);
    public static final Block MINT_BANNER = registerBlockWithoutBlockItem("mint_banner", createBannerBlock(ModDyeColor.MINT));
    public static final Block MINT_WALL_BANNER = registerBlockWithoutBlockItem("mint_wall_banner", createWallBannerBlock(ModDyeColor.MINT, (BannerBlock)ModBlocks.MINT_BANNER));
    // MINT - Special
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop", new MintCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.LICHEN_GREEN)));
    public static final Block WILD_MINT = registerBlock("wild_mint", new FernBlock(FabricBlockSettings.copyOf(Blocks.FERN).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_SPRIG_BLOCK = registerBlock("mint_sprig_block", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BRICKS = registerBlock("mint_bricks", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_SLAB = registerBlock("mint_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_STAIRS = registerBlock("mint_brick_stairs", new StairsBlock(MINT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    //public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);

    // PEACH - Main
    public static final Block PEACH_WOOL = registerBlock("peach_wool", createWoolBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CARPET = registerBlock("peach_carpet", createCarpetBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", createTerracottaBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", createConcreteBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", createConcretePowderBlock(ModDyeColor.PEACH, PEACH_CONCRETE), ModItemGroups.PEACH);
    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", createStainedGlassBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PEACH));
    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PEACH));
    public static final Block PEACH_CANDLE = registerBlock("peach_candle", createCandleBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CANDLE_CAKE = registerBlock("peach_candle_cake", createCandleCakeBlock(ModDyeColor.PEACH, PEACH_CANDLE), ModItemGroups.PEACH);
    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PEACH));
    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PEACH, (BannerBlock)ModBlocks.PEACH_BANNER));
    // PEACH - Special

    // PERIWINKLE - Main
    public static final Block PERIWINKLE_WOOL = registerBlock("periwinkle_wool", createWoolBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CARPET = registerBlock("periwinkle_carpet", createCarpetBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("periwinkle_terracotta", createTerracottaBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE = registerBlock("periwinkle_concrete", createConcreteBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("periwinkle_concrete_powder", createConcretePowderBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CONCRETE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("periwinkle_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("periwinkle_stained_glass", createStainedGlassBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("periwinkle_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("periwinkle_shulker_box", createShulkerBoxBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("periwinkle_bed", createBedBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_CANDLE = registerBlock("periwinkle_candle", createCandleBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlock("periwinkle_candle_cake", createCandleCakeBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CANDLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("periwinkle_banner", createBannerBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("periwinkle_wall_banner", createWallBannerBlock(ModDyeColor.PERIWINKLE, (BannerBlock)ModBlocks.PERIWINKLE_BANNER));
    // PERIWINKLE - Special

    // ARTICHOKE - Main
    public static final Block ARTICHOKE_WOOL = registerBlock("artichoke_wool", createWoolBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CARPET = registerBlock("artichoke_carpet", createCarpetBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("artichoke_terracotta", createTerracottaBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE = registerBlock("artichoke_concrete", createConcreteBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("artichoke_concrete_powder", createConcretePowderBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CONCRETE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("artichoke_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("artichoke_stained_glass", createStainedGlassBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("artichoke_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("artichoke_shulker_box", createShulkerBoxBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("artichoke_bed", createBedBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_CANDLE = registerBlock("artichoke_candle", createCandleBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlock("artichoke_candle_cake", createCandleCakeBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CANDLE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("artichoke_banner", createBannerBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("artichoke_wall_banner", createWallBannerBlock(ModDyeColor.ARTICHOKE, (BannerBlock)ModBlocks.ARTICHOKE_BANNER));
    // ARTICHOKE - Special

    // FUCHSIA - Main
    public static final Block FUCHSIA_WOOL = registerBlock("fuchsia_wool", createWoolBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CARPET = registerBlock("fuchsia_carpet", createCarpetBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta", createTerracottaBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE = registerBlock("fuchsia_concrete", createConcreteBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE_POWDER = registerBlock("fuchsia_concrete_powder", createConcretePowderBlock(ModDyeColor.FUCHSIA, FUCHSIA_CONCRETE), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_GLAZED_TERRACOTTA = registerBlock("fuchsia_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass", createStainedGlassBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS_PANE = registerBlock("fuchsia_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_SHULKER_BOX = registerBlockWithoutBlockItem("fuchsia_shulker_box", createShulkerBoxBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_BED = registerBlockWithoutBlockItem("fuchsia_bed", createBedBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_CANDLE = registerBlock("fuchsia_candle", createCandleBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CANDLE_CAKE = registerBlock("fuchsia_candle_cake", createCandleCakeBlock(ModDyeColor.FUCHSIA, FUCHSIA_CANDLE), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_BANNER = registerBlockWithoutBlockItem("fuchsia_banner", createBannerBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_WALL_BANNER = registerBlockWithoutBlockItem("fuchsia_wall_banner", createWallBannerBlock(ModDyeColor.FUCHSIA, (BannerBlock)ModBlocks.FUCHSIA_BANNER));
    // FUCHSIA - Special

    // VERMILION - Main
    public static final Block VERMILION_WOOL = registerBlock("vermilion_wool", createWoolBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CARPET = registerBlock("vermilion_carpet", createCarpetBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta", createTerracottaBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE = registerBlock("vermilion_concrete", createConcreteBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE_POWDER = registerBlock("vermilion_concrete_powder",createConcretePowderBlock(ModDyeColor.VERMILION, VERMILION_CONCRETE), ModItemGroups.VERMILION);
    public static final Block VERMILION_GLAZED_TERRACOTTA = registerBlock("vermilion_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass", createStainedGlassBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS_PANE = registerBlock("vermilion_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_SHULKER_BOX = registerBlockWithoutBlockItem("vermilion_shulker_box", createShulkerBoxBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_BED = registerBlockWithoutBlockItem("vermilion_bed", createBedBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_CANDLE = registerBlock("vermilion_candle", createCandleBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CANDLE_CAKE = registerBlock("vermilion_candle_cake", createCandleCakeBlock(ModDyeColor.VERMILION, VERMILION_CANDLE), ModItemGroups.VERMILION);
    public static final Block VERMILION_BANNER = registerBlockWithoutBlockItem("vermilion_banner", createBannerBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_WALL_BANNER = registerBlockWithoutBlockItem("vermilion_wall_banner", createWallBannerBlock(ModDyeColor.VERMILION, (BannerBlock)ModBlocks.VERMILION_BANNER));
    // VERMILION - Special


    // SHAMROCK - Main
    public static final Block SHAMROCK_WOOL = registerBlock("shamrock_wool", createWoolBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CARPET = registerBlock("shamrock_carpet", createCarpetBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_TERRACOTTA = registerBlock("shamrock_terracotta", createTerracottaBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE = registerBlock("shamrock_concrete", createConcreteBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE_POWDER = registerBlock("shamrock_concrete_powder", createConcretePowderBlock(ModDyeColor.SHAMROCK, SHAMROCK_CONCRETE), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_GLAZED_TERRACOTTA = registerBlock("shamrock_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS = registerBlock("shamrock_stained_glass", createStainedGlassBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS_PANE = registerBlock("shamrock_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_SHULKER_BOX = registerBlockWithoutBlockItem("shamrock_shulker_box", createShulkerBoxBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_BED = registerBlockWithoutBlockItem("shamrock_bed", createBedBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_CANDLE = registerBlock("shamrock_candle", createCandleBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CANDLE_CAKE = registerBlock("shamrock_candle_cake", createCandleCakeBlock(ModDyeColor.SHAMROCK, SHAMROCK_CANDLE), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_BANNER = registerBlockWithoutBlockItem("shamrock_banner", createBannerBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_WALL_BANNER = registerBlockWithoutBlockItem("shamrock_wall_banner", createWallBannerBlock(ModDyeColor.SHAMROCK, (BannerBlock)ModBlocks.SHAMROCK_BANNER));
    // SHAMROCK - Special

    // INDIGO - Main
    public static final Block INDIGO_WOOL = registerBlock("indigo_wool", createWoolBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CARPET = registerBlock("indigo_carpet", createCarpetBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_TERRACOTTA = registerBlock("indigo_terracotta", createTerracottaBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE = registerBlock("indigo_concrete", createConcreteBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE_POWDER = registerBlock("indigo_concrete_powder", createConcretePowderBlock(ModDyeColor.INDIGO, INDIGO_CONCRETE), ModItemGroups.INDIGO);
    public static final Block INDIGO_GLAZED_TERRACOTTA = registerBlock("indigo_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass", createStainedGlassBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS_PANE = registerBlock("indigo_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_SHULKER_BOX = registerBlockWithoutBlockItem("indigo_shulker_box", createShulkerBoxBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_BED = registerBlockWithoutBlockItem("indigo_bed", createBedBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_CANDLE = registerBlock("indigo_candle", createCandleBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CANDLE_CAKE = registerBlock("indigo_candle_cake", createCandleCakeBlock( ModDyeColor.INDIGO, INDIGO_CANDLE), ModItemGroups.INDIGO);
    public static final Block INDIGO_BANNER = registerBlockWithoutBlockItem("indigo_banner", createBannerBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_WALL_BANNER = registerBlockWithoutBlockItem("indigo_wall_banner", createWallBannerBlock(ModDyeColor.INDIGO, (BannerBlock)ModBlocks.INDIGO_BANNER));
    // INDIGO - Special


    // BANANA - Main
    public static final Block BANANA_WOOL = registerBlock("banana_wool", createWoolBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CARPET = registerBlock("banana_carpet", createCarpetBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_TERRACOTTA = registerBlock("banana_terracotta", createTerracottaBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE = registerBlock("banana_concrete", createConcreteBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE_POWDER = registerBlock("banana_concrete_powder", createConcretePowderBlock(ModDyeColor.BANANA, BANANA_CONCRETE), ModItemGroups.BANANA);
    public static final Block BANANA_GLAZED_TERRACOTTA = registerBlock("banana_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS = registerBlock("banana_stained_glass", createStainedGlassBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS_PANE = registerBlock("banana_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_SHULKER_BOX = registerBlockWithoutBlockItem("banana_shulker_box", createShulkerBoxBlock(ModDyeColor.BANANA));
    public static final Block BANANA_BED = registerBlockWithoutBlockItem("banana_bed", createBedBlock(ModDyeColor.BANANA));
    public static final Block BANANA_CANDLE = registerBlock("banana_candle", createCandleBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CANDLE_CAKE = registerBlock("banana_candle_cake", createCandleCakeBlock(ModDyeColor.BANANA, BANANA_CANDLE), ModItemGroups.BANANA);
    public static final Block BANANA_BANNER = registerBlockWithoutBlockItem("banana_banner", createBannerBlock(ModDyeColor.BANANA));
    public static final Block BANANA_WALL_BANNER = registerBlockWithoutBlockItem("banana_wall_banner", createWallBannerBlock(ModDyeColor.BANANA, (BannerBlock)ModBlocks.BANANA_BANNER));
    // BANANA - Special
    
    // Arrays
    public static final Block[] WOOL_BLOCKS = {
            ModBlocks.MINT_WOOL,
            ModBlocks.PEACH_WOOL,
            ModBlocks.PERIWINKLE_WOOL,
            ModBlocks.ARTICHOKE_WOOL,
            ModBlocks.FUCHSIA_WOOL,
            ModBlocks.VERMILION_WOOL,
            ModBlocks.SHAMROCK_WOOL,
            ModBlocks.INDIGO_WOOL,
            ModBlocks.BANANA_WOOL,
//            ModBlocks.ACORN_WOOL,
//            ModBlocks.CERULEAN_WOOL,
//            ModBlocks.TAUPE_WOOL,
//            ModBlocks.MAROON_WOOL,
//            ModBlocks.BLUEBERRY_WOOL,
//            ModBlocks.GRAPE_WOOL,
//            ModBlocks.SAP_WOOL
    };
    public static final Block[] CARPET_BLOCKS = {
            MINT_CARPET,
            PEACH_CARPET,
            PERIWINKLE_CARPET,
            ARTICHOKE_CARPET,
            FUCHSIA_CARPET,
            VERMILION_CARPET,
            SHAMROCK_CARPET,
            INDIGO_CARPET,
            BANANA_CARPET,
//            ACORN_CARPET,
//            CERULEAN_CARPET,
//            TAUPE_CARPET,
//            MAROON_CARPET,
//            BLUEBERRY_CARPET,
//            GRAPE_CARPET,
//            SAP_CARPET
    };
    public static final Block[] TERRACOTTA_BLOCKS = {
            MINT_TERRACOTTA,
            PEACH_TERRACOTTA,
            PERIWINKLE_TERRACOTTA,
            ARTICHOKE_TERRACOTTA,
            FUCHSIA_TERRACOTTA,
            VERMILION_TERRACOTTA,
            SHAMROCK_TERRACOTTA,
            INDIGO_TERRACOTTA,
            BANANA_TERRACOTTA,
//            ACORN_TERRACOTTA,
//            CERULEAN_TERRACOTTA,
//            TAUPE_TERRACOTTA,
//            MAROON_TERRACOTTA,
//            BLUEBERRY_TERRACOTTA,
//            GRAPE_TERRACOTTA,
//            SAP_TERRACOTTA
    };
    public static final Block[] CONCRETE_BLOCKS = {
            MINT_CONCRETE,
            PEACH_CONCRETE,
            PERIWINKLE_CONCRETE,
            ARTICHOKE_CONCRETE,
            FUCHSIA_CONCRETE,
            VERMILION_CONCRETE,
            SHAMROCK_CONCRETE,
            INDIGO_CONCRETE,
            BANANA_CONCRETE,
//            ACORN_CONCRETE,
//            CERULEAN_CONCRETE,
//            TAUPE_CONCRETE,
//            MAROON_CONCRETE,
//            BLUEBERRY_CONCRETE,
//            GRAPE_CONCRETE,
//            SAP_CONCRETE,
    };
    public static final Block[] CONCRETE_POWDER_BLOCKS = {
            MINT_CONCRETE_POWDER,
            PEACH_CONCRETE_POWDER,
            PERIWINKLE_CONCRETE_POWDER,
            ARTICHOKE_CONCRETE_POWDER,
            FUCHSIA_CONCRETE_POWDER,
            VERMILION_CONCRETE_POWDER,
            SHAMROCK_CONCRETE_POWDER,
            INDIGO_CONCRETE_POWDER,
            BANANA_CONCRETE_POWDER,
//            ACORN_CONCRETE_POWDER,
//            CERULEAN_CONCRETE_POWDER,
//            TAUPE_CONCRETE_POWDER,
//            MAROON_CONCRETE_POWDER,
//            BLUEBERRY_CONCRETE_POWDER,
//            GRAPE_CONCRETE_POWDER,
//            SAP_CONCRETE_POWDER,
    };
    public static final Block[] GLAZED_TERRACOTTA_BLOCKS = {
            MINT_GLAZED_TERRACOTTA,
            PEACH_GLAZED_TERRACOTTA,
            PERIWINKLE_GLAZED_TERRACOTTA,
            ARTICHOKE_GLAZED_TERRACOTTA,
            FUCHSIA_GLAZED_TERRACOTTA,
            VERMILION_GLAZED_TERRACOTTA,
            SHAMROCK_GLAZED_TERRACOTTA,
            INDIGO_GLAZED_TERRACOTTA,
            BANANA_GLAZED_TERRACOTTA,
//            ACORN_GLAZED_TERRACOTTA,
//            CERULEAN_GLAZED_TERRACOTTA,
//            TAUPE_GLAZED_TERRACOTTA,
//            MAROON_GLAZED_TERRACOTTA,
//            BLUEBERRY_GLAZED_TERRACOTTA,
//            GRAPE_GLAZED_TERRACOTTA,
//            SAP_GLAZED_TERRACOTTA,
    };
    public static final Block[] STAINED_GLASS_BLOCKS = {
            MINT_STAINED_GLASS,
            PEACH_STAINED_GLASS,
            PERIWINKLE_STAINED_GLASS,
            ARTICHOKE_STAINED_GLASS,
            FUCHSIA_STAINED_GLASS,
            VERMILION_STAINED_GLASS,
            SHAMROCK_STAINED_GLASS,
            INDIGO_STAINED_GLASS,
            BANANA_STAINED_GLASS,
//            ACORN_STAINED_GLASS,
//            CERULEAN_STAINED_GLASS,
//            TAUPE_STAINED_GLASS,
//            MAROON_STAINED_GLASS,
//            BLUEBERRY_STAINED_GLASS,
//            GRAPE_STAINED_GLASS,
//            SAP_STAINED_GLASS,
    };
    public static final Block[] STAINED_GLASS_PANE_BLOCKS = {
            MINT_STAINED_GLASS_PANE,
            PEACH_STAINED_GLASS_PANE,
            PERIWINKLE_STAINED_GLASS_PANE,
            ARTICHOKE_STAINED_GLASS_PANE,
            FUCHSIA_STAINED_GLASS_PANE,
            VERMILION_STAINED_GLASS_PANE,
            SHAMROCK_STAINED_GLASS_PANE,
            INDIGO_STAINED_GLASS_PANE,
            BANANA_STAINED_GLASS_PANE,
//            ACORN_STAINED_GLASS_PANE,
//            CERULEAN_STAINED_GLASS_PANE,
//            TAUPE_STAINED_GLASS_PANE,
//            MAROON_STAINED_GLASS_PANE,
//            BLUEBERRY_STAINED_GLASS_PANE,
//            GRAPE_STAINED_GLASS_PANE,
//            SAP_STAINED_GLASS_PANE,
    };
    public static final Block[] SHULKER_BOX_BLOCKS = {
            MINT_SHULKER_BOX,
            PEACH_SHULKER_BOX,
            PERIWINKLE_SHULKER_BOX,
            ARTICHOKE_SHULKER_BOX,
            FUCHSIA_SHULKER_BOX,
            VERMILION_SHULKER_BOX,
            SHAMROCK_SHULKER_BOX,
            INDIGO_SHULKER_BOX,
            BANANA_SHULKER_BOX,
//            ACORN_SHULKER_BOX,
//            CERULEAN_SHULKER_BOX,
//            TAUPE_SHULKER_BOX,
//            MAROON_SHULKER_BOX,
//            BLUEBERRY_SHULKER_BOX,
//            GRAPE_SHULKER_BOX,
//            SAP_SHULKER_BOX,
    };
    public static final Block[] BED_BLOCKS = {
            MINT_BED,
            PEACH_BED,
            PERIWINKLE_BED,
            ARTICHOKE_BED,
            FUCHSIA_BED,
            VERMILION_BED,
            SHAMROCK_BED,
            INDIGO_BED,
            BANANA_BED,
//            ACORN_BED,
//            CERULEAN_BED,
//            TAUPE_BED,
//            MAROON_BED,
//            BLUEBERRY_BED,
//            GRAPE_BED,
//            SAP_BED,
    };
    public static final Block[] CANDLE_BLOCKS = {
            MINT_CANDLE,
            PEACH_CANDLE,
            PERIWINKLE_CANDLE,
            ARTICHOKE_CANDLE,
            FUCHSIA_CANDLE,
            VERMILION_CANDLE,
            SHAMROCK_CANDLE,
            INDIGO_CANDLE,
            BANANA_CANDLE,
//            ACORN_CANDLE,
//            CERULEAN_CANDLE,
//            TAUPE_CANDLE,
//            MAROON_CANDLE,
//            BLUEBERRY_CANDLE,
//            GRAPE_CANDLE,
//            SAP_CANDLE,
    };
    public static final Block[] CANDLE_CAKE_BLOCKS = {
            MINT_CANDLE_CAKE,
            PEACH_CANDLE_CAKE,
            PERIWINKLE_CANDLE_CAKE,
            ARTICHOKE_CANDLE_CAKE,
            FUCHSIA_CANDLE_CAKE,
            VERMILION_CANDLE_CAKE,
            SHAMROCK_CANDLE_CAKE,
            INDIGO_CANDLE_CAKE,
            BANANA_CANDLE_CAKE,
//            ACORN_CANDLE,
//            CERULEAN_CANDLE,
//            TAUPE_CANDLE,
//            MAROON_CANDLE,
//            BLUEBERRY_CANDLE,
//            GRAPE_CANDLE,
//            SAP_CANDLE,
    };
    public static final Block[] BANNER_BLOCKS = {
            MINT_BANNER,
            PEACH_BANNER,
            PERIWINKLE_BANNER,
            ARTICHOKE_BANNER,
            FUCHSIA_BANNER,
            VERMILION_BANNER,
            SHAMROCK_BANNER,
            INDIGO_BANNER,
            BANANA_BANNER,
//            ACORN_BANNER,
//            CERULEAN_BANNER,
//            TAUPE_BANNER,
//            MAROON_BANNER,
//            BLUEBERRY_BANNER,
//            GRAPE_BANNER,
//            SAP_BANNER,
    };
    public static final Block[] WALL_BANNER_BLOCKS = {
            MINT_WALL_BANNER,
            PEACH_WALL_BANNER,
            PERIWINKLE_WALL_BANNER,
            ARTICHOKE_WALL_BANNER,
            FUCHSIA_WALL_BANNER,
            VERMILION_WALL_BANNER,
            SHAMROCK_WALL_BANNER,
            INDIGO_WALL_BANNER,
            BANANA_WALL_BANNER,
//            ACORN_WALL_BANNER,
//            CERULEAN_WALL_BANNER,
//            TAUPE_WALL_BANNER,
//            MAROON_WALL_BANNER,
//            BLUEBERRY_WALL_BANNER,
//            GRAPE_WALL_BANNER,
//            SAP_WALL_BANNER,
    };

    /**
     * @param group unused in 1.20, only defined here in that version to make potential backporting easier.
     * @return
     */
    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name,block,group);
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    public static Block createWoolBlock(DyeColor color) {
        return new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)
                .mapColor(color.getMapColor())
                .instrument(Instrument.GUITAR)
                .strength(0.8f)
                .sounds(BlockSoundGroup.WOOL)
                .burnable()
        );
    }
    public static CarpetBlock createCarpetBlock(DyeColor color) {
        return new CarpetBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET)
                .mapColor(color.getMapColor())
                .strength(0.1f)
                .sounds(BlockSoundGroup.WOOL)
                .burnable()
        );
    }
    public static Block createTerracottaBlock(DyeColor color) {
        return new Block(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.25f, 4.2f)
        );
    }
    public static Block createConcreteBlock(DyeColor color) {
        return new Block(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.8f)
        );
    }
    public static ConcretePowderBlock createConcretePowderBlock(DyeColor color, Block concrete) {
        return new ConcretePowderBlock(concrete, FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE_POWDER)
                .mapColor(color.getMapColor())
                .instrument(Instrument.SNARE)
                .strength(0.5f)
                .sounds(BlockSoundGroup.SAND)
        );
    }
    public static CandleBlock createCandleBlock(DyeColor color) {
        return new CandleBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE)
                .mapColor(color.getMapColor())
                .nonOpaque()
                .strength(0.1f)
                .sounds(BlockSoundGroup.CANDLE)
                .luminance(CandleBlock.STATE_TO_LUMINANCE)
                .pistonBehavior(PistonBehavior.DESTROY)
        );
    }
    public static CandleCakeBlock createCandleCakeBlock(DyeColor color, Block candle) {
        return new CandleCakeBlock(candle, FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE_CAKE)
                .mapColor(color.getMapColor())
                .solid()
                .strength(0.5f)
                .sounds(BlockSoundGroup.WOOL)
                .pistonBehavior(PistonBehavior.DESTROY)
                .luminance(ModBlocks.createLightLevelFromBooleanProperty(3, Properties.LIT))
        );
    }
    public static GlazedTerracottaBlock createGlazedTerracottaBlock(DyeColor color) {
        return new GlazedTerracottaBlock(FabricBlockSettings.copyOf(Blocks.WHITE_GLAZED_TERRACOTTA)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.4f)
                .pistonBehavior(PistonBehavior.PUSH_ONLY)
        );
    }
    public static StainedGlassBlock createStainedGlassBlock(DyeColor color) {
        return new StainedGlassBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_STAINED_GLASS)
                .mapColor(color.getMapColor())
                .instrument(Instrument.HAT)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
                .allowsSpawning(ModBlocks::never)
                .solidBlock(ModBlocks::never)
                .suffocates(ModBlocks::never)
                .blockVision(ModBlocks::never)
        );
    }
    public static StainedGlassPaneBlock createStainedGlassPaneBlock(DyeColor color) {
        return new StainedGlassPaneBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_STAINED_GLASS_PANE)
                .mapColor(color.getMapColor())
                .instrument(Instrument.HAT)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
        );
    }
    private static BannerBlock createBannerBlock(DyeColor color) {
        return new BannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BANNER)
                .mapColor(color.getMapColor())
                .solid()
                .instrument(Instrument.BASS)
                .noCollision()
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
        );
    }
    private static WallBannerBlock createWallBannerBlock(DyeColor color, BannerBlock banner) {
        return new WallBannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_WALL_BANNER)
                .mapColor(color.getMapColor())
                .solid()
                .instrument(Instrument.BASS)
                .noCollision()
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .dropsLike(banner)
                .burnable()
        );
    }
    private static BedBlock createBedBlock(DyeColor color) {
        return new BedBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BED)
                .mapColor(blockState -> blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY)
                .sounds(BlockSoundGroup.WOOD)
                .strength(0.2f)
                .nonOpaque()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .burnable()
        );
    }
    private static ModShulkerBoxBlock createShulkerBoxBlock(DyeColor color) {
        AbstractBlock.ContextPredicate contextPredicate = (state, world, pos) -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof ShulkerBoxBlockEntity)) {
                return true;
            }
            ShulkerBoxBlockEntity shulkerBoxBlockEntity = (ShulkerBoxBlockEntity)blockEntity;
            return shulkerBoxBlockEntity.suffocates();
        };
        return new ModShulkerBoxBlock(color, FabricBlockSettings.copyOf(Blocks.SHULKER_BOX)
                //Same settings as the vanilla shulker, I just have reason to not trust .copyOf
                .mapColor(color.getMapColor())
                .solid()
                .strength(2.0f)
                .dynamicBounds()
                .nonOpaque()
                .suffocates(contextPredicate)
                .blockVision(contextPredicate)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(ModBlocks::always)
        );
    }
    public static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }
    private static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
    private static boolean always(BlockState state, BlockView world, BlockPos pos) { return true; }
    private static ToIntFunction<BlockState> createLightLevelFromBooleanProperty(int litLevel, BooleanProperty property) {
        return state -> state.get(property) != false ? litLevel : 0;
    }
    public static Item[] toItems(Block[] blocks) {
        Item[] items = new Item[blocks.length];
        for(int i = 0; i < items.length; i++) {
            items[i] = blocks[i].asItem();
        }
        return items;
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        //ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(blockItem)); // only for pre 1.20.1, around cuz I'd forget otherwise
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), blockItem);
    }
    public static void registerModBlocks() {
        ModInit.LOGGER.debug("Registering mod blocks for " + Super.MOD_ID);
    }
}

