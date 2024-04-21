package net.leafenzo.mint.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class ReversiblePillarBlock
        extends Block {

    public static final DirectionProperty FACING = Properties.FACING;

    public ReversiblePillarBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        PlayerEntity player = ctx.getPlayer();
        if(player == null) { return this.getDefaultState().with(FACING, ctx.getSide()); } // Don't know what situation there wouldn't be a player doing this, but whatever just in case ig

        if(player.isSneaking()) {
            return this.getDefaultState().with(FACING, ctx.getSide().getOpposite());
        }
        else {
            return this.getDefaultState().with(FACING, ctx.getSide());
        }
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
