package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.state.property.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class DiagonalBlock
    extends Block {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty DIAGONAL = ModProperties.DIAGONAL;

    public DiagonalBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)(BlockState)this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(DIAGONAL, false)
        );
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
//        boolean isWall = isWall(ctx);
//        Direction lookDirection = isWall ? ctx.getVerticalPlayerLookDirection() : ctx.getHorizontalPlayerFacing();
//        float angle = isWall ?
//                (ctx.getPlayer().getBlockY() - ctx.getBlockPos().getY()) * 90
//                : ctx.getPlayerYaw();

        float angle = ctx.getPlayerYaw();
        Direction direction = getDirection(angle, ctx);
        return this.getDefaultState().with(FACING, direction).with(DIAGONAL, isDiagonal(angle));
    }

    protected boolean isWall(ItemPlacementContext ctx) {
        //If the same block is below or above
        return ctx.getWorld().testBlockState(ctx.getBlockPos().down(), blockState -> blockState.isOf(this)) || ctx.getWorld().testBlockState(ctx.getBlockPos().up(), blockState -> blockState.isOf(this));
    }

    protected static boolean isDiagonal(float angle) {
        if (
            angle > -180.0f && angle < -157.5f
         || angle > -112.5f && angle < -67.5f
         || angle > -22.5f && angle < 22.5f
         || angle > 67.5f && angle < 112.5f
         || angle > 157.5f && angle < 180f
        ){
            return false;
        }
        //else
        return true;
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
        builder.add(FACING).add(DIAGONAL);
    }

}
