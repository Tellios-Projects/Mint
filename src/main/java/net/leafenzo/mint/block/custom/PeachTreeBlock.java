package net.leafenzo.mint.block.custom;

import net.leafenzo.mint.item.ElsDyeModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

@Deprecated
public class PeachTreeBlock extends TwoTallCropBlock {
    public static final int MAX_AGE = 7;
    public static final IntProperty AGE = IntProperty.of("age", 0, MAX_AGE);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    public PeachTreeBlock(Settings settings) {
        super(settings);
    }
    private static final VoxelShape[] AGE_TO_SHAPE_UPPER = new VoxelShape[]{
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 7.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 7.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 7.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 7.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 13.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 13.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 13.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 13.0, 14.0)
    };
    private static final VoxelShape[] AGE_TO_SHAPE_LOWER = new VoxelShape[]{
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 5.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 11.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 15.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 16.0, 14.0),
            Block.createCuboidShape(3.0, 0.0, 3.0, 14.0, 16.0, 14.0)
    };
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(HALF) == DoubleBlockHalf.UPPER ? AGE_TO_SHAPE_UPPER[this.getAge(state)] : AGE_TO_SHAPE_LOWER[this.getAge(state)];
    }
    @Override
    public int getAgeToGrowUpperHalf() {
        return 3;
    }
    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ElsDyeModItems.PEACH_PIT;
    }
    @Override
    public float getChanceToGrow(BlockState state) {
        return this.getAge(state) < 4 ? 0.1f : 0.25f;
    }
    @Override
    public int getAgeToHarvestTo() { return 4; }
    @Override
    public ItemConvertible getDroppedHarvestItem() { return ElsDyeModItems.PEACH; }
    @Override
    public int getHarvestAmount(BlockState state, Random random) {
        return this.getAge(state) < this.getMaxAge() ? 0 : 1 + random.nextInt(2);
    }
    @Override
    public float getChanceToFertilize(World world, BlockPos pos, BlockState state) {
        return this.getAge(state) >= 4 ? 0.5f : 1.0f;
    }
}
