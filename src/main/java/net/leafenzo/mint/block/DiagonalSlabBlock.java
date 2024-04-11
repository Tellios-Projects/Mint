package net.leafenzo.mint.block;

import net.leafenzo.mint.state.property.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DiagonalSlabBlock extends SlabBlock {
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty DIAGONAL = ModProperties.DIAGONAL;

    public DiagonalSlabBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(DIAGONAL, false)
        );
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        float angle = ctx.getPlayerYaw();
        Direction lookDirection = getDirection(angle, ctx);

        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = ctx.getWorld().getBlockState(blockPos);
        if (blockState.isOf(this)) {
            return blockState.with(TYPE, SlabType.DOUBLE).with(WATERLOGGED, false).with(FACING, lookDirection).with(DIAGONAL, isDiagonal(angle));
        }
        FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
        BlockState blockState2 = this.getDefaultState().with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        Direction direction = ctx.getSide();
        if (direction == Direction.DOWN || direction != Direction.UP && ctx.getHitPos().y - (double)blockPos.getY() > 0.5) {
            return blockState2.with(TYPE, SlabType.TOP).with(FACING, lookDirection).with(DIAGONAL, isDiagonal(angle));
        }
        return blockState2.with(FACING, lookDirection).with(DIAGONAL, isDiagonal(angle));
    }

    protected static boolean isDiagonal(float angle) {
        return (!(angle > -180.0f) || !(angle < -157.5f))
                && (!(angle > -112.5f) || !(angle < -67.5f))
                && (!(angle > -22.5f) || !(angle < 22.5f))
                && (!(angle > 67.5f) || !(angle < 112.5f))
                && (!(angle > 157.5f) || !(angle < 180f));
    }

    protected static Direction getDirection(float angle, ItemPlacementContext ctx) {
        if (isDiagonal(angle)) {
            if (-157.5f < angle && angle < -112.5f) {
                return Direction.WEST;
            }
            if (-67.5f < angle && angle < -22.5f) {
                return Direction.NORTH;
            }
            if (22.5f < angle && angle < 67.5f) {
                return Direction.EAST;
            }
            if (112.5f < angle && angle < 157.5f) {
                return Direction.SOUTH;
            }
        }
        return ctx.getHorizontalPlayerFacing().getOpposite();
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING).add(DIAGONAL);
    }
}
