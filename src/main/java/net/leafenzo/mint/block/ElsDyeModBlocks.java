package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.custom.*;
import net.leafenzo.mint.block.sapling.PeachSaplingGenerator;
import net.leafenzo.mint.block.sapling.WintergreenSaplingGenerator;
import net.leafenzo.mint.datageneration.ElsDyeModConfiguredFeatures;
import net.leafenzo.mint.effect.ElsDyeModEffects;
import net.leafenzo.mint.entity.ElsDyeModBoatEntity;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

import static net.leafenzo.mint.registration.ElsDyeModRegistryHelper.BlockRegistry.*;

public class ElsDyeModBlocks {
    //<editor-fold desc ="Hashmaps & Arrays">
    public static final HashMap<Block, FlowerPotBlock> FLOWER_POT_FROM_BLOCK = new HashMap<Block, FlowerPotBlock>();
    public static final HashMap<Block, DyeColor> DYECOLOR_FROM_BLOCK = new HashMap<Block, DyeColor>();
    public static final HashMap<DyeColor, Block> SHULKER_BOX_FROM_DYECOLOR = new HashMap<DyeColor, Block>();
    public static final HashMap<Block, Block> WOOL_CARPET_FROM_WOOL = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> CANDLE_CAKE_FROM_CANDLE = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> STAINED_GLASS_PANE_FROM_STAINED_GLASS = new HashMap<Block, Block>();
    public static final HashMap<Block, Block> WALL_BANNER_FROM_BANNER = new HashMap<Block, Block>();
    public static final ArrayList<Block> WOOL_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> WOOL_CARPET_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> DYED_TERRACOTTA_BLOCKS = new ArrayList<Block>();
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
    public static final ArrayList<Block> MUSHROOM_PLANTS = new ArrayList<Block>();
    public static final ArrayList<Block> SLABS = new ArrayList<Block>();
    public static final ArrayList<Block> STAIRS = new ArrayList<Block>();
    public static final ArrayList<Block> WALLS = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_SLABS = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_STAIRS = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_FENCES = new ArrayList<Block>();
    public static final ArrayList<Block> FENCE_GATES = new ArrayList<Block>();
    public static final ArrayList<Block> PLANKS = new ArrayList<Block>();
    public static final ArrayList<Block> LOGS = new ArrayList<Block>();
    public static final ArrayList<Block> LOGS_THAT_BURN = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_DOORS = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_TRAPDOORS = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_PRESSURE_PLATES = new ArrayList<Block>();
    public static final ArrayList<Block> WOODEN_BUTTONS = new ArrayList<Block>();
    public static final ArrayList<Block> SIGNS = new ArrayList<Block>();
    public static final ArrayList<Block> RENDER_LAYER_CUTOUT = new ArrayList<Block>();
    public static final ArrayList<Block> RENDER_LAYER_CUTOUT_MIPPED = new ArrayList<Block>();
    public static final ArrayList<Block> RENDER_LAYER_TRANSLUCENT = new ArrayList<Block>();
    public static final ArrayList<Block> HAS_FOLIAGE_COLOR_PROVIDER = new ArrayList<Block>();
    public static final ArrayList<Block> HAS_GRASS_COLOR_PROVIDER = new ArrayList<Block>();
    public static final ArrayList<Block> LEAVES = new ArrayList<Block>();
    public static final ArrayList<Block> SAPLINGS = new ArrayList<Block>();
    public static final ArrayList<WoodSet> WOODSETS = new ArrayList<WoodSet>();

    //Decor Additions
    //TODO, corrugated iron soundgroup?
    public static final ArrayList<Block> ALL_CORRUGATED_IRON_BLOCKS = new ArrayList<Block>();
    public static final ArrayList<Block> ALL_MUCKTUFF_BLOCKS = new ArrayList<Block>();

