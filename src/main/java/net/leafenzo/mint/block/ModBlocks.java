package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.item.ModItemGroups;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final HashMap<FlowerBlock, FlowerPotBlock> FLOWER_POT_FROM_FLOWER = new HashMap<FlowerBlock, FlowerPotBlock>();
    public static final HashMap<Block, DyeColor> DYECOLOR_FROM_BLOCK = new HashMap<Block, DyeColor>();
    public static final HashMap<Block, Block> WOOL_CARPET_FROM_WOOL = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> CANDLE_CAKE_FROM_CANDLE = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> STAINED_GLASS_PANE_FROM_STAINED_GLASS = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> WALL_BANNER_FROM_BANNER = new HashMap<Block, Block>();

    //    public static final TagKey<Block> WOOL_BLOCKS = ModTags.Blocks.getOrCreateTag("wool_blocks");
    public static final ArrayList<Block> WOOL_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> WOOL_CARPET_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> TERRACOTTA_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> CONCRETE_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> CONCRETE_POWDER_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> GLAZED_TERRACOTTA_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> STAINED_GLASS_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> STAINED_GLASS_PANE_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> SHULKER_BOX_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> BED_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> CANDLE_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> CANDLE_CAKE_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> BANNER_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> WALL_BANNER_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> SMALL_FLOWERS = new ArrayList<Block>();
    public static final ArrayList<Block> FLOWER_POTS = new ArrayList<Block>();

    /**
     * This list is just used in ItemGroups
     */
    public static final ArrayList<Block> COLORED_BLOCKS = new ArrayList<Block>();
    /**
     * This list is just used in ItemGroups
     */
    public static final ArrayList<Block> FUNCTIONAL_BLOCKS = new ArrayList<Block>();


    // MINT - Main
    public static final Block MINT_WOOL = registerBlock("mint_wool", createWoolBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", createDyedCarpetBlock(ModDyeColor.MINT, MINT_WOOL), ModItemGroups.MINT);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", createTerracottaBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", createConcreteBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", createConcretePowderBlock(ModDyeColor.MINT, MINT_CONCRETE), ModItemGroups.MINT);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", createStainedGlassBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MINT, MINT_STAINED_GLASS), ModItemGroups.MINT);
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ModDyeColor.MINT));
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", createCandleBlock(ModDyeColor.MINT), ModItemGroups.MINT);
    public static final Block MINT_CANDLE_CAKE = registerBlock("mint_candle_cake", createCandleCakeBlock(ModDyeColor.MINT, MINT_CANDLE), ModItemGroups.MINT);
    public static final Block MINT_BANNER = registerBlockWithoutBlockItem("mint_banner", createBannerBlock(ModDyeColor.MINT));
    public static final Block MINT_WALL_BANNER = registerBlockWithoutBlockItem("mint_wall_banner", createWallBannerBlock(ModDyeColor.MINT, (BannerBlock)ModBlocks.MINT_BANNER));
    // MINT - Special
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop", new MintCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT).mapColor(MapColor.LICHEN_GREEN)));
    public static final Block WILD_MINT = registerBlock("wild_mint", createFlowerBlock(ModEffects.MENTHOL, 900), ModItemGroups.MINT);
    public static final Block POTTED_WILD_MINT = registerBlock("potted_wild_mint", createFlowerPotBlock((FlowerBlock) WILD_MINT), ModItemGroups.PEACH);
        static { FLOWER_POT_FROM_FLOWER.put((FlowerBlock) WILD_MINT, (FlowerPotBlock) POTTED_WILD_MINT); }
    public static final Block MINT_SPRIG_BLOCK = registerBlock("mint_sprig_block", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BRICKS = registerBlock("mint_bricks", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.LICHEN_GREEN)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_SLAB = registerBlock("mint_brick_slab", new SlabBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    public static final Block MINT_BRICK_STAIRS = registerBlock("mint_brick_stairs", new StairsBlock(MINT_BRICKS.getDefaultState(), FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);
    //public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", new WallBlock(FabricBlockSettings.copyOf(MINT_BRICKS)), ModItemGroups.MINT);

    // PEACH - Main
    public static final Block PEACH_WOOL = registerBlock("peach_wool", createWoolBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CARPET = registerBlock("peach_carpet", createDyedCarpetBlock(ModDyeColor.PEACH, PEACH_WOOL), ModItemGroups.PEACH);
    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", createTerracottaBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", createConcreteBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", createConcretePowderBlock(ModDyeColor.PEACH, PEACH_CONCRETE), ModItemGroups.PEACH);
    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", createStainedGlassBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PEACH, PEACH_STAINED_GLASS), ModItemGroups.PEACH);
    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PEACH));
    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PEACH));
    public static final Block PEACH_CANDLE = registerBlock("peach_candle", createCandleBlock(ModDyeColor.PEACH), ModItemGroups.PEACH);
    public static final Block PEACH_CANDLE_CAKE = registerBlock("peach_candle_cake", createCandleCakeBlock(ModDyeColor.PEACH, PEACH_CANDLE), ModItemGroups.PEACH);
    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PEACH));
    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PEACH, (BannerBlock)ModBlocks.PEACH_BANNER));
    // PEACH - Special
    public static final Block HYPERICUM = registerBlock("hypericum", createFlowerBlock(StatusEffects.HUNGER, 900), ModItemGroups.PEACH); //hunger cause hypericum berries cause digestion issues irl
    public static final Block POTTED_HYPERICUM = registerBlock("potted_hypericum", createFlowerPotBlock((FlowerBlock) HYPERICUM), ModItemGroups.PEACH);
        static { FLOWER_POT_FROM_FLOWER.put((FlowerBlock) HYPERICUM, (FlowerPotBlock) POTTED_HYPERICUM); }
    //public static final Block PEACH_TREE = registerBlock("peach_tree", PeachTreeBlock)
    public static final Block PEACH_LOG = registerBlock("peach_log", new Block(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0f).sounds(BlockSoundGroup.WOOD).burnable().mapColor(MapColor.STONE_GRAY)), ModItemGroups.PEACH);

    // PERIWINKLE - Main
    public static final Block PERIWINKLE_WOOL = registerBlock("periwinkle_wool", createWoolBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CARPET = registerBlock("periwinkle_carpet", createDyedCarpetBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_WOOL), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("periwinkle_terracotta", createTerracottaBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE = registerBlock("periwinkle_concrete", createConcreteBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("periwinkle_concrete_powder", createConcretePowderBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CONCRETE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("periwinkle_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("periwinkle_stained_glass", createStainedGlassBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("periwinkle_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_STAINED_GLASS), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("periwinkle_shulker_box", createShulkerBoxBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("periwinkle_bed", createBedBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_CANDLE = registerBlock("periwinkle_candle", createCandleBlock(ModDyeColor.PERIWINKLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlock("periwinkle_candle_cake", createCandleCakeBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CANDLE), ModItemGroups.PERIWINKLE);
    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("periwinkle_banner", createBannerBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("periwinkle_wall_banner", createWallBannerBlock(ModDyeColor.PERIWINKLE, (BannerBlock)ModBlocks.PERIWINKLE_BANNER));
    // PERIWINKLE - Special

    // ARTICHOKE - Main
    public static final Block ARTICHOKE_WOOL = registerBlock("artichoke_wool", createWoolBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CARPET = registerBlock("artichoke_carpet", createDyedCarpetBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_WOOL), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("artichoke_terracotta", createTerracottaBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE = registerBlock("artichoke_concrete", createConcreteBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("artichoke_concrete_powder", createConcretePowderBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CONCRETE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("artichoke_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("artichoke_stained_glass", createStainedGlassBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("artichoke_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_STAINED_GLASS), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("artichoke_shulker_box", createShulkerBoxBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("artichoke_bed", createBedBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_CANDLE = registerBlock("artichoke_candle", createCandleBlock(ModDyeColor.ARTICHOKE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlock("artichoke_candle_cake", createCandleCakeBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CANDLE), ModItemGroups.ARTICHOKE);
    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("artichoke_banner", createBannerBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("artichoke_wall_banner", createWallBannerBlock(ModDyeColor.ARTICHOKE, (BannerBlock)ModBlocks.ARTICHOKE_BANNER));
    // ARTICHOKE - Special

    // FUCHSIA - Main
    public static final Block FUCHSIA_WOOL = registerBlock("fuchsia_wool", createWoolBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CARPET = registerBlock("fuchsia_carpet", createDyedCarpetBlock(ModDyeColor.FUCHSIA, FUCHSIA_WOOL), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta", createTerracottaBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE = registerBlock("fuchsia_concrete", createConcreteBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CONCRETE_POWDER = registerBlock("fuchsia_concrete_powder", createConcretePowderBlock(ModDyeColor.FUCHSIA, FUCHSIA_CONCRETE), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_GLAZED_TERRACOTTA = registerBlock("fuchsia_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass", createStainedGlassBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_STAINED_GLASS_PANE = registerBlock("fuchsia_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.FUCHSIA, FUCHSIA_STAINED_GLASS), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_SHULKER_BOX = registerBlockWithoutBlockItem("fuchsia_shulker_box", createShulkerBoxBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_BED = registerBlockWithoutBlockItem("fuchsia_bed", createBedBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_CANDLE = registerBlock("fuchsia_candle", createCandleBlock(ModDyeColor.FUCHSIA), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_CANDLE_CAKE = registerBlock("fuchsia_candle_cake", createCandleCakeBlock(ModDyeColor.FUCHSIA, FUCHSIA_CANDLE), ModItemGroups.FUCHSIA);
    public static final Block FUCHSIA_BANNER = registerBlockWithoutBlockItem("fuchsia_banner", createBannerBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_WALL_BANNER = registerBlockWithoutBlockItem("fuchsia_wall_banner", createWallBannerBlock(ModDyeColor.FUCHSIA, (BannerBlock)ModBlocks.FUCHSIA_BANNER));
    // FUCHSIA - Special

    // VERMILION - Main
    public static final Block VERMILION_WOOL = registerBlock("vermilion_wool", createWoolBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CARPET = registerBlock("vermilion_carpet", createDyedCarpetBlock(ModDyeColor.VERMILION, VERMILION_WOOL), ModItemGroups.VERMILION);
    public static final Block VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta", createTerracottaBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE = registerBlock("vermilion_concrete", createConcreteBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CONCRETE_POWDER = registerBlock("vermilion_concrete_powder",createConcretePowderBlock(ModDyeColor.VERMILION, VERMILION_CONCRETE), ModItemGroups.VERMILION);
    public static final Block VERMILION_GLAZED_TERRACOTTA = registerBlock("vermilion_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass", createStainedGlassBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_STAINED_GLASS_PANE = registerBlock("vermilion_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.VERMILION, VERMILION_STAINED_GLASS), ModItemGroups.VERMILION);
    public static final Block VERMILION_SHULKER_BOX = registerBlockWithoutBlockItem("vermilion_shulker_box", createShulkerBoxBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_BED = registerBlockWithoutBlockItem("vermilion_bed", createBedBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_CANDLE = registerBlock("vermilion_candle", createCandleBlock(ModDyeColor.VERMILION), ModItemGroups.VERMILION);
    public static final Block VERMILION_CANDLE_CAKE = registerBlock("vermilion_candle_cake", createCandleCakeBlock(ModDyeColor.VERMILION, VERMILION_CANDLE), ModItemGroups.VERMILION);
    public static final Block VERMILION_BANNER = registerBlockWithoutBlockItem("vermilion_banner", createBannerBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_WALL_BANNER = registerBlockWithoutBlockItem("vermilion_wall_banner", createWallBannerBlock(ModDyeColor.VERMILION, (BannerBlock)ModBlocks.VERMILION_BANNER));
    // VERMILION - Special


    // SHAMROCK - Main
    public static final Block SHAMROCK_WOOL = registerBlock("shamrock_wool", createWoolBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CARPET = registerBlock("shamrock_carpet", createDyedCarpetBlock(ModDyeColor.SHAMROCK, SHAMROCK_WOOL), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_TERRACOTTA = registerBlock("shamrock_terracotta", createTerracottaBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE = registerBlock("shamrock_concrete", createConcreteBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CONCRETE_POWDER = registerBlock("shamrock_concrete_powder", createConcretePowderBlock(ModDyeColor.SHAMROCK, SHAMROCK_CONCRETE), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_GLAZED_TERRACOTTA = registerBlock("shamrock_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS = registerBlock("shamrock_stained_glass", createStainedGlassBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_STAINED_GLASS_PANE = registerBlock("shamrock_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.SHAMROCK, SHAMROCK_STAINED_GLASS), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_SHULKER_BOX = registerBlockWithoutBlockItem("shamrock_shulker_box", createShulkerBoxBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_BED = registerBlockWithoutBlockItem("shamrock_bed", createBedBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_CANDLE = registerBlock("shamrock_candle", createCandleBlock(ModDyeColor.SHAMROCK), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_CANDLE_CAKE = registerBlock("shamrock_candle_cake", createCandleCakeBlock(ModDyeColor.SHAMROCK, SHAMROCK_CANDLE), ModItemGroups.SHAMROCK);
    public static final Block SHAMROCK_BANNER = registerBlockWithoutBlockItem("shamrock_banner", createBannerBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_WALL_BANNER = registerBlockWithoutBlockItem("shamrock_wall_banner", createWallBannerBlock(ModDyeColor.SHAMROCK, (BannerBlock)ModBlocks.SHAMROCK_BANNER));
    // SHAMROCK - Special

    // INDIGO - Main
    public static final Block INDIGO_WOOL = registerBlock("indigo_wool", createWoolBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CARPET = registerBlock("indigo_carpet", createDyedCarpetBlock(ModDyeColor.INDIGO, INDIGO_WOOL), ModItemGroups.INDIGO);
    public static final Block INDIGO_TERRACOTTA = registerBlock("indigo_terracotta", createTerracottaBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE = registerBlock("indigo_concrete", createConcreteBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CONCRETE_POWDER = registerBlock("indigo_concrete_powder", createConcretePowderBlock(ModDyeColor.INDIGO, INDIGO_CONCRETE), ModItemGroups.INDIGO);
    public static final Block INDIGO_GLAZED_TERRACOTTA = registerBlock("indigo_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass", createStainedGlassBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_STAINED_GLASS_PANE = registerBlock("indigo_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.INDIGO, INDIGO_STAINED_GLASS), ModItemGroups.INDIGO);
    public static final Block INDIGO_SHULKER_BOX = registerBlockWithoutBlockItem("indigo_shulker_box", createShulkerBoxBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_BED = registerBlockWithoutBlockItem("indigo_bed", createBedBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_CANDLE = registerBlock("indigo_candle", createCandleBlock(ModDyeColor.INDIGO), ModItemGroups.INDIGO);
    public static final Block INDIGO_CANDLE_CAKE = registerBlock("indigo_candle_cake", createCandleCakeBlock( ModDyeColor.INDIGO, INDIGO_CANDLE), ModItemGroups.INDIGO);
    public static final Block INDIGO_BANNER = registerBlockWithoutBlockItem("indigo_banner", createBannerBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_WALL_BANNER = registerBlockWithoutBlockItem("indigo_wall_banner", createWallBannerBlock(ModDyeColor.INDIGO, (BannerBlock)ModBlocks.INDIGO_BANNER));
    // INDIGO - Special


    // BANANA - Main
    public static final Block BANANA_WOOL = registerBlock("banana_wool", createWoolBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CARPET = registerBlock("banana_carpet", createDyedCarpetBlock(ModDyeColor.BANANA, BANANA_WOOL), ModItemGroups.BANANA);
    public static final Block BANANA_TERRACOTTA = registerBlock("banana_terracotta", createTerracottaBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE = registerBlock("banana_concrete", createConcreteBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CONCRETE_POWDER = registerBlock("banana_concrete_powder", createConcretePowderBlock(ModDyeColor.BANANA, BANANA_CONCRETE), ModItemGroups.BANANA);
    public static final Block BANANA_GLAZED_TERRACOTTA = registerBlock("banana_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS = registerBlock("banana_stained_glass", createStainedGlassBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_STAINED_GLASS_PANE = registerBlock("banana_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.BANANA, BANANA_STAINED_GLASS), ModItemGroups.BANANA);
    public static final Block BANANA_SHULKER_BOX = registerBlockWithoutBlockItem("banana_shulker_box", createShulkerBoxBlock(ModDyeColor.BANANA));
    public static final Block BANANA_BED = registerBlockWithoutBlockItem("banana_bed", createBedBlock(ModDyeColor.BANANA));
    public static final Block BANANA_CANDLE = registerBlock("banana_candle", createCandleBlock(ModDyeColor.BANANA), ModItemGroups.BANANA);
    public static final Block BANANA_CANDLE_CAKE = registerBlock("banana_candle_cake", createCandleCakeBlock(ModDyeColor.BANANA, BANANA_CANDLE), ModItemGroups.BANANA);
    public static final Block BANANA_BANNER = registerBlockWithoutBlockItem("banana_banner", createBannerBlock(ModDyeColor.BANANA));
    public static final Block BANANA_WALL_BANNER = registerBlockWithoutBlockItem("banana_wall_banner", createWallBannerBlock(ModDyeColor.BANANA, (BannerBlock)ModBlocks.BANANA_BANNER));
    // BANANA - Special
    
    // Arrays



    /**
     * @param group unused in 1.20, only defined here in that version to make potential backporting easier.
     * @return
     */
    public static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name,block,group);
        return Registry.register(Registries.BLOCK, new Identifier(Super.MOD_ID, name), block);
    }

    public static FlowerBlock createFlowerBlock(StatusEffect suspiciousStewEffect, int effectDuration) {
        FlowerBlock block = new FlowerBlock(suspiciousStewEffect, effectDuration,
                FabricBlockSettings.copyOf(Blocks.DANDELION)
                .mapColor(MapColor.DARK_GREEN)
                .noCollision()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .offset(AbstractBlock.OffsetType.XZ)
                .pistonBehavior(PistonBehavior.DESTROY)
        );
        SMALL_FLOWERS.add(block);
        return block;
    }
    public static FlowerPotBlock createFlowerPotBlock(FlowerBlock flower) {
        FlowerPotBlock block = new FlowerPotBlock(flower, FabricBlockSettings.create()
                    .breakInstantly()
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
        );
        FLOWER_POTS.add(block);
        FLOWER_POT_FROM_FLOWER.put(flower, block);
        return block;
    }

    public static Block createWoolBlock(DyeColor color) {
        Block block =  new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)
                .mapColor(color.getMapColor())
                .instrument(Instrument.GUITAR)
                .strength(0.8f)
                .sounds(BlockSoundGroup.WOOL)
                .burnable()
        );
        WOOL_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static DyedCarpetBlock createDyedCarpetBlock(DyeColor color, Block wool) {
        DyedCarpetBlock block = new DyedCarpetBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_CARPET)
                .mapColor(color.getMapColor())
                .strength(0.1f)
                .sounds(BlockSoundGroup.WOOL)
                .burnable()
        );
        WOOL_CARPET_BLOCKS.add(block);
        WOOL_CARPET_FROM_WOOL.put(wool, block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static Block createTerracottaBlock(DyeColor color) {
        Block block = new Block(FabricBlockSettings.copyOf(Blocks.WHITE_TERRACOTTA)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.25f, 4.2f)
        );
        TERRACOTTA_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static Block createConcreteBlock(DyeColor color) {
        Block block = new Block(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.8f)
        );
        CONCRETE_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static ConcretePowderBlock createConcretePowderBlock(DyeColor color, Block concrete) {
        ConcretePowderBlock block = new ConcretePowderBlock(concrete, FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE_POWDER)
                .mapColor(color.getMapColor())
                .instrument(Instrument.SNARE)
                .strength(0.5f)
                .sounds(BlockSoundGroup.SAND)
        );
        CONCRETE_POWDER_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color); COLORED_BLOCKS.add(block);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static CandleBlock createCandleBlock(DyeColor color) {
        CandleBlock block = new CandleBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE)
                .mapColor(color.getMapColor())
                .nonOpaque()
                .strength(0.1f)
                .sounds(BlockSoundGroup.CANDLE)
                .luminance(CandleBlock.STATE_TO_LUMINANCE)
                .pistonBehavior(PistonBehavior.DESTROY)
        );
        CANDLE_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color); COLORED_BLOCKS.add(block);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
        return block;
    }
    public static CandleCakeBlock createCandleCakeBlock(DyeColor color, Block candle) {
        CandleCakeBlock block = new CandleCakeBlock(candle, FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE_CAKE)
                .mapColor(color.getMapColor())
                .solid()
                .strength(0.5f)
                .sounds(BlockSoundGroup.WOOL)
                .pistonBehavior(PistonBehavior.DESTROY)
                .luminance(ModBlocks.createLightLevelFromBooleanProperty(3, Properties.LIT))
        );
        CANDLE_CAKE_BLOCKS.add(block);
        CANDLE_CAKE_FROM_CANDLE.put(candle, block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        return block;
    }
    public static GlazedTerracottaBlock createGlazedTerracottaBlock(DyeColor color) {
        GlazedTerracottaBlock block = new GlazedTerracottaBlock(FabricBlockSettings.copyOf(Blocks.WHITE_GLAZED_TERRACOTTA)
                .mapColor(color.getMapColor())
                .instrument(Instrument.BASEDRUM)
                .requiresTool()
                .strength(1.4f)
                .pistonBehavior(PistonBehavior.PUSH_ONLY)
        );
        GLAZED_TERRACOTTA_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static StainedGlassBlock createStainedGlassBlock(DyeColor color) {
        StainedGlassBlock block = new StainedGlassBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_STAINED_GLASS)
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
        STAINED_GLASS_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    public static StainedGlassPaneBlock createStainedGlassPaneBlock(DyeColor color, Block glass) {
        StainedGlassPaneBlock block = new StainedGlassPaneBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_STAINED_GLASS_PANE)
                .mapColor(color.getMapColor())
                .instrument(Instrument.HAT)
                .strength(0.3f)
                .sounds(BlockSoundGroup.GLASS)
                .nonOpaque()
        );
        STAINED_GLASS_PANE_BLOCKS.add(block);
        STAINED_GLASS_PANE_FROM_STAINED_GLASS.put(glass, block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        return block;
    }
    private static BannerBlock createBannerBlock(DyeColor color) {
        BannerBlock block = new BannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BANNER)
                .mapColor(color.getMapColor())
                .solid()
                .instrument(Instrument.BASS)
                .noCollision()
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .burnable()
        );
        BANNER_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
        return block;
    }
    private static WallBannerBlock createWallBannerBlock(DyeColor color, BannerBlock banner) {
        WallBannerBlock block = new WallBannerBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_WALL_BANNER)
                .mapColor(color.getMapColor())
                .solid()
                .instrument(Instrument.BASS)
                .noCollision()
                .strength(1.0f)
                .sounds(BlockSoundGroup.WOOD)
                .dropsLike(banner)
                .burnable()
        );
        WALL_BANNER_BLOCKS.add(block);
        WALL_BANNER_FROM_BANNER.put(banner, block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        return block;
    }
    private static BedBlock createBedBlock(DyeColor color) {
        BedBlock block = new BedBlock(color, FabricBlockSettings.copyOf(Blocks.WHITE_BED)
                .mapColor(blockState -> blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY)
                .sounds(BlockSoundGroup.WOOD)
                .strength(0.2f)
                .nonOpaque()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .burnable()
        );
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
        return block;
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
        ModShulkerBoxBlock block = new ModShulkerBoxBlock(color, FabricBlockSettings.copyOf(Blocks.SHULKER_BOX)
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
        return block;
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
    public static Item[] toItemArray(Block[] blocks) {
        Item[] items = new Item[blocks.length];
        for(int i = 0; i < items.length; i++) {
            items[i] = blocks[i].asItem();
        }
        return items;
    }

    public static Block[] toBlockArray(ArrayList<Block> input) {
        return new Block[input.size()];
    }

    public static Item[] toItemArray(ArrayList<Block> input) {
        return new Item[input.size()];
    }


    public static Block firstMatchOfColor(ArrayList<Block> blocks, DyeColor color) {
        for(Block block : blocks) {
            if(DYECOLOR_FROM_BLOCK.get(block) == color) { return block; }
        }
        return null;
    }
    public static Block[] allMatchesOfColor(ArrayList<Block> blocks, DyeColor color) {
        ArrayList<Block> output = new ArrayList<>();
        for(Block block : blocks) {
            if(DYECOLOR_FROM_BLOCK.get(block) == color) { output.add(block); }
        }
        return ModBlocks.toBlockArray(output);
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

