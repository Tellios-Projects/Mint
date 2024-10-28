package net.leafenzo.mint.block.custom;


import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class FlowerbedCropBlock extends CropBlock {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty FLOWER_AMOUNT = Properties.FLOWER_AMOUNT;

    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    public FlowerbedCropBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(FLOWER_AMOUNT, 1)
                .with(this.getAgeProperty(), 0)
        );
    }

    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction() && context.getStack().isOf(getSeedsItem().asItem()) && state.get(FLOWER_AMOUNT) < 4 || super.canReplace(state, context);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        return blockState.isOf(this) ? blockState.with(FLOWER_AMOUNT, Math.min(4, blockState.get(FLOWER_AMOUNT) + 1)).with(AGE, Math.max(0, blockState.get(AGE) - 1)) : this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override public boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state) || state.get(FLOWER_AMOUNT) < 4;
    }

    @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            if (!isFullyGrown(state)) {
                world.setBlockState(pos, state
                        .with(AGE, Math.min(MAX_AGE, state.get(AGE) + getGrowthAmount(world)))
                        .with(FLOWER_AMOUNT, Math.min(4, state.get(FLOWER_AMOUNT) + getFlowerGrowthAmount(world))),
                        Block.NOTIFY_LISTENERS
                );
            }
        }
    }

    @Override public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (isReadyForHarvest(state) && (player.getStackInHand(hand).isOf(Items.BONE_MEAL) || player.getStackInHand(hand).isOf(getSeedsItem().asItem()))) {
            return ActionResult.PASS;
        }
        else if (isReadyForHarvest(state)) {
            dropStack(world, pos, new ItemStack(getDroppedHarvestItem().asItem(), state.get(FLOWER_AMOUNT)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState blockState = state.with(AGE, 0);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }

    @Override public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int age = this.getAge(state) + this.getGrowthAmount(world);
        int maxAge = this.getMaxAge();
        if (age > maxAge) {
            age = maxAge;
        }

        int flowers = state.get(FLOWER_AMOUNT) + this.getFlowerGrowthAmount(world);
        int maxFlowers = 4;
        if (flowers > maxFlowers) {
            flowers = maxFlowers;
        }

        world.setBlockState(pos, state.with(AGE, age).with(FLOWER_AMOUNT, flowers), Block.NOTIFY_LISTENERS);
    }

    public int getFlowerGrowthAmount(World world) {
        return ElsDyeModUtil.intAtRandom(world.getRandom(), new int[] {
                0, 0, 0, 1
        });
    }

    @Override protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 1, 2);
    }

    public int getMaxAge() {
        return MAX_AGE;
    }

    public ItemConvertible getDroppedHarvestItem() {
        return this.asItem();
    }

    @Override protected ItemConvertible getSeedsItem() {
        return this.asItem();
    }

    @Override protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override public int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !isFullyGrown(state);
    }

    public boolean isReadyForHarvest(BlockState state) {
        return state.get(AGE) == MAX_AGE;
    }

    public boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == MAX_AGE && state.get(FLOWER_AMOUNT) == 4;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING).add(FLOWER_AMOUNT).add(AGE);
    }
}
