package net.leafenzo.mint.block.custom;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class StrawberryPlantBlock extends FlowerbedCropBlock {
    public static final int MAX_AGE = 4;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);

    public StrawberryPlantBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(FLOWER_AMOUNT, 1).with(AGE, 0));
    }

    public boolean canAgeNaturally(WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() instanceof FarmlandBlock || world.getBlockState(pos.down()).isIn(BlockTags.DIRT); // This check is here instead of canPlaceAt just so you can still use it as decoration on cobblestone and stuff :3
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isOpaque() && !world.getBlockState(pos.down()).isOf(Blocks.DIRT); // Just so that it breaks if farmland is trampled underneath it
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.25 && world.getBaseLightLevel(pos, 0) >= 9 && canAgeNaturally(world, pos)) {
            boolean isOnFarmland = world.getBlockState(pos.down()).getBlock() instanceof FarmlandBlock;
            if (!isOnFarmland) { // I would put a moisture check here too, but I just don't really see a point since farmland decays without it
                world.setBlockState(pos, state.with(AGE, random.nextBetween(0,3)), Block.NOTIFY_LISTENERS);
            }
            else if (!isFullyGrown(state)) {
                world.setBlockState(pos, state
                                .with(AGE, Math.min(MAX_AGE, state.get(AGE) + getGrowthAmount(world)))
                                .with(FLOWER_AMOUNT, Math.min(4, state.get(FLOWER_AMOUNT) + getFlowerGrowthAmount(world))),
                        Block.NOTIFY_LISTENERS
                );
            }
        }
    }

    @Override
    public ItemConvertible getDroppedHarvestItem() {
        return this.asItem();
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return this.asItem();
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !isFullyGrown(state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

}
