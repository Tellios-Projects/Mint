package net.leafenzo.mint.block;

import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemConvertible;
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

public class TwoTallCropBlock extends CropBlock {
    public TwoTallCropBlock(Settings settings, int max_age) {
        super(settings);
        this.MAX_AGE = max_age;
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(HALF, DoubleBlockHalf.LOWER));
    }

    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]{
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

    public final int MAX_AGE;
    public static final IntProperty AGE = IntProperty.of("age", 0, CropBlock.MAX_AGE);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[Math.max(this.getAge(state), AGE_TO_SHAPE.length)]; //clamp to the last value in the array, as we will never need a larger hitbox for a single block of it
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf doubleBlockHalf = state.get(HALF);
        if (!(direction.getAxis() != Direction.Axis.Y || doubleBlockHalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || neighborState.isOf(this) && neighborState.get(HALF) != doubleBlockHalf)) {
            return Blocks.AIR.getDefaultState();
        }
        if (doubleBlockHalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
//
//    @Override
//    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        if (world.getBaseLightLevel(pos, 0) >= 9) {
//            int currentAge = this.getAge(state);
//            if (currentAge < this.getMaxAge()) {
//                float f = getAvailableMoisture(this, world, pos);
//                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
//                    if(currentAge == FIRST_STAGE_MAX_AGE) {
//                        if(world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
//                            world.setBlockState(pos.up(1), this.withAge(currentAge + 1), 2);
//                        }
//                    } else {
//                        world.setBlockState(pos, this.withAge(currentAge + 1), 2);
//                    }
//                }
//            }
//        }
//    }
//
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
//
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
    protected ItemConvertible getSeedsItem() {
        return null;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
