package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PineappleStemBlock extends PlantBlock implements Fertilizable {
    public static final int MAX_AGE = 5;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    private static final VoxelShape[] AGE_TO_SHAPE_UPPER = new VoxelShape[]{
            Block.createCuboidShape(6, 0, 6, 10, 4, 10),
            Block.createCuboidShape(5, 0, 5, 11, 6, 11),
            Block.createCuboidShape(3, 0, 3, 13, 10, 13),
            Block.createCuboidShape(2, 0, 2, 14, 12, 14),
            Block.createCuboidShape(2, 0, 2, 14, 16, 14),
            Block.createCuboidShape(2, 0, 2, 14, 16, 14)
    };

    public PineappleStemBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(AGE, 0));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.1f) {
            if (state.get(AGE) < 4) {
                world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
            }
            if (state.get(AGE) == 4) {
                BlockPos pineapplePos = pos.offset(Direction.UP);
                if ((world.getBlockState(pineapplePos).isAir())) {
                    world.setBlockState(pineapplePos, ElsDyeModBlocks.PINEAPPLE.getDefaultState(), 2);
                    world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
                }
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE_UPPER[state.get(AGE)];
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (isFullyGrown(state) && !world.getBlockState(pos.up()).isOf(ElsDyeModBlocks.PINEAPPLE)) {
            return state.with(AGE, 4);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return state.get(AGE) < 4;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (state.get(AGE) < 4) {
            world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
        }
    }

    protected static boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == MAX_AGE;
    }
}
