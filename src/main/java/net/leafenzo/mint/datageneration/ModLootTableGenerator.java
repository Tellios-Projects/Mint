package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.mint.block.*;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registration.WoodSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.block.enums.SlabType;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.util.math.BlockPos;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    private static final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(WITH_SILK_TOUCH);
    private static final LootCondition.Builder WITHOUT_SILK_TOUCH_NOR_SHEARS = WITH_SILK_TOUCH_OR_SHEARS.invert();

    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    public LootTable.Builder wildMintDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder)ItemEntry.builder(ModItems.MINT_SPRIG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))));
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

    public LootTable.Builder peachTreeDrops(Block peachTreeBlock, ItemConvertible branchItem/*, ItemConvertible fruitItem*/) {
        //Dropping of fruit is handled by the PeachTree class.
        ItemEntry.Builder builder = this.addSurvivesExplosionCondition(peachTreeBlock, ItemEntry.builder(branchItem).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));
        return LootTable.builder().pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(peachTreeBlock).properties(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.LOWER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(peachTreeBlock).state(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.UPPER).build()).build()), new BlockPos(0, 1, 0)))).pool(LootPool.builder().with(builder).conditionally(BlockStatePropertyLootCondition.builder(peachTreeBlock).properties(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.UPPER))).conditionally(LocationCheckLootCondition.builder(LocationPredicate.Builder.create().block(BlockPredicate.Builder.create().blocks(peachTreeBlock).state(StatePredicate.Builder.create().exactMatch(TwoTallCropBlock.HALF, DoubleBlockHalf.LOWER).build()).build()), new BlockPos(0, -1, 0))));
    }
    public LootTable.Builder wintergreenLeavesDrops(Block leaves, Block drop, float ... chance) {
            return this.leavesDrops(leaves, drop, chance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITHOUT_SILK_TOUCH_NOR_SHEARS).with((LootPoolEntry.Builder<?>)((LeafEntry.Builder)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(ModItems.WINTERGREEN_BERRIES)))
                    .conditionally(TableBonusLootCondition.builder(Enchantments.FORTUNE, 0.03f, 0.0305555557f, 0.03125f, 0.033333334f, 0.06f)))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
            );
    }
    public void addWoodsetDrops(WoodSet woodSet) {
        if(woodSet.getWoodType() == ModBlocks.WINTERGREEN_WOODSET.getWoodType()) {
            this.addDrop(woodSet.getLeaves(), (Block block) -> this.wintergreenLeavesDrops((Block)block, woodSet.getSapling(), SAPLING_DROP_CHANCE));
        }
        else {
            this.addDrop(woodSet.getLeaves(), (Block block) -> this.leavesDrops((Block)block, woodSet.getSapling(), SAPLING_DROP_CHANCE));
        }
        this.addDrop(woodSet.getSapling());
        this.addPottedPlantDrops(woodSet.getPottedSapling());
        this.addDrop(woodSet.getLog());
        this.addDrop(woodSet.getWood());
        this.addDrop(woodSet.getStrippedLog());
        this.addDrop(woodSet.getStrippedWood());
        if (woodSet.getPlanks() != null) { this.addDrop(woodSet.getPlanks()); }
        if (woodSet.getStairs() != null) { this.addDrop(woodSet.getStairs()); }
        if (woodSet.getSlab() != null) { this.addDrop(woodSet.getSlab(), (Block block) -> this.slabDrops((Block) block)); }
        if (woodSet.getMosaic() != null) { this.addDrop(woodSet.getMosaic()); }
        if (woodSet.getMosaicStairs() != null) { this.addDrop(woodSet.getMosaicStairs()); }
        if (woodSet.getMosaicSlab() != null) { this.addDrop(woodSet.getMosaicSlab(), (Block block) -> this.slabDrops((Block)block)); }
        this.addDrop(woodSet.getFence());
        this.addDrop(woodSet.getFenceGate());
        this.addDrop(woodSet.getDoor(),(Block block) -> this.doorDrops((Block) block));
        this.addDrop(woodSet.getTrapDoor());
        this.addDrop(woodSet.getPressurePlate());
        this.addDrop(woodSet.getButton());
        this.addDrop(woodSet.getSign());
        this.addDrop(woodSet.getHangingSign());
    }

    @Override
    public void generate() {
        //  MINT - Special
        this.addDrop(ModBlocks.WILD_MINT, (Block block) -> this.wildMintDrops(ModBlocks.WILD_MINT));
        BlockStatePropertyLootCondition.Builder mintCropBuilder = BlockStatePropertyLootCondition.builder(ModBlocks.MINT_CROP).properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, MintCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.MINT_CROP, this.applyExplosionDecay(ModBlocks.MINT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItems.MINT_SPRIG))).pool(LootPool.builder().conditionally(mintCropBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(ModItems.MINT_SPRIG).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3)))))));
        this.addDrop(ModBlocks.MINT_SPRIG_BLOCK);
        this.addDrop(ModBlocks.MINT_BRICKS);
        this.addDrop(ModBlocks.MINT_BRICK_SLAB, (Block block) -> this.slabDrops((Block)block));
        this.addDrop(ModBlocks.MINT_BRICK_STAIRS);
        this.addWoodsetDrops(ModBlocks.WINTERGREEN_WOODSET);

        //  PEACH - Special
        this.addDrop(ModBlocks.HYPERICUM);
        this.addPottedPlantDrops(ModBlocks.POTTED_HYPERICUM);

        this.addDrop(ModBlocks.PEACH_TREE, (Block block) -> this.peachTreeDrops((Block)block, ModItems.PEACH_BRANCH));
        this.addDrop(ModBlocks.PEACH_LOG);
        this.addDrop(ModBlocks.CORAL_ANEMONE);

        //  PERIWINKLE - Special
        this.addDrop(ModBlocks.LAVENDER_BRICKS);
        this.addDrop(ModBlocks.LAVENDER_BRICK_SLAB, (Block block) -> this.slabDrops((Block)block));
        this.addDrop(ModBlocks.LAVENDER_BRICK_STAIRS);
        this.addDrop(ModBlocks.LAVENDER_BRICK_WALL);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICKS);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_SLAB, (Block block) -> this.slabDrops((Block)block));
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_WALL);
        this.addDrop(ModBlocks.LAVENDER_CLAY);
        this.addDrop(ModBlocks.LAVENDER_BUSHEL);
        this.addDrop(ModBlocks.PERIWINKLE_PETALS, this.flowerbedDrops(ModBlocks.PERIWINKLE_PETALS));
        this.addDrop(ModBlocks.LAVENDER_OIL_LANTERN, this::drops);
        this.addDrop(ModBlocks.HIDCOTE_LAVENDER);

        //  ARTICHOKE - Special
        this.addDrop(ModBlocks.WAXCAP_MUSHROOM);
        this.addDrop(ModBlocks.THISTLE_FLOWER);
        this.addDrop(ModBlocks.HANGING_WAXCAP_WAX);
        this.addDrop(ModBlocks.WAXCAP_WAX_BLOCK);
        this.addDrop(ModBlocks.WAXCAP_CAP_BLOCK, (Block block) -> this.mushroomBlockDrops((Block)block, ModBlocks.WAXCAP_MUSHROOM));
        this.addDrop(ModBlocks.WAXCAP_STEM_BLOCK, (Block block) -> this.mushroomBlockDrops((Block)block, ModBlocks.WAXCAP_MUSHROOM));
        this.addDrop(ModBlocks.HANGING_WAXCAP_WAX, (Block block) -> this.drops(ModItems.WAXCAP_WAX));

        this.addDrop(ModBlocks.WAXCAP_GILL_SLAB, (Block block) ->  waxcapGillSlabDrops(ModBlocks.WAXCAP_GILL_SLAB, ModBlocks.HANGING_WAXCAP_WAX));

        this.addDrop(ModBlocks.WAXCAP_GILLS, (Block block) -> this.drops((Block)block, ModItems.WAXCAP_WAX, ConstantLootNumberProvider.create(4.0f)));
        BlockStatePropertyLootCondition.Builder artichokeCropBuilder = BlockStatePropertyLootCondition.builder(ModBlocks.ARTICHOKE_CROP).properties(StatePredicate.Builder.create().exactMatch(ArtichokeCropBlock.AGE, ArtichokeCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.ARTICHOKE_CROP, this.applyExplosionDecay(ModBlocks.ARTICHOKE_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItems.ARTICHOKE))).pool(LootPool.builder().conditionally(artichokeCropBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(ModItems.ARTICHOKE).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3)))))));


        // Decor Additions
        for(Block block : ModBlocks.ALL_MUCKTUFF_BLOCKS) { this.addDrop(block); }
        for(Block block : ModBlocks.ALL_CORRUGATED_IRON_BLOCKS) { this.addDrop(block); }

        //  Main
