package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.block.custom.*;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ElsDyeModLootTableGenerator extends FabricBlockLootTableProvider {
    public ElsDyeModLootTableGenerator(FabricDataOutput dataOutput) { super(dataOutput); }
    public static final ArrayList<Block> usedBlocks = new ArrayList<Block>();

    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();

    public void addDrop(Block block, ItemConvertible drop) {
        this.addDrop(block, this.drops(drop));
        usedBlocks.add(block);
    }
    public void addDrop(Block block, Function<Block, LootTable.Builder> lootTableFunction) {
        addDrop(block, lootTableFunction.apply(block));
        usedBlocks.add(block);
    }
    public void addDrop(Block block) {
        addDrop(block, block);
        usedBlocks.add(block);
    }
    public void addDrop(Block block, LootTable.Builder lootTable) {
        this.lootTables.put(block.getLootTableId(), lootTable);
        usedBlocks.add(block);
    }
    public void addPottedPlantDrops(Block block) {
        this.addDrop(block, (Block flowerPot) -> this.pottedPlantDrops(((FlowerPotBlock)flowerPot).getContent()));
        usedBlocks.add(block);
    }
    public void dropsNothing(Block block) {
        usedBlocks.add(block);
    }


    public LootTable.Builder wildMintDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder)ItemEntry.builder(ElsDyeModItems.MINT_SPRIG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))));
    }
    public LootTable.Builder waxcapGillSlabDrops(Block block, Block waxDrop) {
        AlternativeEntry.Builder builder =
                ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0f))).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)))
                            .alternatively(ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))
                .alternatively(this.addSurvivesExplosionCondition(block, ItemEntry.builder(waxDrop)
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f)))).conditionally(BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(SlabBlock.TYPE, SlabType.DOUBLE)))
                            .alternatively(ItemEntry.builder(waxDrop).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))));

        return LootTable.builder().pool(LootPool.builder().with(builder));
    }
    public LootTable.Builder waxcapGillFullBlockDrops(Block block, Block waxDrop) {
        AlternativeEntry.Builder builder =
                ItemEntry.builder(block).conditionally(WITH_SILK_TOUCH).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)))
                                .alternatively(this.addSurvivesExplosionCondition(block, ItemEntry.builder(waxDrop)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 6.0f)))));

        return LootTable.builder().pool(LootPool.builder().with(builder));
    }
    public LootTable.Builder peachTreeDrops(Block peachTreeBlock, ItemConvertible branchItem/*, ItemConvertible fruitItem*/) {
        //Dropping of fruit is handled by the PeachTree class.
        ItemEntry.Builder builder = this.addSurvivesExplosionCondition(peachTreeBlock, ItemEntry.builder(branchItem).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));
        return LootTable.builder().pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(peachTreeBlock).properties(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.LOWER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(peachTreeBlock).state(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(peachTreeBlock).properties(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.UPPER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(peachTreeBlock).state(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }
    public LootTable.Builder wintergreenLeavesDrops(Block leaves, Block drop, float ... chance) {
            return this.leavesDrops(leaves, drop, chance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with((LootPoolEntry.Builder<?>)((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(ElsDyeModItems.WINTERGREEN_BERRIES)))
                    .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.03f, 0.0305555557f, 0.03125f, 0.033333334f, 0.06f)))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
            );
    }

    public LootTable.Builder hangingFruitDrops(Block block, Item drop) {
        return LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(BlockStatePropertyLootCondition.builder(block)
                                .properties(StatePredicate.Builder.create().exactMatch(HangingFruitBlock.AGE, 4)))
                        .with(this.applyExplosionDecay(block, ItemEntry.builder(drop))));
    }

    public void addWoodsetDrops(WoodSet woodSet) {
        if(woodSet.getWoodType() == ElsDyeModBlocks.WINTERGREEN_WOODSET.getWoodType()) {
            this.addDrop(woodSet.getLeaves(), (Block block) -> this.wintergreenLeavesDrops(block, woodSet.getSapling(), SAPLING_DROP_CHANCE));
        }
        else {
            this.addDrop(woodSet.getLeaves(), (Block block) -> this.leavesDrops(block, woodSet.getSapling(), SAPLING_DROP_CHANCE));
        }
        this.addDrop(woodSet.getSapling());
        this.addPottedPlantDrops(woodSet.getPottedSapling());
        this.addDrop(woodSet.getLog());
        this.addDrop(woodSet.getWood());
        this.addDrop(woodSet.getStrippedLog());
        this.addDrop(woodSet.getStrippedWood());
        if (woodSet.getPlanks() != null) { this.addDrop(woodSet.getPlanks()); }
        if (woodSet.getStairs() != null) { this.addDrop(woodSet.getStairs()); }
        if (woodSet.getSlab() != null) { this.addDrop(woodSet.getSlab(), (Block block) -> this.slabDrops(block)); }
        if (woodSet.getMosaic() != null) { this.addDrop(woodSet.getMosaic()); }
        if (woodSet.getMosaicStairs() != null) { this.addDrop(woodSet.getMosaicStairs()); }
        if (woodSet.getMosaicSlab() != null) { this.addDrop(woodSet.getMosaicSlab(), (Block block) -> this.slabDrops(block)); }
        this.addDrop(woodSet.getFence());
        this.addDrop(woodSet.getFenceGate());
        this.addDrop(woodSet.getDoor(),(Block block) -> this.doorDrops(block));
        this.addDrop(woodSet.getTrapDoor());
        this.addDrop(woodSet.getPressurePlate());
        this.addDrop(woodSet.getButton());
        this.addDrop(woodSet.getSign());
        this.addDrop(woodSet.getHangingSign());
    }

    public static final float[] FRUIT_SAPLING_DROP_CHANCE = new float[]{0.01F, 0.05F, 0.08F, 0.1F};
    public static final float[] FLOWERING_FRUIT_SAPLING_DROP_CHANCE = new float[]{0.1F, 0.12F, 0.15F, 0.2F};
    @Override
    public void generate() {
        //  MINT - Special
        this.addDrop(ElsDyeModBlocks.WILD_MINT, (Block block) -> this.wildMintDrops(ElsDyeModBlocks.WILD_MINT));
        BlockStatePropertyLootCondition.Builder mintCropBuilder = BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.MINT_CROP).properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, MintCropBlock.MAX_AGE));
        this.addDrop(ElsDyeModBlocks.MINT_CROP, this.applyExplosionDecay(ElsDyeModBlocks.MINT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ElsDyeModItems.MINT_SPRIG))).pool(LootPool.builder().conditionally(mintCropBuilder).with(ItemEntry.builder(ElsDyeModItems.MINT_SPRIG).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3))))));
        this.addDrop(ElsDyeModBlocks.MINT_BRICK_SLAB, (Block block) -> this.slabDrops(block));
        this.addWoodsetDrops(ElsDyeModBlocks.WINTERGREEN_WOODSET);

        //  PEACH - Special
        this.addDrop(ElsDyeModBlocks.CORALSOIL_BRICK_SLAB, (Block block) -> this.slabDrops(block));
        this.addPottedPlantDrops(ElsDyeModBlocks.POTTED_HYPERICUM);
        this.addDrop(ElsDyeModBlocks.PEACH_TREE, (Block block) -> this.peachTreeDrops(block, ElsDyeModItems.PEACH_BRANCH));
        this.addDrop(ElsDyeModBlocks.HANGING_PEACH, hangingFruitDrops(ElsDyeModBlocks.HANGING_PEACH, ElsDyeModItems.PEACH));
        addDrop(ElsDyeModBlocks.PEACH_LEAVES, leavesDrops(ElsDyeModBlocks.PEACH_LEAVES, ElsDyeModBlocks.PEACH_SAPLING, FRUIT_SAPLING_DROP_CHANCE));
        addDrop(ElsDyeModBlocks.FLOWERING_PEACH_LEAVES, leavesDrops(ElsDyeModBlocks.FLOWERING_PEACH_LEAVES, ElsDyeModBlocks.PEACH_SAPLING, FLOWERING_FRUIT_SAPLING_DROP_CHANCE));

        //  PERIWINKLE - Special
        this.addDrop(ElsDyeModBlocks.LAVENDER_BRICK_SLAB, (Block block) -> this.slabDrops(block));
        this.addDrop(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB, (Block block) -> this.slabDrops(block));
        this.addDrop(ElsDyeModBlocks.PERIWINKLE_PETALS, this.flowerbedDrops(ElsDyeModBlocks.PERIWINKLE_PETALS));
        this.addDrop(ElsDyeModBlocks.LAVENDER_OIL_LANTERN, this::drops);

        //  ARTICHOKE - Special
        this.addDrop(ElsDyeModBlocks.WAXCAP_CAP_BLOCK, (Block block) -> this.mushroomBlockDrops(block, ElsDyeModBlocks.WAXCAP_MUSHROOM));
        this.addDrop(ElsDyeModBlocks.WAXCAP_STEM_BLOCK, (Block block) -> this.mushroomBlockDrops(block, ElsDyeModBlocks.WAXCAP_MUSHROOM));
        this.addDrop(ElsDyeModBlocks.HANGING_WAXCAP_WAX, (Block block) -> this.drops(ElsDyeModItems.WAXCAP_WAX));
        this.addDrop(ElsDyeModBlocks.WAXCAP_GILL_SLAB, (Block block) ->  waxcapGillSlabDrops(ElsDyeModBlocks.WAXCAP_GILL_SLAB, ElsDyeModBlocks.HANGING_WAXCAP_WAX));
        this.addDrop(ElsDyeModBlocks.WAXCAP_GILLS, (Block block) ->  waxcapGillFullBlockDrops(ElsDyeModBlocks.WAXCAP_GILLS, ElsDyeModBlocks.HANGING_WAXCAP_WAX));
        BlockStatePropertyLootCondition.Builder artichokeCropBuilder = BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.ARTICHOKE_CROP).properties(StatePredicate.Builder.create().exactMatch(ArtichokeCropBlock.AGE, ArtichokeCropBlock.MAX_AGE));
        this.addDrop(ElsDyeModBlocks.ARTICHOKE_CROP, this.applyExplosionDecay(ElsDyeModBlocks.ARTICHOKE_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ElsDyeModItems.ARTICHOKE))).pool(LootPool.builder().conditionally(artichokeCropBuilder).with(ItemEntry.builder(ElsDyeModItems.ARTICHOKE).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3))))));

        this.addDrop(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK, LootTable.builder()
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(WITH_SILK_TOUCH)
                                .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK, ItemEntry.builder(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK))))
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK, ItemEntry.builder(Blocks.DIRT))))
                        .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(WITHOUT_SILK_TOUCH)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                                .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK, ItemEntry.builder(ElsDyeModItems.MADDER_ROOT))))
        );
        this.addDrop(ElsDyeModBlocks.MADDER_ROOTED_DIRT, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(WITH_SILK_TOUCH)
                        .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_DIRT, ItemEntry.builder(ElsDyeModBlocks.MADDER_ROOTED_DIRT))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(WITHOUT_SILK_TOUCH)
                        .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_DIRT, ItemEntry.builder(Blocks.DIRT))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(WITHOUT_SILK_TOUCH)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
                        .with(this.applyExplosionDecay(ElsDyeModBlocks.MADDER_ROOTED_DIRT, ItemEntry.builder(ElsDyeModItems.MADDER_ROOT))))
        );

        usedBlocks.add(ElsDyeModBlocks.BUDDING_CINNABAR);
        this.addDropWithSilkTouch(ElsDyeModBlocks.SMALL_CINNABAR_BUD);
        this.addDropWithSilkTouch(ElsDyeModBlocks.MEDIUM_CINNABAR_BUD);
        this.addDropWithSilkTouch(ElsDyeModBlocks.LARGE_CINNABAR_BUD);
        this.addDrop(ElsDyeModBlocks.CINNABAR_CLUSTER, (block) -> dropsWithSilkTouch(block, ItemEntry.builder(ElsDyeModItems.CINNABAR).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F))).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE)).conditionally(MatchToolLootCondition.builder(net.minecraft.predicate.item.ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES))).alternatively(this.applyExplosionDecay(block, ItemEntry.builder(ElsDyeModItems.CINNABAR).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))))));

        // AMBER - Special
        this.addDrop(ElsDyeModBlocks.AMBER_BRICK_SLAB, (Block block) -> this.slabDrops(block));
        this.dropsNothing(ElsDyeModBlocks.EMBER);

        addDrop(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP)
                                .properties(StatePredicate.Builder.create().exactMatch(SavannabudsCropBlock.AGE, 3)))
                        .with(this.applyExplosionDecay(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP, ItemEntry.builder(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP)
                                        .properties(StatePredicate.Builder.create().exactMatch(SavannabudsCropBlock.AGE, 0))
                                .or(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP)
                                        .properties(StatePredicate.Builder.create().exactMatch(SavannabudsCropBlock.AGE, 1))
                                .or(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP)
                                        .properties(StatePredicate.Builder.create().exactMatch(SavannabudsCropBlock.AGE, 2)))))
                        .with(this.applyExplosionDecay(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP, ItemEntry.builder(ElsDyeModItems.SAVANNABUD_SEEDS)))));

        addDrop(ElsDyeModBlocks.PINEAPPLE_STEM, ElsDyeModItems.PINEAPPLE_CROWN);
        addDrop(ElsDyeModBlocks.PINEAPPLE_CROWN, ElsDyeModItems.PINEAPPLE_CROWN);

        this.addDrop(ElsDyeModBlocks.STRAWBERRY_PLANT, LootTable.builder()
            .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(this.applyExplosionDecay(ElsDyeModBlocks.STRAWBERRY_PLANT, ItemEntry.builder(ElsDyeModItems.STRAWBERRY)
                        .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.STRAWBERRY_PLANT)
                                .properties(StatePredicate.Builder.create().exactMatch(StrawberryPlantBlock.AGE, StrawberryPlantBlock.MAX_AGE)))
                        .apply(IntStream.rangeClosed(1, 4).boxed().toList(), (flowerAmount) -> SetCountLootFunction.builder(ConstantLootNumberProvider.create((float)flowerAmount))
                                .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.STRAWBERRY_PLANT)
                                        .properties(StatePredicate.Builder.create().exactMatch(FlowerbedBlock.FLOWER_AMOUNT, flowerAmount))))))));


        this.addDrop(ElsDyeModBlocks.TALL_CORDYLINE, LootTable.builder().pool(this.addSurvivesExplosionCondition(ElsDyeModBlocks.TALL_CORDYLINE, LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .with(ItemEntry.builder(ElsDyeModBlocks.TALL_CORDYLINE)
                        .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.TALL_CORDYLINE)
                                .properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)))))));
        this.addDrop(ElsDyeModBlocks.TALL_PLUM_CORDYLINE, LootTable.builder().pool(this.addSurvivesExplosionCondition(ElsDyeModBlocks.TALL_PLUM_CORDYLINE, LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .with(ItemEntry.builder(ElsDyeModBlocks.TALL_PLUM_CORDYLINE)
                        .conditionally(BlockStatePropertyLootCondition.builder(ElsDyeModBlocks.TALL_PLUM_CORDYLINE)
                                .properties(StatePredicate.Builder.create().exactMatch(TallPlantBlock.HALF, DoubleBlockHalf.LOWER)))))));


        // Decor Additions
        for(Block block : ElsDyeModBlocks.ALL_MUCKTUFF_BLOCKS) { this.addDrop(block); }
        for(Block block : ElsDyeModBlocks.ALL_CORRUGATED_IRON_BLOCKS) { this.addDrop(block); }

        //  Main
