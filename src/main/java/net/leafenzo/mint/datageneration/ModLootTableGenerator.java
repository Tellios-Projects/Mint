package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    public LootTable.Builder wildMintDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder)ItemEntry.builder(ModItems.MINT_SPRIG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))));
    }
    @Override
    public void generate() {
        //  MINT - Special
        this.addDrop(ModBlocks.WILD_MINT, (Block block) -> this.wildMintDrops(ModBlocks.WILD_MINT));
        BlockStatePropertyLootCondition.Builder mintCropBuilder = BlockStatePropertyLootCondition.builder(ModBlocks.MINT_CROP).properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, MintCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.MINT_CROP, this.applyExplosionDecay(ModBlocks.MINT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItems.MINT_SPRIG))).pool(LootPool.builder().conditionally(mintCropBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(ModItems.MINT_SPRIG).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3)))))));
        this.addDrop(ModBlocks.MINT_SPRIG_BLOCK);
        this.addDrop(ModBlocks.MINT_BRICKS);
        this.addDrop(ModBlocks.MINT_BRICK_SLAB);
        this.addDrop(ModBlocks.MINT_BRICK_STAIRS);

        //  PEACH - Special
        // TODO this stuff


        //  PERIWINKLE - Special
        this.addDrop(ModBlocks.LAVENDER_BRICKS);
        this.addDrop(ModBlocks.LAVENDER_BRICK_SLAB);
        this.addDrop(ModBlocks.LAVENDER_BRICK_STAIRS);
        this.addDrop(ModBlocks.LAVENDER_BRICK_WALL);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICKS);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_SLAB);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
        this.addDrop(ModBlocks.MOSSY_LAVENDER_BRICK_WALL);
        this.addDrop(ModBlocks.LAVENDER_CLAY);
        this.addDrop(ModBlocks.LAVENDER_BUSHEL);
        this.addDrop(ModBlocks.PERIWINKLE_PETALS, this.flowerbedDrops(ModBlocks.PERIWINKLE_PETALS));
        this.addDrop(ModBlocks.LAVENDER_OIL_LANTERN, this::drops);

        //  ARTICHOKE - Special

        //  Main
//  WOOL_BLOCKS
        for(Block block : ModBlocks.WOOL_BLOCKS) { this.addDrop(block); }
//  CARPET_BLOCKS
        for(Block block : ModBlocks.WOOL_CARPET_BLOCKS) { this.addDrop(block); }
//  TERRACOTTA_BLOCKS
        for(Block block : ModBlocks.TERRACOTTA_BLOCKS) { this.addDrop(block); }
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



        //this.addDrop(ModBlocks.BLAZE_ROD_BLOCK);
        //this.addDrop(ModBlocks.GRASS_CLIPPINGS_BLOCK, (Block block) -> this.drops((Block)block, block));
        //this.addDrop(ModBlocks.BOOK_BLOCK, (Block block) -> this.drops((Block)block, Items.BOOK, ConstantLootNumberProvider.create(9.0f)));
        //this.addDrop(ModBlocks.COMPRESSED_OAK_LEAVES, (Block block) -> this.leavesDrops((Block)block, Blocks.OAK_SAPLING, SAPLING_DROP_CHANCE));
        //this.addDropWithSilkTouch(ModBlocks.EGG_BLOCK);
    }
}
