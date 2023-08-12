package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class ModBlocks {
    // MINT - Main
    public static final Block MINT_WOOL = registerBlock("mint_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", new ConcretePowderBlock(MINT_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", new StainedGlassBlock(ModDyeColor.MINT, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.MINT, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ModDyeColor.MINT));
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CANDLE_CAKE = registerBlock("mint_candle_cake", new CandleCakeBlock(MINT_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.MINT);
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
    public static final Block PEACH_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(PEACH_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.PEACH, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.PEACH, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PEACH));
    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PEACH));
    public static final Block PEACH_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
    public static final Block PEACH_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(PEACH_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.PEACH);
    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PEACH));
    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PEACH, (BannerBlock)ModBlocks.PEACH_BANNER));
    // PEACH - Special

    // PERIWINKLE - Main
    public static final Block PERIWINKLE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(PERIWINKLE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.PERIWINKLE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.PERIWINKLE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(PERIWINKLE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PERIWINKLE, (BannerBlock)ModBlocks.PERIWINKLE_BANNER));
    // PERIWINKLE - Special

    // ARTICHOKE - Main
    public static final Block ARTICHOKE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(ARTICHOKE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.ARTICHOKE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.ARTICHOKE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(ARTICHOKE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.ARTICHOKE, (BannerBlock)ModBlocks.ARTICHOKE_BANNER));
    // ARTICHOKE - Special

    // FUCHSIA - Main
    public static final Block FUCHSIA_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(FUCHSIA_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.FUCHSIA, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.FUCHSIA, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(FUCHSIA_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.FUCHSIA, (BannerBlock)ModBlocks.FUCHSIA_BANNER));
    // FUCHSIA - Special

    // VERMILION - Main
    public static final Block VERMILION_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(VERMILION_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.VERMILION, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.VERMILION, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.VERMILION);
    public static final Block VERMILION_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(VERMILION_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.VERMILION);
    public static final Block VERMILION_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.VERMILION, (BannerBlock)ModBlocks.VERMILION_BANNER));
    // VERMILION - Special


    // SHAMROCK - Main
    public static final Block SHAMROCK_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(SHAMROCK_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.SHAMROCK, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.SHAMROCK, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(SHAMROCK_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.SHAMROCK, (BannerBlock)ModBlocks.SHAMROCK_BANNER));
    // SHAMROCK - Special

    // INDIGO - Main
    public static final Block INDIGO_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(INDIGO_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.INDIGO, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.INDIGO, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.INDIGO);
    public static final Block INDIGO_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(INDIGO_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.INDIGO);
    public static final Block INDIGO_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.INDIGO, (BannerBlock)ModBlocks.INDIGO_BANNER));
    // INDIGO - Special


    // BANANA - Main
    public static final Block BANANA_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(BANANA_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.BANANA, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.BANANA, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.BANANA));
    public static final Block BANANA_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.BANANA));
    public static final Block BANANA_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BANANA);
    public static final Block BANANA_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(BANANA_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.BANANA);
    public static final Block BANANA_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.BANANA));
    public static final Block BANANA_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.BANANA, (BannerBlock)ModBlocks.BANANA_BANNER));
    // BANANA - Special

    // ACORN - Main
    public static final Block ACORN_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(ACORN_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.ACORN, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.ACORN, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.ACORN));
    public static final Block ACORN_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.ACORN));
    public static final Block ACORN_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ACORN);
    public static final Block ACORN_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(ACORN_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.ACORN);
    public static final Block ACORN_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.ACORN));
    public static final Block ACORN_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.ACORN, (BannerBlock)ModBlocks.ACORN_BANNER));
    // ACORN - Special

    // CERULEAN - Main
    public static final Block CERULEAN_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(CERULEAN_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.CERULEAN, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.CERULEAN, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(CERULEAN_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.CERULEAN);
    public static final Block CERULEAN_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.CERULEAN, (BannerBlock)ModBlocks.CERULEAN_BANNER));
    // CERULEAN - Special
    
    // TAUPE - Main
    public static final Block TAUPE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(TAUPE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.TAUPE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.TAUPE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.TAUPE));
    public static final Block TAUPE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.TAUPE));
    public static final Block TAUPE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.TAUPE);
    public static final Block TAUPE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(TAUPE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.TAUPE);
    public static final Block TAUPE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.TAUPE));
    public static final Block TAUPE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.TAUPE, (BannerBlock)ModBlocks.TAUPE_BANNER));
    // TAUPE - Special

    // MAROON - Main
    public static final Block MAROON_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(MAROON_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.MAROON, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.MAROON, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.MAROON));
    public static final Block MAROON_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.MAROON));
    public static final Block MAROON_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.MAROON);
    public static final Block MAROON_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(MAROON_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.MAROON);
    public static final Block MAROON_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.MAROON));
    public static final Block MAROON_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.MAROON, (BannerBlock)ModBlocks.MAROON_BANNER));
    // MAROON - Special
    
    // BLUEBERRY - Main
    public static final Block BLUEBERRY_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(BLUEBERRY_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.BLUEBERRY, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.BLUEBERRY, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.BLUEBERRY));
    public static final Block BLUEBERRY_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.BLUEBERRY));
    public static final Block BLUEBERRY_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(BLUEBERRY_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.BLUEBERRY);
    public static final Block BLUEBERRY_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.BLUEBERRY));
    public static final Block BLUEBERRY_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.BLUEBERRY, (BannerBlock)ModBlocks.BLUEBERRY_BANNER));
    // BLUEBERRY - Special

    // GRAPE - Main
    public static final Block GRAPE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(GRAPE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.GRAPE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.GRAPE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.GRAPE);
    public static final Block GRAPE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(GRAPE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.GRAPE);
    public static final Block GRAPE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.GRAPE, (BannerBlock)ModBlocks.GRAPE_BANNER));
    // GRAPE - Special

    // SAP - Main
    public static final Block SAP_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(SAP_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.SAP, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.SAP, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.SAP));
    public static final Block SAP_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.SAP));
    public static final Block SAP_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.SAP);
    public static final Block SAP_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(SAP_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.SAP);
    public static final Block SAP_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.SAP));
    public static final Block SAP_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.SAP, (BannerBlock)ModBlocks.SAP_BANNER));
    // SAP - Special
    
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
            ModBlocks.ACORN_WOOL,
            ModBlocks.CERULEAN_WOOL,
            ModBlocks.TAUPE_WOOL,
            ModBlocks.MAROON_WOOL,
            ModBlocks.BLUEBERRY_WOOL,
            ModBlocks.GRAPE_WOOL,
            ModBlocks.SAP_WOOL
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
            ACORN_CARPET,
            CERULEAN_CARPET,
            TAUPE_CARPET,
            MAROON_CARPET,
            BLUEBERRY_CARPET,
            GRAPE_CARPET,
            SAP_CARPET
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
            ACORN_TERRACOTTA,
            CERULEAN_TERRACOTTA,
            TAUPE_TERRACOTTA,
            MAROON_TERRACOTTA,
            BLUEBERRY_TERRACOTTA,
            GRAPE_TERRACOTTA,
            SAP_TERRACOTTA
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
            ACORN_CONCRETE,
            CERULEAN_CONCRETE,
            TAUPE_CONCRETE,
            MAROON_CONCRETE,
            BLUEBERRY_CONCRETE,
            GRAPE_CONCRETE,
            SAP_CONCRETE,
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
            ACORN_CONCRETE_POWDER,
            CERULEAN_CONCRETE_POWDER,
            TAUPE_CONCRETE_POWDER,
            MAROON_CONCRETE_POWDER,
            BLUEBERRY_CONCRETE_POWDER,
            GRAPE_CONCRETE_POWDER,
            SAP_CONCRETE_POWDER,
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
            ACORN_GLAZED_TERRACOTTA,
            CERULEAN_GLAZED_TERRACOTTA,
            TAUPE_GLAZED_TERRACOTTA,
            MAROON_GLAZED_TERRACOTTA,
            BLUEBERRY_GLAZED_TERRACOTTA,
            GRAPE_GLAZED_TERRACOTTA,
            SAP_GLAZED_TERRACOTTA,
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
            ACORN_STAINED_GLASS,
            CERULEAN_STAINED_GLASS,
            TAUPE_STAINED_GLASS,
            MAROON_STAINED_GLASS,
            BLUEBERRY_STAINED_GLASS,
            GRAPE_STAINED_GLASS,
            SAP_STAINED_GLASS,
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
            ACORN_STAINED_GLASS_PANE,
            CERULEAN_STAINED_GLASS_PANE,
            TAUPE_STAINED_GLASS_PANE,
            MAROON_STAINED_GLASS_PANE,
            BLUEBERRY_STAINED_GLASS_PANE,
            GRAPE_STAINED_GLASS_PANE,
            SAP_STAINED_GLASS_PANE,
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
            ACORN_SHULKER_BOX,
            CERULEAN_SHULKER_BOX,
            TAUPE_SHULKER_BOX,
            MAROON_SHULKER_BOX,
            BLUEBERRY_SHULKER_BOX,
            GRAPE_SHULKER_BOX,
            SAP_SHULKER_BOX,
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
            ACORN_BED,
            CERULEAN_BED,
            TAUPE_BED,
            MAROON_BED,
            BLUEBERRY_BED,
            GRAPE_BED,
            SAP_BED,
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
            ACORN_CANDLE,
            CERULEAN_CANDLE,
            TAUPE_CANDLE,
            MAROON_CANDLE,
            BLUEBERRY_CANDLE,
            GRAPE_CANDLE,
            SAP_CANDLE,
    };
    public static final Block[] CANDLE_CAKE_BLOCKS = {
            MINT_CANDLE,
            PEACH_CANDLE,
            PERIWINKLE_CANDLE,
            ARTICHOKE_CANDLE,
            FUCHSIA_CANDLE,
            VERMILION_CANDLE,
            SHAMROCK_CANDLE,
            INDIGO_CANDLE,
            BANANA_CANDLE,
            ACORN_CANDLE,
            CERULEAN_CANDLE,
            TAUPE_CANDLE,
            MAROON_CANDLE,
            BLUEBERRY_CANDLE,
            GRAPE_CANDLE,
            SAP_CANDLE,
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
            ACORN_BANNER,
            CERULEAN_BANNER,
            TAUPE_BANNER,
            MAROON_BANNER,
            BLUEBERRY_BANNER,
            GRAPE_BANNER,
            SAP_BANNER,
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
            ACORN_WALL_BANNER,
            CERULEAN_WALL_BANNER,
            TAUPE_WALL_BANNER,
            MAROON_WALL_BANNER,
            BLUEBERRY_WALL_BANNER,
            GRAPE_WALL_BANNER,
            SAP_WALL_BANNER,
    };

    /**
     * @param group unused in 1.20, only defined here in that version to make potential backporting easier.
     * @return
     */
    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name,block,group);
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    private static BannerBlock createBannerBlock(DyeColor color) {
        return new BannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BANNER).mapColor(color.getMapColor()));
    }
    private static WallBannerBlock createWallBannerBlock(DyeColor color, BannerBlock banner) {
        return new WallBannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_WALL_BANNER).mapColor(color.getMapColor()).dropsLike(banner));
    }
    private static BedBlock createBedBlock(DyeColor color) {
        return new BedBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BED).mapColor(blockState -> blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY));
    }
    private static ModShulkerBoxBlock createShulkerBoxBlock(DyeColor color) {
        return new ModShulkerBoxBlock(color, FabricBlockSettings.copyOf(Blocks.SHULKER_BOX).mapColor(MapColor.LICHEN_GREEN));
    }
    public static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

    public static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    private static ToIntFunction<BlockState> createLightLevelFromProperty(int litLevel, BooleanProperty property) {
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

