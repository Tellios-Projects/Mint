package net.leafenzo.template.datageneration;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.template.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        //this.addDrop(ModBlocks.BLAZE_ROD_BLOCK);
        //this.addDrop(ModBlocks.GRASS_CLIPPINGS_BLOCK, (Block block) -> this.drops((Block)block, block));
        //this.addDrop(ModBlocks.BOOK_BLOCK, (Block block) -> this.drops((Block)block, Items.BOOK, ConstantLootNumberProvider.create(9.0f)));
        //this.addDrop(ModBlocks.COMPRESSED_OAK_LEAVES, (Block block) -> this.leavesDrops((Block)block, Blocks.OAK_SAPLING, SAPLING_DROP_CHANCE));
        //this.addDropWithSilkTouch(ModBlocks.EGG_BLOCK);
    }
}