// POTTED_PLANTS
        for(Block block : ElsDyeModBlocks.FLOWER_POT_FROM_BLOCK.keySet()) {
            this.addPottedPlantDrops(ElsDyeModBlocks.FLOWER_POT_FROM_BLOCK.get(block));
        }

////  WOOL_BLOCKS
//        for(Block block : ModBlocks.WOOL_BLOCKS) { this.addDrop(block); }
////  CARPET_BLOCKS
//        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) { this.addDrop(block); }
////  TERRACOTTA_BLOCKS
//        for(Block block : ModBlocks.DYED_TERRACOTTA_BLOCKS) { this.addDrop(block); }
////  CONCRETE_BLOCKS
//        for(Block block : ModBlocks.CONCRETE_BLOCKS) { this.addDrop(block); }
////  CONCRETE_POWDER_BLOCKS
//        for(Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) { this.addDrop(block); }
////  GLAZED_TERRACOTTA_BLOCKS
//        for(Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) { this.addDrop(block); }
//  STAINED_GLASS_BLOCKS
        for(Block block : ElsDyeModBlocks.STAINED_GLASS_BLOCKS) { this.addDropWithSilkTouch(block); }
//  STAINED_GLASS_PANE_BLOCKS
        for(Block block : ElsDyeModBlocks.STAINED_GLASS_PANE_BLOCKS) { this.addDropWithSilkTouch(block); }
