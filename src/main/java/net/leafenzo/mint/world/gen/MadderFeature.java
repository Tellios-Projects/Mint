package net.leafenzo.mint.world.gen;

import com.mojang.serialization.Codec;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.block.custom.CochinealBeetlesBlock;
import net.leafenzo.mint.block.custom.MadderBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;
import java.util.function.BiConsumer;

public class MadderFeature extends Feature<MadderFeatureConfig> {
    public MadderFeature(Codec<MadderFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<MadderFeatureConfig> context) {
        int rootRadius = context.getConfig().root_depth;
        for (int i = 1; i > -2; i--) {
            for (int j = 1; j > -2; j--) {
                if (context.getRandom().nextFloat() < 0.2) {
                    tryPlaceMadder(context.getWorld(), context.getOrigin().add(i, 0, j), ModBlocks.MADDER.getDefaultState());
                }
            }
        }
        for (int depth = 0; depth < rootRadius; depth++) {
            tryPlaceMadderRoot(context.getWorld(), context.getOrigin().down(depth));
            for (int i = depth; i > -depth-1; i--) {
                for (int j = depth; j > -depth-1; j--) {
                    if (context.getRandom().nextBetween(0, depth) < 2) {
                        tryPlaceMadderRoot(context.getWorld(), context.getOrigin().down(depth).add(i, 0, j));
                    }
                }
            }
        }
        return true;
    }

    private void getAndSetState(StructureWorldAccess world, BlockPos pos, BlockState state) {
        if (world.testBlockState(pos, (blockstate) -> blockstate.isAir())) {
            world.setBlockState(pos, state, 2);
        }
    }
    private void tryPlaceMadder(StructureWorldAccess world, BlockPos pos, BlockState state) {
        for (int i = 0; i < 3; i++) {
            BlockState floor = world.getBlockState(pos.down(i));
            if (floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND)) {
                this.getAndSetState(world, pos.down(i-1), state);
            }
        }
    }
    private void tryPlaceMadderRoot(StructureWorldAccess world, BlockPos pos) {
        if (world.testBlockState(pos, blockState -> blockState.isOf(Blocks.GRASS_BLOCK)) || world.testBlockState(pos, blockState -> blockState.isOf(ModBlocks.MADDER_ROOTED_GRASS_BLOCK))) {
            world.setBlockState(pos, ModBlocks.MADDER_ROOTED_GRASS_BLOCK.getDefaultState(), 2);
        } else if (world.testBlockState(pos, blockState -> blockState.isIn(BlockTags.DIRT))) {
            world.setBlockState(pos, ModBlocks.MADDER_ROOTED_DIRT.getDefaultState(), 2);
        }
    }
}
