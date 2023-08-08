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
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop", new MintCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.LICHEN_GREEN)));
    public static final Block WILD_MINT = registerBlock("wild_mint", new FernBlock(FabricBlockSettings.copyOf(Blocks.FERN).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_WOOL = registerBlock("mint_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", new ConcretePowderBlock(MINT_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_SPRIG_BLOCK = registerBlock("mint_sprig_block", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", new StainedGlassBlock(ModDyeColor.MINT, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.MINT, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ModDyeColor.MINT));
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ModDyeColor.MINT));
    public static final Block MINT_BANNER = registerBlockWithoutBlockItem("mint_banner", createBannerBlock(ModDyeColor.MINT));
    public static final Block MINT_WALL_BANNER = registerBlockWithoutBlockItem("mint_wall_banner", createWallBannerBlock(ModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_CANDLE_CAKE = registerBlock("mint_candle_cake", new CandleCakeBlock(MINT_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.MINT);
    public static final Block MINT_BRICKS = registerBlock("mint_bricks", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_SLAB = registerBlock("mint_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_STAIRS = registerBlock("mint_brick_stairs", new StairsBlock(MINT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    //public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    //#65ff8e

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
    private static WallBannerBlock createWallBannerBlock(DyeColor color) {
        return new WallBannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_WALL_BANNER).mapColor(color.getMapColor()));
    }

    private static BedBlock createBedBlock(DyeColor color) {
        return new BedBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BED).mapColor(blockState -> blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY));
    }

    private static ShulkerBoxBlock createShulkerBoxBlock(DyeColor color) {
        return new ShulkerBoxBlock(color, FabricBlockSettings.copyOf(Blocks.SHULKER_BOX).mapColor(MapColor.LICHEN_GREEN));
    }

    public static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    public static Block registerBlockWithoutBlockItemAndWithBlockEntity(String name, Block block) {
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

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        BlockItem blockItem = new BlockItem(block, new FabricItemSettings());
        //ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(blockItem)); // only for pre 1.20.1, around cuz I'd forget otherwise
        return Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), blockItem);
    }


    public static void registerModBlocks() {
        ModInit.LOGGER.debug("Registering mod blocks for " + Super.MOD_ID);
    }
}

