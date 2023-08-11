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
//    public static final Block PEACH_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(PEACH_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.PEACH, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.PEACH, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PEACH));
//    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PEACH));
//    public static final Block PEACH_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PEACH);
//    public static final Block PEACH_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(PEACH_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.PEACH);
//    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PEACH));
//    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PEACH, (BannerBlock)ModBlocks.PEACH_BANNER));
    // PEACH - Special

    // PERIWINKLE - Main
//    public static final Block PERIWINKLE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(PERIWINKLE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.PERIWINKLE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.PERIWINKLE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PERIWINKLE));
//    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PERIWINKLE));
//    public static final Block PERIWINKLE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(PERIWINKLE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.PERIWINKLE);
//    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PERIWINKLE));
//    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PERIWINKLE, (BannerBlock)ModBlocks.PERIWINKLE_BANNER));
    // PERIWINKLE - Special

    // ARTICHOKE - Main
//    public static final Block ARTICHOKE_WOOL = registerBlock("peach_wool", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_WOOL).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_CARPET = registerBlock("peach_carpet", new CarpetBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CARPET).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("peach_terracotta", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_CONCRETE = registerBlock("peach_concrete", new Block(FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("peach_concrete_powder", new ConcretePowderBlock(ARTICHOKE_CONCRETE, FabricBlockSettings.copyOf(Blocks.BLACK_CONCRETE_POWDER).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", new ReversiblePillarBlock(FabricBlockSettings.copyOf(Blocks.BLACK_GLAZED_TERRACOTTA).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("peach_stained_glass", new StainedGlassBlock(ModDyeColor.ARTICHOKE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", new StainedGlassPaneBlock(ModDyeColor.ARTICHOKE, FabricBlockSettings.copyOf(Blocks.BLACK_STAINED_GLASS_PANE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.ARTICHOKE));
//    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.ARTICHOKE));
//    public static final Block ARTICHOKE_CANDLE = registerBlock("peach_candle", new CandleBlock(FabricBlockSettings.copyOf(Blocks.BLACK_CANDLE).mapColor(MapColor.RAW_IRON_PINK)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlock("peach_candle_cake", new CandleCakeBlock(ARTICHOKE_CANDLE, FabricBlockSettings.copyOf(Blocks.CANDLE_CAKE)), ModItemGroups.ARTICHOKE);
//    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.ARTICHOKE));
//    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.ARTICHOKE, (BannerBlock)ModBlocks.ARTICHOKE_BANNER));
    // ARTICHOKE - Special

    // Arrays
    public static final Block[] WOOL_BLOCKS =               { MINT_WOOL/*,               PEACH_WOOL,               PERIWINKLE_WOOL,               ARTICHOKE_WOOL*/ };
    public static final Block[] CARPET_BLOCKS =             { MINT_CARPET/*,             PEACH_CARPET,             PERIWINKLE_CARPET,             ARTICHOKE_CARPET*/ };
    public static final Block[] TERRACOTTA_BLOCKS =         { MINT_TERRACOTTA/*,         PEACH_TERRACOTTA,         PERIWINKLE_TERRACOTTA,         ARTICHOKE_TERRACOTTA*/ };
    public static final Block[] CONCRETE_BLOCKS =           { MINT_CONCRETE/*,           PEACH_CONCRETE,           PERIWINKLE_CONCRETE,           ARTICHOKE_CONCRETE*/ };
    public static final Block[] CONCRETE_POWDER_BLOCKS =    { MINT_CONCRETE_POWDER/*,    PEACH_CONCRETE_POWDER,    PERIWINKLE_CONCRETE_POWDER,    ARTICHOKE_CONCRETE_POWDER*/ };
    public static final Block[] GLAZED_TERRACOTTA_BLOCKS =  { MINT_GLAZED_TERRACOTTA/*,  PEACH_GLAZED_TERRACOTTA,  PERIWINKLE_GLAZED_TERRACOTTA,  ARTICHOKE_GLAZED_TERRACOTTA*/ };
    public static final Block[] STAINED_GLASS_BLOCKS =      { MINT_STAINED_GLASS/*,      PEACH_STAINED_GLASS,      PERIWINKLE_STAINED_GLASS,      ARTICHOKE_STAINED_GLASS*/ };
    public static final Block[] STAINED_GLASS_PANE_BLOCKS = { MINT_STAINED_GLASS_PANE/*, PEACH_STAINED_GLASS_PANE, PERIWINKLE_STAINED_GLASS_PANE, ARTICHOKE_STAINED_GLASS_PANE*/ };
    public static final Block[] SHULKER_BOX_BLOCKS =        { MINT_SHULKER_BOX/*,        PEACH_SHULKER_BOX,        PERIWINKLE_SHULKER_BOX,        ARTICHOKE_SHULKER_BOX*/ };
    public static final Block[] BED_BLOCKS =                { MINT_BED/*,                PEACH_BED,                PERIWINKLE_BED,                ARTICHOKE_BED*/ };
    public static final Block[] CANDLE_BLOCKS =             { MINT_CANDLE/*,             PEACH_CANDLE,             PERIWINKLE_CANDLE,             ARTICHOKE_CANDLE*/ };
    public static final Block[] CANDLE_CAKE_BLOCKS =        { MINT_CANDLE_CAKE/*,        PEACH_CANDLE_CAKE,        PERIWINKLE_CANDLE_CAKE,        ARTICHOKE_CANDLE_CAKE*/ };
    public static final Block[] BANNER_BLOCKS =             { MINT_BANNER/*,             PEACH_BANNER,             PERIWINKLE_BANNER,             ARTICHOKE_BANNER*/ };
    public static final Block[] WALL_BANNER_BLOCKS =        { MINT_WALL_BANNER/*,        PEACH_WALL_BANNER,        PERIWINKLE_WALL_BANNER,        ARTICHOKE_WALL_BANNER*/ };

//    public static Block[] WOOL_BLOCKS = (Block[])Arrays.stream(Registries.BLOCK.stream().toArray()).filter((Block x) -> x.getName().contains(Text.literal("_wool"))); // Worried this would include vanilla blocks

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

