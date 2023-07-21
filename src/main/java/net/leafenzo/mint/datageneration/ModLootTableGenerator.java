package net.leafenzo.mint.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.predicate.StatePredicate;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        this.addDrop(ModBlocks.MINT, (Block block) -> this.cropDrops(block, ModItems.MINT_SPRIG, ModItems.MINT_SEEDS,
                BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, 6))
        ));
        this.addDrop(ModBlocks.MINT_WOOL);
        this.addDrop(ModBlocks.MINT_CONCRETE);
        this.addDropWithSilkTouch(ModBlocks.MINT_STAINED_GLASS);
        //this.addDropWithSilkTouch(ModBlocks.MINT_STAINED_GLASS_PANE); //TODO ADDME
        this.addDrop(ModBlocks.MINT_GLAZED_TERRACOTTA);


        //this.addDrop(ModBlocks.BLAZE_ROD_BLOCK);
        //this.addDrop(ModBlocks.GRASS_CLIPPINGS_BLOCK, (Block block) -> this.drops((Block)block, block));
        //this.addDrop(ModBlocks.BOOK_BLOCK, (Block block) -> this.drops((Block)block, Items.BOOK, ConstantLootNumberProvider.create(9.0f)));
        //this.addDrop(ModBlocks.COMPRESSED_OAK_LEAVES, (Block block) -> this.leavesDrops((Block)block, Blocks.OAK_SAPLING, SAPLING_DROP_CHANCE));
        //this.addDropWithSilkTouch(ModBlocks.EGG_BLOCK);
    }
}
