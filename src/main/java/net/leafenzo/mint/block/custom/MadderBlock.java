package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class MadderBlock extends PlantBlock implements Fertilizable {
    public MadderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(3, 0, 3, 13, 8, 13);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int rootRadius = 4;
        for (int depth = 0; depth < rootRadius; depth++) {
            tryPlaceMadderRoot(world, pos.down(depth));
            for (int i = depth-1; i > -depth; i--) {
                for (int j = depth-1; j > -depth; j--) {
                    if (random.nextBetween(0, depth) < 2 + (depth/2)) {
                        tryPlaceMadderRoot(world, pos.down(depth).add(i, 0, j));
                    }
                }
            }
        }
    }

    private void tryPlaceMadderRoot(World world, BlockPos pos) {
        if (world.testBlockState(pos, blockState -> blockState.isOf(Blocks.GRASS_BLOCK)) || world.testBlockState(pos, blockState -> blockState.isOf(ModBlocks.MADDER_ROOTED_GRASS_BLOCK))) {
            world.setBlockState(pos, ModBlocks.MADDER_ROOTED_GRASS_BLOCK.getDefaultState());
        } else if (world.testBlockState(pos, blockState -> blockState.isIn(BlockTags.DIRT))) {
            world.setBlockState(pos, ModBlocks.MADDER_ROOTED_DIRT.getDefaultState());
        }
    }
}
