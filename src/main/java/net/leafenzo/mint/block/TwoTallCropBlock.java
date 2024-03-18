package net.leafenzo.mint.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.event.GameEvent;
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
    public int getAgeToHarvestTo() { return 4; }
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
    public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }
    public int getHarvestAmount(BlockState state, Random random) {
        int age = this.getAge(state);
        return age < this.getMaxAge() ? 0 : 1 + random.nextInt(2);
    }
    public ItemConvertible getDroppedHarvestItem() { return null; }
    public boolean trySetAge(BlockState state, World world, BlockPos pos, int age) {
        int prevAge = this.getAge(state);
        if(age < prevAge) {
            BlockState b = this.tryGetOtherHalf(state, world, pos);
            if (b == null) { world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS); }
            else {
                BlockPos pos2 = (state.get(HALF) == DoubleBlockHalf.UPPER) ? pos.down() : pos.up();
                if (age < this.getAgeToGrowUpperHalf()) {
                    world.setBlockState(pos2, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                    world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
                } else {
                    world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
                    world.setBlockState(pos2, b.with(AGE, age), Block.NOTIFY_LISTENERS);
                }
            }
        }
        else if(age > prevAge) {
            BlockState b = this.tryGetOtherHalf(state, world, pos);
            if (b != null) { // if there is two halves already
                BlockPos pos2 = (state.get(HALF) == DoubleBlockHalf.UPPER) ? pos.down() : pos.up();
                world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
                world.setBlockState(pos2, b.with(AGE, age), Block.NOTIFY_LISTENERS);
            }
            else if(state.get(HALF) == DoubleBlockHalf.LOWER && age >= this.getAgeToGrowUpperHalf()) { // if we need to grow an upper half
                if(this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).canPlaceAt(world, pos.up()) && world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
                    world.setBlockState(pos.up(), this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).with(AGE, age), Block.NOTIFY_LISTENERS);
                }
                else {
                    return false; //growth has failed
                }
            }
            else { // if there's only the one half
                world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
            }
        }
        // else, do nothing when the new age is the same as the prevAge

        return true; // return true if we haven't failed
    }
    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
    }
    public float getChanceToGrow(BlockState state) {
        return 1.0f;
    }
    public float getChanceToFertilize(World world, BlockPos pos, BlockState state) {
        return 1.0f;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // return if we are the upper half, random ticks only trigger for the lower half, or else this plant would grow at double the speed one would expect.
        if(state.get(Properties.DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) { return; }
        // return and do not grow if the growing conditions are not met
        if(world.getBaseLightLevel(pos, 0) < 9 /*|| random.nextInt((int) (25.0f / (CropBlock.getAvailableMoisture(this, world, pos))) + 1) != 0*/) { return; }
        if(this.getChanceToGrow(state) < random.nextFloat()) { return; }

        //grow
        this.tryGrow(state, world, pos);
    }
    public void tryGrow(BlockState state, ServerWorld world, BlockPos pos) {
        int currAge = this.getAge(state);
        if(currAge + 1 <= this.getMaxAge()) {
            this.trySetAge(state, world, pos, currAge + 1);
        }
    }
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
                || direction == Direction.DOWN && !neighborState.isOf(this)
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
                int j = this.getHarvestAmount(state, world.getRandom());
                dropFruit(j, world, pos);
                this.dropStacks(state, world, pos, null, player, player.getMainHandStack());
            }
        }
        super.onBreak(world, pos, state, player);
    }
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, Blocks.AIR.getDefaultState(), blockEntity, tool);
    }
    protected void onBreakInCreative(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        DoubleBlockHalf half = state.get(HALF);
        BlockState blockState2 = tryGetOtherHalf(state, world, pos);
        if(blockState2 != null) {
            BlockPos pos2 = half == DoubleBlockHalf.UPPER ? pos.down() : pos.up();
            BlockState replacement = blockState2.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
            world.setBlockState(pos2, replacement, Block.NOTIFY_ALL | Block.SKIP_DROPS);
            world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, pos2, Block.getRawIdFromState(state));
        }
    }
    public @Nullable BlockState tryGetOtherHalf(BlockState state, World world, BlockPos pos) {
        DoubleBlockHalf half = state.get(HALF);
        BlockState b = half == DoubleBlockHalf.LOWER ? world.getBlockState(pos.up()) : world.getBlockState(pos.down());
        if(b.isOf(this)) { return b; }
        return null;
    }
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        //Don't break on ravager collision. Just because I haven't tested that and I don't really want to worry about it
    }
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        if(this.getAge(state) == this.getAgeToGrowUpperHalf()-1 && !(this.getDefaultState().with(HALF, DoubleBlockHalf.UPPER).canPlaceAt(world, pos.up()) && world.getBlockState(pos.up()).isAir())) {
            return false;
        }

        BlockState b = this.tryGetOtherHalf(state, (World) world, pos);
        if(b != null) {
            return !( this.isMature(state) || this.isMature(b) );
        }
        return !( this.isMature(state) );
    }
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if(state.get(HALF) == DoubleBlockHalf.UPPER) {
            BlockState b = this.tryGetOtherHalf(state, world, pos);
            if (b != null) { this.applyGrowth(world, pos.down(), b); } // always try to apply growth through the lower block, never the upper one
        }
        else {
            this.applyGrowth(world, pos, state);
        }
    }
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        float e = this.getChanceToFertilize(world, pos, state);
        if(e < 1 && e < world.random.nextFloat()) { return; }

        int i = this.getGrowthAmount(world);
        int currAge = this.getAge(state);
        if(currAge + i >= this.getMaxAge()) {
            i = this.getMaxAge() - currAge;
        }
        boolean success = this.trySetAge(state, world, pos, currAge + i);
        if( (!success) && currAge < getAgeToGrowUpperHalf()) {this.trySetAge(state, world, pos, this.getAgeToGrowUpperHalf()-1); }
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(this.getDroppedHarvestItem() == null) { return ActionResult.PASS; }
//        if (player.getStackInHand(hand).isOf(Items.BONE_MEAL)) { return ActionResult.PASS; }

        int j = this.getHarvestAmount(state, world.getRandom());
        if(j > 0) {
            boolean growSucceeded = this.trySetAge(state, world, pos, this.getAgeToHarvestTo());
            if(growSucceeded) {
                dropFruit(j, world, pos);
                world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state.with(AGE, this.getAgeToHarvestTo())));
                return ActionResult.success(world.isClient);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
    private void dropFruit(int amount, World world, BlockPos pos) {
        Block.dropStack(world, pos, new ItemStack(this.getDroppedHarvestItem(), amount));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }
}
