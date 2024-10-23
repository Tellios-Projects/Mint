package net.leafenzo.mint.block.custom;

import com.google.common.collect.Sets;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

public class NeonTubeBlock extends PillarBlock {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final IntProperty POWER = Properties.POWER;
    private boolean givesPower = true;

    //TODO Makeme an alternative to redstone wire

    public NeonTubeBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWER, 0));
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }

    //public int getRedstonePower(BlockView world, BlockPos pos, Direction direction)

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return false; //because it requires a neon exciter to interact with regular redstone wire and components
    }

//    @Override
//    public int getWeakRedstonePower(BlockView world, BlockPos pos, Direction direction) {
//        return this.getBlock().getWeakRedstonePower(this.asBlockState(), world, pos, direction);
//    }


    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(AXIS, ctx.getSide().getAxis());
    }

    private BlockState getPlacementState(BlockView world, BlockState state, BlockPos pos) {
        return this.getDefaultState()
                .with(POWER, state.get(POWER))
                .with(LIT, state.get(POWER) > 0);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void prepare(BlockState state, WorldAccess world, BlockPos pos, int flags, int maxUpdateDepth) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(mutable.set(pos, direction)).getBlock() != this.asBlock()) continue;
            Vec3i blockPos = mutable.offset(direction.getOpposite());
            world.replaceWithStateForNeighborUpdate(direction.getOpposite(), world.getBlockState((BlockPos)blockPos), mutable, (BlockPos)blockPos, flags, maxUpdateDepth);

            mutable.move(Direction.DOWN);
            BlockState blockState = world.getBlockState(mutable);
            if (blockState.isOf(this)) {
                Vec3i blockPos1 = mutable.offset(direction.getOpposite());
                world.replaceWithStateForNeighborUpdate(direction.getOpposite(), world.getBlockState((BlockPos)blockPos1), mutable, (BlockPos)blockPos1, flags, maxUpdateDepth);
            }
            mutable.set(pos, direction).move(Direction.UP);
            BlockState blockState2 = world.getBlockState(mutable);
            if (!blockState2.isOf(this)) continue;
            Vec3i blockPos2 = mutable.offset(direction.getOpposite());
            world.replaceWithStateForNeighborUpdate(direction.getOpposite(), world.getBlockState((BlockPos)blockPos2), mutable, (BlockPos)blockPos2, flags, maxUpdateDepth);
        }
    }


    private int flickerTimer;
    private void update(World world, BlockPos pos, BlockState state) {
        int i = this.getReceivedRedstonePower(world, pos);
        if (state.get(POWER) != i) {
            if (world.getBlockState(pos) == state) {
                world.setBlockState(pos, state.with(POWER, i), Block.NOTIFY_LISTENERS);
            }
            HashSet<BlockPos> set = Sets.newHashSet();
            set.add(pos);
            for (Direction direction : Direction.values()) {
                set.add(pos.offset(direction));
            }
            for (BlockPos blockPos : set) {
                world.updateNeighborsAlways(blockPos, this);
            }

//            if (state.get(POWER) > 0) {
//                flickerTimer = flickerSwitchConstant.length;
//            }
        }

//        while(flickerTimer > 0) {
//            flickerTimer--;
//            world.setBlockState(pos, (BlockState)state.with(LIT, flickerSwitchConstant[flickerTimer]));
//        }
    }

    /**
     * wowie this sucks
     */
    private static final boolean[] flickerSwitchConstant = {
            false, false, false, false, false, false, false, false,
            true, true, true, 
            false, false, 
            true, true, 
            false, false, false, false,
            true, true, true, true,
            false, false,
            true, true, true,
            false, false, false, false,
            true, true, true, true, true, true, true, true,
            false, false,
            true, true,
            false, false,
    };

    private int getReceivedRedstonePower(World world, BlockPos pos) {
        this.givesPower = false;
        int i = world.getReceivedRedstonePower(pos);
        this.givesPower = true;
        int j = 0;
        if (i < 15) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                BlockPos blockPos = pos.offset(direction);
                BlockState blockState = world.getBlockState(blockPos);
                j = Math.max(j, this.increasePower(blockState));
                BlockPos blockPos2 = pos.up();
                if (blockState.isSolidBlock(world, blockPos) && !world.getBlockState(blockPos2).isSolidBlock(world, blockPos2)) {
                    j = Math.max(j, this.increasePower(world.getBlockState(blockPos.up())));
                    continue;
                }
                if (blockState.isSolidBlock(world, blockPos)) continue;
                j = Math.max(j, this.increasePower(world.getBlockState(blockPos.down())));
            }
        }
        return Math.max(i, j - 1);
    }

    private void updateNeighbors(World world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() != this.asBlock()) {
            return;
        }

        world.updateNeighborsAlways(pos, this);
        for (Direction direction : Direction.values()) {
            world.updateNeighborsAlways(pos.offset(direction), this);
        }
    }

    private int increasePower(BlockState state) {
        return state.isOf(this) ? state.get(POWER) : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(POWER);
        builder.add(LIT);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (oldState.isOf(state.getBlock()) || world.isClient) {
            return;
        }
        this.update(world, pos, state);
        for (Direction direction : Direction.values()) {
            if(world.getBlockState(pos.offset(direction)).getBlock() == this.asBlock()) {
                world.updateNeighborsAlways(pos.offset(direction), this);
            }
        }
        this.updateNeighbors(world, pos);
    }

//    @Override
//    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
//        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
//    }

}
