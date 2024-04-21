package net.leafenzo.mint.item;

import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.PineappleStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.jetbrains.annotations.Nullable;

public class PineappleCrownItem extends AliasedBlockItem {
    public PineappleCrownItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Nullable
    @Override
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = this.getBlock().getPlacementState(context);
        return blockState != null && context.getWorld().getBlockState(context.getBlockPos().down()).isOf(Blocks.FARMLAND) ?
                ModBlocks.PINEAPPLE_STEM.getDefaultState().with(PineappleStemBlock.AGE, 0) :
                super.getPlacementState(context);
    }
}
