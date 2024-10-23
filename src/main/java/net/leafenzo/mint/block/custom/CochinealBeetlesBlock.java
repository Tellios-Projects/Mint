package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CochinealBeetlesBlock extends FacingBlock {
    public static final IntProperty AGE = Properties.AGE_3;
    public CochinealBeetlesBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)) {
            case UP -> {
                return Block.createCuboidShape(0, 0, 0, 16, 1, 16);
            }
            case DOWN -> {
                return Block.createCuboidShape(0, 15, 0, 16, 16, 16);
            }
            case NORTH -> {
                return Block.createCuboidShape(0, 0, 15, 16, 16, 16);
            }
            case EAST -> {
                return Block.createCuboidShape(0, 0, 0, 1, 16, 16);
            }
            case SOUTH -> {
                return Block.createCuboidShape(0, 0, 0, 16, 16, 1);
            }
            case WEST -> {
                return Block.createCuboidShape(15, 0, 0, 16, 16, 16);
            }
        }
        return VoxelShapes.fullCube();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return canPlaceAt(state, world, pos) ? super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos) : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.offset(state.get(FACING).getOpposite(), 1)).isSideSolidFullSquare(world, pos, state.get(FACING)) || world.getBlockState(pos.offset(state.get(FACING).getOpposite(), 1)).isOf(Blocks.CACTUS);
    }

    public boolean isOnCactusFeed(BlockState state, World world, BlockPos pos) {
        return world.getBlockState(pos.offset(state.get(FACING).getOpposite())).isOf(ElsDyeModBlocks.CACTUS_FEED);
    }

    public void spreadBeetles(BlockState state, World world, BlockPos pos) {
        Direction facing = state.get(FACING);
        List<Direction> dirsToCheck = new ArrayList<>(List.of());
        if (facing == Direction.UP || facing == Direction.DOWN) {
            dirsToCheck.add(Direction.NORTH);
            dirsToCheck.add(Direction.EAST);
            dirsToCheck.add(Direction.SOUTH);
            dirsToCheck.add(Direction.WEST);
        }
        if (facing == Direction.NORTH || facing == Direction.SOUTH) {
            dirsToCheck.add(Direction.UP);
            dirsToCheck.add(Direction.EAST);
            dirsToCheck.add(Direction.DOWN);
            dirsToCheck.add(Direction.WEST);
        }
        if (facing == Direction.EAST || facing == Direction.WEST) {
            dirsToCheck.add(Direction.UP);
            dirsToCheck.add(Direction.NORTH);
            dirsToCheck.add(Direction.DOWN);
            dirsToCheck.add(Direction.SOUTH);
        }
        int randDir = world.random.nextBetween(0, 3);
        if (world.getBlockState(pos.offset(dirsToCheck.get(randDir)).offset(facing.getOpposite())).isOf(ElsDyeModBlocks.CACTUS_FEED) && world.getBlockState(pos.offset(dirsToCheck.get(randDir))).isAir()) {
            world.setBlockState(pos.offset(dirsToCheck.get(randDir)), state.with(AGE, 0));
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!isFullyGrown(state)) {
            if (isOnCactusFeed(state, world, pos)) {
                world.setBlockState(pos, state.cycle(AGE));
            } else if (random.nextFloat() < 0.2f) {
                world.setBlockState(pos, state.cycle(AGE));
            }
        } else if (isOnCactusFeed(state, world, pos)) {
            if (random.nextFloat() < 0.2f) {
                spreadBeetles(state, world, pos);
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isFullyGrown(state)) {
            dropStack(world, pos, new ItemStack(ElsDyeModItems.CARMINIC_COCHINEAL_BEETLE, world.random.nextBetween(1, 3)));
            world.setBlockState(pos, state.with(AGE, 0));
            world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat()/3);
            return ActionResult.SUCCESS;
        }
        if (player.getStackInHand(hand).isOf(ElsDyeModItems.CACTUS_CHUNK)) {
            world.setBlockState(pos, state.cycle(AGE));
            if (!player.isCreative()) {
                player.getStackInHand(hand).decrement(1);
            }
            world.playSound(null, pos, SoundEvents.ENTITY_FROG_EAT, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat()/3);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    public boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == 3;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
        super.appendProperties(builder);
    }
}
