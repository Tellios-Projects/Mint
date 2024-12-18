package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.minecraft.block.*;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

public class BuddingCinnabarBlock extends Block {
    private static final Direction[] DIRECTIONS = Direction.values();

    public BuddingCinnabarBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockPos = pos.offset(direction);
            BlockState blockState = world.getBlockState(blockPos);
            Block block = null;
            if (canGrowIn(blockState)) {
                block = ElsDyeModBlocks.SMALL_CINNABAR_BUD;
            } else if (blockState.isOf(ElsDyeModBlocks.SMALL_CINNABAR_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = ElsDyeModBlocks.MEDIUM_CINNABAR_BUD;
            } else if (blockState.isOf(ElsDyeModBlocks.MEDIUM_CINNABAR_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = ElsDyeModBlocks.LARGE_CINNABAR_BUD;
            } else if (blockState.isOf(ElsDyeModBlocks.LARGE_CINNABAR_BUD) && blockState.get(AmethystClusterBlock.FACING) == direction) {
                block = ElsDyeModBlocks.CINNABAR_CLUSTER;
            }

            if (block != null) {
                BlockState blockState2 = block.getDefaultState().with(AmethystClusterBlock.FACING, direction).with(AmethystClusterBlock.WATERLOGGED, blockState.getFluidState().getFluid() == Fluids.WATER);
                world.setBlockState(blockPos, blockState2);
            }

        }
    }

    public static boolean canGrowIn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) && state.getFluidState().getLevel() == 8;
    }
}
