package net.leafenzo.mint.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoralAnemoneBlock extends PlantBlock
//        implements Fertilizable
{
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape COLLISION_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 3.0, 11.0);
    protected static final VoxelShape OUTLINE_SHAPE = Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 3.0, 11.0);
    private final RegistryKey<ConfiguredFeature<?, ?>> featureKey;

    public CoralAnemoneBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
        super(settings);
        this.featureKey = featureKey;
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, true));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION_SHAPE;
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE_SHAPE;
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        return (BlockState)super.getPlacementState(ctx).with(WATERLOGGED, bl);
    }
    public static boolean isDry(BlockState state) {
        return state.get(WATERLOGGED) == false;
    }

    @Override
    public boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return !floor.getCollisionShape(world, pos).getFace(Direction.UP).isEmpty() || floor.isSideSolidFullSquare(world, pos, Direction.UP);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return this.canPlantOnTop(world.getBlockState(blockPos), world, blockPos);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        if (state.get(WATERLOGGED).booleanValue()) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

//    @Override
//    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
//        return state.get(WATERLOGGED);
//    }
//    @Override
//    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
//        return state.get(WATERLOGGED);
//    }
//    @Override
//    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
//        Optional<RegistryEntry.Reference<ConfiguredFeature<?, ?>>> optional = world.getRegistryManager().get(RegistryKeys.CONFIGURED_FEATURE).getEntry(this.featureKey);
//        if (optional.isEmpty()) { return; }
//        //world.removeBlock(pos, false);
//        ((ConfiguredFeature)((RegistryEntry)optional.get()).value()).generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
//        world.setBlockState(pos, state, Block.NOTIFY_ALL);
//    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
}
