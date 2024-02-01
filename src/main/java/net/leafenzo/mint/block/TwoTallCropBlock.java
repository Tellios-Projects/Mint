package net.leafenzo.mint.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class TwoTallCropBlock extends CropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public TwoTallCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HALF, DoubleBlockHalf.LOWER));
    }
    private static final VoxelShape[] AGE_TO_SHAPE_UPPER = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 2.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 2.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 2.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 4.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 6.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 8.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 10.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 14.0, 11.0)
    };
    private static final VoxelShape[] AGE_TO_SHAPE_LOWER = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 4.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 8.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 14.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 16.0, 11.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 11.0, 16.0, 11.0)
    };
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? AGE_TO_SHAPE_UPPER[this.getAge(state)] : AGE_TO_SHAPE_LOWER[this.getAge(state)];
    }
    public int getAgeToGrowUpperHalf() {
        return 3;
    }
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return null;
    }
    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // return if we are the upper half, the lower half is responsible for growing us
        if(state.get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) { return; }

        // return and do not grow if the growing conditions are not met
        if(world.getBaseLightLevel(pos, 0) < 9 /*|| random.nextInt((int) (25.0f / (CropBlock.getAvailableMoisture(this, world, pos))) + 1) != 0*/) { return; }

        // when we are young, growing is guaranteed if conditions are alright
        int i;
        if((i = this.getAge(state)) < this.getAgeToGrowUpperHalf()-1) {
            world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
        }
        // try to grow and place the upper block, but we cannot grow if the upper block would be obscured
        else if ((i = this.getAge(state)) == this.getAgeToGrowUpperHalf()-1) {
            if(this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).canPlaceAt(world, pos.up()) && world.getBlockState(pos.up()).isAir()) {
                world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                world.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(AGE, i + 1), Block.NOTIFY_LISTENERS);
            }
        }
        // if we are old enough, try to grow both halves-
        else if(this.getAge(state) >= this.getAgeToGrowUpperHalf() && (i = this.getAge(state)) < this.getMaxAge()) {
            BlockState upperState = world.getBlockState(pos.up());
                // -but only grow either if there is a top half at all
            if(upperState.isOf(this) && upperState.get(HALF) == DoubleBlockHalf.UPPER) {
                if(upperState.isOf(this) && upperState.get(HALF) == DoubleBlockHalf.UPPER) {
                    world.setBlockState(pos.up(), upperState.with(AGE,i + 1), Block.NOTIFY_LISTENERS);
                }
                world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
            }
        }
    }

    // TODO Make it so that you can bonemeal the lower block (but only if the upper block still can grow)
    // TODO Make me plantable even if there is a block one above me, and make me so I'm only destroyed on my neighbor breaking if that neighbor is actually part of me.
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(HALF) == DoubleBlockHalf.UPPER) {
            BlockState blockState = world.getBlockState(pos.down());
            return blockState.isOf(this) && blockState.get(HALF) == DoubleBlockHalf.LOWER;
        }
        return super.canPlaceAt(state, world, pos);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.get(HALF);
        if (    !(
                direction.getAxis() != Direction.Axis.Y // ignore if updating neighbor is not either above or below i
                || direction == Direction.UP != (half == DoubleBlockHalf.LOWER && neighborState.isAir() && state.get(AGE) >= this.getAgeToGrowUpperHalf()) // ignore if its updating above the upper block, or if it's updating above the lower block while it's not grown enough to have an upper half
                || neighborState.isOf(this) && neighborState.get(HALF) != half)
        ) {
            return Blocks.AIR.getDefaultState();
        }
        if (half == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) { //break the lower segment if the block below is broken
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient) {
            if (player.isCreative()) {
                this.onBreakInCreative(world, pos, state, player);
            } else {
                this.dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }
        super.onBreak(world, pos, state, player);
    }
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, tool);
    }
    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockPos blockPos;
        BlockState blockState;
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (doubleBlockHalf == DoubleBlockHalf.UPPER && (blockState = world.getBlockState(blockPos = pos.down())).isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
            BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
            world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
            world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
        }
    }

    //    @Override
//    public void applyGrowth(World world, BlockPos pos, BlockState state) {
//        int nextAge = this.getAge(state) + this.getGrowthAmount(world);
//        int maxAge = this.getMaxAge();
//        if(nextAge > maxAge) {
//            nextAge = maxAge;
//        }
//
//        if(this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
//            world.setBlockState(pos.up(1), this.withAge(nextAge), 2);
//        } else {
//            world.setBlockState(pos, this.withAge(nextAge - 1), 2);
//        }
//    }

//    @Override
//    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        if (!world.isClient && player.isCreative()) {
//            TallPlantBlock.onBreakInCreative(world, pos, state, player);
//        }
//        super.onBreak(world, pos, state, player);
//    }
//
//    protected static void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
//        BlockPos blockPos;
//        BlockState blockState;
//        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
//        if (doubleBlockHalf == DoubleBlockHalf.UPPER && (blockState = world.getBlockState(blockPos = pos.down())).isOf(state.getBlock()) && blockState.get(HALF) == DoubleBlockHalf.LOWER) {
//            BlockState blockState2 = blockState.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
//            world.setBlockState(blockPos, blockState2, Block.NOTIFY_ALL | Block.SKIP_DROPS);
//            world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
//        }
//    }
//
//    @Override
//    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
//        return super.canPlaceAt(state, world, pos) || (world.getBlockState(pos.down(1)).isOf(this) &&
//                world.getBlockState(pos.down(1)).get(AGE) == 7);
//    }
//
//    @Override
//    public int getMaxAge() {
//        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
//    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }
}
