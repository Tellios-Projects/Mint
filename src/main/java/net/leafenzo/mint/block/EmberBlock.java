package net.leafenzo.mint.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public class EmberBlock extends Block implements Waterloggable {
    private static final VoxelShape UP_SHAPE = Block.createCuboidShape(0.0, 15.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape DOWN_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 1.0, 16.0, 16.0);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(15.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 1.0);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(0.0, 0.0, 15.0, 16.0, 16.0, 16.0);
    private static final Map<Direction, BooleanProperty> FACING_PROPERTIES;
    private static final Map SHAPES_FOR_DIRECTIONS;
    protected static final Direction[] DIRECTIONS;
    private final ImmutableMap<BlockState, VoxelShape> SHAPES;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final boolean hasAllHorizontalDirections;
    private final boolean canMirrorX;
    private final boolean canMirrorZ;

    public EmberBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(withAllDirections(this.stateManager));
        this.SHAPES = this.getShapesForStates(EmberBlock::getShapeForState);
        this.hasAllHorizontalDirections = Direction.Type.HORIZONTAL.stream().allMatch(this::canHaveDirection);
        this.canMirrorX = Direction.Type.HORIZONTAL.stream().filter(Direction.Axis.X).filter(this::canHaveDirection).count() % 2L == 0L;
        this.canMirrorZ = Direction.Type.HORIZONTAL.stream().filter(Direction.Axis.Z).filter(this::canHaveDirection).count() % 2L == 0L;
    }

    public static Set<Direction> collectDirections(BlockState state) {
        if (!(state.getBlock() instanceof EmberBlock)) {
            return Set.of();
        } else {
            Set<Direction> set = EnumSet.noneOf(Direction.class);
            Direction[] var2 = Direction.values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Direction direction = var2[var4];
                if (hasDirection(state, direction)) {
                    set.add(direction);
                }
            }

            return set;
        }
    }

    protected boolean canHaveDirection(Direction direction) {
        return true;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        Direction[] var2 = DIRECTIONS;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Direction direction = var2[var4];
            if (this.canHaveDirection(direction)) {
                builder.add(new Property[]{getProperty(direction)});
            }
        }
        builder.add(WATERLOGGED);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if ((Boolean)state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (!hasAnyDirection(state)) {
            return Blocks.AIR.getDefaultState();
        } else {
            return hasDirection(state, direction) && !canPlaceOn(world, direction, neighborPos, neighborState) ? disableDirection(state, getProperty(direction)) : state;
        }
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape)this.SHAPES.get(state);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        boolean bl = false;
        Direction[] var5 = DIRECTIONS;
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Direction direction = var5[var7];
            if (hasDirection(state, direction)) {
                BlockPos blockPos = pos.offset(direction);
                if (!canPlaceOn(world, direction, blockPos, world.getBlockState(blockPos))) {
                    return false;
                }

                bl = true;
            }
        }

        return bl;
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return isNotFullBlock(state);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        return Arrays.stream(ctx.getPlacementDirections()).map((direction) -> {
            return this.withDirection(blockState, world, blockPos, direction);
        }).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public boolean canGrowWithDirection(BlockView world, BlockState state, BlockPos pos, Direction direction) {
        if (this.canHaveDirection(direction) && (!state.isOf(this) || !hasDirection(state, direction))) {
            BlockPos blockPos = pos.offset(direction);
            return canPlaceOn(world, direction, blockPos, world.getBlockState(blockPos));
        } else {
            return false;
        }
    }

    @Nullable
    public BlockState withDirection(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if (!this.canGrowWithDirection(world, state, pos, direction)) {
            return null;
        } else {
            BlockState blockState;
            if (state.isOf(this)) {
                blockState = state;
            } else if (this.isWaterlogged() && state.getFluidState().isEqualAndStill(Fluids.WATER)) {
                blockState = this.getDefaultState().with(net.minecraft.state.property.Properties.WATERLOGGED, true);
            } else {
                blockState = this.getDefaultState();
            }

            return blockState.with(getProperty(direction), true);
        }
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        if (!this.hasAllHorizontalDirections) {
            return state;
        } else {
            Objects.requireNonNull(rotation);
            return this.mirror(state, rotation::rotate);
        }
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        if (mirror == BlockMirror.FRONT_BACK && !this.canMirrorX) {
            return state;
        } else if (mirror == BlockMirror.LEFT_RIGHT && !this.canMirrorZ) {
            return state;
        } else {
            Objects.requireNonNull(mirror);
            return this.mirror(state, mirror::apply);
        }
    }

    private BlockState mirror(BlockState state, Function<Direction, Direction> mirror) {
        BlockState blockState = state;
        Direction[] var4 = DIRECTIONS;
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            if (this.canHaveDirection(direction)) {
                blockState = (BlockState)blockState.with(getProperty((Direction)mirror.apply(direction)), (Boolean)state.get(getProperty(direction)));
            }
        }

        return blockState;
    }

    public static boolean hasDirection(BlockState state, Direction direction) {
        BooleanProperty booleanProperty = getProperty(direction);
        return state.contains(booleanProperty) && (Boolean)state.get(booleanProperty);
    }

    public static boolean canPlaceOn(BlockView world, Direction direction, BlockPos pos, BlockState state) {
        return Block.isFaceFullSquare(state.getSidesShape(world, pos), direction.getOpposite()) || Block.isFaceFullSquare(state.getCollisionShape(world, pos), direction.getOpposite());
    }

    private boolean isWaterlogged() {
        return this.stateManager.getProperties().contains(Properties.WATERLOGGED);
    }

    private static BlockState disableDirection(BlockState state, BooleanProperty direction) {
        BlockState blockState = (BlockState)state.with(direction, false);
        return hasAnyDirection(blockState) ? blockState : Blocks.AIR.getDefaultState();
    }

    public static BooleanProperty getProperty(Direction direction) {
        return (BooleanProperty)FACING_PROPERTIES.get(direction);
    }

    private static BlockState withAllDirections(StateManager<Block, BlockState> stateManager) {
        BlockState blockState = (BlockState)stateManager.getDefaultState();
        Iterator var2 = FACING_PROPERTIES.values().iterator();

        while(var2.hasNext()) {
            BooleanProperty booleanProperty = (BooleanProperty)var2.next();
            if (blockState.contains(booleanProperty)) {
                blockState = (BlockState)blockState.with(booleanProperty, false);
            }
        }

        return blockState;
    }

    private static VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelShape = VoxelShapes.empty();
        Direction[] var2 = DIRECTIONS;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Direction direction = var2[var4];
            if (hasDirection(state, direction)) {
                voxelShape = VoxelShapes.union(voxelShape, (VoxelShape)SHAPES_FOR_DIRECTIONS.get(direction));
            }
        }

        return voxelShape.isEmpty() ? VoxelShapes.fullCube() : voxelShape;
    }

    protected static boolean hasAnyDirection(BlockState state) {
        return Arrays.stream(DIRECTIONS).anyMatch((direction) -> {
            return hasDirection(state, direction);
        });
    }

    private static boolean isNotFullBlock(BlockState state) {
        return Arrays.stream(DIRECTIONS).anyMatch((direction) -> {
            return !hasDirection(state, direction);
        });
    }

    static {
        FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES;
        SHAPES_FOR_DIRECTIONS = Util.make(Maps.newEnumMap(Direction.class), (shapes) -> {
            shapes.put(Direction.NORTH, SOUTH_SHAPE);
            shapes.put(Direction.EAST, WEST_SHAPE);
            shapes.put(Direction.SOUTH, NORTH_SHAPE);
            shapes.put(Direction.WEST, EAST_SHAPE);
            shapes.put(Direction.UP, UP_SHAPE);
            shapes.put(Direction.DOWN, DOWN_SHAPE);
        });
        DIRECTIONS = Direction.values();
    }
}
