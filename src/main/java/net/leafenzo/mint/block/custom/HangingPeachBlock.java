package net.leafenzo.mint.block.custom;

import net.fabricmc.loader.api.FabricLoader;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class HangingPeachBlock extends HangingFruitBlock {
    private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.createCuboidShape(7, 14, 7, 9, 16, 9),
            Block.createCuboidShape(6, 13, 6, 10, 16, 10),
            Block.createCuboidShape(7, 13, 7, 9, 16, 9),
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(6.5, 11, 6.5, 9.5, 14.5, 9.5), Block.createCuboidShape(7, 14.5, 7, 9, 16, 9), BooleanBiFunction.OR),
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(5.5, 9, 5.5, 10.5, 14, 10.5), Block.createCuboidShape(7, 14, 7, 9, 16, 9), BooleanBiFunction.OR)};
    private static final VoxelShape[] COLL_SHAPES = new VoxelShape[]{VoxelShapes.empty(),
            VoxelShapes.empty(),
            Block.createCuboidShape(7, 13, 7, 9, 16, 9),
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(6.5, 11, 6.5, 9.5, 14.5, 9.5), Block.createCuboidShape(7, 14.5, 7, 9, 16, 9), BooleanBiFunction.OR),
            VoxelShapes.combineAndSimplify(Block.createCuboidShape(5.5, 9, 5.5, 10.5, 14, 10.5), Block.createCuboidShape(7, 14, 7, 9, 16, 9), BooleanBiFunction.OR)};
    public HangingPeachBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = SHAPES[state.get(AGE)];
        if (!FabricLoader.getInstance().isModLoaded("twigs") && !FabricLoader.getInstance().isModLoaded("etcetera")) {
            Vec3d vec3d = state.getModelOffset(world, pos);
            return voxelShape.offset(vec3d.x, vec3d.y, vec3d.z);
        }
        return voxelShape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = COLL_SHAPES[state.get(AGE)];
        if (!FabricLoader.getInstance().isModLoaded("twigs") && !FabricLoader.getInstance().isModLoaded("etcetera")) {
            Vec3d vec3d = state.getModelOffset(world, pos);
            return voxelShape.offset(vec3d.x, vec3d.y, vec3d.z);
        }
        return voxelShape;
    }

    @Override
    public float getMaxHorizontalModelOffset() {
        if (FabricLoader.getInstance().isModLoaded("twigs") || FabricLoader.getInstance().isModLoaded("etcetera")) {
            return 0;
        }
        return super.getMaxHorizontalModelOffset();
    }


    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.up(), Direction.DOWN) && !world.isWater(pos)
                || world.getBlockState(pos.up()).isOf(ModBlocks.PEACH_LEAVES) && !world.isWater(pos)
                || world.getBlockState(pos.up()).isOf(ModBlocks.FLOWERING_PEACH_LEAVES) && !world.isWater(pos);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int i = state.get(AGE);
        if (i != 4 && player.getStackInHand(hand).isOf(Items.BONE_MEAL)) {
            return ActionResult.PASS;
        }
        if (i == 4) {
            HangingFruitBlock.dropStack(world, pos, new ItemStack(ModItems.PEACH, 1));
            world.playSound(null, pos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0f, 0.8f + world.random.nextFloat() * 0.4f);
            BlockState blockState = state.with(AGE, 0);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.PEACH);
    }
}
