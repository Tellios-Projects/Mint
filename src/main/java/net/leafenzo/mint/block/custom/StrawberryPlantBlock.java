package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class StrawberryPlantBlock extends FlowerbedBlock implements Fertilizable {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public StrawberryPlantBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(FLOWER_AMOUNT, 1).with(AGE, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        if (isFullyGrown(state) && (player.getStackInHand(hand).isOf(Items.BONE_MEAL) || player.getStackInHand(hand).isOf(ModItems.STRAWBERRY))) {
            return ActionResult.PASS;
        }
        if (isFullyGrown(state)) {
            dropStack(world, pos, new ItemStack(ModItems.STRAWBERRY, state.get(FLOWER_AMOUNT)));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState blockState = state.with(AGE, 0);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.25 && world.getBaseLightLevel(pos, 0) >= 9 && canAgeNaturally(state, world, pos)) {
            boolean isOnFarmland = world.getBlockState(pos.down()).getBlock() instanceof FarmlandBlock;
            if (!isFullyGrown(state)) {
                world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
            }
            else if (!isOnFarmland) { // I would put a moisture check here too, but I just don't really see a point since farmland decays without it
                world.setBlockState(pos, state.with(AGE, random.nextBetween(0,3)), Block.NOTIFY_LISTENERS);
            }
        }

        super.randomTick(state, world, pos, random);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOpaque() && !world.getBlockState(pos.down()).isOf(Blocks.DIRT); // Just so that it breaks if farmland is trampled underneath it
    }

    public boolean canAgeNaturally(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() instanceof FarmlandBlock || world.getBlockState(pos.down()).isIn(BlockTags.DIRT); // This check is here instead of canPlaceAt just so you can still use it as decoration on cobblestone and stuff :3
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.cycle(AGE), Block.NOTIFY_LISTENERS);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !isFullyGrown(state);
    }

    public static boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == MAX_AGE;
    }

}
