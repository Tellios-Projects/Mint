package net.leafenzo.mint.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.sapling.WintergreenSaplingGenerator;
import net.leafenzo.mint.datageneration.ModConfiguredFeatures;
import net.leafenzo.mint.effect.ModEffects;
import net.leafenzo.mint.registration.ModRegistryHelper;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import net.leafenzo.mint.entity.ModBoatEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.ToIntFunction;

import static net.leafenzo.mint.registration.ModRegistryHelper.BlockRegistry.*;

public class ModBlocks {
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
    public static final Block MINT_WOOL = registerBlock("mint_wool", createWoolBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CARPET = registerBlock("mint_carpet", createDyedCarpetBlock(ModDyeColor.MINT, MINT_WOOL)/*, ModItemGroups.MINT*/);
    public static final Block MINT_TERRACOTTA = registerBlock("mint_terracotta", createTerracottaBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CONCRETE = registerBlock("mint_concrete", createConcreteBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CONCRETE_POWDER = registerBlock("mint_concrete_powder", createConcretePowderBlock(ModDyeColor.MINT, MINT_CONCRETE)/*, ModItemGroups.MINT*/);
    public static final Block MINT_GLAZED_TERRACOTTA = registerBlock("mint_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_STAINED_GLASS = registerBlock("mint_stained_glass", createStainedGlassBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_STAINED_GLASS_PANE = registerBlock("mint_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MINT, MINT_STAINED_GLASS)/*, ModItemGroups.MINT*/);
    public static final Block MINT_SHULKER_BOX = registerBlockWithoutBlockItem("mint_shulker_box", createShulkerBoxBlock(ModDyeColor.MINT));
    public static final Block MINT_BED = registerBlockWithoutBlockItem("mint_bed", createBedBlock(ModDyeColor.MINT));
    public static final Block MINT_CANDLE = registerBlock("mint_candle", createCandleBlock(ModDyeColor.MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_CANDLE_CAKE = registerBlockWithoutBlockItem("mint_candle_cake", createCandleCakeBlock(ModDyeColor.MINT, MINT_CANDLE)/*, ModItemGroups.MINT*/);
    public static final Block MINT_BANNER = registerBlockWithoutBlockItem("mint_banner", createBannerBlock(ModDyeColor.MINT));
    public static final Block MINT_WALL_BANNER = registerBlockWithoutBlockItem("mint_wall_banner", createWallBannerBlock(ModDyeColor.MINT, (BannerBlock)ModBlocks.MINT_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MINT - Special">
    public static final Block MINT_CROP = registerBlockWithoutBlockItem("mint_crop", new MintCropBlock(FabricBlockSettings.create().mapColor(MapColor.LICHEN_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(MINT_CROP); }
    public static final Block WILD_MINT = registerBlock("wild_mint", createFlowerBlock(ModEffects.MINT_CHILL, 900)/*, ModItemGroups.MINT*/);
    public static final Block POTTED_WILD_MINT = registerBlockWithoutBlockItem("potted_wild_mint", createFlowerPotBlock((FlowerBlock) WILD_MINT)/*, ModItemGroups.MINT*/);
    public static final Block MINT_SPRIG_BLOCK = registerBlock("mint_sprig_block", new Block(FabricBlockSettings.copyOf(Blocks.ACACIA_LEAVES).mapColor(MapColor.LICHEN_GREEN))/*, ModItemGroups.MINT*/);
        static { RENDER_LAYER_CUTOUT.add(MINT_SPRIG_BLOCK); }
    public static final Block MINT_BRICKS = registerBlock("mint_bricks", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.LICHEN_GREEN))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_SLAB = registerBlock("mint_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_STAIRS = registerBlock("mint_brick_stairs", createStairsBlock(MINT_BRICKS, FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", createWallBlock(FabricBlockSettings.copyOf(MINT_BRICKS))/*, ModItemGroups.MINT*/);
    public static WoodSet WINTERGREEN_WOODSET = new WoodSet(
            new Identifier(Super.MOD_ID, "wintergreen"),
            MapColor.TEAL,
            MapColor.BRIGHT_TEAL,
            MapColor.DARK_GREEN,
            ModBoatEntity.ModBoat.WINTERGREEN,
            WoodSet.WoodPreset.DEFAULT,
            false,
            new WintergreenSaplingGenerator()
    );
    static { HAS_FOLIAGE_COLOR_PROVIDER.remove(WINTERGREEN_WOODSET.getLeaves()); }

    public static final Block WINTERGREEN_CANDY_CANE_BLOCK = registerBlock("wintergreen_candy_cane_block", new PillarBlock(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).strength(0.4f).sounds(BlockSoundGroup.BONE).instrument(Instrument.CHIME)));
    public static final Block WINTERGREEN_CANDY_CANE_BARK = registerBlock("wintergreen_candy_cane_bark", new PillarBlock(FabricBlockSettings.copyOf(WINTERGREEN_CANDY_CANE_BLOCK).instrument(Instrument.CHIME)));
    public static final Block PEPPERMINT_CANDY_CANE_BLOCK = registerBlock("peppermint_candy_cane_block", new PillarBlock(FabricBlockSettings.copyOf(WINTERGREEN_CANDY_CANE_BLOCK).mapColor(MapColor.RAW_IRON_PINK).instrument(Instrument.CHIME)));
    public static final Block PEPPERMINT_CANDY_CANE_BARK = registerBlock("peppermint_candy_cane_bark", new PillarBlock(FabricBlockSettings.copyOf(PEPPERMINT_CANDY_CANE_BLOCK).instrument(Instrument.CHIME)));

    //public static final Block MINT_BRICK_WALL = registerBlock("mint_brick_wall", createWallBlock new WallBlock(FabricBlockSettings.copyOf(MINT_BRICKS/*)), ModItemGroups.MINT*/);
    //</editor-fold>
    //<editor-fold desc ="PEACH - Template">
    public static final Block PEACH_WOOL = registerBlock("peach_wool", createWoolBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CARPET = registerBlock("peach_carpet", createDyedCarpetBlock(ModDyeColor.PEACH, PEACH_WOOL)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_TERRACOTTA = registerBlock("peach_terracotta", createTerracottaBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CONCRETE = registerBlock("peach_concrete", createConcreteBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CONCRETE_POWDER = registerBlock("peach_concrete_powder", createConcretePowderBlock(ModDyeColor.PEACH, PEACH_CONCRETE)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_GLAZED_TERRACOTTA = registerBlock("peach_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_STAINED_GLASS = registerBlock("peach_stained_glass", createStainedGlassBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_STAINED_GLASS_PANE = registerBlock("peach_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PEACH, PEACH_STAINED_GLASS)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_SHULKER_BOX = registerBlockWithoutBlockItem("peach_shulker_box", createShulkerBoxBlock(ModDyeColor.PEACH));
    public static final Block PEACH_BED = registerBlockWithoutBlockItem("peach_bed", createBedBlock(ModDyeColor.PEACH));
    public static final Block PEACH_CANDLE = registerBlock("peach_candle", createCandleBlock(ModDyeColor.PEACH)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_CANDLE_CAKE = registerBlockWithoutBlockItem("peach_candle_cake", createCandleCakeBlock(ModDyeColor.PEACH, PEACH_CANDLE)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_BANNER = registerBlockWithoutBlockItem("peach_banner", createBannerBlock(ModDyeColor.PEACH));
    public static final Block PEACH_WALL_BANNER = registerBlockWithoutBlockItem("peach_wall_banner", createWallBannerBlock(ModDyeColor.PEACH, (BannerBlock)ModBlocks.PEACH_BANNER));
    //</editor-fold>
    //<editor-fold desc ="PEACH - Special">
    public static final Block HYPERICUM = registerBlock("hypericum", createFlowerBlock(StatusEffects.HUNGER, 900)/*, ModItemGroups.PEACH*/); //causes hunger because hypericum berries cause digestion issues irl
    public static final Block POTTED_HYPERICUM = registerBlockWithoutBlockItem("potted_hypericum", createFlowerPotBlock((FlowerBlock) HYPERICUM)/*, ModItemGroups.PEACH*/);
    public static final Block PEACH_TREE = registerBlockWithoutBlockItem("peach_tree", new PeachTreeBlock(FabricBlockSettings.create().noCollision().strength(0.2f).sounds(BlockSoundGroup.GRASS).burnable().pistonBehavior(PistonBehavior.DESTROY).ticksRandomly().offset(AbstractBlock.OffsetType.XZ).nonOpaque().solidBlock(ModBlocks::never)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(PEACH_TREE); }
    public static final Block PEACH_LOG = registerBlock("peach_log", new Block(FabricBlockSettings.create().instrument(Instrument.BASS).strength(2.0f).sounds(BlockSoundGroup.WOOD).burnable().mapColor(MapColor.STONE_GRAY))/*, ModItemGroups.PEACH*/);
        static { LOGS_THAT_BURN.add(PEACH_LOG); }
    public static final Block CORAL_ANEMONE = registerBlock("coral_anemone", new CoralAnemoneBlock(ModConfiguredFeatures.PATCH_BONEMEAL_CORAL_ANEMONE, FabricBlockSettings.create().breakInstantly().sounds(BlockSoundGroup.SLIME).mapColor(MapColor.RAW_IRON_PINK).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance(createLightLevelFromBooleanProperty(6, Properties.WATERLOGGED)))/*, ModItemGroups.PEACH*/);
        static { RENDER_LAYER_CUTOUT_MIPPED.add(CORAL_ANEMONE); }
    public static final Block CORALSOIL = registerBlock("coralsoil", new Block(FabricBlockSettings.copyOf(Blocks.CLAY))/*, ModItemGroups.PEACH*/);
    public static final Block CRACKED_CORALSOIL_BRICKS = registerBlock("cracked_coralsoil_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ModDyeColor.PEACH.getMapColor()).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICKS = registerBlock("coralsoil_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ModDyeColor.PEACH.getMapColor()).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_SLAB = registerBlock("coralsoil_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_STAIRS = registerBlock("coralsoil_brick_stairs", createStairsBlock(CORALSOIL_BRICKS, FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);
    public static final Block CORALSOIL_BRICK_WALL = registerBlock("coralsoil_brick_wall", createWallBlock(FabricBlockSettings.copyOf(CORALSOIL_BRICKS).requiresTool())/*, ModItemGroups.PEACH*/);

    //</editor-fold>
    //<editor-fold desc ="PERIWINKLE - Template">
    public static final Block PERIWINKLE_WOOL = registerBlock("periwinkle_wool", createWoolBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CARPET = registerBlock("periwinkle_carpet", createDyedCarpetBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_WOOL)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_TERRACOTTA = registerBlock("periwinkle_terracotta", createTerracottaBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CONCRETE = registerBlock("periwinkle_concrete", createConcreteBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CONCRETE_POWDER = registerBlock("periwinkle_concrete_powder", createConcretePowderBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CONCRETE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_GLAZED_TERRACOTTA = registerBlock("periwinkle_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_STAINED_GLASS = registerBlock("periwinkle_stained_glass", createStainedGlassBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_STAINED_GLASS_PANE = registerBlock("periwinkle_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_STAINED_GLASS)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_SHULKER_BOX = registerBlockWithoutBlockItem("periwinkle_shulker_box", createShulkerBoxBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_BED = registerBlockWithoutBlockItem("periwinkle_bed", createBedBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_CANDLE = registerBlock("periwinkle_candle", createCandleBlock(ModDyeColor.PERIWINKLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_CANDLE_CAKE = registerBlockWithoutBlockItem("periwinkle_candle_cake", createCandleCakeBlock(ModDyeColor.PERIWINKLE, PERIWINKLE_CANDLE)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_BANNER = registerBlockWithoutBlockItem("periwinkle_banner", createBannerBlock(ModDyeColor.PERIWINKLE));
    public static final Block PERIWINKLE_WALL_BANNER = registerBlockWithoutBlockItem("periwinkle_wall_banner", createWallBannerBlock(ModDyeColor.PERIWINKLE, (BannerBlock)ModBlocks.PERIWINKLE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="PERIWINKLE - Special">
    public static final Block LAVENDER_BRICKS = registerBlock("lavender_bricks", new Block(FabricBlockSettings.copyOf(Blocks.COBBLESTONE).mapColor(ModDyeColor.PERIWINKLE.getMapColor()).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_SLAB = registerBlock("lavender_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_STAIRS = registerBlock("lavender_brick_stairs", createStairsBlock(LAVENDER_BRICKS, FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BRICK_WALL = registerBlock("lavender_brick_wall", createWallBlock(FabricBlockSettings.copyOf(LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICKS = registerBlock("mossy_lavender_bricks", new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_COBBLESTONE).mapColor(ModDyeColor.PERIWINKLE.getMapColor()).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_SLAB = registerBlock("mossy_lavender_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_STAIRS = registerBlock("mossy_lavender_brick_stairs", createStairsBlock(MOSSY_LAVENDER_BRICKS, FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);
    public static final Block MOSSY_LAVENDER_BRICK_WALL = registerBlock("mossy_lavender_brick_wall", createWallBlock(FabricBlockSettings.copyOf(MOSSY_LAVENDER_BRICKS).requiresTool())/*, ModItemGroups.PERIWINKLE*/);

    public static final Block LAVENDER_CLAY = registerBlock("lavender_clay", new Block(FabricBlockSettings.copyOf(Blocks.CLAY))/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_BUSHEL = registerBlock("lavender_bushel", new LavenderBushelBlock(FabricBlockSettings.copyOf(Blocks.HAY_BLOCK))/*, ModItemGroups.PERIWINKLE*/);
    public static final Block PERIWINKLE_PETALS = registerBlock("periwinkle_petals", new FlowerbedBlock(FabricBlockSettings.copyOf(Blocks.PINK_PETALS).mapColor(MapColor.DARK_GREEN))/*, ModItemGroups.PERIWINKLE*/);
        static { RENDER_LAYER_CUTOUT_MIPPED.add(PERIWINKLE_PETALS); }
    public static final Block HIDCOTE_LAVENDER = registerBlock("hidcote_lavender", createSpreadableFlowerBlock(StatusEffects.BAD_OMEN, 600, ModConfiguredFeatures.PATCH_HIDCOTE_LAVENDER)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block POTTED_HIDCOTE_LAVENDER = registerBlockWithoutBlockItem("potted_hidcote_lavender", createFlowerPotBlock(HIDCOTE_LAVENDER)/*, ModItemGroups.PERIWINKLE*/);
    public static final Block LAVENDER_OIL_LANTERN = registerBlock("lavender_oil_lantern", new LanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5f).sounds(BlockSoundGroup.LANTERN).luminance(state -> 15).nonOpaque().pistonBehavior(PistonBehavior.DESTROY))/*, ModItemGroups.PERIWINKLE*/);
        static { RENDER_LAYER_CUTOUT.add(LAVENDER_OIL_LANTERN); }
    //</editor-fold>
    //<editor-fold desc ="ARTICHOKE - Template">
    public static final Block ARTICHOKE_WOOL = registerBlock("artichoke_wool", createWoolBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CARPET = registerBlock("artichoke_carpet", createDyedCarpetBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_WOOL)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_TERRACOTTA = registerBlock("artichoke_terracotta", createTerracottaBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CONCRETE = registerBlock("artichoke_concrete", createConcreteBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CONCRETE_POWDER = registerBlock("artichoke_concrete_powder", createConcretePowderBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CONCRETE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_GLAZED_TERRACOTTA = registerBlock("artichoke_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_STAINED_GLASS = registerBlock("artichoke_stained_glass", createStainedGlassBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_STAINED_GLASS_PANE = registerBlock("artichoke_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_STAINED_GLASS)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_SHULKER_BOX = registerBlockWithoutBlockItem("artichoke_shulker_box", createShulkerBoxBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_BED = registerBlockWithoutBlockItem("artichoke_bed", createBedBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_CANDLE = registerBlock("artichoke_candle", createCandleBlock(ModDyeColor.ARTICHOKE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CANDLE_CAKE = registerBlockWithoutBlockItem("artichoke_candle_cake", createCandleCakeBlock(ModDyeColor.ARTICHOKE, ARTICHOKE_CANDLE)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_BANNER = registerBlockWithoutBlockItem("artichoke_banner", createBannerBlock(ModDyeColor.ARTICHOKE));
    public static final Block ARTICHOKE_WALL_BANNER = registerBlockWithoutBlockItem("artichoke_wall_banner", createWallBannerBlock(ModDyeColor.ARTICHOKE, (BannerBlock)ModBlocks.ARTICHOKE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="ARTICHOKE - Special">
    public static final Block THISTLE_FLOWER = registerBlock("thistle_flower", createFlowerBlock(ModEffects.THORNS, 600)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block POTTED_THISTLE_FLOWER = registerBlockWithoutBlockItem("potted_thistle_flower", createFlowerPotBlock(THISTLE_FLOWER)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_MUSHROOM = registerBlock("waxcap_mushroom", createMushroomPlantBlock(MapColor.DARK_GREEN, ModConfiguredFeatures.HUGE_WAXCAP_MUSHROOM)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block POTTED_WAXCAP_MUSHROOM = registerBlockWithoutBlockItem("potted_waxcap_mushroom", createFlowerPotBlock(WAXCAP_MUSHROOM)/*, ModItemGroups.ARTICHOKE*/);
    public static final Block HANGING_WAXCAP_WAX = registerBlockWithoutBlockItem("hanging_waxcap_wax", new HangingWaxcapWaxBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).noCollision().breakInstantly().sounds(BlockSoundGroup.SLIME).offset(AbstractBlock.OffsetType.XZ).pistonBehavior(PistonBehavior.DESTROY)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(HANGING_WAXCAP_WAX); }
    public static final Block WAXCAP_WAX_BLOCK = registerBlock("waxcap_wax_block", new Block(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.HONEY).strength(0.5f).burnable())/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_GILL_SLAB = registerBlock("waxcap_gill_slab", new DiagonalSlabBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.SLIME).strength(0.5f).burnable().luminance(state -> 5))/*, ModItemGroups.ARTICHOKE*/);
        static { SLABS.add(WAXCAP_GILL_SLAB); }
    public static final Block WAXCAP_GILLS = registerBlock("waxcap_gills", new DiagonalBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_YELLOW).sounds(BlockSoundGroup.SLIME).breakInstantly().burnable().luminance(state -> 5))/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_STEM_BLOCK = registerBlock("waxcap_stem_block", new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.SPRUCE_BROWN).instrument(Instrument.BASS).strength(0.2f).sounds(BlockSoundGroup.WOOD))/*, ModItemGroups.ARTICHOKE*/);
    public static final Block WAXCAP_CAP_BLOCK = registerBlock("waxcap_cap_block", new MushroomBlock(FabricBlockSettings.create().mapColor(MapColor.PALE_GREEN).instrument(Instrument.BASS).strength(0.2f).sounds(BlockSoundGroup.WOOD).burnable())/*, ModItemGroups.ARTICHOKE*/);
    public static final Block ARTICHOKE_CROP = registerBlockWithoutBlockItem("artichoke_crop", new ArtichokeCropBlock(FabricBlockSettings.create().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.DARK_GREEN)));
        static { RENDER_LAYER_CUTOUT_MIPPED.add(ARTICHOKE_CROP); }
    //</editor-fold>

    //<editor-fold desc ="FUCHSIA - Template">
    public static final Block FUCHSIA_WOOL = registerBlock("fuchsia_wool", createWoolBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CARPET = registerBlock("fuchsia_carpet", createDyedCarpetBlock(ModDyeColor.FUCHSIA, FUCHSIA_WOOL)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_TERRACOTTA = registerBlock("fuchsia_terracotta", createTerracottaBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CONCRETE = registerBlock("fuchsia_concrete", createConcreteBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CONCRETE_POWDER = registerBlock("fuchsia_concrete_powder", createConcretePowderBlock(ModDyeColor.FUCHSIA, FUCHSIA_CONCRETE)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_GLAZED_TERRACOTTA = registerBlock("fuchsia_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_STAINED_GLASS = registerBlock("fuchsia_stained_glass", createStainedGlassBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_STAINED_GLASS_PANE = registerBlock("fuchsia_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.FUCHSIA, FUCHSIA_STAINED_GLASS)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_SHULKER_BOX = registerBlockWithoutBlockItem("fuchsia_shulker_box", createShulkerBoxBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_BED = registerBlockWithoutBlockItem("fuchsia_bed", createBedBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_CANDLE = registerBlock("fuchsia_candle", createCandleBlock(ModDyeColor.FUCHSIA)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_CANDLE_CAKE = registerBlockWithoutBlockItem("fuchsia_candle_cake", createCandleCakeBlock(ModDyeColor.FUCHSIA, FUCHSIA_CANDLE)/*, ModItemGroups.FUCHSIA*/);
    public static final Block FUCHSIA_BANNER = registerBlockWithoutBlockItem("fuchsia_banner", createBannerBlock(ModDyeColor.FUCHSIA));
    public static final Block FUCHSIA_WALL_BANNER = registerBlockWithoutBlockItem("fuchsia_wall_banner", createWallBannerBlock(ModDyeColor.FUCHSIA, (BannerBlock)ModBlocks.FUCHSIA_BANNER));
    //</editor-fold>
    //<editor-fold desc ="FUCHSIA - Special">
    //</editor-fold>
    //<editor-fold desc ="VERMILION - Template">
    public static final Block VERMILION_WOOL = registerBlock("vermilion_wool", createWoolBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CARPET = registerBlock("vermilion_carpet", createDyedCarpetBlock(ModDyeColor.VERMILION, VERMILION_WOOL)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_TERRACOTTA = registerBlock("vermilion_terracotta", createTerracottaBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CONCRETE = registerBlock("vermilion_concrete", createConcreteBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CONCRETE_POWDER = registerBlock("vermilion_concrete_powder",createConcretePowderBlock(ModDyeColor.VERMILION, VERMILION_CONCRETE)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_GLAZED_TERRACOTTA = registerBlock("vermilion_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_STAINED_GLASS = registerBlock("vermilion_stained_glass", createStainedGlassBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_STAINED_GLASS_PANE = registerBlock("vermilion_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.VERMILION, VERMILION_STAINED_GLASS)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_SHULKER_BOX = registerBlockWithoutBlockItem("vermilion_shulker_box", createShulkerBoxBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_BED = registerBlockWithoutBlockItem("vermilion_bed", createBedBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_CANDLE = registerBlock("vermilion_candle", createCandleBlock(ModDyeColor.VERMILION)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_CANDLE_CAKE = registerBlockWithoutBlockItem("vermilion_candle_cake", createCandleCakeBlock(ModDyeColor.VERMILION, VERMILION_CANDLE)/*, ModItemGroups.VERMILION*/);
    public static final Block VERMILION_BANNER = registerBlockWithoutBlockItem("vermilion_banner", createBannerBlock(ModDyeColor.VERMILION));
    public static final Block VERMILION_WALL_BANNER = registerBlockWithoutBlockItem("vermilion_wall_banner", createWallBannerBlock(ModDyeColor.VERMILION, (BannerBlock)ModBlocks.VERMILION_BANNER));
    //</editor-fold>
    //<editor-fold desc ="VERMILION - Special">
    //</editor-fold>
    //<editor-fold desc ="SHAMROCK - Template">
    public static final Block SHAMROCK_WOOL = registerBlock("shamrock_wool", createWoolBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CARPET = registerBlock("shamrock_carpet", createDyedCarpetBlock(ModDyeColor.SHAMROCK, SHAMROCK_WOOL)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_TERRACOTTA = registerBlock("shamrock_terracotta", createTerracottaBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CONCRETE = registerBlock("shamrock_concrete", createConcreteBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CONCRETE_POWDER = registerBlock("shamrock_concrete_powder", createConcretePowderBlock(ModDyeColor.SHAMROCK, SHAMROCK_CONCRETE)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_GLAZED_TERRACOTTA = registerBlock("shamrock_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_STAINED_GLASS = registerBlock("shamrock_stained_glass", createStainedGlassBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_STAINED_GLASS_PANE = registerBlock("shamrock_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.SHAMROCK, SHAMROCK_STAINED_GLASS)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_SHULKER_BOX = registerBlockWithoutBlockItem("shamrock_shulker_box", createShulkerBoxBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_BED = registerBlockWithoutBlockItem("shamrock_bed", createBedBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_CANDLE = registerBlock("shamrock_candle", createCandleBlock(ModDyeColor.SHAMROCK)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_CANDLE_CAKE = registerBlockWithoutBlockItem("shamrock_candle_cake", createCandleCakeBlock(ModDyeColor.SHAMROCK, SHAMROCK_CANDLE)/*, ModItemGroups.SHAMROCK*/);
    public static final Block SHAMROCK_BANNER = registerBlockWithoutBlockItem("shamrock_banner", createBannerBlock(ModDyeColor.SHAMROCK));
    public static final Block SHAMROCK_WALL_BANNER = registerBlockWithoutBlockItem("shamrock_wall_banner", createWallBannerBlock(ModDyeColor.SHAMROCK, (BannerBlock)ModBlocks.SHAMROCK_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SHAMROCK - Special">
    //</editor-fold>
    //<editor-fold desc ="INDIGO - Template">
    public static final Block INDIGO_WOOL = registerBlock("indigo_wool", createWoolBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CARPET = registerBlock("indigo_carpet", createDyedCarpetBlock(ModDyeColor.INDIGO, INDIGO_WOOL)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_TERRACOTTA = registerBlock("indigo_terracotta", createTerracottaBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CONCRETE = registerBlock("indigo_concrete", createConcreteBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CONCRETE_POWDER = registerBlock("indigo_concrete_powder", createConcretePowderBlock(ModDyeColor.INDIGO, INDIGO_CONCRETE)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_GLAZED_TERRACOTTA = registerBlock("indigo_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_STAINED_GLASS = registerBlock("indigo_stained_glass", createStainedGlassBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_STAINED_GLASS_PANE = registerBlock("indigo_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.INDIGO, INDIGO_STAINED_GLASS)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_SHULKER_BOX = registerBlockWithoutBlockItem("indigo_shulker_box", createShulkerBoxBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_BED = registerBlockWithoutBlockItem("indigo_bed", createBedBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_CANDLE = registerBlock("indigo_candle", createCandleBlock(ModDyeColor.INDIGO)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_CANDLE_CAKE = registerBlockWithoutBlockItem("indigo_candle_cake", createCandleCakeBlock( ModDyeColor.INDIGO, INDIGO_CANDLE)/*, ModItemGroups.INDIGO*/);
    public static final Block INDIGO_BANNER = registerBlockWithoutBlockItem("indigo_banner", createBannerBlock(ModDyeColor.INDIGO));
    public static final Block INDIGO_WALL_BANNER = registerBlockWithoutBlockItem("indigo_wall_banner", createWallBannerBlock(ModDyeColor.INDIGO, (BannerBlock)ModBlocks.INDIGO_BANNER));
    //</editor-fold>
    //<editor-fold desc ="INDIGO - Special">
    //</editor-fold>

    //<editor-fold desc ="BANANA - Template">
    public static final Block BANANA_WOOL = registerBlock("banana_wool", createWoolBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CARPET = registerBlock("banana_carpet", createDyedCarpetBlock(ModDyeColor.BANANA, BANANA_WOOL)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_TERRACOTTA = registerBlock("banana_terracotta", createTerracottaBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CONCRETE = registerBlock("banana_concrete", createConcreteBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CONCRETE_POWDER = registerBlock("banana_concrete_powder", createConcretePowderBlock(ModDyeColor.BANANA, BANANA_CONCRETE)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_GLAZED_TERRACOTTA = registerBlock("banana_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_STAINED_GLASS = registerBlock("banana_stained_glass", createStainedGlassBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_STAINED_GLASS_PANE = registerBlock("banana_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.BANANA, BANANA_STAINED_GLASS)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_SHULKER_BOX = registerBlockWithoutBlockItem("banana_shulker_box", createShulkerBoxBlock(ModDyeColor.BANANA));
    public static final Block BANANA_BED = registerBlockWithoutBlockItem("banana_bed", createBedBlock(ModDyeColor.BANANA));
    public static final Block BANANA_CANDLE = registerBlock("banana_candle", createCandleBlock(ModDyeColor.BANANA)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_CANDLE_CAKE = registerBlockWithoutBlockItem("banana_candle_cake", createCandleCakeBlock(ModDyeColor.BANANA, BANANA_CANDLE)/*, ModItemGroups.BANANA*/);
    public static final Block BANANA_BANNER = registerBlockWithoutBlockItem("banana_banner", createBannerBlock(ModDyeColor.BANANA));
    public static final Block BANANA_WALL_BANNER = registerBlockWithoutBlockItem("banana_wall_banner", createWallBannerBlock(ModDyeColor.BANANA, (BannerBlock)ModBlocks.BANANA_BANNER));
    //</editor-fold>
    //<editor-fold desc ="BANANA - Special">
    //</editor-fold>
    //<editor-fold desc ="CERULEAN - Template">
    public static final Block CERULEAN_WOOL = registerBlock("cerulean_wool", createWoolBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CARPET = registerBlock("cerulean_carpet", createDyedCarpetBlock(ModDyeColor.CERULEAN, CERULEAN_WOOL)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_TERRACOTTA = registerBlock("cerulean_terracotta", createTerracottaBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CONCRETE = registerBlock("cerulean_concrete", createConcreteBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CONCRETE_POWDER = registerBlock("cerulean_concrete_powder", createConcretePowderBlock(ModDyeColor.CERULEAN, CERULEAN_CONCRETE)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_GLAZED_TERRACOTTA = registerBlock("cerulean_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_STAINED_GLASS = registerBlock("cerulean_stained_glass", createStainedGlassBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_STAINED_GLASS_PANE = registerBlock("cerulean_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.CERULEAN, CERULEAN_STAINED_GLASS)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_SHULKER_BOX = registerBlockWithoutBlockItem("cerulean_shulker_box", createShulkerBoxBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_BED = registerBlockWithoutBlockItem("cerulean_bed", createBedBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_CANDLE = registerBlock("cerulean_candle", createCandleBlock(ModDyeColor.CERULEAN)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_CANDLE_CAKE = registerBlockWithoutBlockItem("cerulean_candle_cake", createCandleCakeBlock(ModDyeColor.CERULEAN, CERULEAN_CANDLE)/*, ModItemGroups.CERULEAN*/);
    public static final Block CERULEAN_BANNER = registerBlockWithoutBlockItem("cerulean_banner", createBannerBlock(ModDyeColor.CERULEAN));
    public static final Block CERULEAN_WALL_BANNER = registerBlockWithoutBlockItem("cerulean_wall_banner", createWallBannerBlock(ModDyeColor.CERULEAN, (BannerBlock)ModBlocks.CERULEAN_BANNER));
    //</editor-fold>
    //<editor-fold desc ="CERULEAN - Special">
    //</editor-fold>
    //<editor-fold desc ="ACORN - Template">
    public static final Block ACORN_WOOL = registerBlock("acorn_wool", createWoolBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CARPET = registerBlock("acorn_carpet", createDyedCarpetBlock(ModDyeColor.ACORN, ACORN_WOOL)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_TERRACOTTA = registerBlock("acorn_terracotta", createTerracottaBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CONCRETE = registerBlock("acorn_concrete", createConcreteBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CONCRETE_POWDER = registerBlock("acorn_concrete_powder", createConcretePowderBlock(ModDyeColor.ACORN, ACORN_CONCRETE)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_GLAZED_TERRACOTTA = registerBlock("acorn_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_STAINED_GLASS = registerBlock("acorn_stained_glass", createStainedGlassBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_STAINED_GLASS_PANE = registerBlock("acorn_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.ACORN, ACORN_STAINED_GLASS)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_SHULKER_BOX = registerBlockWithoutBlockItem("acorn_shulker_box", createShulkerBoxBlock(ModDyeColor.ACORN));
    public static final Block ACORN_BED = registerBlockWithoutBlockItem("acorn_bed", createBedBlock(ModDyeColor.ACORN));
    public static final Block ACORN_CANDLE = registerBlock("acorn_candle", createCandleBlock(ModDyeColor.ACORN)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_CANDLE_CAKE = registerBlockWithoutBlockItem("acorn_candle_cake", createCandleCakeBlock(ModDyeColor.ACORN, ACORN_CANDLE)/*, ModItemGroups.ACORN*/);
    public static final Block ACORN_BANNER = registerBlockWithoutBlockItem("acorn_banner", createBannerBlock(ModDyeColor.ACORN));
    public static final Block ACORN_WALL_BANNER = registerBlockWithoutBlockItem("acorn_wall_banner", createWallBannerBlock(ModDyeColor.ACORN, (BannerBlock)ModBlocks.ACORN_BANNER));
    //</editor-fold>
    //<editor-fold desc ="ACORN - Special">
    //</editor-fold>
    //<editor-fold desc ="MAUVE - Template">
    public static final Block MAUVE_WOOL = registerBlock("mauve_wool", createWoolBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CARPET = registerBlock("mauve_carpet", createDyedCarpetBlock(ModDyeColor.MAUVE, MAUVE_WOOL)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_TERRACOTTA = registerBlock("mauve_terracotta", createTerracottaBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CONCRETE = registerBlock("mauve_concrete", createConcreteBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CONCRETE_POWDER = registerBlock("mauve_concrete_powder", createConcretePowderBlock(ModDyeColor.MAUVE, MAUVE_CONCRETE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_GLAZED_TERRACOTTA = registerBlock("mauve_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_STAINED_GLASS = registerBlock("mauve_stained_glass", createStainedGlassBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_STAINED_GLASS_PANE = registerBlock("mauve_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MAUVE, MAUVE_STAINED_GLASS)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_SHULKER_BOX = registerBlockWithoutBlockItem("mauve_shulker_box", createShulkerBoxBlock(ModDyeColor.MAUVE));
    public static final Block MAUVE_BED = registerBlockWithoutBlockItem("mauve_bed", createBedBlock(ModDyeColor.MAUVE));
    public static final Block MAUVE_CANDLE = registerBlock("mauve_candle", createCandleBlock(ModDyeColor.MAUVE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_CANDLE_CAKE = registerBlockWithoutBlockItem("mauve_candle_cake", createCandleCakeBlock(ModDyeColor.MAUVE, MAUVE_CANDLE)/*, ModItemGroups.MAUVE*/);
    public static final Block MAUVE_BANNER = registerBlockWithoutBlockItem("mauve_banner", createBannerBlock(ModDyeColor.MAUVE));
    public static final Block MAUVE_WALL_BANNER = registerBlockWithoutBlockItem("mauve_wall_banner", createWallBannerBlock(ModDyeColor.MAUVE, (BannerBlock)ModBlocks.MAUVE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MAUVE - Special">
    //</editor-fold>

    //<editor-fold desc ="MAROON - Template">
    public static final Block MAROON_WOOL = registerBlock("maroon_wool", createWoolBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CARPET = registerBlock("maroon_carpet", createDyedCarpetBlock(ModDyeColor.MAROON, MAROON_WOOL)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_TERRACOTTA = registerBlock("maroon_terracotta", createTerracottaBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CONCRETE = registerBlock("maroon_concrete", createConcreteBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CONCRETE_POWDER = registerBlock("maroon_concrete_powder", createConcretePowderBlock(ModDyeColor.MAROON, MAROON_CONCRETE)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_GLAZED_TERRACOTTA = registerBlock("maroon_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_STAINED_GLASS = registerBlock("maroon_stained_glass", createStainedGlassBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_STAINED_GLASS_PANE = registerBlock("maroon_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MAROON, MAROON_STAINED_GLASS)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_SHULKER_BOX = registerBlockWithoutBlockItem("maroon_shulker_box", createShulkerBoxBlock(ModDyeColor.MAROON));
    public static final Block MAROON_BED = registerBlockWithoutBlockItem("maroon_bed", createBedBlock(ModDyeColor.MAROON));
    public static final Block MAROON_CANDLE = registerBlock("maroon_candle", createCandleBlock(ModDyeColor.MAROON)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_CANDLE_CAKE = registerBlockWithoutBlockItem("maroon_candle_cake", createCandleCakeBlock(ModDyeColor.MAROON, MAROON_CANDLE)/*, ModItemGroups.MAROON*/);
    public static final Block MAROON_BANNER = registerBlockWithoutBlockItem("maroon_banner", createBannerBlock(ModDyeColor.MAROON));
    public static final Block MAROON_WALL_BANNER = registerBlockWithoutBlockItem("maroon_wall_banner", createWallBannerBlock(ModDyeColor.MAROON, (BannerBlock)ModBlocks.MAROON_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MAROON - Special">
    //</editor-fold>
    //<editor-fold desc ="GRAPE - Template">
    public static final Block GRAPE_WOOL = registerBlock("grape_wool", createWoolBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CARPET = registerBlock("grape_carpet", createDyedCarpetBlock(ModDyeColor.GRAPE, GRAPE_WOOL)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_TERRACOTTA = registerBlock("grape_terracotta", createTerracottaBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CONCRETE = registerBlock("grape_concrete", createConcreteBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CONCRETE_POWDER = registerBlock("grape_concrete_powder", createConcretePowderBlock(ModDyeColor.GRAPE, GRAPE_CONCRETE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_GLAZED_TERRACOTTA = registerBlock("grape_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_STAINED_GLASS = registerBlock("grape_stained_glass", createStainedGlassBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_STAINED_GLASS_PANE = registerBlock("grape_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.GRAPE, GRAPE_STAINED_GLASS)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_SHULKER_BOX = registerBlockWithoutBlockItem("grape_shulker_box", createShulkerBoxBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_BED = registerBlockWithoutBlockItem("grape_bed", createBedBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_CANDLE = registerBlock("grape_candle", createCandleBlock(ModDyeColor.GRAPE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_CANDLE_CAKE = registerBlockWithoutBlockItem("grape_candle_cake", createCandleCakeBlock(ModDyeColor.GRAPE, GRAPE_CANDLE)/*, ModItemGroups.GRAPE*/);
    public static final Block GRAPE_BANNER = registerBlockWithoutBlockItem("grape_banner", createBannerBlock(ModDyeColor.GRAPE));
    public static final Block GRAPE_WALL_BANNER = registerBlockWithoutBlockItem("grape_wall_banner", createWallBannerBlock(ModDyeColor.GRAPE, (BannerBlock)ModBlocks.GRAPE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="GRAPE - Special">
    //</editor-fold>
    //<editor-fold desc ="NAVY - Template">
    public static final Block NAVY_WOOL = registerBlock("navy_wool", createWoolBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CARPET = registerBlock("navy_carpet", createDyedCarpetBlock(ModDyeColor.NAVY, NAVY_WOOL)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_TERRACOTTA = registerBlock("navy_terracotta", createTerracottaBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CONCRETE = registerBlock("navy_concrete", createConcreteBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CONCRETE_POWDER = registerBlock("navy_concrete_powder", createConcretePowderBlock(ModDyeColor.NAVY, NAVY_CONCRETE)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_GLAZED_TERRACOTTA = registerBlock("navy_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_STAINED_GLASS = registerBlock("navy_stained_glass", createStainedGlassBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_STAINED_GLASS_PANE = registerBlock("navy_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.NAVY, NAVY_STAINED_GLASS)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_SHULKER_BOX = registerBlockWithoutBlockItem("navy_shulker_box", createShulkerBoxBlock(ModDyeColor.NAVY));
    public static final Block NAVY_BED = registerBlockWithoutBlockItem("navy_bed", createBedBlock(ModDyeColor.NAVY));
    public static final Block NAVY_CANDLE = registerBlock("navy_candle", createCandleBlock(ModDyeColor.NAVY)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_CANDLE_CAKE = registerBlockWithoutBlockItem("navy_candle_cake", createCandleCakeBlock(ModDyeColor.NAVY, NAVY_CANDLE)/*, ModItemGroups.NAVY*/);
    public static final Block NAVY_BANNER = registerBlockWithoutBlockItem("navy_banner", createBannerBlock(ModDyeColor.NAVY));
    public static final Block NAVY_WALL_BANNER = registerBlockWithoutBlockItem("navy_wall_banner", createWallBannerBlock(ModDyeColor.NAVY, (BannerBlock)ModBlocks.NAVY_BANNER));
    //</editor-fold>
    //<editor-fold desc ="NAVY - Special">
    //</editor-fold>
    //<editor-fold desc ="SAP - Template">
    public static final Block SAP_WOOL = registerBlock("sap_wool", createWoolBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CARPET = registerBlock("sap_carpet", createDyedCarpetBlock(ModDyeColor.SAP, SAP_WOOL)/*, ModItemGroups.SAP*/);
    public static final Block SAP_TERRACOTTA = registerBlock("sap_terracotta", createTerracottaBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CONCRETE = registerBlock("sap_concrete", createConcreteBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CONCRETE_POWDER = registerBlock("sap_concrete_powder", createConcretePowderBlock(ModDyeColor.SAP, SAP_CONCRETE)/*, ModItemGroups.SAP*/);
    public static final Block SAP_GLAZED_TERRACOTTA = registerBlock("sap_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_STAINED_GLASS = registerBlock("sap_stained_glass", createStainedGlassBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_STAINED_GLASS_PANE = registerBlock("sap_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.SAP, SAP_STAINED_GLASS)/*, ModItemGroups.SAP*/);
    public static final Block SAP_SHULKER_BOX = registerBlockWithoutBlockItem("sap_shulker_box", createShulkerBoxBlock(ModDyeColor.SAP));
    public static final Block SAP_BED = registerBlockWithoutBlockItem("sap_bed", createBedBlock(ModDyeColor.SAP));
    public static final Block SAP_CANDLE = registerBlock("sap_candle", createCandleBlock(ModDyeColor.SAP)/*, ModItemGroups.SAP*/);
    public static final Block SAP_CANDLE_CAKE = registerBlockWithoutBlockItem("sap_candle_cake", createCandleCakeBlock(ModDyeColor.SAP, SAP_CANDLE)/*, ModItemGroups.SAP*/);
    public static final Block SAP_BANNER = registerBlockWithoutBlockItem("sap_banner", createBannerBlock(ModDyeColor.SAP));
    public static final Block SAP_WALL_BANNER = registerBlockWithoutBlockItem("sap_wall_banner", createWallBannerBlock(ModDyeColor.SAP, (BannerBlock)ModBlocks.SAP_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SAP - Special">
    //</editor-fold>

    //<editor-fold desc ="AMBER - Template">
    public static final Block AMBER_WOOL = registerBlock("amber_wool", createWoolBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CARPET = registerBlock("amber_carpet", createDyedCarpetBlock(ModDyeColor.AMBER, AMBER_WOOL)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_TERRACOTTA = registerBlock("amber_terracotta", createTerracottaBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CONCRETE = registerBlock("amber_concrete", createConcreteBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CONCRETE_POWDER = registerBlock("amber_concrete_powder", createConcretePowderBlock(ModDyeColor.AMBER, AMBER_CONCRETE)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_GLAZED_TERRACOTTA = registerBlock("amber_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_STAINED_GLASS = registerBlock("amber_stained_glass", createStainedGlassBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_STAINED_GLASS_PANE = registerBlock("amber_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.AMBER, AMBER_STAINED_GLASS)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_SHULKER_BOX = registerBlockWithoutBlockItem("amber_shulker_box", createShulkerBoxBlock(ModDyeColor.AMBER));
    public static final Block AMBER_BED = registerBlockWithoutBlockItem("amber_bed", createBedBlock(ModDyeColor.AMBER));
    public static final Block AMBER_CANDLE = registerBlock("amber_candle", createCandleBlock(ModDyeColor.AMBER)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_CANDLE_CAKE = registerBlockWithoutBlockItem("amber_candle_cake", createCandleCakeBlock(ModDyeColor.AMBER, AMBER_CANDLE)/*, ModItemGroups.AMBER*/);
    public static final Block AMBER_BANNER = registerBlockWithoutBlockItem("amber_banner", createBannerBlock(ModDyeColor.AMBER));
    public static final Block AMBER_WALL_BANNER = registerBlockWithoutBlockItem("amber_wall_banner", createWallBannerBlock(ModDyeColor.AMBER, (BannerBlock)ModBlocks.AMBER_BANNER));
    //</editor-fold>
    //<editor-fold desc ="AMBER - Special">
    public static final Block AMBER_BLOCK = registerBlock("amber_block", new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK).mapColor(MapColor.GOLD)));
    public static final Block AMBER_BRICKS = registerBlock("amber_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.AMBER_BLOCK)));
    public static final Block AMBER_BRICK_STAIRS = registerBlock("amber_brick_stairs", createStairsBlock(AMBER_BRICKS, FabricBlockSettings.copyOf(ModBlocks.AMBER_BRICKS)));
    public static final Block AMBER_BRICK_SLAB = registerBlock("amber_brick_slab", createSlabBlock(FabricBlockSettings.copyOf(ModBlocks.AMBER_BRICKS)));
    public static final Block AMBER_BRICK_WALL = registerBlock("amber_brick_wall", createWallBlock(FabricBlockSettings.copyOf(ModBlocks.AMBER_BRICKS)));
    public static final Block CHISELED_AMBER_BRICKS = registerBlock("chiseled_amber_bricks", new Block(FabricBlockSettings.copyOf(ModBlocks.AMBER_BLOCK)));
    public static final Block EMBER = registerBlockWithoutBlockItem("ember", new EmberBlock(FabricBlockSettings.create().mapColor(MapColor.GOLD).breakInstantly().nonOpaque().sounds(BlockSoundGroup.FROGSPAWN).noCollision().luminance(15)));
    static { RENDER_LAYER_CUTOUT_MIPPED.add(EMBER); }
    //</editor-fold>

    //<editor-fold desc ="SAGE - Template">
    public static final Block SAGE_WOOL = registerBlock("sage_wool", createWoolBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CARPET = registerBlock("sage_carpet", createDyedCarpetBlock(ModDyeColor.SAGE, SAGE_WOOL)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_TERRACOTTA = registerBlock("sage_terracotta", createTerracottaBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CONCRETE = registerBlock("sage_concrete", createConcreteBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CONCRETE_POWDER = registerBlock("sage_concrete_powder", createConcretePowderBlock(ModDyeColor.SAGE, SAGE_CONCRETE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_GLAZED_TERRACOTTA = registerBlock("sage_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_STAINED_GLASS = registerBlock("sage_stained_glass", createStainedGlassBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_STAINED_GLASS_PANE = registerBlock("sage_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.SAGE, SAGE_STAINED_GLASS)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_SHULKER_BOX = registerBlockWithoutBlockItem("sage_shulker_box", createShulkerBoxBlock(ModDyeColor.SAGE));
    public static final Block SAGE_BED = registerBlockWithoutBlockItem("sage_bed", createBedBlock(ModDyeColor.SAGE));
    public static final Block SAGE_CANDLE = registerBlock("sage_candle", createCandleBlock(ModDyeColor.SAGE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_CANDLE_CAKE = registerBlockWithoutBlockItem("sage_candle_cake", createCandleCakeBlock(ModDyeColor.SAGE, SAGE_CANDLE)/*, ModItemGroups.SAGE*/);
    public static final Block SAGE_BANNER = registerBlockWithoutBlockItem("sage_banner", createBannerBlock(ModDyeColor.SAGE));
    public static final Block SAGE_WALL_BANNER = registerBlockWithoutBlockItem("sage_wall_banner", createWallBannerBlock(ModDyeColor.SAGE, (BannerBlock)ModBlocks.SAGE_BANNER));
    //</editor-fold>
    //<editor-fold desc ="SAGE - Special">
    //</editor-fold>

    //<editor-fold desc ="VELVET - Template">
    public static final Block VELVET_WOOL = registerBlock("velvet_wool", createWoolBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CARPET = registerBlock("velvet_carpet", createDyedCarpetBlock(ModDyeColor.VELVET, VELVET_WOOL)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_TERRACOTTA = registerBlock("velvet_terracotta", createTerracottaBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CONCRETE = registerBlock("velvet_concrete", createConcreteBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CONCRETE_POWDER = registerBlock("velvet_concrete_powder", createConcretePowderBlock(ModDyeColor.VELVET, VELVET_CONCRETE)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_GLAZED_TERRACOTTA = registerBlock("velvet_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_STAINED_GLASS = registerBlock("velvet_stained_glass", createStainedGlassBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_STAINED_GLASS_PANE = registerBlock("velvet_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.VELVET, VELVET_STAINED_GLASS)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_SHULKER_BOX = registerBlockWithoutBlockItem("velvet_shulker_box", createShulkerBoxBlock(ModDyeColor.VELVET));
    public static final Block VELVET_BED = registerBlockWithoutBlockItem("velvet_bed", createBedBlock(ModDyeColor.VELVET));
    public static final Block VELVET_CANDLE = registerBlock("velvet_candle", createCandleBlock(ModDyeColor.VELVET)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_CANDLE_CAKE = registerBlockWithoutBlockItem("velvet_candle_cake", createCandleCakeBlock(ModDyeColor.VELVET, VELVET_CANDLE)/*, ModItemGroups.VELVET*/);
    public static final Block VELVET_BANNER = registerBlockWithoutBlockItem("velvet_banner", createBannerBlock(ModDyeColor.VELVET));
    public static final Block VELVET_WALL_BANNER = registerBlockWithoutBlockItem("velvet_wall_banner", createWallBannerBlock(ModDyeColor.VELVET, (BannerBlock)ModBlocks.VELVET_BANNER));
    //</editor-fold>
    //<editor-fold desc ="VELVET - Special">
    //</editor-fold>

    //<editor-fold desc ="MOLD - Template">
    public static final Block MOLD_WOOL = registerBlock("mold_wool", createWoolBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CARPET = registerBlock("mold_carpet", createDyedCarpetBlock(ModDyeColor.MOLD, MOLD_WOOL)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_TERRACOTTA = registerBlock("mold_terracotta", createTerracottaBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CONCRETE = registerBlock("mold_concrete", createConcreteBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CONCRETE_POWDER = registerBlock("mold_concrete_powder", createConcretePowderBlock(ModDyeColor.MOLD, MOLD_CONCRETE)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_GLAZED_TERRACOTTA = registerBlock("mold_glazed_terracotta", createGlazedTerracottaBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_STAINED_GLASS = registerBlock("mold_stained_glass", createStainedGlassBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_STAINED_GLASS_PANE = registerBlock("mold_stained_glass_pane", createStainedGlassPaneBlock(ModDyeColor.MOLD, MOLD_STAINED_GLASS)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_SHULKER_BOX = registerBlockWithoutBlockItem("mold_shulker_box", createShulkerBoxBlock(ModDyeColor.MOLD));
    public static final Block MOLD_BED = registerBlockWithoutBlockItem("mold_bed", createBedBlock(ModDyeColor.MOLD));
    public static final Block MOLD_CANDLE = registerBlock("mold_candle", createCandleBlock(ModDyeColor.MOLD)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_CANDLE_CAKE = registerBlockWithoutBlockItem("mold_candle_cake", createCandleCakeBlock(ModDyeColor.MOLD, MOLD_CANDLE)/*, ModItemGroups.MOLD*/);
    public static final Block MOLD_BANNER = registerBlockWithoutBlockItem("mold_banner", createBannerBlock(ModDyeColor.MOLD));
    public static final Block MOLD_WALL_BANNER = registerBlockWithoutBlockItem("mold_wall_banner", createWallBannerBlock(ModDyeColor.MOLD, (BannerBlock)ModBlocks.MOLD_BANNER));
    //</editor-fold>
    //<editor-fold desc ="MOLD - Special">
    //</editor-fold>

    //<editor-fold desc ="Decor Additions">
    public static final Block MUCKTUFF = registerBlock("mucktuff", createMucktuffBlock());
    public static final Block CORRUGATED_IRON = registerBlock("corrugated_iron", createCorrugatedIronBlock(null));

    static {
        //With using ModDyeColor it should only loop through the dyes we add, and the vanilla dye colors is only using predefined expected ones- not grabbing from the dye color enum that may have been mixined into
        for (DyeColor color : ModUtil.concat(ModDyeColor.VALUES, ModUtil.VANILLA_DYE_COLORS)) {
            registerBlock(color.getName() + "_corrugated_iron", createCorrugatedIronBlock(color));
        }

        for (DyeColor color : ModUtil.concat(ModDyeColor.VALUES, ModUtil.VANILLA_DYE_COLORS)) {
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
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
        DYED_TERRACOTTA_BLOCKS.add(block);
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        COLORED_BLOCKS.add(block);
        RENDER_LAYER_TRANSLUCENT.add(block);
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
        SHULKER_BOX_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
        SHULKER_BOX_FROM_DYECOLOR.put(color, (Block) block);
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
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
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
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
        ALL_CORRUGATED_IRON_BLOCKS.add((Block) block);
        if(color != null) {
            DYECOLOR_FROM_BLOCK.put((Block) block, color);
        }
        return block;
    }


    // Decor Additions
    public static Block createDyedMucktuffBlock(DyeColor color) {
        Block block = new Block(mucktuffBlockSettings()
                        .mapColor(color.getMapColor())
        );
        ALL_MUCKTUFF_BLOCKS.add(block);
        DYECOLOR_FROM_BLOCK.put((Block) block, color);
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
                .postProcess(ModBlocks::always).pistonBehavior(PistonBehavior.DESTROY),
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
        return ModBlocks.toBlockArray(output);
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
        return state -> state.get(property) != false ? litLevel : 0;
    }
//    private static ToFloatFunction<BlockState> createStrengthFromIntProperty(int cutoff, int above, int below, IntProperty property) {
//        return state -> state.get(property) >= cutoff ? above : below; // grahhh why doesn't this work?
//    }


    //</editor-fold>
    public static void registerModBlocks() {
        ModInit.LOGGER.debug("Registering mod blocks for " + Super.MOD_ID);
    }
}