//  SHULKER_BOX_BLOCKS
        for(Block block : ElsDyeModBlocks.SHULKER_BOX_BLOCKS) {  this.addDrop(block, (Block block2) -> this.shulkerBoxDrops(block2)); }
//  BED_BLOCKS
        for(Block block : ElsDyeModBlocks.BED_BLOCKS) { this.addDrop(block, (Block b) -> this.dropsWithProperty(b, BedBlock.PART, BedPart.HEAD)); }
//  CANDLE_BLOCKS
        for(Block block : ElsDyeModBlocks.CANDLE_BLOCKS) { this.addDrop(ElsDyeModBlocks.MINT_CANDLE, (Block block2) -> this.candleDrops(block2)); }
//  CANDLE_CAKE_BLOCKS
        for(Block block : ElsDyeModBlocks.CANDLE_CAKE_FROM_CANDLE.values()) { VanillaBlockLootTableGenerator.candleCakeDrops(block); }
//  BANNER_BLOCKS
        for(Block block : ElsDyeModBlocks.BANNER_BLOCKS) { this.addDrop(block, (Block block2) -> this.bannerDrops(block2)); }
//  WALL_BANNER_BLOCKS
//        for(Block block : ModBlocks.WALL_BANNER_BLOCKS) { } // Not needed

        // FALLBACK
        for(Identifier id : ElsDyeModUtil.allBlockIdsInNamespace(ElsDyeMod.MOD_ID)) {
            Block block = Registries.BLOCK.get(id);
            if(usedBlocks.contains(block) || block instanceof AbstractBannerBlock) { continue; }
            this.addDrop(block);
        }
    }
}