    /**
     * This list is just used in ItemGroups
     */ public static final ArrayList<Block> COLORED_BLOCKS = new ArrayList<Block>();
    /**
     * This list is just used in ItemGroups
     */ public static final ArrayList<Block> FUNCTIONAL_BLOCKS = new ArrayList<Block>();
    //</editor-fold>
    //<editor-fold desc ="MINT - Template">
    public static final Block MINT_WOOL = registerBlock("mint_wool", createWoolBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.MINT, MINT_WOOL)/*, ModItemGroups.MINT*/);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", createTerracottaBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", createConcreteBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.MINT, MINT_CONCRETE)/*, ModItemGroups.MINT*/);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.MINT, MINT_STAINED_GLASS)/*, ModItemGroups.MINT*/);
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.MINT));
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ElsDyeModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", createCandleBlock(ElsDyeModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CANDLE_CAKE = registerBlockWithoutBlockItem("mint_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.MINT, MINT_CANDLE)/*, ModItemGroups.MINT*/);
    public static final Block MINT_BANNER = registerBlockWithoutBlockItem("mint_banner", createBannerBlock(ElsDyeModDyeColor.MINT));
    public static final Block MINT_WALL_BANNER = registerBlockWithoutBlockItem("mint_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.MINT, (BannerBlock) ElsDyeModBlocks.MINT_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MINT - Special">
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop", new MintCropBlock(FabricBlockSettings.create().mapColor(MapColor.LICHEN_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(MINT_CROP); }
    public static final Block WILD_MINT = registerBlock("wild_mint", createFlowerBlock(ElsDyeModEffects.MINT_CHILL, 900)/*, ModItemGroups.MINT*/);
    public static final Block POTTED_WILD_MINT = registerBlockWithoutBlockItem("potted_wild_mint", createFlowerPotBlock(WILD_MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_SPRIG_BLOCK = registerBlock("mint_sprig_block", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).mapColor(MapColor.LICHEN_GREEN))/*, ModItemGroups.MINT*/);
        static { RENDER_LAYER_CUTOUT.add(MINT_SPRIG_BLOCK); }
    public static final Block MINT_BRICKS = registerBlock("mint_bricks", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.LICHEN_GREEN))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_SLAB = registerBlock("mint_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_STAIRS = registerBlock("mint_brick_stairs", createStairsBlock(MINT_BRICKS, FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", createWallBlock(FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static WoodSet WINTERGREEN_WOODSET = new WoodSet(
            new Identifier(ElsDyeMod.MOD_ID, "wintergreen"),
            MapColor.TEAL,
            MapColor.BRIGHT_TEAL,
            MapColor.DARK_GREEN,
            ElsDyeModBoatEntity.ModBoat.WINTERGREEN,
            WoodSet.WoodPreset.DEFAULT, // just so it's non-flammable, effects nothing else
            false,
            new WintergreenSaplingGenerator(),
            false,
            false
    );
    static { HAS_FOLIAGE_COLOR_PROVIDER.remove(WINTERGREEN_WOODSET.getLeaves()); }

    public static final Block WINTERGREEN_CANDY_CANE_BLOCK = registerBlock("wintergreen_candy_cane_block", new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).strength(0.4f).sounds(BlockSoundGroup.BONE).instrument(Instrument.CHIME)));
    public static final Block WINTERGREEN_CANDY_CANE_BARK = registerBlock("wintergreen_candy_cane_bark", new PillarBlock(FabricBlockSettings.copyOf(WINTERGREEN_CANDY_CANE_BLOCK).instrument(Instrument.CHIME)));
    public static final Block PEPPERMINT_CANDY_CANE_BLOCK = registerBlock("peppermint_candy_cane_block", new PillarBlock(FabricBlockSettings.copyOf(WINTERGREEN_CANDY_CANE_BLOCK).mapColor(MapColor.RAW_IRON_PINK).instrument(Instrument.CHIME)));
    public static final Block PEPPERMINT_CANDY_CANE_BARK = registerBlock("peppermint_candy_cane_bark", new PillarBlock(FabricBlockSettings.copyOf(PEPPERMINT_CANDY_CANE_BLOCK).instrument(Instrument.CHIME)));

    //public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", createWallBlock new WallBlock(FabricBlockSettings.copyOf(MINT_BRICKS/*)), ModItemGroups.MINT*/);
    //</editor-fold>
    //<editor-fold desc ="PEACH - Template">
    public static final Block PEACH_WOOL = registerBlock("peach_wool", createWoolBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CARPET = registerBlock("peach_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.PEACH, PEACH_WOOL)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", createTerracottaBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", createConcreteBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.PEACH, PEACH_CONCRETE)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.PEACH, PEACH_STAINED_GLASS)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.PEACH));
    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ElsDyeModDyeColor.PEACH));
    public static final Block PEACH_CANDLE = registerBlock("peach_candle", createCandleBlock(ElsDyeModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CANDLE_CAKE = registerBlockWithoutBlockItem("peach_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.PEACH, PEACH_CANDLE)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ElsDyeModDyeColor.PEACH));
    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.PEACH, (BannerBlock) ElsDyeModBlocks.PEACH_BANNER));
    //</editor-fold>
    //<editor-fold desc ="PEACH - Special">
    public static final Block HYPERICUM = registerBlock("hypericum", createFlowerBlock(StatusEffects.HUNGER, 900)/*, ModItemGroups.PEACH*/); //causes hunger because hypericum berries cause digestion issues irl
    public static final Block POTTED_HYPERICUM = registerBlockWithoutBlockItem("potted_hypericum", createFlowerPotBlock(HYPERICUM)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_TREE = registerBlockWithoutBlockItem("peach_tree", new PeachTreeBlock(FabricBlockSettings.create().noCollision().strength(0.2f).sounds(BlockSoundGroup.GRASS).burnable().pistonBehavior(PistonBehavior.DESTROY).ticksRandomly().offset(AbstractBlock.OffsetType.XZ).nonOpaque().solidBlock(ElsDyeModBlocks::never)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_TREE); }

    public static final Block PEACH_SAPLING = registerBlock("peach_sapling", new SaplingBlock(new PeachSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING).sounds(BlockSoundGroup.CHERRY_SAPLING)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_SAPLING); SAPLINGS.add(PEACH_SAPLING); }
    public static final Block POTTED_PEACH_SAPLING = registerBlockWithoutBlockItem("potted_peach_sapling", new FlowerPotBlock(ElsDyeModBlocks.PEACH_SAPLING, FabricBlockSettings.copyOf(Blocks.POTTED_OAK_SAPLING)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(POTTED_PEACH_SAPLING); }
    public static final Block HANGING_PEACH = registerBlockWithoutBlockItem("hanging_peach", new HangingPeachBlock(FabricBlockSettings.create().mapColor(MapColor.CLEAR).dynamicBounds().sounds(BlockSoundGroup.AZALEA).pistonBehavior(PistonBehavior.DESTROY).ticksRandomly().offset(AbstractBlock.OffsetType.XZ)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(HANGING_PEACH); }
    public static final Block PEACH_LEAVES = registerBlock("peach_leaves", new FruitLeavesBlock(ElsDyeModBlocks.HANGING_PEACH, FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_LEAVES); LEAVES.add(PEACH_LEAVES);  }
    public static final Block FLOWERING_PEACH_LEAVES = registerBlock("flowering_peach_leaves", new FruitLeavesBlock(ElsDyeModBlocks.HANGING_PEACH, FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(FLOWERING_PEACH_LEAVES); LEAVES.add(FLOWERING_PEACH_LEAVES); }
    public static final Block PEACH_LOG = registerBlock("peach_log", new FruitLogBlock(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0f).sounds(BlockSoundGroup.WOOD).burnable().mapColor(MapColor.STONE_GRAY))/*, ModItemGroups.PEACH*/);
    static { LOGS_THAT_BURN.add(PEACH_LOG); }
    static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_LOG); }
    public static final Block PEACH_WOOD = registerBlock("peach_wood", new FruitLogBlock(FabricBlockSettings.copyOf(ElsDyeModBlocks.PEACH_LOG)));
    static { LOGS_THAT_BURN.add(PEACH_WOOD); }
    static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_WOOD); }
    public static final Block STRIPPED_PEACH_LOG = registerBlock("stripped_peach_log", new StrippedFruitLogBlock(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0f).sounds(BlockSoundGroup.WOOD).burnable().mapColor(MapColor.TERRACOTTA_WHITE))/*, ModItemGroups.PEACH*/);
    static { LOGS_THAT_BURN.add(STRIPPED_PEACH_LOG); }
    public static final Block STRIPPED_PEACH_WOOD = registerBlock("stripped_peach_wood", new StrippedFruitLogBlock(FabricBlockSettings.copyOf(ElsDyeModBlocks.STRIPPED_PEACH_LOG)));
    static { LOGS_THAT_BURN.add(STRIPPED_PEACH_WOOD); }
    public static final Block CORAL_ANEMONE = registerBlock("coral_anemone", new CoralAnemoneBlock(ElsDyeModConfiguredFeatures.PATCH_BONEMEAL_CORAL_ANEMONE, FabricBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.SLIME).mapColor(MapColor.RAW_IRON_PINK).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(createLightLevelFromBooleanProperty(6, Properties.WATERLOGGED)))/*, ModItemGroups.PEACH*/);
        static { RENDER_LAYER_CUTOUT_MIPPED.add(CORAL_ANEMONE); }
    public static final Block CORALSOIL = registerBlock("coralsoil", new Block(FabricBlockSettings.copyOf(Blocks.CLAY))/*, ModItemGroups.PEACH*/);
    public static final Block CRACKED_CORALSOIL_BRICKS = registerBlock("cracked_coralsoil_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ElsDyeModDyeColor.PEACH.getMapColor()).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICKS = registerBlock("coralsoil_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ElsDyeModDyeColor.PEACH.getMapColor()).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_SLAB = registerBlock("coralsoil_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_STAIRS = registerBlock("coralsoil_brick_stairs", createStairsBlock(CORALSOIL_BRICKS, FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_WALL = registerBlock("coralsoil_brick_wall", createWallBlock(FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);

    //</editor-fold>
    //<editor-fold desc ="PERIWINKLE - Template">
    public static final Block PERIWINKLE_WOOL = registerBlock("periwinkle_wool", createWoolBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CARPET = registerBlock("periwinkle_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.PERIWINKLE, PERIWINKLE_WOOL)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("periwinkle_terracotta", createTerracottaBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CONCRETE = registerBlock("periwinkle_concrete", createConcreteBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("periwinkle_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.PERIWINKLE, PERIWINKLE_CONCRETE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("periwinkle_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("periwinkle_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("periwinkle_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.PERIWINKLE, PERIWINKLE_STAINED_GLASS)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("periwinkle_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("periwinkle_bed", createBedBlock(ElsDyeModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_CANDLE = registerBlock("periwinkle_candle", createCandleBlock(ElsDyeModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlockWithoutBlockItem("periwinkle_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.PERIWINKLE, PERIWINKLE_CANDLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("periwinkle_banner", createBannerBlock(ElsDyeModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("periwinkle_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.PERIWINKLE, (BannerBlock) ElsDyeModBlocks.PERIWINKLE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="PERIWINKLE - Special">
    public static final Block LAVENDER_BRICKS = registerBlock("lavender_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ElsDyeModDyeColor.PERIWINKLE.getMapColor()).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_SLAB = registerBlock("lavender_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_STAIRS = registerBlock("lavender_brick_stairs", createStairsBlock(LAVENDER_BRICKS, FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_WALL = registerBlock("lavender_brick_wall", createWallBlock(FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICKS = registerBlock("mossy_lavender_bricks", new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_COBBLESTONE).mapColor(ElsDyeModDyeColor.PERIWINKLE.getMapColor()).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_SLAB = registerBlock("mossy_lavender_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_STAIRS = registerBlock("mossy_lavender_brick_stairs", createStairsBlock(MOSSY_LAVENDER_BRICKS, FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_WALL = registerBlock("mossy_lavender_brick_wall", createWallBlock(FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);

    public static final Block LAVENDER_CLAY = registerBlock("lavender_clay", new Block(FabricBlockSettings.copyOf(Blocks.CLAY))/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BUSHEL = registerBlock("lavender_bushel", new LavenderBushelBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK))/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_PETALS = registerBlock("periwinkle_petals", new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS).mapColor(MapColor.DARK_GREEN))/*, ModItemGroups.PERIWINKLE*/);
        static { RENDER_LAYER_CUTOUT_MIPPED.add(PERIWINKLE_PETALS); }
    public static final Block HIDCOTE_LAVENDER = registerBlock("hidcote_lavender", createSpreadableFlowerBlock(StatusEffects.BAD_OMEN, 600, ElsDyeModConfiguredFeatures.PATCH_HIDCOTE_LAVENDER)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block POTTED_HIDCOTE_LAVENDER = registerBlockWithoutBlockItem("potted_hidcote_lavender", createFlowerPotBlock(HIDCOTE_LAVENDER)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_OIL_LANTERN = registerBlock("lavender_oil_lantern", new LanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).luminance(state -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))/*, ModItemGroups.PERIWINKLE*/);
        static { RENDER_LAYER_CUTOUT.add(LAVENDER_OIL_LANTERN); }
    //</editor-fold>
    //<editor-fold desc ="ARTICHOKE - Template">
    public static final Block ARTICHOKE_WOOL = registerBlock("artichoke_wool", createWoolBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CARPET = registerBlock("artichoke_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.ARTICHOKE, ARTICHOKE_WOOL)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("artichoke_terracotta", createTerracottaBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CONCRETE = registerBlock("artichoke_concrete", createConcreteBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("artichoke_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.ARTICHOKE, ARTICHOKE_CONCRETE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("artichoke_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("artichoke_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("artichoke_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.ARTICHOKE, ARTICHOKE_STAINED_GLASS)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("artichoke_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("artichoke_bed", createBedBlock(ElsDyeModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_CANDLE = registerBlock("artichoke_candle", createCandleBlock(ElsDyeModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlockWithoutBlockItem("artichoke_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.ARTICHOKE, ARTICHOKE_CANDLE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("artichoke_banner", createBannerBlock(ElsDyeModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("artichoke_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.ARTICHOKE, (BannerBlock) ElsDyeModBlocks.ARTICHOKE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="ARTICHOKE - Special">
    public static final Block THISTLE_FLOWER = registerBlock("thistle_flower", createFlowerBlock(ElsDyeModEffects.THORNS, 600)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block POTTED_THISTLE_FLOWER = registerBlockWithoutBlockItem("potted_thistle_flower", createFlowerPotBlock(THISTLE_FLOWER)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_MUSHROOM = registerBlock("waxcap_mushroom", createMushroomPlantBlock(MapColor.DARK_GREEN, ElsDyeModConfiguredFeatures.HUGE_WAXCAP_MUSHROOM)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block POTTED_WAXCAP_MUSHROOM = registerBlockWithoutBlockItem("potted_waxcap_mushroom", createFlowerPotBlock(WAXCAP_MUSHROOM)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block HANGING_WAXCAP_WAX = registerBlockWithoutBlockItem("hanging_waxcap_wax", new HangingWaxcapWaxBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).noCollision().breakInstantly().sounds(BlockSoundGroup.SLIME).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY).burnable()));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(HANGING_WAXCAP_WAX); }
    public static final Block WAXCAP_WAX_BLOCK = registerBlock("waxcap_wax_block", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.HONEY).strength(0.5f).burnable())/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_GILL_SLAB = registerBlock("waxcap_gill_slab", new DiagonalSlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.SLIME).strength(0.5f).burnable().luminance(state -> 5))/*, ModItemGroups.ARTICHOKE*/);
        static { SLABS.add(WAXCAP_GILL_SLAB); }
    public static final Block WAXCAP_GILLS = registerBlock("waxcap_gills", new DiagonalBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.SLIME).breakInstantly().burnable().luminance(state -> 5))/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_STEM_BLOCK = registerBlock("waxcap_stem_block", new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(0.2f).sounds(BlockSoundGroup.WOOD).burnable())/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_CAP_BLOCK = registerBlock("waxcap_cap_block", new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(0.2f).sounds(BlockSoundGroup.WOOD).burnable())/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CROP = registerBlockWithoutBlockItem("artichoke_crop", new ArtichokeCropBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(ARTICHOKE_CROP); }
    //</editor-fold>

    //<editor-fold desc ="FUCHSIA - Template">
    public static final Block FUCHSIA_WOOL = registerBlock("fuchsia_wool", createWoolBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CARPET = registerBlock("fuchsia_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.FUCHSIA, FUCHSIA_WOOL)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta", createTerracottaBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CONCRETE = registerBlock("fuchsia_concrete", createConcreteBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CONCRETE_POWDER = registerBlock("fuchsia_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.FUCHSIA, FUCHSIA_CONCRETE)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_GLAZED_TERRACOTTA = registerBlock("fuchsia_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_STAINED_GLASS_PANE = registerBlock("fuchsia_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.FUCHSIA, FUCHSIA_STAINED_GLASS)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_SHULKER_BOX = registerBlockWithoutBlockItem("fuchsia_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_BED = registerBlockWithoutBlockItem("fuchsia_bed", createBedBlock(ElsDyeModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_CANDLE = registerBlock("fuchsia_candle", createCandleBlock(ElsDyeModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CANDLE_CAKE = registerBlockWithoutBlockItem("fuchsia_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.FUCHSIA, FUCHSIA_CANDLE)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_BANNER = registerBlockWithoutBlockItem("fuchsia_banner", createBannerBlock(ElsDyeModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_WALL_BANNER = registerBlockWithoutBlockItem("fuchsia_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.FUCHSIA, (BannerBlock) ElsDyeModBlocks.FUCHSIA_BANNER));
    //</editor-fold>
    //<editor-fold desc ="FUCHSIA - Special">
    //</editor-fold>
    //<editor-fold desc ="VERMILION - Template">
    public static final Block VERMILION_WOOL = registerBlock("vermilion_wool", createWoolBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CARPET = registerBlock("vermilion_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.VERMILION, VERMILION_WOOL)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta", createTerracottaBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CONCRETE = registerBlock("vermilion_concrete", createConcreteBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CONCRETE_POWDER = registerBlock("vermilion_concrete_powder",createConcretePowderBlock(ElsDyeModDyeColor.VERMILION, VERMILION_CONCRETE)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_GLAZED_TERRACOTTA = registerBlock("vermilion_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_STAINED_GLASS_PANE = registerBlock("vermilion_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.VERMILION, VERMILION_STAINED_GLASS)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_SHULKER_BOX = registerBlockWithoutBlockItem("vermilion_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.VERMILION));
    public static final Block VERMILION_BED = registerBlockWithoutBlockItem("vermilion_bed", createBedBlock(ElsDyeModDyeColor.VERMILION));
    public static final Block VERMILION_CANDLE = registerBlock("vermilion_candle", createCandleBlock(ElsDyeModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CANDLE_CAKE = registerBlockWithoutBlockItem("vermilion_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.VERMILION, VERMILION_CANDLE)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_BANNER = registerBlockWithoutBlockItem("vermilion_banner", createBannerBlock(ElsDyeModDyeColor.VERMILION));
    public static final Block VERMILION_WALL_BANNER = registerBlockWithoutBlockItem("vermilion_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.VERMILION, (BannerBlock) ElsDyeModBlocks.VERMILION_BANNER));
    //</editor-fold>
    //<editor-fold desc ="VERMILION - Special">
    //</editor-fold>
    //<editor-fold desc ="SHAMROCK - Template">
    public static final Block SHAMROCK_WOOL = registerBlock("shamrock_wool", createWoolBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CARPET = registerBlock("shamrock_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.SHAMROCK, SHAMROCK_WOOL)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_TERRACOTTA = registerBlock("shamrock_terracotta", createTerracottaBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CONCRETE = registerBlock("shamrock_concrete", createConcreteBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CONCRETE_POWDER = registerBlock("shamrock_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.SHAMROCK, SHAMROCK_CONCRETE)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_GLAZED_TERRACOTTA = registerBlock("shamrock_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_STAINED_GLASS = registerBlock("shamrock_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_STAINED_GLASS_PANE = registerBlock("shamrock_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.SHAMROCK, SHAMROCK_STAINED_GLASS)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_SHULKER_BOX = registerBlockWithoutBlockItem("shamrock_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_BED = registerBlockWithoutBlockItem("shamrock_bed", createBedBlock(ElsDyeModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_CANDLE = registerBlock("shamrock_candle", createCandleBlock(ElsDyeModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CANDLE_CAKE = registerBlockWithoutBlockItem("shamrock_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.SHAMROCK, SHAMROCK_CANDLE)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_BANNER = registerBlockWithoutBlockItem("shamrock_banner", createBannerBlock(ElsDyeModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_WALL_BANNER = registerBlockWithoutBlockItem("shamrock_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.SHAMROCK, (BannerBlock) ElsDyeModBlocks.SHAMROCK_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SHAMROCK - Special">
    //</editor-fold>
    //<editor-fold desc ="INDIGO - Template">
    public static final Block INDIGO_WOOL = registerBlock("indigo_wool", createWoolBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CARPET = registerBlock("indigo_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.INDIGO, INDIGO_WOOL)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_TERRACOTTA = registerBlock("indigo_terracotta", createTerracottaBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CONCRETE = registerBlock("indigo_concrete", createConcreteBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CONCRETE_POWDER = registerBlock("indigo_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.INDIGO, INDIGO_CONCRETE)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_GLAZED_TERRACOTTA = registerBlock("indigo_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_STAINED_GLASS_PANE = registerBlock("indigo_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.INDIGO, INDIGO_STAINED_GLASS)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_SHULKER_BOX = registerBlockWithoutBlockItem("indigo_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.INDIGO));
    public static final Block INDIGO_BED = registerBlockWithoutBlockItem("indigo_bed", createBedBlock(ElsDyeModDyeColor.INDIGO));
    public static final Block INDIGO_CANDLE = registerBlock("indigo_candle", createCandleBlock(ElsDyeModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CANDLE_CAKE = registerBlockWithoutBlockItem("indigo_candle_cake", createCandleCakeBlock( ElsDyeModDyeColor.INDIGO, INDIGO_CANDLE)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_BANNER = registerBlockWithoutBlockItem("indigo_banner", createBannerBlock(ElsDyeModDyeColor.INDIGO));
    public static final Block INDIGO_WALL_BANNER = registerBlockWithoutBlockItem("indigo_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.INDIGO, (BannerBlock) ElsDyeModBlocks.INDIGO_BANNER));
    //</editor-fold>
    //<editor-fold desc ="INDIGO - Special">
    //</editor-fold>

    //<editor-fold desc ="BANANA - Template">
    public static final Block BANANA_WOOL = registerBlock("banana_wool", createWoolBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CARPET = registerBlock("banana_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.BANANA, BANANA_WOOL)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_TERRACOTTA = registerBlock("banana_terracotta", createTerracottaBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CONCRETE = registerBlock("banana_concrete", createConcreteBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CONCRETE_POWDER = registerBlock("banana_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.BANANA, BANANA_CONCRETE)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_GLAZED_TERRACOTTA = registerBlock("banana_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_STAINED_GLASS = registerBlock("banana_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_STAINED_GLASS_PANE = registerBlock("banana_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.BANANA, BANANA_STAINED_GLASS)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_SHULKER_BOX = registerBlockWithoutBlockItem("banana_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.BANANA));
    public static final Block BANANA_BED = registerBlockWithoutBlockItem("banana_bed", createBedBlock(ElsDyeModDyeColor.BANANA));
    public static final Block BANANA_CANDLE = registerBlock("banana_candle", createCandleBlock(ElsDyeModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CANDLE_CAKE = registerBlockWithoutBlockItem("banana_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.BANANA, BANANA_CANDLE)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_BANNER = registerBlockWithoutBlockItem("banana_banner", createBannerBlock(ElsDyeModDyeColor.BANANA));
    public static final Block BANANA_WALL_BANNER = registerBlockWithoutBlockItem("banana_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.BANANA, (BannerBlock) ElsDyeModBlocks.BANANA_BANNER));
    //</editor-fold>
    //<editor-fold desc ="BANANA - Special">
    //</editor-fold>
    //<editor-fold desc ="CERULEAN - Template">
    public static final Block CERULEAN_WOOL = registerBlock("cerulean_wool", createWoolBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CARPET = registerBlock("cerulean_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.CERULEAN, CERULEAN_WOOL)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_TERRACOTTA = registerBlock("cerulean_terracotta", createTerracottaBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CONCRETE = registerBlock("cerulean_concrete", createConcreteBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CONCRETE_POWDER = registerBlock("cerulean_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.CERULEAN, CERULEAN_CONCRETE)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_GLAZED_TERRACOTTA = registerBlock("cerulean_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_STAINED_GLASS = registerBlock("cerulean_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_STAINED_GLASS_PANE = registerBlock("cerulean_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.CERULEAN, CERULEAN_STAINED_GLASS)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_SHULKER_BOX = registerBlockWithoutBlockItem("cerulean_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.CERULEAN));
    public static final Block CERULEAN_BED = registerBlockWithoutBlockItem("cerulean_bed", createBedBlock(ElsDyeModDyeColor.CERULEAN));
    public static final Block CERULEAN_CANDLE = registerBlock("cerulean_candle", createCandleBlock(ElsDyeModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CANDLE_CAKE = registerBlockWithoutBlockItem("cerulean_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.CERULEAN, CERULEAN_CANDLE)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_BANNER = registerBlockWithoutBlockItem("cerulean_banner", createBannerBlock(ElsDyeModDyeColor.CERULEAN));
    public static final Block CERULEAN_WALL_BANNER = registerBlockWithoutBlockItem("cerulean_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.CERULEAN, (BannerBlock) ElsDyeModBlocks.CERULEAN_BANNER));
    //</editor-fold>
    //<editor-fold desc ="CERULEAN - Special">
    //</editor-fold>
    //<editor-fold desc ="ACORN - Template">
    public static final Block ACORN_WOOL = registerBlock("acorn_wool", createWoolBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CARPET = registerBlock("acorn_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.ACORN, ACORN_WOOL)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_TERRACOTTA = registerBlock("acorn_terracotta", createTerracottaBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CONCRETE = registerBlock("acorn_concrete", createConcreteBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CONCRETE_POWDER = registerBlock("acorn_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.ACORN, ACORN_CONCRETE)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_GLAZED_TERRACOTTA = registerBlock("acorn_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_STAINED_GLASS = registerBlock("acorn_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_STAINED_GLASS_PANE = registerBlock("acorn_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.ACORN, ACORN_STAINED_GLASS)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_SHULKER_BOX = registerBlockWithoutBlockItem("acorn_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.ACORN));
    public static final Block ACORN_BED = registerBlockWithoutBlockItem("acorn_bed", createBedBlock(ElsDyeModDyeColor.ACORN));
    public static final Block ACORN_CANDLE = registerBlock("acorn_candle", createCandleBlock(ElsDyeModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CANDLE_CAKE = registerBlockWithoutBlockItem("acorn_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.ACORN, ACORN_CANDLE)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_BANNER = registerBlockWithoutBlockItem("acorn_banner", createBannerBlock(ElsDyeModDyeColor.ACORN));
    public static final Block ACORN_WALL_BANNER = registerBlockWithoutBlockItem("acorn_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.ACORN, (BannerBlock) ElsDyeModBlocks.ACORN_BANNER));
    //</editor-fold>
    //<editor-fold desc ="ACORN - Special">
    //</editor-fold>
    //<editor-fold desc ="MAUVE - Template">
    public static final Block MAUVE_WOOL = registerBlock("mauve_wool", createWoolBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CARPET = registerBlock("mauve_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.MAUVE, MAUVE_WOOL)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_TERRACOTTA = registerBlock("mauve_terracotta", createTerracottaBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CONCRETE = registerBlock("mauve_concrete", createConcreteBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CONCRETE_POWDER = registerBlock("mauve_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.MAUVE, MAUVE_CONCRETE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_GLAZED_TERRACOTTA = registerBlock("mauve_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_STAINED_GLASS = registerBlock("mauve_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_STAINED_GLASS_PANE = registerBlock("mauve_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.MAUVE, MAUVE_STAINED_GLASS)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_SHULKER_BOX = registerBlockWithoutBlockItem("mauve_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.MAUVE));
    public static final Block MAUVE_BED = registerBlockWithoutBlockItem("mauve_bed", createBedBlock(ElsDyeModDyeColor.MAUVE));
    public static final Block MAUVE_CANDLE = registerBlock("mauve_candle", createCandleBlock(ElsDyeModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CANDLE_CAKE = registerBlockWithoutBlockItem("mauve_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.MAUVE, MAUVE_CANDLE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_BANNER = registerBlockWithoutBlockItem("mauve_banner", createBannerBlock(ElsDyeModDyeColor.MAUVE));
    public static final Block MAUVE_WALL_BANNER = registerBlockWithoutBlockItem("mauve_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.MAUVE, (BannerBlock) ElsDyeModBlocks.MAUVE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MAUVE - Special">
    //</editor-fold>

    //<editor-fold desc ="MAROON - Template">
    public static final Block MAROON_WOOL = registerBlock("maroon_wool", createWoolBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CARPET = registerBlock("maroon_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.MAROON, MAROON_WOOL)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_TERRACOTTA = registerBlock("maroon_terracotta", createTerracottaBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CONCRETE = registerBlock("maroon_concrete", createConcreteBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CONCRETE_POWDER = registerBlock("maroon_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.MAROON, MAROON_CONCRETE)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_GLAZED_TERRACOTTA = registerBlock("maroon_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_STAINED_GLASS = registerBlock("maroon_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_STAINED_GLASS_PANE = registerBlock("maroon_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.MAROON, MAROON_STAINED_GLASS)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_SHULKER_BOX = registerBlockWithoutBlockItem("maroon_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.MAROON));
    public static final Block MAROON_BED = registerBlockWithoutBlockItem("maroon_bed", createBedBlock(ElsDyeModDyeColor.MAROON));
    public static final Block MAROON_CANDLE = registerBlock("maroon_candle", createCandleBlock(ElsDyeModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CANDLE_CAKE = registerBlockWithoutBlockItem("maroon_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.MAROON, MAROON_CANDLE)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_BANNER = registerBlockWithoutBlockItem("maroon_banner", createBannerBlock(ElsDyeModDyeColor.MAROON));
    public static final Block MAROON_WALL_BANNER = registerBlockWithoutBlockItem("maroon_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.MAROON, (BannerBlock) ElsDyeModBlocks.MAROON_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MAROON - Special">
    public static final Block COCHINEAL_BEETLES = registerBlockWithoutBlockItem("cochineal_beetles", new CochinealBeetlesBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().noBlockBreakParticles().sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));
    static { RENDER_LAYER_CUTOUT.add(COCHINEAL_BEETLES); }
    public static final Block CACTUS_FEED = registerBlock("cactus_feed", new Block(FabricBlockSettings.create().sounds(BlockSoundGroup.ROOTED_DIRT).hardness(0.5f).mapColor(MapColor.GREEN)));
    public static final Block MADDER_ROOTED_DIRT = registerBlock("madder_rooted_dirt", new Block(FabricBlockSettings.copyOf(Blocks.ROOTED_DIRT)));
    public static final Block MADDER_ROOTED_GRASS_BLOCK = registerBlock("madder_rooted_grass_block", new MadderRootedGrassBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK)));
    static { RENDER_LAYER_CUTOUT.add(MADDER_ROOTED_GRASS_BLOCK); }
    static { HAS_GRASS_COLOR_PROVIDER.add(MADDER_ROOTED_GRASS_BLOCK); }

    public static final Block CINNABAR_BLOCK = registerBlock("cinnabar_block", new Block(FabricBlockSettings.copyOf(Blocks.STONE).strength(3.5f).sounds(BlockSoundGroup.TUFF).mapColor(MapColor.RED)));
    public static final Block BUDDING_CINNABAR = registerBlock("budding_cinnabar", new BuddingCinnabarBlock(FabricBlockSettings.copyOf(CINNABAR_BLOCK).ticksRandomly()));
    public static final Block CINNABAR_PILLAR = registerBlock("cinnabar_pillar", new PillarBlock(FabricBlockSettings.copyOf(CINNABAR_BLOCK)));
    public static final Block SMALL_CINNABAR_BUD = registerBlock("small_cinnabar_bud", new CinnabarClusterBlock(3, 4, FabricBlockSettings.copyOf(CINNABAR_BLOCK)));
    static { RENDER_LAYER_CUTOUT.add(SMALL_CINNABAR_BUD); }

    public static final Block MEDIUM_CINNABAR_BUD = registerBlock("medium_cinnabar_bud", new CinnabarClusterBlock(4, 3, FabricBlockSettings.copyOf(CINNABAR_BLOCK)));
    static { RENDER_LAYER_CUTOUT.add(MEDIUM_CINNABAR_BUD); }

    public static final Block LARGE_CINNABAR_BUD = registerBlock("large_cinnabar_bud", new CinnabarClusterBlock(5, 3, FabricBlockSettings.copyOf(CINNABAR_BLOCK)));
    static { RENDER_LAYER_CUTOUT.add(LARGE_CINNABAR_BUD); }

    public static final Block CINNABAR_CLUSTER = registerBlock("cinnabar_cluster", new CinnabarClusterBlock(7, 3, FabricBlockSettings.copyOf(CINNABAR_BLOCK)));
    static { RENDER_LAYER_CUTOUT.add(CINNABAR_CLUSTER); }

    public static final Block CINNAMON_BRICKS = registerBlock("cinnamon_bricks", new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS).mapColor(MapColor.DARK_RED)));
    public static final Block CINNAMON_BRICK_STAIRS = registerBlock("cinnamon_brick_stairs", createStairsBlock(ElsDyeModBlocks.CINNAMON_BRICKS, FabricBlockSettings.copyOf(CINNAMON_BRICKS)));
    public static final Block CINNAMON_BRICK_SLAB = registerBlock("cinnamon_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(CINNAMON_BRICKS)));
    public static final Block CINNAMON_BRICK_WALL = registerBlock("cinnamon_brick_wall", createWallBlock(FabricBlockSettings.copyOf(CINNAMON_BRICKS)));
    public static final Block CRACKED_CINNAMON_BRICKS = registerBlock("cracked_cinnamon_bricks", new Block(FabricBlockSettings.copyOf(CINNAMON_BRICKS)));
    public static final Block MADDER = registerBlock("madder", createMadderFlowerBlock(StatusEffects.NAUSEA, 15, ElsDyeModConfiguredFeatures.PATCH_BONEMEAL_MADDER));
    public static WoodSet MADDER_WOODSET = new WoodSet(
            new Identifier(ElsDyeMod.MOD_ID, "madder"),
            MapColor.DARK_RED,
            MapColor.DARK_RED,
            MapColor.DARK_RED,
            ElsDyeModBoatEntity.ModBoat.MADDER,
            WoodSet.WoodPreset.NO_TREE,
            false,
            new WintergreenSaplingGenerator(),
            false,
            false
    );
    //</editor-fold>
    //<editor-fold desc ="GRAPE - Template">
    public static final Block GRAPE_WOOL = registerBlock("grape_wool", createWoolBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CARPET = registerBlock("grape_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.GRAPE, GRAPE_WOOL)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_TERRACOTTA = registerBlock("grape_terracotta", createTerracottaBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CONCRETE = registerBlock("grape_concrete", createConcreteBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CONCRETE_POWDER = registerBlock("grape_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.GRAPE, GRAPE_CONCRETE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_GLAZED_TERRACOTTA = registerBlock("grape_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_STAINED_GLASS = registerBlock("grape_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_STAINED_GLASS_PANE = registerBlock("grape_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.GRAPE, GRAPE_STAINED_GLASS)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_SHULKER_BOX = registerBlockWithoutBlockItem("grape_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.GRAPE));
    public static final Block GRAPE_BED = registerBlockWithoutBlockItem("grape_bed", createBedBlock(ElsDyeModDyeColor.GRAPE));
    public static final Block GRAPE_CANDLE = registerBlock("grape_candle", createCandleBlock(ElsDyeModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CANDLE_CAKE = registerBlockWithoutBlockItem("grape_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.GRAPE, GRAPE_CANDLE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_BANNER = registerBlockWithoutBlockItem("grape_banner", createBannerBlock(ElsDyeModDyeColor.GRAPE));
    public static final Block GRAPE_WALL_BANNER = registerBlockWithoutBlockItem("grape_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.GRAPE, (BannerBlock) ElsDyeModBlocks.GRAPE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="GRAPE - Special">
    //</editor-fold>
    //<editor-fold desc ="NAVY - Template">
    public static final Block NAVY_WOOL = registerBlock("navy_wool", createWoolBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CARPET = registerBlock("navy_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.NAVY, NAVY_WOOL)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_TERRACOTTA = registerBlock("navy_terracotta", createTerracottaBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CONCRETE = registerBlock("navy_concrete", createConcreteBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CONCRETE_POWDER = registerBlock("navy_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.NAVY, NAVY_CONCRETE)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_GLAZED_TERRACOTTA = registerBlock("navy_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_STAINED_GLASS = registerBlock("navy_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_STAINED_GLASS_PANE = registerBlock("navy_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.NAVY, NAVY_STAINED_GLASS)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_SHULKER_BOX = registerBlockWithoutBlockItem("navy_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.NAVY));
    public static final Block NAVY_BED = registerBlockWithoutBlockItem("navy_bed", createBedBlock(ElsDyeModDyeColor.NAVY));
    public static final Block NAVY_CANDLE = registerBlock("navy_candle", createCandleBlock(ElsDyeModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CANDLE_CAKE = registerBlockWithoutBlockItem("navy_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.NAVY, NAVY_CANDLE)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_BANNER = registerBlockWithoutBlockItem("navy_banner", createBannerBlock(ElsDyeModDyeColor.NAVY));
    public static final Block NAVY_WALL_BANNER = registerBlockWithoutBlockItem("navy_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.NAVY, (BannerBlock) ElsDyeModBlocks.NAVY_BANNER));
    //</editor-fold>
    //<editor-fold desc ="NAVY - Special">
    //</editor-fold>
    //<editor-fold desc ="SAP - Template">
    public static final Block SAP_WOOL = registerBlock("sap_wool", createWoolBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CARPET = registerBlock("sap_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.SAP, SAP_WOOL)/*, ModItemGroups.SAP*/);
    public static final Block SAP_TERRACOTTA = registerBlock("sap_terracotta", createTerracottaBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CONCRETE = registerBlock("sap_concrete", createConcreteBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CONCRETE_POWDER = registerBlock("sap_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.SAP, SAP_CONCRETE)/*, ModItemGroups.SAP*/);
    public static final Block SAP_GLAZED_TERRACOTTA = registerBlock("sap_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_STAINED_GLASS = registerBlock("sap_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_STAINED_GLASS_PANE = registerBlock("sap_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.SAP, SAP_STAINED_GLASS)/*, ModItemGroups.SAP*/);
    public static final Block SAP_SHULKER_BOX = registerBlockWithoutBlockItem("sap_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.SAP));
    public static final Block SAP_BED = registerBlockWithoutBlockItem("sap_bed", createBedBlock(ElsDyeModDyeColor.SAP));
    public static final Block SAP_CANDLE = registerBlock("sap_candle", createCandleBlock(ElsDyeModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CANDLE_CAKE = registerBlockWithoutBlockItem("sap_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.SAP, SAP_CANDLE)/*, ModItemGroups.SAP*/);
    public static final Block SAP_BANNER = registerBlockWithoutBlockItem("sap_banner", createBannerBlock(ElsDyeModDyeColor.SAP));
    public static final Block SAP_WALL_BANNER = registerBlockWithoutBlockItem("sap_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.SAP, (BannerBlock) ElsDyeModBlocks.SAP_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SAP - Special">
    //</editor-fold>

    //<editor-fold desc ="AMBER - Template">
    public static final Block AMBER_WOOL = registerBlock("amber_wool", createWoolBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CARPET = registerBlock("amber_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.AMBER, AMBER_WOOL)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_TERRACOTTA = registerBlock("amber_terracotta", createTerracottaBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CONCRETE = registerBlock("amber_concrete", createConcreteBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CONCRETE_POWDER = registerBlock("amber_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.AMBER, AMBER_CONCRETE)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_GLAZED_TERRACOTTA = registerBlock("amber_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_STAINED_GLASS = registerBlock("amber_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_STAINED_GLASS_PANE = registerBlock("amber_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.AMBER, AMBER_STAINED_GLASS)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_SHULKER_BOX = registerBlockWithoutBlockItem("amber_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.AMBER));
    public static final Block AMBER_BED = registerBlockWithoutBlockItem("amber_bed", createBedBlock(ElsDyeModDyeColor.AMBER));
    public static final Block AMBER_CANDLE = registerBlock("amber_candle", createCandleBlock(ElsDyeModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CANDLE_CAKE = registerBlockWithoutBlockItem("amber_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.AMBER, AMBER_CANDLE)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_BANNER = registerBlockWithoutBlockItem("amber_banner", createBannerBlock(ElsDyeModDyeColor.AMBER));
    public static final Block AMBER_WALL_BANNER = registerBlockWithoutBlockItem("amber_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.AMBER, (BannerBlock) ElsDyeModBlocks.AMBER_BANNER));
    //</editor-fold>
    //<editor-fold desc ="AMBER - Special">
    public static final Block AMBER_BLOCK = registerBlock("amber_block", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.GOLD)));
    public static final Block AMBER_BRICKS = registerBlock("amber_bricks", new Block(FabricBlockSettings.copyOf(ElsDyeModBlocks.AMBER_BLOCK)));
    public static final Block AMBER_BRICK_STAIRS = registerBlock("amber_brick_stairs", createStairsBlock(AMBER_BRICKS, FabricBlockSettings.copyOf(ElsDyeModBlocks.AMBER_BRICKS)));
    public static final Block AMBER_BRICK_SLAB = registerBlock("amber_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(ElsDyeModBlocks.AMBER_BRICKS)));
    public static final Block AMBER_BRICK_WALL = registerBlock("amber_brick_wall", createWallBlock(FabricBlockSettings.copyOf(ElsDyeModBlocks.AMBER_BRICKS)));
    public static final Block CHISELED_AMBER_BRICKS = registerBlock("chiseled_amber_bricks", new Block(FabricBlockSettings.copyOf(ElsDyeModBlocks.AMBER_BLOCK)));
    public static final Block EMBER = registerBlockWithoutBlockItem("ember", new EmberBlock(FabricBlockSettings.create().mapColor(MapColor.GOLD).breakInstantly().nonOpaque().sounds(BlockSoundGroup.FROGSPAWN).noCollision().luminance(15).replaceable().pistonBehavior(PistonBehavior.DESTROY)));
    static { RENDER_LAYER_TRANSLUCENT.add(EMBER); }
    public static final Block SHIMMERING_SAVANNABUDS_CROP = registerBlockWithoutBlockItem("shimmering_savannabuds_crop", new SavannabudsCropBlock(FabricBlockSettings.create().mapColor(MapColor.EMERALD_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    static { RENDER_LAYER_CUTOUT.add(SHIMMERING_SAVANNABUDS_CROP); }
    public static final Block SHIMMERING_SAVANNABUDS = registerBlock("shimmering_savannabuds", new SavannabudsBlock(FabricBlockSettings.create().mapColor(MapColor.EMERALD_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY).burnable()));
    static { RENDER_LAYER_CUTOUT.add(SHIMMERING_SAVANNABUDS); }

    public static final Block PINEAPPLE = registerBlockWithoutBlockItem("pineapple", new PineappleBlock(FabricBlockSettings.create().strength(1f, 1f).mapColor(MapColor.GOLD).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY).nonOpaque()));
    static { RENDER_LAYER_CUTOUT.add(PINEAPPLE); }
    public static final Block PINEAPPLE_STEM = registerBlockWithoutBlockItem("pineapple_stem", new PineappleStemBlock(FabricBlockSettings.create().mapColor(MapColor.EMERALD_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
    static { RENDER_LAYER_CUTOUT.add(PINEAPPLE_STEM); }

    public static final Block PINEAPPLE_CROWN = registerBlockWithoutBlockItem("pineapple_crown", new PineappleCrownBlock(FabricBlockSettings.create().mapColor(MapColor.GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY)));
    static { RENDER_LAYER_CUTOUT.add(PINEAPPLE_CROWN); }
    //</editor-fold>

    //<editor-fold desc ="SAGE - Template">
    public static final Block SAGE_WOOL = registerBlock("sage_wool", createWoolBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CARPET = registerBlock("sage_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.SAGE, SAGE_WOOL)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_TERRACOTTA = registerBlock("sage_terracotta", createTerracottaBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CONCRETE = registerBlock("sage_concrete", createConcreteBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CONCRETE_POWDER = registerBlock("sage_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.SAGE, SAGE_CONCRETE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_GLAZED_TERRACOTTA = registerBlock("sage_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_STAINED_GLASS = registerBlock("sage_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_STAINED_GLASS_PANE = registerBlock("sage_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.SAGE, SAGE_STAINED_GLASS)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_SHULKER_BOX = registerBlockWithoutBlockItem("sage_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.SAGE));
    public static final Block SAGE_BED = registerBlockWithoutBlockItem("sage_bed", createBedBlock(ElsDyeModDyeColor.SAGE));
    public static final Block SAGE_CANDLE = registerBlock("sage_candle", createCandleBlock(ElsDyeModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CANDLE_CAKE = registerBlockWithoutBlockItem("sage_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.SAGE, SAGE_CANDLE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_BANNER = registerBlockWithoutBlockItem("sage_banner", createBannerBlock(ElsDyeModDyeColor.SAGE));
    public static final Block SAGE_WALL_BANNER = registerBlockWithoutBlockItem("sage_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.SAGE, (BannerBlock) ElsDyeModBlocks.SAGE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SAGE - Special">
    public static final Block ALOE = registerBlock("aloe", new PlantBlock(FabricBlockSettings.copyOf(Blocks.FERN).mapColor(MapColor.LICHEN_GREEN)));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(ALOE);}
    public static final Block POTTED_ALOE = registerBlockWithoutBlockItem("potted_aloe", createFlowerPotBlock(ElsDyeModBlocks.ALOE));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(POTTED_ALOE);}
    //</editor-fold>

    //<editor-fold desc ="VELVET - Template">
    public static final Block VELVET_WOOL = registerBlock("velvet_wool", createWoolBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CARPET = registerBlock("velvet_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.VELVET, VELVET_WOOL)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_TERRACOTTA = registerBlock("velvet_terracotta", createTerracottaBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CONCRETE = registerBlock("velvet_concrete", createConcreteBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CONCRETE_POWDER = registerBlock("velvet_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.VELVET, VELVET_CONCRETE)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_GLAZED_TERRACOTTA = registerBlock("velvet_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_STAINED_GLASS = registerBlock("velvet_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_STAINED_GLASS_PANE = registerBlock("velvet_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.VELVET, VELVET_STAINED_GLASS)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_SHULKER_BOX = registerBlockWithoutBlockItem("velvet_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.VELVET));
    public static final Block VELVET_BED = registerBlockWithoutBlockItem("velvet_bed", createBedBlock(ElsDyeModDyeColor.VELVET));
    public static final Block VELVET_CANDLE = registerBlock("velvet_candle", createCandleBlock(ElsDyeModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CANDLE_CAKE = registerBlockWithoutBlockItem("velvet_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.VELVET, VELVET_CANDLE)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_BANNER = registerBlockWithoutBlockItem("velvet_banner", createBannerBlock(ElsDyeModDyeColor.VELVET));
    public static final Block VELVET_WALL_BANNER = registerBlockWithoutBlockItem("velvet_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.VELVET, (BannerBlock) ElsDyeModBlocks.VELVET_BANNER));
    //</editor-fold>
    //<editor-fold desc ="VELVET - Special">

    public static final Block CORDYLINE = registerBlock("cordyline", new CordylineBlock(FabricBlockSettings.copyOf(Blocks.GRASS).mapColor(MapColor.DARK_DULL_PINK)));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(CORDYLINE);}
    public static final Block POTTED_CORDYLINE = registerBlockWithoutBlockItem("potted_cordyline", createFlowerPotBlock(ElsDyeModBlocks.CORDYLINE));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(POTTED_CORDYLINE);}
    public static final Block TALL_CORDYLINE = registerBlock("tall_cordyline", new TallCordylineBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS).mapColor(MapColor.DARK_DULL_PINK)));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(TALL_CORDYLINE);}
    public static final Block PLUM_CORDYLINE = registerBlock("plum_cordyline", new CordylineBlock(FabricBlockSettings.copyOf(Blocks.GRASS).mapColor(MapColor.DARK_CRIMSON)));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(PLUM_CORDYLINE);}
    public static final Block POTTED_PLUM_CORDYLINE = registerBlockWithoutBlockItem("potted_plum_cordyline", createFlowerPotBlock(ElsDyeModBlocks.PLUM_CORDYLINE));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(POTTED_PLUM_CORDYLINE);}
    public static final Block TALL_PLUM_CORDYLINE = registerBlock("tall_plum_cordyline", new TallCordylineBlock(FabricBlockSettings.copyOf(Blocks.GRASS).mapColor(MapColor.DARK_CRIMSON)));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(TALL_PLUM_CORDYLINE);}
    public static final Block STRAWBERRY_PLANT = registerBlockWithoutBlockItem("strawberry_plant", new StrawberryPlantBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS).mapColor(MapColor.DARK_GREEN).ticksRandomly()));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(STRAWBERRY_PLANT);}

    public static final Block POKEWEED = registerBlockWithoutBlockItem("pokeweed", new PlantBlock(FabricBlockSettings.copyOf(Blocks.POPPY)) {
        @Override
        public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
            return Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
        }
    });

    static {RENDER_LAYER_CUTOUT_MIPPED.add(POKEWEED);}
    public static final Block POTTED_POKEWEED = registerBlockWithoutBlockItem("potted_pokeweed", createFlowerPotBlock(ElsDyeModBlocks.POKEWEED));
    static {RENDER_LAYER_CUTOUT_MIPPED.add(POTTED_POKEWEED);}

    public static final Block VELVET_CAKE = registerBlock("velvet_cake", new VelvetCakeBlock(FabricBlockSettings.copyOf(Blocks.CAKE).mapColor(MapColor.SPRUCE_BROWN)));
    //</editor-fold>

    //<editor-fold desc ="MOLD - Template">
    public static final Block MOLD_WOOL = registerBlock("mold_wool", createWoolBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CARPET = registerBlock("mold_carpet", createDyedCarpetBlock(ElsDyeModDyeColor.MOLD, MOLD_WOOL)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_TERRACOTTA = registerBlock("mold_terracotta", createTerracottaBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CONCRETE = registerBlock("mold_concrete", createConcreteBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CONCRETE_POWDER = registerBlock("mold_concrete_powder", createConcretePowderBlock(ElsDyeModDyeColor.MOLD, MOLD_CONCRETE)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_GLAZED_TERRACOTTA = registerBlock("mold_glazed_terracotta", createGlazedTerracottaBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_STAINED_GLASS = registerBlock("mold_stained_glass", createStainedGlassBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_STAINED_GLASS_PANE = registerBlock("mold_stained_glass_pane", createStainedGlassPaneBlock(ElsDyeModDyeColor.MOLD, MOLD_STAINED_GLASS)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_SHULKER_BOX = registerBlockWithoutBlockItem("mold_shulker_box", createShulkerBoxBlock(ElsDyeModDyeColor.MOLD));
    public static final Block MOLD_BED = registerBlockWithoutBlockItem("mold_bed", createBedBlock(ElsDyeModDyeColor.MOLD));
    public static final Block MOLD_CANDLE = registerBlock("mold_candle", createCandleBlock(ElsDyeModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CANDLE_CAKE = registerBlockWithoutBlockItem("mold_candle_cake", createCandleCakeBlock(ElsDyeModDyeColor.MOLD, MOLD_CANDLE)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_BANNER = registerBlockWithoutBlockItem("mold_banner", createBannerBlock(ElsDyeModDyeColor.MOLD));
    public static final Block MOLD_WALL_BANNER = registerBlockWithoutBlockItem("mold_wall_banner", createWallBannerBlock(ElsDyeModDyeColor.MOLD, (BannerBlock) ElsDyeModBlocks.MOLD_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MOLD - Special">
    //</editor-fold>

    //<editor-fold desc ="Decor Additions">
    public static final Block MUCKTUFF = registerBlock("mucktuff", createMucktuffBlock());
    public static final Block CORRUGATED_IRON = registerBlock("corrugated_iron", createCorrugatedIronBlock(null));

    static {
        //With using ModDyeColor it should only loop through the dyes we add, and the vanilla dye colors is only using predefined expected ones- not grabbing from the dye color enum that may have been mixined into
        for (DyeColor color : ElsDyeModUtil.concat(ElsDyeModDyeColor.VALUES, ElsDyeModUtil.VANILLA_DYE_COLORS)) {
            registerBlock(color.getName() + "_corrugated_iron", createCorrugatedIronBlock(color));
        }

        for (DyeColor color : ElsDyeModUtil.concat(ElsDyeModDyeColor.VALUES, ElsDyeModUtil.VANILLA_DYE_COLORS)) {
            registerBlock(color.getName() + "_mucktuff", createDyedMucktuffBlock(color));
        }
    }
    //</editor-fold>



    //<editor-fold desc ="Common Block Creation Functions">
    public static Block createWoolBlock(DyeColor color) {
        Block block = new Block(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)
                .mapColor(color.getMapColor())
                .instrument(Instrument.GUITAR)
                .strength(0.8f)
                .sounds(BlockSoundGroup.WOOL)
                .burnable()
        );
        WOOL_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
        COLOR_FROM_WOOL.put(color, block);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
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
        DYED_TERRACOTTA_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
        COLORED_BLOCKS.add(block);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
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
                .allowsSpawning(ElsDyeModBlocks::never)
                .solidBlock(ElsDyeModBlocks::never)
                .suffocates(ElsDyeModBlocks::never)
                .blockVision(ElsDyeModBlocks::never)
        );
        STAINED_GLASS_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
        COLORED_BLOCKS.add(block);
        RENDER_LAYER_TRANSLUCENT.add(block);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
        COLORED_BLOCKS.add(block);
        RENDER_LAYER_TRANSLUCENT.add(block);
        return block;
    }
    private static ElsDyeModShulkerBoxBlock createShulkerBoxBlock(DyeColor color) {
        AbstractBlock.ContextPredicate contextPredicate = (state, world, pos) -> {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (!(blockEntity instanceof ShulkerBoxBlockEntity shulkerBoxBlockEntity)) {
                return true;
            }
            return shulkerBoxBlockEntity.suffocates();
        };
        ElsDyeModShulkerBoxBlock block = new ElsDyeModShulkerBoxBlock(color, FabricBlockSettings.copyOf(Blocks.SHULKER_BOX)
                //Same settings as the vanilla shulker, I just have reason to not trust .copyOf
                .mapColor(color.getMapColor())
                .solid()
                .strength(2.0f)
                .dynamicBounds()
                .nonOpaque()
                .suffocates(contextPredicate)
                .blockVision(contextPredicate)
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(ElsDyeModBlocks::always)
        );
        SHULKER_BOX_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
        SHULKER_BOX_FROM_DYECOLOR.put(color, block);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
        return block;
    }
    private static BedBlock createBedBlock(DyeColor color) {
        BedBlock block = new BedBlock(color, FabricBlockSettings.create()
                .mapColor(blockState -> blockState.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY)
                .sounds(BlockSoundGroup.WOOD)
                .strength(0.2f)
                .nonOpaque()
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
        );
        BED_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
        COLORED_BLOCKS.add(block);
        FUNCTIONAL_BLOCKS.add(block);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
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
                .luminance(ElsDyeModBlocks.createLightLevelFromBooleanProperty(3, Properties.LIT))
        );
        CANDLE_CAKE_BLOCKS.add(block);
        CANDLE_CAKE_FROM_CANDLE.put(candle, block);
        DYECOLOR_FROM_BLOCK.put(block, color);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
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
        DYECOLOR_FROM_BLOCK.put(block, color);
        return block;
    }
    //</editor-fold>
    //<editor-fold desc ="Special Block Creation Functions">
    public static PillarBlock createCorrugatedIronBlock(@Nullable DyeColor color) {
        MapColor mapColor = MapColor.IRON_GRAY;
        if(color != null) {
            mapColor = color.getMapColor();
        }
        PillarBlock block = new PillarBlock(
                FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
                        .mapColor(mapColor)
                        .sounds(BlockSoundGroup.COPPER)
                        .strength(3) // less than iron block
                        .requiresTool()
        );
        ALL_CORRUGATED_IRON_BLOCKS.add(block);
        if(color != null) {
            DYECOLOR_FROM_BLOCK.put(block, color);
        }
        return block;
    }


    // Decor Additions
    public static Block createDyedMucktuffBlock(DyeColor color) {
        Block block = new Block(mucktuffBlockSettings()
                        .mapColor(color.getMapColor())
        );
        ALL_MUCKTUFF_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put(block, color);
        return block;
    }
    public static Block createMucktuffBlock() {
        Block block = new Block(mucktuffBlockSettings());
        ALL_MUCKTUFF_BLOCKS.add(block);
        return block;
    }
    private static FabricBlockSettings mucktuffBlockSettings() {
        return FabricBlockSettings.copyOf(Blocks.TUFF)
                .mapColor(MapColor.STONE_GRAY)
                .sounds(BlockSoundGroup.TUFF)
                .requiresTool();
    }


    private static FabricBlockSettings createFlowerBlockSettings() {
        return FabricBlockSettings.create() //.copyOf(Blocks.DANDELION)
                .mapColor(MapColor.DARK_GREEN)
                .noCollision().breakInstantly()
                .sounds(BlockSoundGroup.GRASS)
                .offset(AbstractBlock.OffsetType.XZ).
                pistonBehavior(PistonBehavior.DESTROY)
                .burnable();
    }
    public static SpreadableFlowerBlock createSpreadableFlowerBlock(StatusEffect suspiciousStewEffect, int effectDuration, RegistryKey<ConfiguredFeature<?, ?>> featureKey) {
        SpreadableFlowerBlock block = new SpreadableFlowerBlock(suspiciousStewEffect, effectDuration, createFlowerBlockSettings(), featureKey);
        SMALL_FLOWERS.add(block);
        RENDER_LAYER_CUTOUT_MIPPED.add(block);
        return block;
    }
    public static MadderFlowerBlock createMadderFlowerBlock(StatusEffect suspiciousStewEffect, int effectDuration, RegistryKey<ConfiguredFeature<?, ?>> featureKey) {
        MadderFlowerBlock block = new MadderFlowerBlock(suspiciousStewEffect, effectDuration, createFlowerBlockSettings(), featureKey);
        SMALL_FLOWERS.add(block);
        RENDER_LAYER_CUTOUT_MIPPED.add(block);
        return block;
    }
    public static FlowerBlock createFlowerBlock(StatusEffect suspiciousStewEffect, int effectDuration) {
        FlowerBlock block = new FlowerBlock(suspiciousStewEffect, effectDuration, createFlowerBlockSettings());
        SMALL_FLOWERS.add(block);
        RENDER_LAYER_CUTOUT_MIPPED.add(block);
        return block;
    }
    public static FlowerPotBlock createFlowerPotBlock(Block pottedBlock) {
        FlowerPotBlock block = new FlowerPotBlock(pottedBlock, FabricBlockSettings.create()
                .breakInstantly()
                .nonOpaque()
                .pistonBehavior(PistonBehavior.DESTROY)
        );
        FLOWER_POTS.add(block);
        FLOWER_POT_FROM_BLOCK.put(pottedBlock, block);
        RENDER_LAYER_CUTOUT_MIPPED.add(block);
        return block;
    }
    public static MushroomPlantBlock createMushroomPlantBlock(MapColor mapColor, @Nullable RegistryKey<ConfiguredFeature<?, ?>> largeMushroomFeature) { //TODO testme to see if null feature value here actually even works
        MushroomPlantBlock block = new MushroomPlantBlock(FabricBlockSettings.create()
                .mapColor(mapColor)
                .noCollision()
                .ticksRandomly()
                .breakInstantly()
                .sounds(BlockSoundGroup.GRASS).luminance(state -> 1)
                .postProcess(ElsDyeModBlocks::always).pistonBehavior(PistonBehavior.DESTROY),
                largeMushroomFeature
        );
        MUSHROOM_PLANTS.add(block);
        RENDER_LAYER_CUTOUT_MIPPED.add(block);
        return block;
    }
    public static Block createStairsBlock(Block sourceBlock, FabricBlockSettings settings) {
        Block block = new StairsBlock(sourceBlock.getDefaultState(), settings);
        STAIRS.add(block);
        return block;
    }
    public static Block createSlabBlock(FabricBlockSettings settings) {
        Block block = new SlabBlock(settings);
        SLABS.add(block);
        return block;
    }
    public static Block createWallBlock(FabricBlockSettings settings) {
        Block block = new WallBlock(settings);
        WALLS.add(block);
        return block;
    }

    //</editor-fold>
    //<editor-fold desc ="Util">
    public static Item[] toItemArray(Block[] blocks) {
        Item[] items = new Item[blocks.length];
        for(int i = 0; i < items.length; i++) {
            items[i] = blocks[i].asItem();
        }
        return items;
    }
    public static Block[] toBlockArray(ArrayList<Block> al) {
        Block[] array = new Block[al.size()];
        return al.toArray(array);
    }
    public static Item[] toItemArray(ArrayList<Block> input) {
        Item[] array = new Item[input.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = input.get(i).asItem();
        }
        return array;
    }
    public static Block firstMatchOfColor(ArrayList<Block> blocks, DyeColor color) {
        for(Block block : blocks) {
            if(DYECOLOR_FROM_BLOCK.get(block) == color) { return block; }
        }
        return null;
    }
    public static Block[] allMatchesOfColor(ArrayList<Block> blocks, DyeColor color) {
        ArrayList<Block> output = new ArrayList<Block>();
        for(Block block : blocks) {
            if(DYECOLOR_FROM_BLOCK.get(block) == color) { output.add(block); }
        }
        return ElsDyeModBlocks.toBlockArray(output);
    }
    //</editor-fold>
    //<editor-fold desc ="Attribute Util">
    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }
    private static boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }
    private static boolean always(BlockState state, BlockView world, BlockPos pos) { return true; }
    private static ToIntFunction<BlockState> createLightLevelFromBooleanProperty(int litLevel, BooleanProperty property) {
        return state -> state.get(property) ? litLevel : 0;
    }
//    private static ToFloatFunction<BlockState> createStrengthFromIntProperty(int cutoff, int above, int below, IntProperty property) {
//        return state -> state.get(property) >= cutoff ? above : below; // grahhh why doesn't this work?
//    }


    //</editor-fold>
    public static void registerModBlocks() {
        ElsDyeModInit.LOGGER.debug("Registering mod blocks for " + ElsDyeMod.MOD_ID);
    }
}