// POTTED_PLANTS
        for(Block block : ModBlocks.FLOWER_POT_FROM_BLOCK.keySet()) {
            this.addPottedPlantDrops(ModBlocks.FLOWER_POT_FROM_BLOCK.get(block));
        }

//  WOOL_BLOCKS
        for(Block block : ModBlocks.WOOL_BLOCKS) { this.addDrop(block); }
//  CARPET_BLOCKS
        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) { this.addDrop(block); }
//  TERRACOTTA_BLOCKS
        for(Block block : ModBlocks.DYED_TERRACOTTA_BLOCKS) { this.addDrop(block); }
//  CONCRETE_BLOCKS
        for(Block block : ModBlocks.CONCRETE_BLOCKS) { this.addDrop(block); }
//  CONCRETE_POWDER_BLOCKS
        for(Block block : ModBlocks.CONCRETE_POWDER_BLOCKS) { this.addDrop(block); }
//  GLAZED_TERRACOTTA_BLOCKS
        for(Block block : ModBlocks.GLAZED_TERRACOTTA_BLOCKS) { this.addDrop(block); }
//  STAINED_GLASS_BLOCKS
        for(Block block : ModBlocks.STAINED_GLASS_BLOCKS) { this.addDropWithSilkTouch(block); }
//  STAINED_GLASS_PANE_BLOCKS
        for(Block block : ModBlocks.STAINED_GLASS_PANE_BLOCKS) { this.addDropWithSilkTouch(block); }
//  SHULKER_BOX_BLOCKS
        for(Block block : ModBlocks.SHULKER_BOX_BLOCKS) {  this.addDrop(block, (Block block2) -> this.shulkerBoxDrops((Block)block2)); }
//  BED_BLOCKS
//        for(Block block : ModBlocks.BED_BLOCKS) { }
//  CANDLE_BLOCKS
        for(Block block : ModBlocks.CANDLE_BLOCKS) { this.addDrop(ModBlocks.MINT_CANDLE, (Block block2) -> this.candleDrops((Block)block2)); }
//  CANDLE_CAKE_BLOCKS
//        for(Block block : ModBlocks.CANDLE_CAKE_BLOCKS) { }
//  BANNER_BLOCKS
        for(Block block : ModBlocks.BANNER_BLOCKS) { this.addDrop(block, (Block block2) -> this.bannerDrops((Block)block2)); }
//  WALL_BANNER_BLOCKS
//        for(Block block : ModBlocks.WALL_BANNER_BLOCKS) { }
    }
}
